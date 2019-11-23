package com.xunyi_ko.mynotes.leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * 
 * @author zy
 *
 */
public class FizzBuzz {
    @Test
    public void testFizzBuzz() {
        List<String> fizzBuzz = fizzBuzz(15);
        
        for(String s : fizzBuzz) {
            System.out.println(s);
        }
    }
    
    
    private final String FIZZ = "Fizz";
    private final String BUZZ = "Buzz";
    private final String FIZZ_BUZZ = "FizzBuzz";
    
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        
        for(int i = 1; i <= n; i++) {
            if(i % 3 == 0 && i % 5 == 0) {
                res.add(FIZZ_BUZZ);
            }else if(i % 3 == 0) {
                res.add(FIZZ);
            }else if(i % 5 == 0) {
                res.add(BUZZ);
            }else {
                res.add(Integer.toString(i));
            }
        }
        return res;
    }
}
