package com.xunyi_ko.mynotes.leetcode;

import org.junit.Test;

/**
 * 给定正整数 K，你需要找出可以被 K 整除的、仅包含数字 1 的最小正整数 N。
 * 返回 N 的长度。如果不存在这样的 N，就返回 -1。
 * @author zy
 */
public class SmallestRepunitDivByK {
    @Test
    public void testSmallestRepunitDivByK(){
        System.out.println(smallestRepunitDivByK(1000));
    }
    
    public int smallestRepunitDivByK(int K) {
        // 不可能符合条件
        if(K % 2 == 0 || K % 5 == 0) {
            return -1;
        }
        
        int res = 1;
        for(int i = 1; i <= K; i++) {
            if(res % K == 0) {
                return i;
            }
            
            res = res * 10 + 1;
        }
        return -1;
    }
}
