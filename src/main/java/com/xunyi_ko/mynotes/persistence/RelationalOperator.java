package com.xunyi_ko.mynotes.persistence;

public enum RelationalOperator {
    EQUALS("="),
    MORE_THAN(">"),
    LESS_THAN("<"),
    NO_MORE_THAN("<="),
    NO_LESS_THAN(">="),
    NOT("<>"),
    IN(" in "),
    NOT_IN(" not in "),
    LIKE(" like "),
    NOT_LIKE(" not like ");
    
    private String symbol;
    RelationalOperator(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }
}
