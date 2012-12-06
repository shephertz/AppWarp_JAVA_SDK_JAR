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
        
    }

    @Override
    public void onLeaveLobbyDone(LobbyEvent le) {
        
    }

    @Override
    public void onSubscribeLobbyDone(LobbyEvent le) {
        
    }

    @Override
    public void onUnSubscribeLobbyDone(LobbyEvent le) {
        
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
