package com.xunyi_ko.mynotes.leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 给出第一个词 first 和第二个词 second，
 * 考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，
 * 其中 second 紧随 first 出现，third 紧随 second 出现。
 * 
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
 * 
 * @author xunyi
 */
public class FindOcurrences {
    @Test
    public void testFindOcurrences() {
        String text = "we will we will rock you!";
        String first = "we";
        String second = "will";
        
        String[] strings = findOcurrences(text, first, second);
        for(String string : strings) {
            System.out.println(string);
        }
    }
    
    public String[] findOcurrences(String text, String first, String second) {
        if(text == null || "".equals(text.trim())) {
            return null;
        }
        
        List<String> res = new ArrayList<>();
        String[] strings = text.split(" ");
        for(int i = 0; i < strings.length - 2; i++) {
            if(strings[i].equals(first)) {
                if(strings[i + 1].equals(second)) {
                    res.add(strings[i + 2]);
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
