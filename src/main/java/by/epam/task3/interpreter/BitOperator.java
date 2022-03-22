package by.epam.task3.interpreter;

public enum BitOperator {
    FIRST_BRACKET("(", 0),
    LAST_BRACKET(")", 0),
    OR("|", 1),
    XOR("^", 2),
    AND("&", 3),
    SHIFT_LEFT("<", 4),
    SHIFT_RIGHT(">", 4),
    ASSIGN_SHIFT_RIGHT("R", 4),
    NOT("~", 5);

    private final String operator;
    private final int priority;

    BitOperator(String operator, int priority) {
        this.priority = priority;
        this.operator = operator;
    }

    public static BitOperator findOperator(String name) {
        for (BitOperator token : values()) {
            if (token.operator.equals(name)) {
                return token;
            }
        }
        return null;
    }

    public int getPriority() {
        return priority;
    }
}
