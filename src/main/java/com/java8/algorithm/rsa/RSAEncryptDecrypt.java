package com.java8.algorithm.rsa;



import javax.crypto.Cipher;
import java.security.Key;
import java.util.Base64;

public class RSAEncryptDecrypt {
    public static String encrypt(String original, Key privateKey) {
        if (original != null && privateKey != null) {
            byte[] encryptedData = convert(original.getBytes(), privateKey, Cipher.ENCRYPT_MODE);
            return new String(Base64.getEncoder().encode(encryptedData));
        }

        return null;
    }

    public static String decrypt(String encryptedData, Key publicKey) {
        if (encryptedData != null && publicKey != null) {
            return new String(convert(Base64.getDecoder().decode(encryptedData), publicKey, Cipher.DECRYPT_MODE));
        }

        return null;
    }

    private static byte[] convert(byte[] data, Key key, int mode) {
        try {
            Cipher cipher = Cipher.getInstance(RSAConstants.ALGORITHM);
            cipher.init(mode, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
