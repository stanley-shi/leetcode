package Create_Maximum_Number;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Solution_bak {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        return internalMax(nums1, 0, nums2, 0, k, result, 0);
    }

    private int[] internalMax(int[] nums1, int start1, int[] nums2, int start2, int k, int[] result, int ridx) {
        print(result);
        int idx = findLargestNumIdx(nums1, start1, nums2, start2, k);
        int num = idx >= nums1.length ?
                nums2[idx - nums1.length] :
                nums1[idx];
        result[ridx] = num;
        if (k == 1) {
            return result;
        }
        internalMax(nums1, idx >= nums1.length ? start1 : (idx + 1), nums2, idx >= nums1.length ? (idx + 1 - nums1.length) : start2, k - 1, result, ridx + 1);
        return result;

    }

    private int findLargestNumIdx(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if (k == nums1.length - start1 + nums2.length - start2) {
            // all numbers will be used;
            // nums1 or nums2 may have been all used;
            if (start1 >= nums1.length) {
                return start2 + nums1.length;
            } else if (start2 >= nums2.length) {
                return start1;
            }
            if (nums1[start1] > nums2[start2]) {
                return start1;
            } else if (nums1[start1] < nums2[start2])
                return start2 + nums1.length;
            else {
                if (start1 >= nums1.length - 1)
                    return start2 + nums1.length;
                else if (start2 >= nums2.length - 1)
                    return start1;
                else {
                    // TODO not only the next one, all the consequent numbers;
                    return (nums1[start1 + 1] < nums2[start2 + 1]) ? (start2 + nums1.length) : start1;
                }
            }
        }
        int idx1 = -1;
        int num1 = Integer.MIN_VALUE;
        int pidx1 = idx1;
        int pnum1 = num1;
        for (int i = start1; i <= nums1.length - (k - (nums2.length - start2)) && i < nums1.length; i++) {
            if (nums1[i] > num1) {
                pidx1 = idx1;
                pnum1 = num1;
                num1 = nums1[i];
                idx1 = i;
            }
        }
        int idx2 = -1;
        int num2 = Integer.MIN_VALUE;
        int pidx2 = idx2;
        int pnum2 = num2;
        for (int i = start2; i <= nums2.length - (k - (nums1.length - start1)) && i < nums2.length; i++) {
            if (nums2[i] > num2) {
                pidx2 = idx2;
                pnum2 = num2;
                num2 = nums2[i];
                idx2 = i + nums1.length;
            }
        }
        if (num1 > num2) return idx1;
        else if (num1 < num2) return idx2;
        else {
            if (pnum2 > pnum1) return idx1;
            else return idx2;
        }
    }


    @Test
    public void test() {
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        int[] res = maxNumber(nums1, nums2, 5);
        print(res);
        assertArrayEquals(res, new int[]{9, 8, 6, 5, 3});
    }


    @Test
    public void test2() {
        int[] nums1 = {8, 9};
        int[] nums2 = {3, 9};
        int[] res = maxNumber(nums1, nums2, 3);
        print(res);
        assertArrayEquals(res, new int[]{9, 8, 9});
    }

    @Test
    public void test3() {
        int[] nums1 = {2, 5, 6, 4, 4, 0};
        int[] nums2 = {7, 3, 8, 0, 6, 5, 7, 6, 2};
        int[] res = maxNumber(nums1, nums2, 15);
        print(res);
        assertArrayEquals(res, new int[]{7, 3, 8, 2, 5, 6, 4, 4, 0, 6, 5, 7, 6, 2, 0});
    }


    @Test
    public void test4() {
        int[] nums1 = {6, 7};
        int[] nums2 = {6, 0, 4};
        int[] res = maxNumber(nums1, nums2, 5);
        print(res);
        assertArrayEquals(res, new int[]{6, 7, 6, 0, 4});
    }

    @Test
    public void test5() {
        int[] nums1 = {2, 8, 0, 4, 5, 1, 4, 8, 9, 9, 0, 8, 2, 9};
        int[] nums2 = {5, 9, 6, 6, 4, 1, 0, 7};
        int[] res = maxNumber(nums1, nums2, 22);
        print(res);
        assertArrayEquals(res, new int[]{5, 9, 6, 6, 4, 2, 8, 1, 0, 7, 0, 4, 5, 1, 4, 8, 9, 9, 0, 8, 2, 9});
    }

    @Test
    public void test6() {
        int[] nums1 = {5, 0, 2, 1, 0, 1, 0, 3, 9, 1, 2, 8, 0, 9, 8, 1, 4, 7, 3};
        int[] nums2 = {7, 6, 7, 1, 0, 1, 0, 5, 6, 0, 5, 0};
        int[] res = maxNumber(nums1, nums2, 31);
        print(res);
        assertArrayEquals(res, new int[]{6, 7, 6, 0, 4});
    }

    private void print(int[] res) {
        for (int i = 0; i < res.length; i++)
            System.out.print(res[i]);
        System.out.println();
    }
}