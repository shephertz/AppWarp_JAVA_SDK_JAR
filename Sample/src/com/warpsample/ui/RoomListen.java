/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warpsample.ui;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.listener.RoomRequestListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dhruv chopra
 * @version 0.1
 * @date Jul 22, 2012
 */
public class RoomListen implements RoomRequestListener{

    private WarpSampleUI container;
    RoomListen(WarpSampleUI container){
        this.container = container;
    }
    @Override
    public void onSubscribeRoomDone(RoomEvent event) {

    }

    @Override
    public void onUnSubscribeRoomDone(RoomEvent event) {
        
    }

    @Override
    public void onJoinRoomDone(RoomEvent event) {
        if(event.getResult() != WarpResponseResultCode.SUCCESS){
            container.appendErrorNotificationResult("Room join failed!");
        }
        else{
        container.appendResult("Room join Success");
        }
    }

    @Override
    public void onLeaveRoomDone(RoomEvent event) {
        
    }

    @Override
    public void onGetLiveRoomInfoDone(LiveRoomInfoEvent event) {
        if(event.getResult() == WarpResponseResultCode.SUCCESS){
            String[] users = event.getJoinedUsers();
            String result = "";
           container.appendResult("Room Name " +event.getData().getName()+"(Room Id= " +event.getData().getId()+")(Custom Data= " +event.getCustomData() + ") Users are");
            for(int i=0; i<users.length; i++){
                result += " "+users[i];                
            }
            container.appendResult(result);
        }
    }

    @Override
    public void onSetCustomRoomDataDone(LiveRoomInfoEvent event) {
//        System.out.println(event.getJoinedUsers());
//        container.appendResult("Room" +event.getData()+ " is" +event.getCustomData());

    }
}
