package com.example.android.bluetoothchat.encryption;

import java.security.PublicKey;

/**
 * Created by Callum on 07/02/2015.
 */
public class PeerIdentity extends Identity {

    private String MACAddress;

    public PeerIdentity(String MACAddress ,PublicKey publickey) {
        super(publickey);
        this.MACAddress = MACAddress;
    }

    public String getMACAddress() {
        return MACAddress;
    }
}
