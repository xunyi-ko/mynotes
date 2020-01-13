package com.xunyi_ko.mynotes.persistence;

import javax.persistence.Query;

public interface QueryFilter {
    QueryFilter and(QueryFilterEntry e);
    QueryFilter or(String name, Object value);
    QueryFilter in(String name, Iterable<?> value);
    QueryFilter starts(String name, String value);
    QueryFilter ends(String name, String value);
    QueryFilter like(String name, String value);
    Query filter(Query query);
}
