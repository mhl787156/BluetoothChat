package com.example.android.bluetoothchat;

import android.util.Base64;

import com.example.android.bluetoothchat.encryption.Encrytion;
import com.example.android.bluetoothchat.encryption.PeerIdentity;
import com.example.android.bluetoothchat.encryption.UserIdentity;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Callum on 07/02/2015.
 */
public class Interpretor {

    private Map<String , UserIdentity> userIDS = new HashMap<>();
    private Map<String , PeerIdentity> peerIDS = new HashMap<>();

    public Interpretor(){
        //Open both maps from file
    }

    public void addUser(String name, UserIdentity userIdentity){
        userIDS.put(name,userIdentity);
    }

    public void addPeer(String name, PeerIdentity peerIdentity){
        peerIDS.put(name,peerIdentity);
    }

    public boolean isUser(String name){
        return userIDS.containsKey(name);
    }

    public boolean isPeer(String name){
        return peerIDS.containsKey(name);
    }

    public String createSendString(String name, String target, String message){

        PublicKey pp = peerIDS.get(target).getPublicKey();
        PrivateKey pk = userIDS.get(target).getPrivateKey();

        String p = Base64.encodeToString(pp.getEncoded(),Base64.DEFAULT);
        String crMessage = Base64.encodeToString(Encrytion.encryptMessage(message.getBytes(), pp), Base64.DEFAULT);
        String signedMessage = Base64.encodeToString(Encrytion.signMessage(crMessage.getBytes() , pk), Base64.DEFAULT);

        if(target.toLowerCase().equals("all")) {
            return Command.MSG_BROADCAST +  "," + p + "," + name + "," + crMessage + "," + signedMessage;
        } else {
            return Command.MSG_PRIVATE + "," + p + "," + name + "," + crMessage + "," + signedMessage;
        }
    }


}
