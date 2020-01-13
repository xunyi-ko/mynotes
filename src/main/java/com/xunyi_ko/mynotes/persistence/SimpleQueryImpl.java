package com.xunyi_ko.mynotes.persistence;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.xunyi_ko.mynotes.utils.ObjectUtil;

public class SimpleQueryImpl<T> implements SimpleQuery<T>{
    Class<T> clazz;
    /**
     * 查询的字段列表，可以为空
     */
    String[] selects;
    String table;
    List<QueryFilter> filters;
    List<FilterOperation> operations;
    List<Order> orders;
    Set<String> groups;
    
    
    public SimpleQueryImpl() {
        
    }
    public SimpleQueryImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    private enum FilterOperation{
        AND,OR;
    }
    
    private static class Order{
        String col;
        Direction direction;
        
        private Order(String col, Direction direction) {
            this.col = col;
            this.direction = direction;
        }
        static Order by(String col, Direction direction) {
            return new Order(col, direction);
        }
    }


    @Override
    public SimpleQuery<T> select(String... selects) {
        this.selects = selects;
        return this;
    }


    @Override
    public SimpleQuery<T> from(String table) {
        this.table = table;
        return this;
    }


    @Override
    public SimpleQuery<T> where(QueryFilter filter) {
        this.filters = new ArrayList<>();
        this.operations = new ArrayList<>();
        filters.add(filter);
        operations.add(FilterOperation.AND);
        return this;
    }


    @Override
    public SimpleQuery<T> and(QueryFilter filter) {
        this.filters.add(filter);
        operations.add(FilterOperation.AND);
        return this;
    }


    @Override
    public SimpleQuery<T> or(QueryFilter filter) {
        this.filters.add(filter);
        operations.add(FilterOperation.OR);
        return this;
    }


    @Override
    public SimpleQuery<T> orderBy(String col, Direction direction) {
        orders.add(Order.by(col, direction));
        return this;
    }


    @Override
    public SimpleQuery<T> groupBy(String... cols) {
        groups = Arrays.asList(cols).stream().collect(Collectors.toSet());
        return this;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getResultList(EntityManager em) {
        List list = getResult(em);
        if(selects != null && selects.length != 1 && clazz != null) {
            list = dealResultList(list);
        }
        return list;
    }
    
    private boolean typed() {
        return selects == null && clazz != null;
    }
    
    @Override
    public T getSingleResult(EntityManager em) {
        return getResultList(em).get(0);
    }
    
    private static final String SELECT = "select";
    private static final String FROM = "from";
    private static final String BLANK = " ";
    private static final String COMMA = ",";
    // TODO 组装sql
    private String getSQL() {
        StringBuilder sql = new StringBuilder();
        
        if(selects != null) {
            sql.append(SELECT).append(BLANK);
            
            for(int i = 0; i < selects.length; i++) {
                if(i != 0) {
                    sql.append(COMMA);
                }
                sql.append(selects[i]);
            }
        }
        sql.append(BLANK).append(FROM).append(BLANK);
        
        
        
        return sql.toString();
    }
    
    // TODO 获取值
    private List getResult(EntityManager em) {
        Query query;
        if(typed()) {
            query = em.createQuery(getSQL(), clazz);
        }else {
            query = em.createQuery(getSQL());
        }
        
        if(filters != null) {
            for(QueryFilter filter : filters) {
                filter.filter(query);
            }
        }
        
        return query.getResultList();
    }
    
    // TODO 分离select字段
    private List<String> getSelects(){
        List<String> names = new ArrayList<>();
        for(String name : selects) {
            String[] strings = name.split(" ");
            names.add(strings[strings.length - 1]);
        }
        return names;
    }
    
    // TODO 将获取到的list的值装载到对象中
    private List<T> dealResultList(List<Object[]> list){
        List<T> res = new ArrayList<>(list.size());
        List<String> names = getSelects();
        try {
            Constructor<T> constructor = clazz.getConstructor();
            constructor.setAccessible(true);
            for(Object[] array : list) {
                T t = constructor.newInstance();
                for(int i = 0; i < array.length; i++) {
                    Object o = array[i];
                    String name = names.get(i);
                    
                    try {
                        Field field = clazz.getField(name);
                        field.setAccessible(true);
                        field.set(t, o);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
                res.add(t);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }
}
