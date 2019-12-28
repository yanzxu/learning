package java;

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
            throws IllegalBlockSizeException, BadPaddingException, IOException {
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(toWrite);
        fos.flush();
        fos.close();
    }

    public String encryptText(String msg, PublicKey key)
            throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        System.out.println("=========" + new String(cipher.doFinal(msg.getBytes("UTF-8"))));
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
        RsaTest ac = new RsaTest();
        PrivateKey privateKey = ac.getPrivate(RSAConstants.PRIVATE_KEY);
        PublicKey publicKey = ac.getPublic(RSAConstants.PUBLIC_KEY);

        String firstSegment = "xxxxx";
        String secondSegment = "xxx";

        String encrypted_first = ac.encryptText(firstSegment, publicKey);
        String encrypted_second = ac.encryptText(secondSegment, publicKey);

        String decrypted_first = ac.decryptText(encrypted_first, privateKey);
        String decrypted_second = ac.decryptText(encrypted_second, privateKey);

        System.out.println(firstSegment + secondSegment);
        System.out.println();
        System.out.println("decrypted_first: " + decrypted_first);
        System.out.println("decrypted_second: " + decrypted_second);
        System.out.println();
        System.out.println("encrypted_first: " + encrypted_first);
        System.out.println("encrypted_second: " + encrypted_second);
    }
}
