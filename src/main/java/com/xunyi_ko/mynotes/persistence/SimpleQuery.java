package com.xunyi_ko.mynotes.persistence;

import java.util.List;

import javax.persistence.EntityManager;

@SuppressWarnings("rawtypes")
public interface SimpleQuery<T> {
    SimpleQuery select(String... names);
    SimpleQuery from(String... tables);
    SimpleQuery where(QueryFilter filter);
    SimpleQuery and(QueryFilter filter);
    SimpleQuery or(QueryFilter filter);
    SimpleQuery orderBy(String name, Direction direction);
    SimpleQuery groupBy(String... cols);
    
    default List<T> getResultList(EntityManager em){
        return getResultList(em, false);
    }
    List<T> getResultList(EntityManager em, boolean isNative);
    
    default T getSingleResult(EntityManager em) {
        return getSingleResult(em, false);
    }
    T getSingleResult(EntityManager em, boolean isNative);
    
    enum Direction{
        ASC,DESC;
    }
}
