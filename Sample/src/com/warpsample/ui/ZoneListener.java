/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warpsample.ui;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DHRUV CHOPRA
 */
public class ZoneListener implements ZoneRequestListener {
    
    private WarpSampleUI container;

    ZoneListener(WarpSampleUI container){
        this.container = container;
    }

    @Override
    public void onCreateRoomDone(RoomEvent event) {
        if(event.getResult() != WarpResponseResultCode.SUCCESS){
            container.appendErrorNotificationResult("Room creation failed!");
        }
    }
    @Override
    public void onDeleteRoomDone(RoomEvent event) {
        
    }

    @Override
    public void onGetAllRoomsDone(AllRoomsEvent event) {
        
        for(int i=0; i<event.getRoomIds().length; i++){
            container.getClient().getLiveRoomInfo(event.getRoomIds()[i]);
            
        }        
    }

    @Override
    public void onGetOnlineUsersDone(AllUsersEvent event) {
        
        for(int i=0; i<event.getUserNames().length; i++){
            //String result = event.getUserNames()[i];
            //container.appendResult(result);
            container.getClient().getLiveUserInfo(event.getUserNames()[i]);
        }
    }

    @Override
    public void onGetLiveUserInfoDone(LiveUserInfoEvent event) {        
        if(event.getResult() == WarpResponseResultCode.SUCCESS){
            String location = "";
            if(event.isLocationLobby()){
                location = "the lobby";
            }
            else{
                location = "room id"+event.getLocationId();
            }            
            container.appendResult("User "+event.getName()+" is at " +location);
        }
    }

    @Override
    public void onSetCustomUserDataDone(LiveUserInfoEvent event) {
        
       container.appendResult("User " +event.getName()+ "says " +event.getCustomData()); 

    }
}
