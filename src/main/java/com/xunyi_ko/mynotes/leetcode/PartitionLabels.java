package com.xunyi_ko.mynotes.leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 字符串 S 由小写字母组成。
 * 我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。
 * 返回一个表示每个字符串片段的长度的列表。
 * @author zy
 *
 */
public class PartitionLabels {
    @Test
    public void testPartitionLabels() {
        List<Integer> list = partitionLabels("asdjfkhakjdfnkrwhathfdjkac");
        
        for(Integer i : list) {
            System.out.println(i);
        }
    }
    
    public List<Integer> partitionLabels(String S) {
        int[] dist = new int[26];
        
        int c;
        for(int i = 0; i < S.length(); i++) {
            c = S.charAt(i) - 'a';
            dist[c] = i;
        }
        
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < S.length(); i++) {
            c = S.charAt(i) - 'a';
            if(dist[c] == i) {
                res.add(1);
            }else {
                int max = dist[c];
                for(int j = i + 1; j < max; j++) {
                    c = S.charAt(j) - 'a';
                    if(dist[c] == j) {
                        continue;
                    }
                    max = Math.max(dist[c], max);
                }
                res.add(max - i + 1);
                i = max;
            }
        }
        return res;
    }
}
