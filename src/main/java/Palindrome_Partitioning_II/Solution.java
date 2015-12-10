package Palindrome_Partitioning_II;

import org.junit.Test;

import java.util.Arrays;

public class Solution {
    public int minCut(String s) {
        char[] cs = s.toCharArray();
        int[][] result = new int[cs.length][cs.length];
        for (int[] mm : result) {
            Arrays.fill(mm, -1);
            mm[0] = 0;
        }
        if (!isPalindrome(cs, 0, cs.length)) {
            return minCut(cs, result, 0, cs.length);
        } else {
            return 0;
        }
    }

    private int minCut(char[] cs, int[][] result, int offset, int len) {
        if (result[offset][len - 1] != -1)
            return result[offset][len - 1];
        if (isPalindrome(cs, offset, len)) {
            result[offset][len - 1] = 0;
            return 0;
        }
        if (len == 2) {
            result[offset][len - 1] = 1;
            return 1;
        } else {
            int minm = len;
            for (int i = 0; i < len - 1; i++) {
                if (i == 0) {
                    int cmin = 1 + minCut(cs, result, offset + i + 1, len - i - 1);
                    if (cmin < minm)
                        minm = cmin;
                } else {
                    int cmin =
                            minCut(cs, result, offset, i + 1) + minCut(cs, result, offset + i + 1, len - i - 1)
                                    + 1;
                    if (cmin < minm)
                        minm = cmin;
                }
            }
            result[offset][len - 1] = minm;
            return minm;
        }
    }

    boolean isPalindrome(char[] s, int offset, int len) {
        if (offset < 0 || len <= 0 || offset + len > s.length)
            return false;
        if (len == 1)
            return true;

        for (int i = 0; i < len / 2; i++) {
            if (s[offset + i] != s[offset + len - 1 - i])
                return false;
        }
        return true;
    }

    @Test
    public void test() {
        String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        long start = System.currentTimeMillis();
        System.out.println(minCut(input));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

}