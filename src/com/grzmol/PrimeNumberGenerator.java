package com.grzmol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimeNumberGenerator {
    private int primeRangeStart = 1;
    private int primeRangeEnd = 10000;
    private final List<Integer> primesList = new ArrayList<Integer>();

    private final int PRIME_CHECK_ACCURACY = 10;

    /**
     * Konstruktor klasy PrimeNumberGenerator
     * @param primeRangeStart początek przedziału z którego mają być generowane liczby pierwsze
     * @param primeRangeEnd koniec przedziału z którego mają być generowane liczby pierwsze
     */
    public PrimeNumberGenerator(int primeRangeStart, int primeRangeEnd){
        this.primeRangeStart = primeRangeStart;
        this.primeRangeEnd = primeRangeEnd;
    }

    /**
     * Pusty konstuktor klasy PrimeNumberGenerator pozwalający na generowanie licz pierwszych z domyślnego przedziału 500 - 10000
     */
    public PrimeNumberGenerator() { }


    public int getRandomPrimeNumber(){
        if(primesList.isEmpty()){
            generatePrimeNumbers();
        }
        return primesList.get(new Random().nextInt(primesList.size()));
    }

    private void generatePrimeNumbers(){
        for(int i = primeRangeStart; i <= primeRangeEnd; i++){
            if(isPrime(i, PRIME_CHECK_ACCURACY)){
                primesList.add(i);
            }
        }
    }


    /**
     * Metoda ta stanowi implementację testu Rabina-Millera która służy do weryfikacji czy dana liczba jest pierwsza
     * https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test
     */
    private static boolean testRabinMiller(int d, int n) {

        int a = 2 + (int)(Math.random() % (n - 4));

        int x = Utils.power(a, d, n);

        if (x == 1 || x == n - 1){
            return true;
        }

        /**
         * Kontynuuje podnoszenie do kwadratu jesli zachodzi przynajmniej jedno stwierdzenie z poniższych:
         * (1) d nie osiąga n-1
         * (2) (x^2) % n nie równa się 1
         * (3) (x^2) % n nie równa się n-1
         */
        while (d != n - 1) {
            x = (x * x) % n;
            d *= 2;

            if (x == 1){
                return false;
            }
            if (x == n - 1){
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda zwraca prawdę jeśli liczba jest PRAWDOPODOBNIE pierwsza, zaś fałsz jeśli nie jest
     * @param n liczba której "pierwszość" chcemy zweryfikować
     * @param k ten parametr oznacza dokładność z jaką wykonywane będzie sprawdzanie.
     *       Im większa będzie liczba k, tym dokładniejsze wyniki otrzymamy
     */
    private static boolean isPrime(int n, int k) {

        // pokrycie liczb, które mogą zwrócić niepoprawne wyniki
        if (n <= 1 || n == 4){
            return false;
        }
        if (n <= 3){
            return true;
        }
        int d = n - 1;

        while (d % 2 == 0){
            d /= 2;
        }

        for (int i = 0; i < k; i++){
            if (!testRabinMiller(d, n)){
                return false;
            }
        }
        return true;
    }
}
