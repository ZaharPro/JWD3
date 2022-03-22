package by.epam.task3.interpreter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BitInterpreter {
    private static final BitInterpreter INSTANCE = new BitInterpreter();
    private static final String NUMBER_REGEX = "\\d+";

    public static BitInterpreter getInstance() {
        return INSTANCE;
    }

    private BitInterpreter() {
    }


    private List<String> getLexemes(String expression) {
        ArrayDeque<String> basicText = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        StringBuilder numberBuilder = new StringBuilder();

        expression = expression.replaceAll("<<", "<")
                .replaceAll(">>", ">")
                .replaceAll(">>>", "R");

        for (int i = 0; i < expression.length(); i++) {
            String symbol = String.valueOf(expression.charAt(i));

            if (symbol.matches(NUMBER_REGEX)) {
                numberBuilder.append(symbol);

                if (i < expression.length() - 1 && !String.valueOf(expression.charAt(i + 1)).matches(NUMBER_REGEX)) {
                    result.add(numberBuilder.toString());
                    numberBuilder = new StringBuilder();
                } else if (i == expression.length() - 1) {
                    result.add(numberBuilder.toString());
                }
            } else if (symbol.equals("(")) {
                basicText.push(symbol);
            } else if (symbol.equals(")")) {
                while (!basicText.peek().equals("(")) {
                    result.add(basicText.pop());
                }
                basicText.pop();
            } else {
                BitOperator priority = BitOperator.findOperator(symbol);
                BitOperator priorityFirst = BitOperator.findOperator(basicText.peek());

                while (!basicText.isEmpty() && !basicText.peek().equals("(")
                        && priority.getPriority() <= priorityFirst.getPriority()) {
                    String arg = basicText.pop();
                    result.add(arg);
                    priorityFirst = BitOperator.findOperator(basicText.peek());
                }
                basicText.push(symbol);
            }
        }

        while (!basicText.isEmpty()) {
            result.add(basicText.pop());
        }

        return result;
    }

    private List<BitExpression> parseLexemes(List<String> rpnExpression) {
        List<BitExpression> expression = new ArrayList<>();

        for (String token : rpnExpression) {
            switch (BitOperator.findOperator(token)) {
                case NOT: {
                    expression.add(c -> c.push(~(c.pop())));
                    break;
                }

                case XOR: {
                    expression.add(c -> c.push(c.pop() ^ c.pop()));
                    break;
                }

                case AND: {
                    expression.add(c -> c.push(c.pop() & c.pop()));
                    break;
                }

                case OR: {
                    expression.add(c -> c.push(c.pop() | c.pop()));
                    break;
                }

                case SHIFT_RIGHT: {
                    expression.add(c -> {
                        int n = c.pop();
                        int x = c.pop();
                        c.push(x >> n);
                    });

                    break;
                }

                case SHIFT_LEFT: {
                    expression.add(c -> {
                        int n = c.pop();
                        int x = c.pop();
                        c.push(x << n);
                    });
                    break;
                }

                case ASSIGN_SHIFT_RIGHT: {
                    expression.add(c -> {
                        int n = c.pop();
                        int x = c.pop();
                        c.push(x >>> n);
                    });
                    break;
                }

                default: {
                    expression.add(c -> c.push(Integer.parseInt(token)));
                }
            }
        }

        return expression;
    }

    public String execute(String expression) {
        List<String> polishNotation = getLexemes(expression);
        List<BitExpression> expressions = parseLexemes(polishNotation);

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (BitExpression terminal : expressions) {
            terminal.evaluate(stack);
        }

        return stack.pop().toString();
    }
}
