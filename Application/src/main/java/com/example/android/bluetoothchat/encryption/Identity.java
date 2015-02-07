package com.example.android.bluetoothchat.encryption;

import java.security.PublicKey;

/**
 * Created by Callum on 07/02/2015.
 */
public class Identity {

    protected PublicKey publicKey;

    public Identity(PublicKey publickey){
        this.publicKey = publickey;
    }

    public PublicKey getPublicKey(){
        return publicKey;
    }


}
