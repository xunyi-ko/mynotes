package com.xunyi_ko.mynotes.leetcode;

/**
 * 给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。
 * 
 * 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。
 * 你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。
 * 你的表达式不应该含有冗余的括号。
 * 
 * @author zy
 *
 */
public class OptimalDivision {
    public String optimalDivision(int[] nums) {
        if (nums.length == 1)
            return nums[0] + "";
        if (nums.length == 2)
            return nums[0] + "/" + nums[1];
        StringBuilder res = new StringBuilder(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            res.append("/" + nums[i]);
        }
        res.append(")");
        return res.toString();
    }
}
