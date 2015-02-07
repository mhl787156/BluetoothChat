package com.example.android.bluetoothchat;

import com.example.android.bluetoothchat.encryption.PeerIdentity;
import com.example.android.bluetoothchat.encryption.UserIdentity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Callum on 07/02/2015.
 */
public class Interpretor {

    private Map<String, UserIdentity> userIDS = new HashMap<>();
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

}
