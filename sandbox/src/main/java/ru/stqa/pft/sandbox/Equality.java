package ru.stqa.pft.sandbox;

import java.util.Arrays;

public class Equality {

    public static void main(String[] args){
        String s1 = "firefox";
        String s2 = new String(s1);

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        int[] numbers1 = {1, 2, 3, 4, 5};
        int[] numbers2 = numbers1;
        int[] numbers3 = Arrays.copyOf(numbers1, numbers1.length);

        numbers1[3] = 1000;

        System.out.println(Arrays.toString(numbers2));    // [1, 2, 3, 1000, 5]

        System.out.println(Arrays.toString(numbers3));
    }
}
