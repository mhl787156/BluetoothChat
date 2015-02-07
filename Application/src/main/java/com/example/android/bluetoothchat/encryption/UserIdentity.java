package com.example.android.bluetoothchat.encryption;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

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
    //hellohellohello
    public boolean stuff(){
        return true;
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

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
