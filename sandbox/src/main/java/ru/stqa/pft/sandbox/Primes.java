package ru.stqa.pft.sandbox;

public class Primes {

    //проверим, что число простое, то есть оно делится только на себя и на единицу
    public static boolean isPrime(int n){
        for (int i = 2; i < n; i++) {
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    //there is no conflict between function, because they have different type of variables
    public static boolean isPrime(long n){
        for (int i = 2; i < n; i++) {
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeWhile(int n){
        int i = 2;
        while (i < n && n % i != 0) {
            i++;
        }
        return n == i;
    }

    //make test faster
    public static boolean isPrimeFast(int n){
        int m = (int) Math.sqrt(n);
        for (int i = 2; i < m; i++) {
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
}
