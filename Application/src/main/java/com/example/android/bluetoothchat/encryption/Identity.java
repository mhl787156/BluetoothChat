package com.example.android.bluetoothchat.encryption;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Callum on 07/02/2015.
 */
public class Identity {

    protected PublicKey publicKey;

    public Identity(PublicKey publickey){
        this.publicKey = publickey;
    }

    public Identity(String publicString){
        try {
            this.publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(publicString , Base64.DEFAULT)));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public PublicKey getPublicKey(){
        return publicKey;
    }



}
