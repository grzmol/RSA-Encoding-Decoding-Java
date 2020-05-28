package com.grzmol;

/**
 * Klasa ta jest reprezentacją zarówno klucza prywatnego, jak i publicznego
 */
public class EncryptionKey {
    private int numA;
    private int numB;


    public EncryptionKey(int numA, int numB){
        this.numA = numA;
        this.numB = numB;
    }

    public int getNumA() {
        return numA;
    }

    public void setNumA(int numA) {
        this.numA = numA;
    }

    public int getNumB() {
        return numB;
    }

    public void setNumB(int numB) {
        this.numB = numB;
    }
}
