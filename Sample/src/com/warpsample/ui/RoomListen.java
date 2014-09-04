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
    public void onUpdatePropertyDone(LiveRoomInfoEvent roominfo)
    {
      container.appendResponseResult("UpdatePropertyDone "+roominfo.getProperties().size());
    }
     
    @Override
    public void onLockPropertiesDone(byte bt)
    {
      container.appendResponseResult("LockPropertiesDone "+bt);
    }
    
    @Override
    public void onUnlockPropertiesDone(byte bt)
    {
      container.appendResponseResult("UnlockPropertiesDone "+bt);
    }
    
    @Override
    public void onSubscribeRoomDone(RoomEvent event) {
        container.appendResponseResult("SubscribeRoom "+event.getData().getName());
    }

    @Override
    public void onUnSubscribeRoomDone(RoomEvent event) {
          container.appendResponseResult("UnSubscribeRoom "+event.getData().getName());
    }

    @Override
    public void onJoinRoomDone(RoomEvent event) {
         container.appendResponseResult("onJoinRoom "+event.getResult());
    }

    @Override
    public void onLeaveRoomDone(RoomEvent event) {
          container.appendResponseResult("Leave Room "+event.getData().getName());
    }

    @Override
    public void onGetLiveRoomInfoDone(LiveRoomInfoEvent event) {
        if(event.getResult() == WarpResponseResultCode.SUCCESS){
            String[] users = event.getJoinedUsers();
            String result = "";
           container.appendResponseResult("Room Name " +event.getData().getName()+"(Room Id= " +event.getData().getId()+")(Custom Data= " +event.getCustomData() + ") Users are");
            for(int i=0; i<users.length; i++){
                result += " "+users[i];                
            }
            container.appendResponseResult(result);
        }
    }

    @Override
    public void onSetCustomRoomDataDone(LiveRoomInfoEvent event) {
       container.appendResponseResult("CustomRoomData "+event.getData().getName()+" "+event.getCustomData().toString());
    }
}
