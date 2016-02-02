package Perfect_Squares;

import org.junit.Test;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

 */
public class Solution {
    public int numSquares(int n) {
        int[] nums = new int[n+1];
        for(int i=0;i<n+1;i++)
            nums[i]=Integer.MAX_VALUE;
        for(int i=0;i*i<=n;i++){
            nums[i*i]=1;
        }
        for(int i=0;i<n+1;i++){
            for(int j=1;i+j*j<n+1;j++){
                nums[i+j*j]=Math.min(nums[i]+1, nums[i+j*j]);
            }
        }
        return nums[n];
    }

    @Test
    public void test(){
        System.out.println(numSquares(12));
        System.out.println(numSquares(13));
    }
}