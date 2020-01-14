package com.xunyi_ko.mynotes.persistence;

public enum SQLOperation {
    EQUALS("="),
    NOT("<>"),
    IN(" in "),
    NOT_IN(" not in "),
    LIKE(" like "),
    NOT_LIKE(" not like ");
    
    private String symbol;
    SQLOperation(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }
}
