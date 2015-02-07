package com.example.android.bluetoothchat.encryption;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Callum on 07/02/2015.
 */
public class UserIdentity extends Identity {

    private KeyPair keys;
    private PrivateKey privateKey;



    public UserIdentity(KeyPair keys, PrivateKey privateKey, PublicKey publickey) {
        super(publickey);
        this.keys = keys;
        this.privateKey = privateKey;

    }

    public static UserIdentity createUser(String name){

        if(true){
            KeyPair keys = Encrytion.createKeys();
            return new UserIdentity(keys,keys.getPrivate(),keys.getPublic());
        }

        //retrieve user identity from memory;
        //TODO
        return null;

    }

    public static UserIdentity createUser(String name , String publicString , String privateString){
        PrivateKey prk = null;

        try {
            prk  = KeyFactory.getInstance("RSA").generatePrivate(new X509EncodedKeySpec(Base64.decode(privateString, Base64.DEFAULT)));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        PublicKey puk = null;

        try {
            puk = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(publicString , Base64.DEFAULT)));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new UserIdentity(null,prk,puk);
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
