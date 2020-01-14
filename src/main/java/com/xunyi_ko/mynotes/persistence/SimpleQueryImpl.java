package com.xunyi_ko.mynotes.persistence;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
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
    String[] tables;
    List<QueryFilter> filters;
    List<FilterOperation> operations;
    List<String> groups;
    List<Order> orders;
    
    List<String[]> selectStrings;
    
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
    public SimpleQuery<T> from(String... tables) {
        this.tables = tables;
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
        return orderBy(Order.by(col, direction));
    }
    
    public SimpleQuery<T> orderBy(Order order){
        orders.add(order);
        return this;
    }


    @Override
    public SimpleQuery<T> groupBy(String... cols) {
        groups = Arrays.asList(cols);
        return this;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getResultList(EntityManager em) {
        validate();
        
        List list = getResult(em);
        if(selects != null && selects.length != 1 && clazz != null) {
            list = dealResultList(list);
        }
        return list;
    }
    
    // TODO 校验是否可以组装sql
    private void validate() {
        
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
    private static final String WHERE = "where";
    private static final String GROUP_BY = "group by";
    private static final String ORDER_BY = "order by";
    private static final String BLANK = " ";
    private static final String COMMA = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";
    
    
    // 组装sql
    private String getSQL() {
        StringBuilder sql = new StringBuilder();
        
        select(sql);
        from(sql);
        where(sql);
        groupBy(sql);
        orderBy(sql);
        
        return sql.toString();
    }
    
    private void select(StringBuilder sql) {
        if(selects == null ||selects.length == 0) {
            return;
        }
        
        this.selectStrings = getSelectStrings();
        
        sql.append(SELECT).append(BLANK);
        
        forEach(sql, selectStrings.stream().map(e -> {
            return e[0];
        }).collect(Collectors.toList()), COMMA);
        sql.append(BLANK);
    }
    
    private void from(StringBuilder sql) {
        sql.append(FROM).append(BLANK);
        
        forEach(sql, Arrays.asList(tables), COMMA);
        sql.append(BLANK);
    }
    
    private void where(StringBuilder sql) {
        if(filters == null || filters.isEmpty()) {
            return;
        }
        
        sql.append(WHERE).append(BLANK);
        for(int i = 0; i < filters.size(); i++) {
            QueryFilter filter = filters.get(i);
            if(i != 0) {
                sql.append(BLANK).append(operations.get(i).name());
            }
            
            sql.append(LEFT);
            filter.where(sql);
            sql.append(RIGHT);
        }
        sql.append(BLANK);
    }
    
    private void groupBy(StringBuilder sql) {
        if(groups == null || groups.isEmpty()) {
            return;
        }
        sql.append(GROUP_BY).append(BLANK);
        forEach(sql, groups, COMMA);
        sql.append(BLANK);
    }
    private void orderBy(StringBuilder sql) {
        if(orders == null || orders.isEmpty()) {
            return;
        }
        sql.append(ORDER_BY).append(BLANK);
        forEach(sql, orders.stream().map(o -> {
            return o.col + o.direction.name();
        }).collect(Collectors.toList()), COMMA);
        sql.append(BLANK);
    }
    
    private void forEach(StringBuilder sql, Iterable<String> iterable, String split) {
        Iterator<String> iterator = iterable.iterator();
        for(int i = 0; iterator.hasNext(); i++) {
            String next = iterator.next();
            if(i != 0) {
                sql.append(split);
            }
            sql.append(next);
        }
    }
    
    // 获取值
    private List getResult(EntityManager em) {
        Query query;
        if(typed()) {
            query = em.createNativeQuery(getSQL(), clazz);
        }else {
            query = em.createNativeQuery(getSQL());
        }
        
        if(filters != null) {
            for(QueryFilter filter : filters) {
                filter.filter(query);
            }
        }
        
        return query.getResultList();
    }
    
    // 分离select字段
    private List<String[]> getSelectStrings(){
        List<String[]> names = new ArrayList<>();
        for(String name : selects) {
            names.add(name.split(" +"));
        }
        return names;
    }
    
    // 将获取到的list的值装载到对象中
    private List<T> dealResultList(List<Object[]> list){
        List<T> res = new ArrayList<>(list.size());
        List<String> names = selectStrings.stream().map(e -> {
            return e[e.length - 1];
        }).collect(Collectors.toList());
        
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            for(Object[] array : list) {
                T t = constructor.newInstance();
                for(int i = 0; i < array.length; i++) {
                    Object o = array[i];
                    String name = names.get(i);
                    
                    try {
                        Field field = clazz.getDeclaredField(name);
                        field.setAccessible(true);
                        field.set(t, o);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
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
