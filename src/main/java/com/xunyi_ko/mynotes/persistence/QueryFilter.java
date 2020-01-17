package com.xunyi_ko.mynotes.persistence;

import javax.persistence.Query;

public interface QueryFilter {
    QueryFilter and(String name, RelationalOperator operation, Object value);
    QueryFilter or(String name, RelationalOperator operation, Object value);
    void where(StringBuilder sql);
    Query filter(Query query);
}
