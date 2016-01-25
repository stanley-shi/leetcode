package heap_sort;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by demshi on 1/22/16.
 */
public class Solution {
    public void heapSort(int[] nums){
        heapfy(nums);
        for(int i=nums.length-1; i>0;i--){
            swap(nums, i, 0);
            maxHeap(nums, 0, i);
        }
    }

    private void heapfy(int[] nums) {
        for(int i = nums.length/2-1;i>=0;i--){
            maxHeap(nums, i, nums.length);
        }
    }

    private void maxHeap(int[] nums, int i, int len) {
        int max  = i;
        int left = 2*i+1;
        int right = 2*i+2;
        if(left<len&&nums[left]>nums[max])
            max=left;
        if(right<len&&nums[right]>nums[max])
            max=right;
        if(max!=i){
            swap(nums, i, max);
            maxHeap(nums, max, len);
        }
    }

    private void swap(int[] nums, int i, int max) {
        int tmp = nums[i];
        nums[i]=nums[max];
        nums[max]=tmp;
    }

    int[] target={0,1,2,3,4,5,6,7,8,9};
    @Test
    public void test(){
        List list = Arrays.asList(3,8,4,6,5,7,9,2,1,0);
        Collections.shuffle(list);
        int[] m = toIntArray(list);
        print(m);
        heapfy(m);
        print(m);
        heapSort(m);
        assertArrayEquals(m, target);
    }

    private int[] toIntArray(List<Integer> list) {
        int[] res = new int[list.size()];
        for(int i =0;i<list.size();i++){
            res[i]=list.get(i);
        }
        return res;
    }

    private void print(int[] m) {
        for(int i =0;i<m.length;i++){
        System.out.print(m[i]);
        System.out.print(" ");}
        System.out.println();
    }
}
