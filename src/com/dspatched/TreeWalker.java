package com.dspatched;

import java.util.LinkedList;
import java.util.Queue;

public class TreeWalker {

    TreeWalker(Node node) {
        currentNode = node;
    }

    private Node currentNode;

    public static void main(String[] args) {
        Node root = populateTree();
        TreeWalker treeWalker = new TreeWalker(root);
        treeWalker.traverseBreadthFirst();
        //treeWalker.traverseDepthFirst();
    }

    private static Node populateTree() {
        int cnt = 0;
        System.out.print(cnt);
        Node node = new Node(cnt++);
        System.out.print(cnt);
        node.left = new Node(cnt++);
        System.out.print(cnt);
        node.right = new Node(cnt++);
        System.out.print(cnt);
        node.left.left = new Node(cnt++);
        System.out.print(cnt);
        node.left.right = new Node(cnt++);
        System.out.print(cnt);
        node.right.left = new Node(cnt++);
        System.out.println();
        return node;
    }

    public void traverseBreadthFirst() {
        Queue<Node> queue = new LinkedList<>();
        if (currentNode == null) return;
        System.out.print(currentNode.id);
        queue.offer(currentNode);
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            if (currentNode.left != null) {
                queue.offer(currentNode.left);
                System.out.print(currentNode.left.id);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
                System.out.print(currentNode.right.id);
            }
        }
    }

    public void traverseDepthFirst() {
        LinkedList<Node> stack = new LinkedList<>();
        if (currentNode == null) return;
        stack.push(currentNode);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            while (node != null) {
                if (node.left != null) {
                    if (node.right != null) stack.push(node.right);
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
    }

}

class Node {

    int id;
    Node left;
    Node right;

    Node(int id) {
        this.id = id;
    }

}
