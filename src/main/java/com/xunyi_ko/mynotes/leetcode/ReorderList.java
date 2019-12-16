package com.xunyi_ko.mynotes.leetcode;

import org.junit.Test;


/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * @author zy
 */
public class ReorderList {
    @Test
    public void testReorderList() {
        ListNode first = new ListNode(1);
        
        ListNode node = first;
        
        for(int i = 2; i < 6; i++) {
            node = node.next = new ListNode(i);
        }
        
        Solution sol = new Solution();
        sol.reorderList(first);
        
        node = first;
        
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
    
    class Solution{
        int pos = 0;
        int size = 16;
        ListNode[] list;
        
        public void reorderList(ListNode head) {
            if(head == null) {
                return;
            }
            list = new ListNode[size];
            while(head != null) {
                if(pos == size) {
                    refactory();
                }
                list[pos++] = head;
                head = head.next;
            }
            
            int left = 1;
            int right = pos - 1;
            
            ListNode node = list[0];
            while(left < right) {
                node = node.next = list[right];
                node = node.next = list[left];
                right--;
                left++;
            }
            if(left == right) {
                node = node.next = list[left];
            }
            node.next = null;
        }
        
        private void refactory() {
            size *= 2;
            ListNode[] newList = new ListNode[size];
            
            for(int i = 0; i < list.length; i++) {
                newList[i] = list[i];
            }
            list = newList;
        }
    }
    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }
}
