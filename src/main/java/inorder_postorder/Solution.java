package inorder_postorder;

/**
 * thought: last number in postorder is the root of the tree, this number in inorder divides the left and right child
 */
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(null==inorder||null==postorder||inorder.length==0||postorder.length==0||
                postorder.length!=inorder.length)return null;
        if(inorder.length==1) return new TreeNode(inorder[0]);
        return buildTree(inorder, 0, postorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] inorder, int iIdx, int[] postorder, int pIdx, int length) {
        if(length==0) return null;
        if(length==1) return new TreeNode(inorder[iIdx]);
        int rootVal = postorder[pIdx+length-1];
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
        TreeNode left = buildTree(inorder, iIdx, postorder, pIdx, (rootIdx-iIdx));
        TreeNode right = buildTree(inorder, rootIdx+1, postorder, pIdx+rootIdx-iIdx, length+iIdx-rootIdx -1 );
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