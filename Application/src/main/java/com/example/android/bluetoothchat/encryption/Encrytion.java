package com.example.android.bluetoothchat.encryption;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

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

    public static String encryptMessage(String plainText , String publicKey){
        //TODO
        return null;

    }

    public static String decryptMessage(String cipherText , String key){
        //TODO
        return null;
    }


}
