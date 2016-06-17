package com.messanger.test.view;

import java.sql.SQLException;

import javafx.application.Platform;

public class Messanger_MessageDialogHandle extends Thread{

	Messanger_MessageDialogController controller;
	
	public Messanger_MessageDialogHandle(Messanger_MessageDialogController controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {
		while(true){
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						controller.updateContentList();
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
