package com.xunyi_ko.mynotes.leetcode;

/**
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。 
 * 
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 * 
 * @author xunyi
 *
 */
public class FindSecondMinimumValue {
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null) {
            return -1;
        }
        
        int left = findMinExclude(root.left, root.val);
        int right = findMinExclude(root.right, root.val);
        
        if(left == -1) {
            return right;
        }else if(right == -1){
            return left;
        }else {
            return Math.min(left, right);
        }
    }
    
    private int findMinExclude(TreeNode node, int exclude) {
        if(node == null) {
            return -1;
        }
        
        if(node.val == exclude) {
            return findSecondMinimumValue(node);
        }else {
            return node.val;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
