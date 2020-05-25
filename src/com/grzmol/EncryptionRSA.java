package com.grzmol;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class EncryptionRSA {
    private static EncryptionKey PRIVATE_KEY;
    private static EncryptionKey PUBLIC_KEY;
    private static List<BigInteger> encryptedCharsList = new ArrayList<>();

    /**
     * Metoda służy ta do szyfrowania tekstu
     */
    public static String encrypt(String message){
        generateEncryptionKeys();
        StringBuilder encodedString = new StringBuilder();

        //do operacji na zakodowanych znakach ASCII uzylem klasy BigInteger tak aby nie pojawialy sie problemy z używaniem zbyt małego typu
        BigInteger currentChar;

        //klasa BigInteger w konstruktorze pobiera wartość typu string, na którym później możemy operować wbudowanymi metodami tej klasy
        //tudzież operatorami arytmetycznymi
        BigInteger exponent = new BigInteger(String.valueOf(PUBLIC_KEY.getNumA()));
        BigInteger modValue = new BigInteger(String.valueOf(PUBLIC_KEY.getNumB()));

        if(!message.isEmpty()){
            for(int i = 0; i < message.length(); i++){
                currentChar = new BigInteger(String.valueOf((int) message.charAt(i)));
                encryptedCharsList.add(currentChar.modPow(exponent, modValue));
            }
        }

        for(BigInteger i : encryptedCharsList){
            //metoda append słuzy do konkatenacji w obrębie klasy StringBuilder (zapobiega to problemom z pamięcią przy konkatenacji w pętli)
            encodedString.append(i).append("/");
        }

        //zwracam wynik zakodowany w base64, dla bardziej 'eleganckiego' wyglądu
        return new String(Base64.getEncoder().encode(encodedString.toString().getBytes()));
    }

    public static void generateEncryptionKeys(){
        PrimeNumberGenerator primeNumberGenerator = new PrimeNumberGenerator();

        //inicjalizuje wartosci zmiennych 'p' i 'q' losowymi liczbami pierwszymi
        int p = primeNumberGenerator.getRandomPrimeNumber();
        int q = primeNumberGenerator.getRandomPrimeNumber();

        int fi = Utils.getFi(p, q);
        int n = p*q;


        //pozbywam sie liczb p i q;
        p = q = 0;

        //teraz znajduje liczbę względnie pierwszą z liczbą fi
        int e = Utils.getRelativelyPrime(fi);

        //wyznaczam odwrotne modulo czyli e^(-1) mod fi
        int d = Utils.reverseModulo(e, fi);

        //ustawiam klucz publiczny
        setPublicKey(new EncryptionKey(e, n));

        //ustawiam klucz prywatny
        setPrivateKey(new EncryptionKey(d, n));

        //wyswietlam uzytkownikowi wartosci obu kluczy
        System.out.println("Klucz prywatny: ("+PRIVATE_KEY.getNumA() + ", " + PRIVATE_KEY.getNumB() + ")");
        System.out.println("Klucz publiczny: ("+PUBLIC_KEY.getNumA() + ", " + PUBLIC_KEY.getNumB() + ")");

    }

    public static void setPrivateKey(EncryptionKey privateKey) {
        PRIVATE_KEY = privateKey;
    }

    public static void setPublicKey(EncryptionKey publicKey) {
        PUBLIC_KEY = publicKey;
    }
}
