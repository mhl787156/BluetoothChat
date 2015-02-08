package com.example.android.bluetoothchat;

import android.util.Base64;

import com.example.android.bluetoothchat.encryption.Encrytion;
import com.example.android.bluetoothchat.encryption.PeerIdentity;
import com.example.android.bluetoothchat.encryption.UserIdentity;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Callum on 07/02/2015.
 */
public class Interpretor {

    public  Map<String , UserIdentity> userIDS = new HashMap<>();
    public  Map<String , PeerIdentity> peerIDS = new HashMap<>();

    private final String escChar = Character.toString('\u001F');


    public Interpretor(){
        //Open both maps from file

        //create default hardcode private key
        addUser("bob",UserIdentity.createUser("bob"));
    }


    public void transmitPublicKey(){

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

        PrivateKey pk = userIDS.get(target).getPrivateKey();


        if(target.toLowerCase().equals("all")) { //If Broadcast message
            String signedMessage = Base64.encodeToString(Encrytion.signMessage(message.getBytes() , pk), Base64.DEFAULT);

            return Command.MSG_BROADCAST +  escChar + " " + escChar + " " + escChar + message + escChar + signedMessage;

        } else { // If privateMessage

            PublicKey pp = peerIDS.get(target).getPublicKey();
            String p = Base64.encodeToString(pp.getEncoded(),Base64.DEFAULT);
            String crMessage = Base64.encodeToString(Encrytion.encryptMessage(message.getBytes(), pp), Base64.DEFAULT);
            String signedMessage = Base64.encodeToString(Encrytion.signMessage(crMessage.getBytes() , pk), Base64.DEFAULT);
            return Command.MSG_PRIVATE + escChar + p + escChar + name + escChar + crMessage + escChar + signedMessage;
        }
    }

    public String decodeReceiveString(String encodedMessage){ // Return null iff PEER_NEW or IF Everything breaks
        String[] splitEncMessage = encodedMessage.split(escChar);

        if(splitEncMessage[0].equals("PEER_NEW")) {
            String name = splitEncMessage[1];
            String publicKey = splitEncMessage[2];
            String mac = splitEncMessage[3];

            PublicKey publicKey1 = null;

            try {
                publicKey1 = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(publicKey , Base64.DEFAULT)));
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            addPeer(name , new PeerIdentity(mac,publicKey1));

            return null;
        }
        else if(splitEncMessage[0].equals("REQUEST_PEERS")){
            //need sendPeers function.

            return null;
        }

        String message = splitEncMessage[3];
        String signature = splitEncMessage[4];
        String pk  = splitEncMessage[1];
        String name = splitEncMessage[2];

        PublicKey puk = null;
        //Assuming we have everybodies publikey
        try {
            puk = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(pk , Base64.DEFAULT)));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        if(Encrytion.verifySignature(message.getBytes(), Base64.decode(signature, Base64.DEFAULT), puk)) {


            if (splitEncMessage[0].equals("MSG_PRIVATE")) {
                PrivateKey prk = userIDS.get(name).getPrivateKey();

                return new String(Encrytion.decryptMessage(  Base64.decode( message , Base64.DEFAULT ) , prk));
            } else {
                return message;
            }
        }
        else{
            return null;
        }


    }


}
