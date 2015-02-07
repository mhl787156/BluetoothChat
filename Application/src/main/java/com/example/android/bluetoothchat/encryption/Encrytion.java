package com.example.android.bluetoothchat.encryption;


import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by Callum on 07/02/2015.
 */
public class Encrytion {


    public static KeyPair createKeys(){
        //TODO
        KeyPairGenerator gen = null;
        try {

            gen = KeyPairGenerator.getInstance("RSA");

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        }
        gen.initialize(4096);

        return gen.generateKeyPair();
    }

    public static byte[] encryptMessage(byte[] plainText , PublicKey publicKey){
        Cipher c = null;
        try {
            c = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }


        if (c != null) {
            try {
                c.init(Cipher.ENCRYPT_MODE,publicKey);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }

        try {
            return c.doFinal(plainText);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] decryptMessage(byte[] cipherText , PrivateKey privateKey){
        Cipher c = null;
        try {
            c = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }


        if (c != null) {
            try {
                c.init(Cipher.DECRYPT_MODE,privateKey);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }

        try {
            return c.doFinal(cipherText);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] signMessage(byte[] text , PrivateKey privateKey){
        Signature s = null;
        try {
            s = Signature.getInstance("SHA256withRSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            s.initSign(privateKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            s.update(text);
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        try {
            return s.sign();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] verifySignature(byte[] text , byte[] sig){
        return null;
    }
}
