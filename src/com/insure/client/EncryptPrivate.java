package com.insure.client;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class EncryptPrivate {

    private Cipher cipher;


    public EncryptPrivate() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("RSA");
    }

    // https://docs.oracle.com/javase/8/docs/api/java/security/spec/PKCS8EncodedKeySpec.html
    public PrivateKey getPrivate(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public String encryptText(String msg, Key key)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(msg.getBytes("UTF-8")));
    }

    public static void main(String[] args) throws Exception {
        EncryptPrivate ac = new EncryptPrivate();

        System.out.print("insert the path to the private keyfile (ex. 'keys\\user1PrivateKey') :");
        Scanner path = new Scanner(System.in);
        String keyFile = path.nextLine();
        PrivateKey privateKey = ac.getPrivate(Paths.get("").toAbsolutePath() + System.getProperty("file.separator") + keyFile);
        System.out.print("Message: ");
        Scanner in = new Scanner(System.in);
        String msg = in.nextLine();

        String encrypted_msg = ac.encryptText(msg, privateKey);

        System.out.println("Original Message: " + msg +
                "\nEncrypted Message: " + encrypted_msg);
    }
}
