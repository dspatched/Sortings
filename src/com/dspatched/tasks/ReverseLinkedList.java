package com.dspatched.tasks;

import java.util.Deque;
import java.util.LinkedList;

public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode c = new ListNode(3, null);
        ListNode b = new ListNode(2, c);
        ListNode a = new ListNode(1, b);

        ListNode d = new ListNode(5, null);
        ListNode e = new ListNode(3, d);
        ListNode f = new ListNode(1, e);

        ListNode res = mergeTwoLists(a, f);

//        a = swapPairs(a);

        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }

//        ListNode l1_2 = new ListNode(9, null);
//        ListNode l1_1 = new ListNode(4, l1_2);
//        ListNode l1 = new ListNode(2, l1_1);
//        //ListNode l1 = new ListNode(9, null);
//
//        ListNode l2_3 = new ListNode(9, null);
//        ListNode l2_2 = new ListNode(4, l2_3);
//        ListNode l2_1 = new ListNode(6, l2_2);
//        ListNode l2 = new ListNode(5, l2_1);
//        ListNode l2_9 = new ListNode(1, null);
//        ListNode l2_8 = new ListNode(9, l2_9);
//        ListNode l2_7 = new ListNode(9, l2_8);
//        ListNode l2_6 = new ListNode(9, l2_7);
//        ListNode l2_5 = new ListNode(9, l2_6);
//        ListNode l2_4 = new ListNode(9, l2_5);
//        ListNode l2_3 = new ListNode(9, l2_4);
//        ListNode l2_2 = new ListNode(9, l2_3);
//        ListNode l2_1 = new ListNode(9, l2_2);
//        ListNode l2 = new ListNode(9, l2_1);

//        ListNode cur = addTwoNumbers(l1, l2);
//        while (cur != null) {
//            System.out.println(cur.val);
//            cur = cur.next;
//        }
    }

    private static void reverse(ListNode listNode) {
        ListNode current = listNode;
        ListNode prev = null;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        if (list1 != null) {
            cur.next = list1;
        } else {
            cur.next = list2;
        }

        return head.next;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;

        first.next = swapPairs(second.next);
        second.next = first;
        return second;
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long num1 = readNumber(l1);
        long num2 = readNumber(l2);
        long sum = num1 + num2;
        return numberToNode(sum);
    }

    private static ListNode numberToNode(long num) {
        int len = (String.valueOf(num)).length();
        ListNode prev = null;
        ListNode cur = null;
        for (int i = len - 1; i >= 0; i--) {
            long digit = (long) Math.pow(10, i);
            int curVal = (int) ((num / digit) % 10);
            prev = cur;
            cur = new ListNode(curVal, prev);
        }
        return cur;
    }

    private static long readNumber(ListNode listNode) {
        Deque<Integer> stack = new LinkedList<>();
        ListNode cur = listNode;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        long num = 0;
        int size = stack.size();
        for (int i = size - 1; i >= 0; i--) {
            long digit = (long) Math.pow(10, i);
            num = num + digit * ((long) stack.pop());
        }
        return num;
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode a = head;
        ListNode b = head;
        while (true) {
            a = a.next;
            b = b.next;
            if (b.next == null) {
                return false;
            } else if (a.val == b.val && a.equals(b)) {
                return true;
            }
            b = b.next;
            if (b.next == null) {
                return false;
            } else if (a.val == b.val && a.equals(b)) {
                return true;
            }
        }
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}