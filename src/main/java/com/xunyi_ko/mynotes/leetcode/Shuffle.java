package com.xunyi_ko.mynotes.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

/**
 * 打乱一个没有重复元素的数组。
 * 
 * 示例:
 *   以数字集合 1, 2 和 3 初始化数组。 int[] nums = {1,2,3}; Solution solution = new Solution(nums);
 *   打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。 solution.shuffle();
 *   重设数组到它的初始状态[1,2,3]。 solution.reset();
 *   随机返回数组[1,2,3]打乱后的结果。 solution.shuffle();
 * 
 * @author zy
 */
public class Shuffle {
    @Test
    public void testShuffle() {
        int[] nums = {1,2,3};
        
        Solution shuffle = new Solution(nums);
        
        int[] ints = shuffle.shuffle();
        for(int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
    }
    
    class Solution{
        int[] sorted;
        public Solution(int[] nums) {
            this.sorted = nums;
        }
        
        public int[] reset() {
            return sorted;
        }
        
        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int[] unsorted = new int[sorted.length];
            
            // 将所有下标存入list
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < sorted.length; i++) {
                list.add(i);
            }
            
            Random random = new Random();
            int pos;
            for(int i = sorted.length; i > 0; i--) {
                // 随机一个小于list长度的值
                pos = random.nextInt(i);
                // 根据随机数获取list中对应的下标，取出数组中的值
                unsorted[i - 1] = sorted[list.remove(pos)];
            }
            return unsorted;
        }
    }
}
