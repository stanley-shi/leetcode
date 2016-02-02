package preorder_inorder;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(null==inorder||null==preorder||inorder.length==0||preorder.length==0||
                preorder.length!=inorder.length)return null;
        if(inorder.length==1) return new TreeNode(inorder[0]);
        return buildTree(inorder, 0, preorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] inorder, int iIdx, int[] preorder, int pIdx, int length) {
        if(length==0) return null;
        if(length==1) return new TreeNode(inorder[iIdx]);
        int rootVal = preorder[pIdx];
        int rootIdx = -1;
        for(int i = iIdx;i<iIdx+length;i++){
            if(inorder[i]==rootVal) {
                rootIdx = i;
                break;
            }
        }
        if(rootIdx==-1){
            throw new RuntimeException("Root element not found in inorder array!!");
        }
        TreeNode root = new TreeNode(rootVal);
        TreeNode left = buildTree(inorder, iIdx, preorder, pIdx+1, (rootIdx-iIdx));
        TreeNode right = buildTree(inorder, rootIdx+1, preorder, pIdx+rootIdx-iIdx+1, length+iIdx-rootIdx -1 );
        root.left=left;
        root.right=right;
        return root;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x ){val=x;}
}