package com.xunyi_ko.mynotes.leetcode;

import org.junit.Test;

/**
 * 给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数。
 * 
 * 示例: 输入: S = "abcde" words = ["a", "bb", "acd", "ace"]
 * 输出: 3 解释: 有三个是 S 的子序列的单词: "a", "acd", "ace"。 注意:
 * 
 * 所有在words和 S 里的单词都只由小写字母组成。 
 * S 的长度在 [1, 50000]。 words 的长度在 [1, 5000]。 words[i]的长度在[1, 50]。
 * 
 * @author zy
 *
 */
public class NumMatchingSubseq {
    @Test
    public void testNumMatchingSubseq() {
        /*
         * "qlhxagxdqh" 
         * ["qlhxagxdq","qlhxagxdq","lhyiftwtut","yfzwraahab"]
         */
        String S = "qlhxagxdqh";
        String[] words = {"qlhxagxdq","qlhxagxdq","lhyiftwtut","yfzwraahab"};
        
        System.out.println(numMatchingSubseq(S, words));
    }
    
    public int numMatchingSubseq(String S, String[] words) {
        int res = 0;
        
        
        
        return res;
    }
    
    // 暴力法（超时）
    public int numMatchingSubseq1(String S, String[] words) {
        int res = 0;
        
        int[] poses = new int[words.length];
        
        for(int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            
            for(int j = 0; j < words.length; j++) {
                String word = words[j];
                
                if(poses[j] == -1) {
                    continue;
                }
                if(word.charAt(poses[j]) == c) {
                    if(poses[j] == word.length() - 1) {
                        poses[j] = -1;
                        res ++;
                    }else {
                        poses[j] += 1;
                    }
                }
            }
        }
        return res;
    }
}
