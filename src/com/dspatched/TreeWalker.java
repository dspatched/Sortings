package com.dspatched;

import java.util.LinkedList;

public class TreeWalker {

    TreeWalker(Node node) {
        currentNode = node;
    }

    private Node currentNode;

    public static void main(String[] args) {
        Node root = populateTree();
        TreeWalker treeWalker = new TreeWalker(root);
        treeWalker.traverseBreadthFirst();
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
        LinkedList<Node> deque = new LinkedList<>();
        if (currentNode == null) return;
        System.out.print(currentNode.id);
        deque.offer(currentNode);
        while (!deque.isEmpty()) {
            currentNode = deque.poll();
            if (currentNode.left != null) {
                deque.offer(currentNode.left);
                System.out.print(currentNode.left.id);
            }
            if (currentNode.right != null) {
                deque.offer(currentNode.right);
                System.out.print(currentNode.right.id);
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
