/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warpsample.ui;

import com.shephertz.app42.gaming.multiplayer.client.Constants;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpContentTypeCode;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpMessageTypeCode;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpRequestTypeCode;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.message.WarpRequestMessage;
import com.shephertz.app42.gaming.multiplayer.client.util.Util;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author dhruv chopra
 * @version 0.1
 * @date Jul 22, 2012
 */
public class ConnectionListener implements ConnectionRequestListener{

    WarpSampleUI container;
    Timer recoverTimer;
    int NumberOfRecoverAttemtps=0;
    RecoverConnectionTask connectionTask;
    ConnectionListener(WarpSampleUI owner) {
        this.container = owner;
    }
    
    @Override
    public void onConnectDone(ConnectEvent event) {
     String message="";
       switch(event.getResult())
       {
           case WarpResponseResultCode.AUTH_ERROR:
               message="Auth Error";
               break;
           case WarpResponseResultCode.BAD_REQUEST:
                message="Bad Request";
               break;
           case WarpResponseResultCode.CONNECTION_ERROR:
                message="Connection Error";
               break;
           case WarpResponseResultCode.CONNECTION_ERROR_RECOVERABLE:
                message="Connection Error Recoverable";
                RecoverConnection();
                break;
           case WarpResponseResultCode.SUCCESS:
                message="Connection Success";
                break;
           case WarpResponseResultCode.SUCCESS_RECOVERED:
                message="Success Recovered";
               recoverTimer.cancel();
               recoverTimer=null;
               break;
           default:break;
       
       }
         container.appendResponseResult("onConnectDone "+message);  
    }
    
    private void RecoverConnection()
    {
       if(recoverTimer==null)
       {
         recoverTimer = new Timer();
         connectionTask = new ConnectionListener.RecoverConnectionTask(this);
         recoverTimer.schedule(connectionTask,6000); 
       }
    }
    
 
    @Override
    public void onInitUDPDone(byte b)
    {
      container.appendResponseResult("Init Udp" +b);   
    }

    @Override
    public void onDisconnectDone(ConnectEvent event) {
        container.appendResponseResult("onDisconnectDone "+event.getResult());  
    }
    
    private class RecoverConnectionTask extends TimerTask {

        ConnectionListener owner;
        RecoverConnectionTask(ConnectionListener conn) {
            owner = conn;
            owner.NumberOfRecoverAttemtps=0;
        }

        @Override
        public void run() {
            if (owner.NumberOfRecoverAttemtps > 10) {
                 owner.NumberOfRecoverAttemtps=0;
                 owner.recoverTimer.cancel();
                 owner.recoverTimer=null;
                return;
            }
            try {
                owner.NumberOfRecoverAttemtps++;
                WarpClient.getInstance().RecoverConnection();
            } catch (Exception e) {
            }
        }
    }
}
