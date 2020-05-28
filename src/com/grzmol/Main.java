package com.grzmol;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Boolean run = true;
        Scanner sc = new Scanner(System.in);

        while(run){
            System.out.println("1. Zaszyfruj wiadomosc \n2. Odszyfruj wiadomosc \n3. Zakończ");
            System.out.print("Co chcesz zrobic?: ");
            int operation = sc.nextInt();


            System.out.println("\n-------------------------------------");
            switch(operation){
                case 1:
                    encodeMessage();
                    break;
                case 2:
                    decodeMessage();
                    break;
                default:
                    run = false;
                    break;
            }
        }
    }

    private static void encodeMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Wiadomość do zaszyfrowania: ");
        String messageToEncode = sc.nextLine();
        System.out.println("Zaszyfrowana wiadomość: " + EncryptionRSA.encrypt(messageToEncode) + "\n\n\n");
    }

    private static void decodeMessage(){
        Scanner sc = new Scanner(System.in);
        String key = "";

        while(!key.matches("^\\([0-9]+,[0-9]+\\)$")){
            System.out.print("Wprowadź klucz prywatny w formacie (d,n):");
            key = sc.nextLine();
        }
        String[] keyArray = key.replaceAll("[\\(\\)]+", "").split(",");
        EncryptionKey ek = new EncryptionKey(Integer.parseInt(keyArray[0]), Integer.parseInt(keyArray[1]));

        System.out.print("Wiadomość do odszyfrowania: ");
        String messageToDecode = sc.nextLine();
        System.out.println("Odszyfrowana wiadomość: " + DecryptionRSA.decrypt(messageToDecode, ek) + "\n\n\n");
    }
}
