package com.java8.algorithm.rsa;

import java.security.KeyPair;

public class RSATest {
    public static void main(String[] args) {
        String password = "password";
        KeyPair keyPair = RSAKeyPair.keyPairRSA();
        System.out.println("=== start encrypt ===");
        System.out.println(" Original: " + password);

        String encrypt = RSAEncryptDecrypt.encrypt(password, keyPair.getPrivate());
        System.out.println("encryptedData: " + encrypt + "  privateKey:  " + keyPair.getPrivate());
        System.out.println("=== encrypt end ===");

        System.out.println();

        System.out.println("=== decrypt start");
        String decryptData = RSAEncryptDecrypt.decrypt(encrypt, keyPair.getPublic());
        System.out.println("decrypted data: " + decryptData + "  publicKey:  " + keyPair.getPublic());
        System.out.println("=== decrypt end");
    }
}
