package com.xunyi_ko.mynotes.leetcode;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。 
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 */
public class SortedListToBST {
    @BeforeEach
    public void before() {
        System.out.println("start: " + System.currentTimeMillis());
    }
    
    @AfterEach
    public void after() {
        System.out.println("after: " + System.currentTimeMillis());
    }
    
    @Test
    public void testSortedListToBST() {
        ListNode first = new ListNode(0);
        ListNode node = first;
        for(int i = 1; i < 100000; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        long start = System.currentTimeMillis();
        sortedListToBST(first);
        System.out.println(System.currentTimeMillis() - start);
    }

    // 通过减少遍历的方式加快速度
    int pos = 0;
    int size = 16;
    int[] ints = new int[size];
    TreeNode cloneTree = new TreeNode(0);
    
    // 由于是有序链表，所以直接取中间的作为根节点就肯定是平衡的
    private TreeNode sortedListToBST(ListNode head) {
        // 遍历一边链表，获取数组
        while(head != null) {
            // 数组长度不够就翻倍
            if(pos == size) {
                refactory();
            }
            
            ints[pos++] = head.val;
            head = head.next;
        }
        return buildTree(ints, 0, pos);
    }

    private void refactory() {
        size *= 2;
        ints = Arrays.copyOf(ints, size);
    }
    private TreeNode buildTree(int[] ints, int start, int end) {
        // 包括头不包括尾。
        // 因此头尾相等时返回null
        if(end == start) {
            return null;
        }
        // 长度为1时，则返回节点即可
        if(end - start == 1) {
            return new TreeNode(ints[start]);
        }
        
        // 当前节点位置为头尾之间
        int cur = (start + end) / 2;
        TreeNode node = new TreeNode(ints[cur]);
        // 用递归的方式创建节点
        node.left = buildTree(ints, start, cur);
        node.right = buildTree(ints, cur + 1, end);
        return node;
    }
    
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode implements Cloneable{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
        
        @Override
        public String toString() {
            if(left != null) {
                left.toString();
            }
            System.out.print(val + " ");
            if(right != null) {
                right.toString();
            }
            return "";
        }
    }
}
