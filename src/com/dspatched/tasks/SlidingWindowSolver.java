package com.dspatched.tasks;

import java.util.*;

public class SlidingWindowSolver {

    public static void main(String[] args) {
        SlidingWindowSolver solver = new SlidingWindowSolver();
        //System.out.println(solver.lengthOfLongestSubstring("abcabcbb"));
        //System.out.println(solver.reverseWords("the sky is blue"));
        //System.out.println(solver.minWindow("ADOBECODEBANC", "ABC"));
        //System.out.println(solver.minWindow("bdab", "ab"));
        //System.out.println(solver.minWindow("bba", "ba"));
        //System.out.println(solver.minWindow("acbbaca", "aba"));
        //System.out.println(solver.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
        //int[] nums = new int[]{1,2,3,4,5,6,7,8,9,-10}; // при к = 3 ответ = 8
        int[] nums = new int[]{8860,-853,6534,4477,-4589,8646,-6155,-5577,-1656,-5779,
                -2619,-8604,-1358,-8009,4983,7063,3104,-1560,4080,2763,5616,-2375,2848,
                1394,-7173,-5225,-8244,-809,8025,-4072,-4391,-9579,1407,6700,2421,-6685,
                5481,-1732,-8892,-6645,3077,3287,-4149,8701,-4393,-9070,-1777,2237,-3253,
                -506,-4931,-7366,-8132,5406,-6300,-275,-1908,67,3569,1433,-7262,-437,8303,
                4498,-379,3054,-6285,4203,6908,4433,3077,2288,9733,-8067,3007,9725,9669,
                1362,-2561,-4225,5442,-9006,-429,160,-9234,-4444,3586,-5711,-9506,-79,-4418,-4348,-5891};
        System.out.println(solver.findMaxAverage(nums, 3));
    }

    public String reverseWords(String s) {
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

    public int lengthOfLongestSubstring(String s) {
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

    public String minWindow(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m == 1 && m == n) {
            return s.charAt(0) == t.charAt(0) ? t : "";
        }
        if (m < n) {
            return "";
        }

        Map<Character, Integer> charPool = new HashMap<>();
        for (char c : t.toCharArray()) {
            charPool.put(c, charPool.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int required = charPool.size();
        int formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();
        int[] result = {-1, 0, 0}; // length, left, right

        while (right < s.length()) {
            char cur = s.charAt(right);
            windowCounts.put(cur, windowCounts.getOrDefault(cur, 0) + 1);

            if (charPool.containsKey(cur) && windowCounts.get(cur).intValue() == charPool.get(cur).intValue()) {
                formed++;
            }

            while (left <= right && formed == required) {
                cur = s.charAt(left);
                if (result[0] == -1 || right - left + 1 < result[0]) {
                    result[0] = right - left + 1;
                    result[1] = left;
                    result[2] = right;
                }

                windowCounts.put(cur, windowCounts.get(cur) - 1);
                if (charPool.containsKey(cur) && windowCounts.get(cur) < charPool.get(cur)) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }

    private double findMaxAverage(int[] nums, int k) {
        if (nums.length == 1 && k == 1) {
            return (double) nums[0];
        }
        double sum = 0;
        double maxSum = -Double.MAX_VALUE;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt++;
            if (cnt > k) {
                cnt--;
                sum = sum - nums[i-k];
            }
            if (cnt <= k) {
                sum = sum + nums[i];
            }
            if (cnt == k) {
                if (maxSum < sum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum / k;
    }
}
