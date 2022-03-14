package by.epam.task3.util;

public enum TokenPriority {
    FIRST_BRACKET("(", 0),
    LAST_BRACKET(")", 0),
    OR("|", 1),
    XOR("^", 2),
    AND("&", 3),
    SHIFT_LEFT("<", 4),
    SHIFT_RIGHT(">", 4),
    ASSIGN_SHIFT_RIGHT("R", 4),
    NOT("~", 5);

    private final String symbol;
    private final int priority;

    TokenPriority(String symbol, int priority){
        this.priority = priority;
        this.symbol = symbol;
    }

    public int getPriority(){
        return priority;
    }

    public static TokenPriority findBitOperation(String name){
        TokenPriority found = FIRST_BRACKET;
        for (TokenPriority token: values()){
            if (token.symbol.equals(name)){
                found = token;
            }
        }

        return found;
    }
}
