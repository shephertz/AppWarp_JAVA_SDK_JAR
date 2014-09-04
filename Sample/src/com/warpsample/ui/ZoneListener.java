/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warpsample.ui;

import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;

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
    public void onGetMatchedRoomsDone(MatchedRoomsEvent event)
    {
      container.appendResult("GetMatchedRooms "+event.getRoomsData().toString());
    }
    
    @Override
    public void onCreateRoomDone(RoomEvent event) {
       container.appendResult("onCreateRoom roomname "+event.getData().getName()+" id"+event.getData().getId());
    }
    @Override
    public void onDeleteRoomDone(RoomEvent event) {
      container.appendResult("DeleteRoom "+event.getData().toString());
    }

    @Override
    public void onGetAllRoomsDone(AllRoomsEvent event) {
        for(int i=0; i<event.getRoomIds().length; i++){
             container.appendResult("RoomId "+event.getRoomIds()[i]);
        }        
    }

    @Override
    public void onGetOnlineUsersDone(AllUsersEvent event) {
        
        for(int i=0; i<event.getUserNames().length; i++){
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
