/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warpsample.ui;

import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;
import java.util.HashMap;

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
    public void onUserResumed(String str1,boolean b1,String str2)
    {
     container.appendResult("User Resumed "+str1+" "+str2);
    }
    
    @Override
    public void onUserPaused(String str1,boolean b1,String str2)
    {
     container.appendResult("User Paused "+str1+" "+str2);
    }
    
    @Override
    public void onGameStopped(String str1,String str2)
    {
     container.appendResult("Game Stopped "+str1+" "+str2);
    }
     
    @Override
    public void onGameStarted(String str1,String str2,String str3)
    {
      container.appendResult("Game Started "+str1+" "+str2+" "+str3);
    }
    
    @Override
    public void onMoveCompleted(MoveEvent event)
    {
     container.appendResult("Move Completed "+event.getMoveData().toString()+" "+event.getSender());
    }
    
    @Override 
    public void onUserChangeRoomProperty(RoomData rd, String str, HashMap<String, Object> hm, HashMap<String, String> hm1)
    {
      container.appendResult("Change Property "+rd.getName()+" "+str);
    }
    
    @Override
    public void onPrivateUpdateReceived(String str, byte[] bytes, boolean bln)
    {
      container.appendResult("onPrivateUpdate "+str+" "+bytes.toString());
    }
    
    @Override
    public void onPrivateChatReceived(String str1, String str2)
    {
       container.appendResult("onPrivateChat room "+str1+" chat"+str2);
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
        container.appendResult(event.getSender()+" says "+event.getMessage()+ " in "+location);
    }

    @Override
    public void onUpdatePeersReceived(UpdateEvent event) {
        container.appendResult("update received " +event.getUpdate());
    }
    
}
