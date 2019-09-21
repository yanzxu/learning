package com.algorithm.rsa;

import java.security.KeyPair;

public class RSATest {
    public static void main(String[] args) {
        KeyPair keyPair = RSAKeyPair.keyPairRSA();

        System.out.println("====== public key ====");
        System.out.println(keyPair.getPublic().toString());
        System.out.println();

        System.out.println("====== private key ====" + keyPair.getPrivate().toString());
        System.out.println(keyPair.getPublic().toString());

    }
}
