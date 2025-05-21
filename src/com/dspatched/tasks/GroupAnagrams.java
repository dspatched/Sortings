package com.dspatched.tasks;

import java.util.*;

public class GroupAnagrams {

    public static void main(String[] args) {
        var result = groupAnagrams(new String[]{"bad", "abd", "acd", "dac"});
        System.out.println(result.size());
        for (List<String> list : result) {
            list.forEach(word -> System.out.print(word + " "));
            System.out.println();
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] cur = strs[i].toCharArray();
            Arrays.sort(cur);
            String s = new String(cur);

            if (!map.containsKey(s)) {
                map.put(s, new ArrayList<>(List.of(strs[i])));
            } else {
                var list = map.get(s);
                list.add(strs[i]);
                map.put(s, list);
            }
        }

        return new ArrayList<>(map.values());
    }

}
