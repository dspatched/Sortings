package com.dspatched.tasks;

public class ReverseLinkedList {

    public static void main(String[] args) {
        Node c = new Node(3, null);
        Node b = new Node(2, c);
        Node a = new Node(1, b);

        reverse(a);

        Node cur = c;
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }

    private static void reverse(Node node) {
        Node current = node;
        Node prev = null;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
    }

}

class Node {
    int value;
    Node next;

    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}