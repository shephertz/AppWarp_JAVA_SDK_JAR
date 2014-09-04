/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warpsample.ui;

import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.LobbyRequestListener;

/**
 *
 * @author DHRUB CHOPRA
 */
public class LobbyListener implements LobbyRequestListener {

    private WarpSampleUI container;
    LobbyListener(WarpSampleUI container){
        this.container = container;
    }
    
    @Override
    public void onJoinLobbyDone(LobbyEvent le) {
           container.appendResult("JoinLobby Name "+le.getInfo().getName()+"Id "+le.getInfo().getId()+"RoomOwner "+le.getInfo().getRoomOwner());
    }

    @Override
    public void onLeaveLobbyDone(LobbyEvent le) {
         container.appendResult("LeaveLobby Name "+le.getInfo().getName()+"Id "+le.getInfo().getId()+"RoomOwner "+le.getInfo().getRoomOwner());
    }

    @Override
    public void onSubscribeLobbyDone(LobbyEvent le) {
         container.appendResult("SubscribeLobby Name "+le.getInfo().getName()+"Id "+le.getInfo().getId()+"RoomOwner "+le.getInfo().getRoomOwner());
    }

    @Override
    public void onUnSubscribeLobbyDone(LobbyEvent le) {
         container.appendResult("UnSubscribeLobby Name "+le.getInfo().getName()+"Id "+le.getInfo().getId()+"RoomOwner "+le.getInfo().getRoomOwner());
    }

    @Override
    public void onGetLiveLobbyInfoDone(LiveRoomInfoEvent event) {
        if(event.getResult() == WarpResponseResultCode.SUCCESS){
            String[] users = event.getJoinedUsers();
            String result = "";
            container.appendResult("Users are");
            for(int i=0; i<users.length; i++){
                result += " "+users[i];                
            }
            container.appendResult(result);
        }
    }
    
}
