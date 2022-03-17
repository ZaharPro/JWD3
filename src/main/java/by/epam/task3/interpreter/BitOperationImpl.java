package by.epam.task3.interpreter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BitOperationImpl implements BitOperation {
    private static final BitOperationImpl INSTANCE = new BitOperationImpl();

    public static BitOperationImpl getInstance() {
        return INSTANCE;
    }

    private static final String NUMBER_REGEX = "\\d+";

    private BitOperationImpl() {
    }

    public int calculate(String expression) {
        List<String> tokens = parse(expression);
        return calculate(tokens);
    }

    private List<String> parse(String expression) {
        ArrayDeque<String> basicText = new ArrayDeque<>();
        List<String> tokens = new ArrayList<>();
        StringBuilder numberString = new StringBuilder();

        expression = expression.replaceAll("<<", "<")
                .replaceAll(">>", ">")
                .replaceAll(">>>", "R");

        for (int i = 0; i < expression.length(); i++) {
            String symbol = String.valueOf(expression.charAt(i));

            if (symbol.matches(NUMBER_REGEX)) {
                numberString.append(symbol);

                if (i < expression.length() - 1 && !String.valueOf(expression.charAt(i + 1)).matches(NUMBER_REGEX)) {
                    tokens.add(numberString.toString());
                    numberString.setLength(0);
                } else if (i == expression.length() - 1) {
                    tokens.add(numberString.toString());
                }
            } else if ("(".equals(symbol)) {
                basicText.push(symbol);
            } else if (")".equals(symbol)) {
                while (!"(".equals(basicText.peek())) {
                    tokens.add(basicText.pop());
                }
                basicText.pop();
            } else {
                TokenPriority priority = TokenPriority.findBitOperation(symbol);
                TokenPriority priorityFirst = TokenPriority.findBitOperation(basicText.peek());

                while (!basicText.isEmpty() && !basicText.peek().equals("(")
                        && priority.getPriority() <= priorityFirst.getPriority()) {
                    String arg = basicText.pop();
                    tokens.add(arg);
                    priorityFirst = TokenPriority.findBitOperation(basicText.peek());
                }
                basicText.push(symbol);
            }
        }

        while (!basicText.isEmpty()) {
            tokens.add(basicText.pop());
        }
        return tokens;
    }

    private int calculate(List<String> rpnExpression) {
        Stack<Integer> stack = new Stack<>();
        for (String token : rpnExpression) {
            if (token.matches(NUMBER_REGEX)) {
                stack.push(Integer.parseInt(token));
            } else {
                Integer b = stack.pop();
                if (TokenPriority.findBitOperation(token) == TokenPriority.NOT) {
                    stack.push(~b);
                } else {
                    Integer a = stack.pop();
                    switch (TokenPriority.findBitOperation(token)) {
                        case OR: {
                            stack.push(a | b);
                            break;
                        }
                        case AND: {
                            stack.push(a & b);
                            break;
                        }
                        case XOR: {
                            stack.push(a ^ b);
                            break;
                        }
                        case SHIFT_LEFT: {
                            stack.push(a << b);
                            break;
                        }
                        case SHIFT_RIGHT: {
                            stack.push(a >> b);
                            break;
                        }
                        case ASSIGN_SHIFT_RIGHT: {
                            stack.push(a >>> b);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            }
        }
        return stack.pop();
    }
}
