package com.xunyi_ko.mynotes.leetcode;

import org.junit.Test;

/**
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * 
 * B.length >= 3 存在 0 < i < B.length - 1 使得 
 * B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1] 
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * 如果不含有 “山脉” 则返回 0。
 * 
 * @author xunyi
 *
 */
public class LongestMountain {
    /*
     * [2,1,4,7,3,2,5]
     */
    @Test
    public void testLongestMountain() {
        System.out.println(longestMountain(new int[]{875,884,239,731,723,685}));
    }
    
    public int longestMountain(int[] A) {
        if(A.length < 3) {
            return 0;
        }
        
        int max = 0;
        // 从上山开始
        boolean climb = true;
        int length = 0;
        for(int i = 0; i < A.length - 1; i++) {
            if(climb) {
                if(A[i] < A[i + 1]) {
                    // 上山
                    length++;
                }else if(A[i] == A[i + 1]){
                    // 经过平地，需要重新计算
                    length = 0;
                    climb = true;
                }else{
                    // 长度不为零，说明有过上山的过程，流程完成了
                    if(length != 0) {
                        length++;
                        climb = false;
                    }
                }
            }else {
                if(A[i] > A[i + 1]) {
                    length++;
                }else {
                    // 一段山路结束了。要么是平地了，要么重新上山了
                    max = Math.max(max, length + 1);
                    climb = true;
                    
                    length = A[i] == A[i + 1] ? 0 : 1;
                }
            }
        }
        
        if(!climb) {
            max = Math.max(max, length + 1);
        }
        return max;
    }
}
