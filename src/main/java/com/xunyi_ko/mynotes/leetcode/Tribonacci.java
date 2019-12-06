package com.xunyi_ko.mynotes.leetcode;

import org.junit.Test;

/**
 * 泰波那契序列 Tn 定义如下： 
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * 
 * @author zy
 *
 */
public class Tribonacci {
    @Test
    public void testTribonacci() {
        for(int i = 0; i < 10; i++) {
            System.out.println(tribonacci(i));
        }
    }
    
    /*
     * 0 1 1 2 4 7 13 24 44
     */
    public int tribonacci(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 1;
        }
        
        int[] tribonacci = new int[n + 1];
        tribonacci[0] = 0;
        tribonacci[1] = 1;
        tribonacci[2] = 1;
        for(int i = 3; i < n + 1; i++) {
            tribonacci[i] = tribonacci[i - 1] + tribonacci[i - 2] + tribonacci[i - 3];
        }
        return tribonacci[n];
    }
}
