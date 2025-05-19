package com.dspatched;

import java.util.HashMap;

public class SlidingWindow {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int curLength = 0;

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
