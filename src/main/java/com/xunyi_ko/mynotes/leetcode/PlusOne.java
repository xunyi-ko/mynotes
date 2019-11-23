package com.xunyi_ko.mynotes.leetcode;

import org.junit.Test;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * @author zy
 */
public class PlusOne {
    @Test
    public void testPlusOne() {
        int[] digits = new int[]{6,7};
        digits = plusOne(digits);
        for(int i : digits) {
            System.out.println(i);
        }
    }
    
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        
        boolean flag = false;
        for(int i = length - 1; i >= 0; i--) {
            int v = digits[i];
            
            flag = ((v + 1)/10 == 1);
            digits[i] = (v + 1) % 10;
            if(!flag) {
                return digits;
            }
        }
        
        int[] res = new int[length + 1];
        for(int i = 1; i <= length; i++) {
            res[i] = digits[i - 1];
        }
        res[0] = 1;
        return res;
    }
}
