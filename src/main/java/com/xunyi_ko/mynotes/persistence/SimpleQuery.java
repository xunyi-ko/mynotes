package com.xunyi_ko.mynotes.persistence;

import java.util.List;

import javax.persistence.EntityManager;

@SuppressWarnings("rawtypes")
public interface SimpleQuery<T> {
    SimpleQuery select(String... names);
    SimpleQuery from(String table);
    SimpleQuery where(QueryFilter filter);
    SimpleQuery and(QueryFilter filter);
    SimpleQuery or(QueryFilter filter);
    SimpleQuery orderBy(String name, Direction direction);
    SimpleQuery groupBy(String... cols);
    
    List<T> getResultList(EntityManager em);
    T getSingleResult(EntityManager em);
    
    enum Direction{
        ASC,DESC;
    }
}
