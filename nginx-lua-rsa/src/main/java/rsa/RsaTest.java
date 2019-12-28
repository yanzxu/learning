package rsa;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaTest {
    private Cipher cipher;

    public RsaTest() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("RSA");
    }

    public void encryptFile(byte[] input, File output, PrivateKey key)
            throws IOException, GeneralSecurityException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

    public void decryptFile(byte[] input, File output, PublicKey key)
            throws IOException, GeneralSecurityException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

    private void writeToFile(File output, byte[] toWrite)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(toWrite);
        fos.flush();
        fos.close();
    }

    public PrivateKey getPrivate(String filename) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        byte[] decodedKey = Base64.decodeBase64(filename);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public PublicKey getPublic(String filename) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        byte[] decodedKey = Base64.decodeBase64(filename);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public String encryptText(String msg, PublicKey key)
            throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
    }

    public String decryptText(String msg, PrivateKey key)
            throws InvalidKeyException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
    }

    public byte[] getFileInBytes(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        byte[] fbytes = new byte[(int) f.length()];
        fis.read(fbytes);
        fis.close();
        return fbytes;
    }

    public String getBase64Data(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    public static void main(String[] args) throws Exception {
        RsaTest rsaTest = new RsaTest();
        PrivateKey privateKey = rsaTest.getPrivate(RSAConstants.PRIVATE_KEY);
        PublicKey publicKey = rsaTest.getPublic(RSAConstants.PUBLIC_KEY);

        // the path you wang to encrypt
        String path = "spring-learning/tree/master/nginx-rsa";
        // encrypt path by public key
        String encrypted_path = rsaTest.encryptText(path, publicKey);
        // decrypt path by public key
        String decrypted_path = rsaTest.decryptText(encrypted_path, privateKey);

        System.out.println("encrypted_path: " + encrypted_path);
        System.out.println("decrypted_path: " + decrypted_path);
    }
}
