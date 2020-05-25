package com.grzmol;

public class Utils {
    /**
     * Metoda ta oblicza liczbę fi Eulera
     */
    public static int getFi(int p, int q) {
        return (p - 1) * (q - 1);
    }

    /**
     * Metoda oblicza Najwiekszy Wspolny Dzielnik dwóch liczb
     */
    private static int NWD(int a, int b) {
        int tmp;
        while (b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public static int getRelativelyPrime(int num) {
        int res = 1;
        for(int i = 2; i<=num; i++){
            if(NWD(i, num) == 1){
                res = i;
                break;
            }
        }
        return res;
    }


    public static int reverseModulo(int a, int b) {
        int result = 0;
        a %= b;
        for (int x = 1; x < b; x++) {
            if ((a * x) % b == 1) {
                return x;
            }
        }
        return result;
    }

    /**
     * Metoda ta wykonuje szybkie potęgowanie modulo
     */
    public static int power(int x, int y, int p) {

        int res = 1;
        x = x % p;

        while (y > 0) {
            // jesli y jest niepatrzysta, pomnoz x razy wynik
            if ((y & 1) == 1){
                res = (res * x) % p;
            }
            y = y >> 1; // y = y/2 - operacja przy wykorzystaniu przesunięcia bitowego
            x = (x * x) % p;
        }

        return res;
    }
}
