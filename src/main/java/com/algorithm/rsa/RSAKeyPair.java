package com.algorithm.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class RSAKeyPair {
    public static KeyPair keyPairRSA(){
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(RSAConstants.ALGORITHM);
            generator.initialize(RSAConstants.ALGORITHM_BITS);
            KeyPair keyPair = generator.generateKeyPair();
            return keyPair;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
