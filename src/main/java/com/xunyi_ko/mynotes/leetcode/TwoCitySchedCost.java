package com.xunyi_ko.mynotes.leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 公司计划面试 2N 人。
 * 第 i 人飞往 A 市的费用为 costs[i][0]，飞往 B 市的费用为 costs[i][1]。
 * 返回将每个人都飞到某座城市的最低费用，要求每个城市都有 N 人抵达。
 * 
 * @author zy
 *
 */
public class TwoCitySchedCost {
    /*
     * [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
     */
    @Test
    public void testTwoCitySchedCost() {
        int[][] costs = {
            {259,770},{448,54},{926,667},{184,139},{840,118},{577,469}
        };
        
        int res = twoCitySchedCost(costs);
        System.out.println(res);
    }
    
    public int twoCitySchedCost(int[][] costs) {
        int peopleNum = costs.length;
        
        List<T> list = new ArrayList<>();
        // 计算去A城市的费用 - 去B城市的费用
        // 存入list
        for(int i = 0; i < peopleNum; i++) {
            list.add(new T(costs[i][0] - costs[i][1], i));
        }
        // 按照费用差排序
        list.sort((t1, t2) -> t1.cost - t2.cost);
        
        int res = 0;
        int pos = 0;
        
        // 费用差越低说明更适合去A城市，否则去B城市
        for(T t : list) {
            if(pos < peopleNum / 2) {
                res += costs[t.pos][0];
            }else {
                res += costs[t.pos][1];
            }
            pos++;
        }
        return res;
    }
    
    class T{
        int cost;
        int pos;
        
        T(int cost, int pos){
            this.cost = cost;
            this.pos = pos;
        }
    }
}
