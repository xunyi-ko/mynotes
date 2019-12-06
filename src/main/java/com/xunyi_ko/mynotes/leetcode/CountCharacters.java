package com.xunyi_ko.mynotes.leetcode;

import org.junit.Test;

/**
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * 返回词汇表 words 中你掌握的所有单词的 字母长度之和。
 * 
 * @author zy
 *
 */
public class CountCharacters {
    @Test
    public void testCountCharacters() {
        
    }
    
    /*
     * ["cat","bt","hat","tree"]
     *  "atach"
     */
    public int countCharacters(String[] words, String chars) {
        int[] cs = new int[26];
        
        for(int i = 0; i < chars.length(); i++) {
            int p = chars.charAt(i) - 'a';
            cs[p] = cs[p] + 1;
        }
        
        int res = 0;
        for(String word : words) {
            if(read(word, cs.clone())) {
                res += word.length();
            }
        }
        return res;
    }
    
    private boolean read(String word, int[] chars) {
        for(int i = 0; i < word.length(); i++) {
            int p = word.charAt(i) - 'a';
            
            if(chars[p] == 0) {
                return false;
            }else {
                chars[p] = chars[p] - 1;
            }
        }
        return true;
    }
}
