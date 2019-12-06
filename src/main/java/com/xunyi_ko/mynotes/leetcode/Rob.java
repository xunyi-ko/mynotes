package com.xunyi_ko.mynotes.leetcode;

import org.junit.Test;

/**
 * middle
 * 
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。 
 * 这个地区只有一个入口，我们称之为“根”。 
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。 
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。 
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 
 * @author zy
 */
public class Rob {
    /*
     * [4,1,null,2,null,3]
     */
    @Test
    public void testRob() {
        TreeNode root = new TreeNode(4);
        TreeNode node = root;
        node = node.left = new TreeNode(1);
        node = node.left = new TreeNode(2);
        node = node.left = new TreeNode(3);
        
        System.out.println(rob(root));
    }
    
    public int rob(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        // 用递归的方式求解
        return max(root, true);
    }
    
    private int max(TreeNode node, boolean flag) {
        if(node == null) {
            return 0;
        }
        int res;
        // flag标识是否包括当前节点
        if(flag) {
            if(node.left == null && node.right == null) {
                return node.val;
            }
            // 最大值为，两个子节点的值，或者当前节点的值加上不包括两个子节点的递归值
            res = Math.max(max(node.left, true) + max(node.right, true),
                    node.val + max(node.left, false) + max(node.right, false));
        }else {
            if(node.left == null && node.right == null) {
                return 0;
            }
            // 两个子节点的值
            res = max(node.left, true) + max(node.right, true);
        }
        return res;
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
