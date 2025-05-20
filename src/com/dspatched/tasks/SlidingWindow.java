package com.dspatched.tasks;

import java.util.*;

public class SlidingWindow {

    public static void main(String[] args) {
        //System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(reverseWords("the sky is blue"));
    }

    public static String reverseWords(String s) {
        String result = "";
        String curWord = "";
        Deque<String> wordStack = new LinkedList<String>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur != ' ') {
                curWord = curWord + cur;
            }
            if (cur == ' ' || i == s.length() - 1) {
                if (!curWord.isEmpty()) {
                    wordStack.push(curWord);
                    curWord = "";
                }
            }
        }
        int n = wordStack.size();
        for (int i = 0; i < n; i++) {
            result = result + wordStack.pop();
            if (i != n - 1) {
                result += " ";
            }
        }
        return result;
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int curLength = 0;
        Deque<String> wordStack = new LinkedList<String>();

        for (int right = 0; right < s.length(); right++) {
            char cur = s.charAt(right);

            if (charIndexMap.containsKey(cur)) {
                left = Math.max(left, charIndexMap.get(cur) + 1);
            }

            charIndexMap.put(cur, right);
            curLength = right - left + 1;
            if (maxLength < curLength) {
                maxLength = curLength;
            }
        }

        return maxLength;
    }
}
