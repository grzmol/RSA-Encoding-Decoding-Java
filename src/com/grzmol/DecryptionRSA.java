package com.grzmol;

import java.math.BigInteger;
import java.util.Base64;

public class DecryptionRSA {

    public static String decrypt(String message, EncryptionKey encryptionKey){
        StringBuilder resultString = new StringBuilder();

        //używam wbudowanego dekodera base64 do wstępnego przygotowania otrzymanej wiaomości
        String messageDecodedFromB64 = new String(Base64.getDecoder().decode(message));

        //wiadomosc w tym momencie wyglada mniej-wiecej tak 111/106/83/45/
        //dlatego najpierw rozdzielam ja na pojedyncze zakodowane znaki
        String[] stringToArray = messageDecodedFromB64.split("/");

        //sprawdzam czy tablica jest wieksza od zera, aby uniknac niepotrzebnych dzialan
        if(stringToArray.length > 0){
            BigInteger charToDecrypt;
            BigInteger exponent = new BigInteger(String.valueOf(encryptionKey.getNumA()));
            BigInteger modValue = new BigInteger(String.valueOf(encryptionKey.getNumB()));


            for(String s : stringToArray){
                charToDecrypt = new BigInteger(s);
                resultString.append((char) charToDecrypt.modPow(exponent, modValue).intValue());
            }

        }
        //zwracam odszyfrowaną wiadomość
        return resultString.toString();
    }
}
