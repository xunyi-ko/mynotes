package com.xunyi_ko.mynotes.leetcode;

import org.junit.Test;

/**
 * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 * @author zy
 */
// TODO
public class FindKthNumber {
    /**
     * 9895 28405 100787757
     * 31666761
     */
    @Test
    public void testFindKthNumber() {
        long start = System.currentTimeMillis();
        int i = findKthNumber(45, 12, 471);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(i);
        
    }
    
    public int findKthNumber(int m, int n, int k) {
        if(m == 1 || n == 1) {
            return k;
        }
        if(k == m * n) {
            return m * n;
        }
        if(m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        int left = 1;
        int right = m * n;
        int mid = 0;
        int res = 0;
        while(left < right) {
            mid = (left + right) / 2;
            res = getNoMoreThanNumSize(m, n, mid);
            if(res > k) {
                right = mid;
            }else if(res < k) {
                left = mid;
            }else {
                break;
            }
        }
        return mid;
    }
    public boolean enough(int x, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(x / i, n);
        }
        return count >= k;
    }

    private int getNoMoreThanNumSize(int m, int n, int v) {
        if(m == 1 || n == 1) {
            return v;
        }
        
        int res = 0;
        int value;
        for(int j = 1; j <= n; j++) {
            value = v / j;
            if(v/j == 0) {
                break;
            }
            res += Math.min(value, m);
        }
        return res;
    }
    
    @Test
    public void testSqrt() {
        System.out.println((int)Math.sqrt(8));
    }
    /*
     * 1  2  3  4  5  6  7  8  9 
     * 2  4  6  8  10 12 14 16 18
     * 3  6  9  12 15 18 21 24 27
     * 4  8  12 16 20 24 28 32 36
     * 5  10 15 20 25 30 35 40 45
     * 6  12 18 24 30 36 42 48 54
     * 7  14 21 28 35 42 49 56 63
     * 8  16 24 32 40 48 56 64 72
     * 9  18 27 36 45 54 63 72 81
     */
}
