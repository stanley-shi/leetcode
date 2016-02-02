package Validate_Binary_Search_Tree;

import org.junit.Test;

/**
 * Definition for a binary tree node.
 * inorder traverse of this tree would create an increasing list;
 */
public class Solution {
    public boolean isValidBST(TreeNode root) {
        MinimumValue curVal = new MinimumValue();
        return checkAndUpdate(root, curVal);
    }

    private boolean checkAndUpdate(TreeNode root, MinimumValue curVal) {
        if(root==null)return true;
        if(root.left!=null)
            if(!checkAndUpdate(root.left, curVal)){
                return false;
            }
        if(!curVal.init||curVal.val<root.val){
            // valid
            curVal.val=root.val;
            curVal.init=true;
        } else return false;
        if(root.right!=null){
            if(!checkAndUpdate(root.right,curVal)){
                return false;
            }
        }
        return true;
    }


    @Test
    public void test1(){
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(1);
        n1.left=n2;
        System.out.println(isValidBST(n1));
    }
}

class MinimumValue {
    boolean init;
    int val;
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}