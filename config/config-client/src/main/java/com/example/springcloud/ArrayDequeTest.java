package com.example.springcloud;

import java.util.*;

public class ArrayDequeTest {

    String[][] memo;

    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(2);
        String s = "abc";
        int hash = 0;
        for(int i = 0; i < s.length(); i++){
            hash = (8 * hash + s.charAt(i)) % 31;
        }
        char first = s.charAt(0);
        char second = s.charAt(1);
        int stringHashCode = s.hashCode();
        Integer num = new Integer(32);
        int intHashCode = num.hashCode();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "pd");
        hashMap.put("name", "senyi");
        Math.max(1,2);
        int[] arr = new int[5];
        int front;
        int rear;
        int abc;
        int[][] dp = new int[2][3];
        System.out.println("gogogo");
        "Q".equals("abc");
        List<Integer> queue = new LinkedList<>();


        String str = "eat";

        Integer hashCode;
    }



}
