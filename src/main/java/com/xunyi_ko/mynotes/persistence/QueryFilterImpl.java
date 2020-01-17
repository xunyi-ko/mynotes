package com.xunyi_ko.mynotes.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class QueryFilterImpl implements QueryFilter{
    private final String prefix;
    
    List<QueryFilterEntry> entrys;
    List<RelationalOperator> sqlOperations;
    List<LogicOperator> operations;
    private enum LogicOperator{
        AND,OR;
    }
    
    public QueryFilterImpl() {
        this("p");
    }
    public QueryFilterImpl(String prefix) {
        entrys = new ArrayList<>();
        operations = new ArrayList<>();
        sqlOperations = new ArrayList<>();
        this.prefix = prefix;
    }
    
    @Override
    public QueryFilter and(String name, RelationalOperator operation, Object value) {
        addOperation(name, value, operation, LogicOperator.AND);
        return this;
    }
    @Override
    public QueryFilter or(String name, RelationalOperator operation, Object value) {
        addOperation(name, value, operation, LogicOperator.OR);
        return this;
    }
    
    
    private void addOperation(String name, Object value, RelationalOperator sqlOperation, LogicOperator operation) {
        entrys.add(new QueryFilterEntry(name, value));
        sqlOperations.add(sqlOperation);
        operations.add(operation);
    }

    private static final String BLANK = " ";
    private static final String COLON = ":";
    @Override
    public void where(StringBuilder sql) {
        for(int i = 0; i < entrys.size(); i++) {
            QueryFilterEntry entry = entrys.get(i);
            RelationalOperator rela = sqlOperations.get(i);
            LogicOperator logic = operations.get(i);
            
            if(i != 0) {
                sql.append(logic.name());
            }
            sql.append(entry.getName()).append(rela.getSymbol()).append(COLON).append(prefix).append(i).append(BLANK);
        }
    }
    @Override
    public Query filter(Query query) {
        for(int i = 0; i < entrys.size(); i++) {
            QueryFilterEntry entry = entrys.get(i);
            query.setParameter(prefix + i, entry.getValue());
        }
        return query;
    }
}
