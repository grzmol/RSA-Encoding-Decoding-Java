package com.grzmol;

public class Main {

    public static void main(String[] args) {

        System.out.println("Zakodowana wiadomość: " + EncryptionRSA.encrypt("testowa wiadomosc 123"));

        EncryptionKey ek = new EncryptionKey(3481037, 4356497);
        System.out.println("Odkodowana wiadomość: " + DecryptionRSA.decrypt("NzQ0NTM5LzIyMjk3MzcvMzk4MTcyMy83NDQ1MzkvNDAwNzY1Mi8zMDAyNTMwLzY4NDY3MC8zMDU4OTUzLzMwMDI1MzAvMjYzNTkxMi82ODQ2NzAvMTgzOTM4NS80MDA3NjUyLzM0NDg2NDIvNDAwNzY1Mi8zOTgxNzIzLzQwMjQwNDUvMzA1ODk1My8zNjU5NDQxLzMxODg3MTMvODYxOTg4Lw==", ek));
    }
}
