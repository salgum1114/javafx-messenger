package com.messanger.test.view;

import java.sql.SQLException;

import javafx.application.Platform;

import com.messanger.test.Messanger_Main;

public class Messanger_MessageListHandle extends Thread{
	
	public Messanger_MessageListHandle() {
	}
	
	@Override
	public void run() {
    	while(true){
    		Platform.runLater(new Runnable() {
    			
    			@Override
    			public void run() {
    				try {
    					Messanger_Main.messangerPanel.updateMessageList();
    				} catch (ClassNotFoundException | SQLException e) {
    					e.printStackTrace();
    				}
    			}
    		});
    		try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
}
