package com.dspatched;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeWalker {

    TreeWalker(Node node) {
        currentNode = node;
    }
    private Node currentNode;
    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public static void main(String[] args) {
        Node root = populateTree(List.of(0,1,2,3,4,5));
        TreeWalker treeWalker = new TreeWalker(root);
        System.out.print("BREADTH FIRST: ");
        treeWalker.traverseBreadthFirst();

        treeWalker.setCurrentNode(root);
        System.out.println();
        System.out.print("DEPTH FIRST: ");
        treeWalker.traverseDepthFirst();
    }

    private static Node populateTree(List<Integer> list) {
        Queue<Node> queue = new LinkedList<>();
        int i = 0;
        Node root = new Node(list.get(i++));
        queue.add(root);

        while (!queue.isEmpty() && i < list.size()) {
            Node node = queue.poll();

            Integer left = list.get(i++);
            if (left != null) {
                node.left = new Node(left);
                queue.add(node.left);
            }

            if (i < list.size()) {
                Integer right = list.get(i++);
                if (right != null) {
                    node.right = new Node(right);
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    public void traverseBreadthFirst() {
        if (currentNode == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(currentNode);
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            System.out.print(currentNode.id + " ");
            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }
    }

    public void traverseDepthFirst() {
        if (currentNode == null) return;
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(currentNode);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.id + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public void invert() {
        invert(currentNode);
    }

    private void invert(Node node) {
        if (node == null) return;

        Node tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        invert(node.left);
        invert(node.right);
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
