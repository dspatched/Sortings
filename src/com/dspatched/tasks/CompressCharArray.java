package com.dspatched.tasks;

public class CompressCharArray {

    public static void main(String[] args) {
        char[] chars = new char[]{'a','a','a','b','b','a','a'};
        //char[] chars = new char[]{'a'};
        int cnt = compress(chars);
        System.out.println(chars);
        System.out.println(cnt);
    }

    private static int compress(char[] chars) {
        int n = chars.length;
        int writePos = 0;
        int readPos = 0;

        while (readPos < n) {
            char current = chars[readPos];
            int groupEnd = readPos + 1;
            while (groupEnd < n && chars[groupEnd] == current) {
                groupEnd++;
            }
            int count = groupEnd - readPos;
            chars[writePos++] = current;

            if (count > 1) {
                String countStr = "" + count;
                for (int i = 0; i < countStr.length(); i++) {
                    chars[writePos++] = countStr.charAt(i);
                }
            }
            readPos = groupEnd;
        }
        return writePos;
    }
}
