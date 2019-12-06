package com.xunyi_ko.mynotes.leetcode;

import org.junit.Test;

/**
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 * 每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。
 * 返回到达终点需要的最小移动次数。
 * @author zy
 */
public class ReachNumber {
    @Test
    public void testReachNumber() {
        for(int i = 1; i < 12; i++) {
            System.out.println(reachNumber(i));
        }
    }
    
    public int reachNumber(int target) {
        if(target < 0) {
            target *= -1;
        }
        int i = 1;
        // 如果从1加到i小于目标值，则不可能
        while((i + 1) * i < target * 2) {
            i++;
        }
        // 判断奇偶是否相同
        // 只要奇偶相同，就一定可以到达
        while((i + i/2) % 2 != target % 2) {
            i++;
        }
        return i;
    }
}
