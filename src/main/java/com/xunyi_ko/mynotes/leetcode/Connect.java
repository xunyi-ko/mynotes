package com.xunyi_ko.mynotes.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * 
 * struct Node { int val; Node *left; Node *right; Node *next; } 
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 
 * 初始状态下，所有 next 指针都被设置为 NULL
 * 
 * @author zy
 *
 */
public class Connect {
    @Test
    public void testConnect() {
        
    }
    
    public Node connect(Node root) {
        if(root == null) {
            return null;
        }
        
        Node node = root;
        
        Map<Integer, List<Node>> map = new HashMap<>();
        
        general(node, map, 1);
        connect(map);
        return root;
    }
    
    private void general(Node node, Map<Integer,List<Node>> map, Integer level) {
        if(node == null) {
            return;
        }
        
        List<Node> list = map.get(level);
        if(list == null) {
            list = new LinkedList<>();
            map.put(level, list);
        }
        
        list.add(node);
        
        general(node.left, map, level + 1);
        general(node.right, map, level + 1);
    }
    
    private void connect(Map<Integer, List<Node>> map) {
        for(List<Node> list : map.values()) {
            connect(list);
        }
    }
    
    private void connect(List<Node> list) {
        Iterator<Node> iterator = list.iterator();
        Node node = iterator.next();
        while(iterator.hasNext()) {
            node = node.next = iterator.next();
        }
    }
    
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    
}
