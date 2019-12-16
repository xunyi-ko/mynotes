package com.xunyi_ko.mynotes.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 */
public class MinimumTotal {
    @Test
    public void testMinimumTotal() {
        List<List<Integer>> triangle = new ArrayList<>();
        
        Random random = new Random();
        int v;
        for(int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j <= i; j++) {
                list.add(v = random.nextInt(10));
                System.out.print(v + " ");
            }
            System.out.println();
            triangle.add(list);
        }
        System.out.println(minimumTotal(triangle));
    }
    
    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        if(length == 0) {
            return 0;
        }
        int[] res = new int[length];
        int[] nums = new int[length];
        
        List<Integer> list;
        for(int row = 0; row < triangle.size(); row++) {
            list = triangle.get(row);
            
            for(int col = 0; col <= row; col++) {
                if(col == 0) {
                }else if(col == row) {
                    res[col] = nums[col - 1];
                }else {
                    res[col] = Math.min(nums[col], nums[col - 1]);
                }
                
                res[col] += list.get(col);
            }
            
            for(int i = 0; i < length; i++) {
                nums[i] = res[i];
            }
        }
        
        int v = Integer.MAX_VALUE;
        for(int i : res) {
            v = Math.min(v, i);
        }
        return v;
    }
}
