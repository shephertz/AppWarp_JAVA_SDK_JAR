/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warpsample.ui;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DHRUV CHOPRA
 */
public class NotificationListener implements NotifyListener {

    
    private WarpSampleUI container;

    NotificationListener(WarpSampleUI container){
        this.container = container;
    }
    
    @Override
    public void onRoomCreated(RoomData event) {
        container.appendResult("room "+event.getName()+" created with id "+event.getId());
    }

    @Override
    public void onRoomDestroyed(RoomData event) {
        container.appendResult("room "+event.getName()+" destroyed with id "+event.getId());
    }

    @Override
    public void onUserLeftRoom(RoomData event, String username) {
        container.appendResult(username+" left room "+event.getName());
    }

    @Override
    public void onUserJoinedRoom(RoomData event, String username) {
        container.appendResult(username+" joined room "+event.getName());
    }

    @Override
    public void onUserLeftLobby(LobbyData event, String username) {
        container.appendResult(username+" left the lobby");
    }

    @Override
    public void onUserJoinedLobby(LobbyData event, String username) {
        container.appendResult(username+" joined the lobby");
    }

    @Override
    public void onChatReceived(ChatEvent event) {        
        String location = "";
        if(event.isLocationLobby()){
            location = "the lobby";
        }
        else{
            location = "room id"+event.getLocationId();
        }
        container.appendChatResult(event.getSender()+" says "+event.getMessage()+ " in "+location);
    }

    @Override
    public void onUpdatePeersReceived(UpdateEvent event) {
        container.appendChatResult("update received " +event.getUpdate());
    }
    
}
