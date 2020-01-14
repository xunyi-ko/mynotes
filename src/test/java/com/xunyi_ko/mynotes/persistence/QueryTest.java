package com.xunyi_ko.mynotes.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.xunyi_ko.mynotes.data.SysUserData;
import com.xunyi_ko.mynotes.persistence.QueryFilterImpl;
import com.xunyi_ko.mynotes.persistence.SQLOperation;
import com.xunyi_ko.mynotes.persistence.SimpleQuery;
import com.xunyi_ko.mynotes.persistence.SimpleQueryImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class QueryTest {
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void select() {
        SimpleQuery<SysUserData> query = new SimpleQueryImpl<>(SysUserData.class);
        query.select("user_id userId", "username username")
        .from("sys_user").where(new QueryFilterImpl().and("user_id", SQLOperation.EQUALS, 1));
        
        List<SysUserData> list = query.getResultList(em);
        
        for(SysUserData data : list) {
            System.out.println(data.getUserId());
            System.out.println(data.getUsername());
        }
    }
}
