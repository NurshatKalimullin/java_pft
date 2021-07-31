package ru.stqa.pft.sandbox;

//here you see import of collections from java utils
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "PHP"}; //array of strings

        /* interface is on the left and class for interface is on the right
        * List<String> languages = new ArrayList<String>();
        * languages.add("Java");
        * languages.add("C#");
        *languages.add("Python");
        */

        //following code converts an Array into a List
        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

        //following construction works like 'for (int i = 0; i < langs.length; i++)'
        /* for (String l: languages) {
        *    System.out.println("I want to learn " + l);
        *}
        */

        //different way to work with Lists
        for (int i = 0; i < languages.size(); i++) {
            System.out.println("I want to learn " + languages.get(i));
        }
    }
}
