/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warpsample.ui;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author dhruv chopra
 * @version 0.1
 * @date Jul 22, 2012
 */
public class ConnectionListener implements ConnectionRequestListener{

    WarpSampleUI container;
    
    ConnectionListener(WarpSampleUI owner) {
        this.container = owner;
    }
    
    @Override
    public void onConnectDone(ConnectEvent event) {
        if(event.getResult() == WarpResponseResultCode.SUCCESS){
            container.appendResult("connection success");               
        }
        else{
            container.appendErrorNotificationResult("connection error");        
        }
    }

    @Override
    public void onJoinZoneDone(ConnectEvent event) {
        if(event.getResult() == WarpResponseResultCode.SUCCESS){
            container.appendResult("authentication success"); 
        }
        else{
            container.appendErrorNotificationResult("authentication error");        
        }     
    }

    @Override
    public void onDisconnectDone(ConnectEvent event) {
        if(event.getResult() == WarpResponseResultCode.SUCCESS){
            container.appendResult("Disconnect Done"); 
        }
        else{
            container.appendErrorNotificationResult(" Can not disconnect");        
        } 
    }
    
}
