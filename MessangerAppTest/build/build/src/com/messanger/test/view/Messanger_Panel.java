package com.messanger.test.view;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.messanger.test.Messanger_Main;
import com.messanger.test.model.Messanger_PanelModel;

public class Messanger_Panel{
	
	private TrayIcon trayIcon;
	
	public static Messanger_PanelModel messangerList = new Messanger_PanelModel();
	
	public void loginStage(Stage loginStage) throws IOException{
		
		messangerList.setLoginStage(loginStage);
		
		Platform.setImplicitExit(false);
		
		messangerList.getLoginStage().setTitle("NKIA Messanger");
		messangerList.getLoginStage().getIcons().add(new Image("file:./resources/icon/messanger_nkiaIcon.png"));
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Messanger_Main.class.getResource("view/Messanger_Login.fxml"));
		
		messangerList.getLoginStage().initStyle(StageStyle.TRANSPARENT);
		
		Messanger_Panel.messangerList.setLoginPane((AnchorPane)loader.load());
		Scene scene = new Scene(Messanger_Panel.messangerList.getLoginPane());
		scene.setFill(Color.TRANSPARENT);
		messangerList.getLoginStage().setScene(scene);
		messangerList.getLoginStage().show();
		
		Messanger_LoginController controller = loader.getController();
		controller.handleLogin(loginStage);
	}
	
	public void confirmStage() throws IOException{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Messanger_Main.class.getResource("view/Messanger_ConfirmDialog.fxml"));
		Messanger_Panel.messangerList.setConfirmPane((AnchorPane) loader.load());
		
		Messanger_Panel.messangerList.setConfirmStage(new Stage());
		Messanger_Panel.messangerList.getConfirmStage().initStyle(StageStyle.TRANSPARENT);
		Messanger_Panel.messangerList.getConfirmStage().initOwner(messangerList.getLoginStage());
		Messanger_Panel.messangerList.getConfirmStage().initModality(Modality.APPLICATION_MODAL);
		
		Scene scene = new Scene(Messanger_Panel.messangerList.getConfirmPane());
		scene.setFill(Color.TRANSPARENT);
		Messanger_Panel.messangerList.getConfirmStage().setScene(scene);
		Messanger_Panel.messangerList.getConfirmStage().show();
	}
	
	public void mainStage(Stage primaryStage) throws IOException{
		
		messangerList.setPrimaryStage(primaryStage);
		
		Platform.setImplicitExit(false);
		
		messangerList.getPrimaryStage().setTitle("NKIA Messanger");
		messangerList.getPrimaryStage().getIcons().add(new Image("file:resources/icon/messanger_nkiaIcon.png"));
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Messanger_Main.class.getResource("view/Messanger_Root.fxml"));
		
//		messangerList.getPrimaryStage().initStyle(StageStyle.TRANSPARENT);
		
		Messanger_Panel.messangerList.setAnchorPane((AnchorPane)loader.load());
		
		Scene scene = new Scene(Messanger_Panel.messangerList.getAnchorPane());
		scene.setFill(Color.TRANSPARENT);
		messangerList.getPrimaryStage().setScene(scene);
		
		messangerList.getPrimaryStage().show();
		
//		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				if(event.getScreenX() != Messanger_Panel.messangerList.getProfileStage().getX()){
//					Messanger_Panel.messangerList.getProfileStage().close();
//			    	Messanger_Panel.messangerList.setProfileStage(null);
//				}
//			}
//		});
	}
	
	public void systemTrayMain(){
    	if(SystemTray.isSupported()){
    		SystemTray tray = SystemTray.getSystemTray();
    		
    		Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					java.awt.Image trayImage = Toolkit.getDefaultToolkit().getImage("resources/icon/messanger_nkiaTrayIcon.png");
					
					final ActionListener closeListener = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Platform.exit();
							System.exit(0);
							tray.remove(trayIcon);
						}
					};
					
					ActionListener showListener = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Platform.runLater(new Runnable() {
								
								@Override
								public void run() {
									messangerList.getPrimaryStage().show();
									messangerList.getPrimaryStage().toFront();
								}
							});
						}
					};
					
					PopupMenu popup = new PopupMenu();
					MenuItem showItem = new MenuItem("Show");
					showItem.addActionListener(showListener);
					popup.add(showItem);
					
					MenuItem closeItem = new MenuItem("Close");
		            closeItem.addActionListener(closeListener);
		            popup.add(closeItem);

		            // construct a TrayIcon
		            trayIcon = new TrayIcon(trayImage, "NKIA", popup);
		            
		            try {
						tray.add(trayIcon);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
		            
		            trayIcon.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Platform.runLater(new Runnable() {
								
								@Override
								public void run() {
									messangerList.getPrimaryStage().show();
									messangerList.getPrimaryStage().toFront();
								}
							});
						}
					});
				}
			});
    		tray.remove(trayIcon);
    	}
    }

	FXMLLoader friendsLoader = new FXMLLoader();
	
	@SuppressWarnings("static-access")
	public void initFriendsList() throws IOException{
		friendsLoader.setLocation(Messanger_Main.class.getResource("view/Messanger_FriendsList.fxml"));
		
		messangerList.setFriendsList((AnchorPane) friendsLoader.load());	
		messangerList.getAnchorPane().setTopAnchor(messangerList.getFriendsList(), messangerList.getAnchorPane().getChildren().get(1).getLayoutY()+50);
		messangerList.getAnchorPane().setBottomAnchor(messangerList.getFriendsList(), 44.0);
		messangerList.getAnchorPane().setLeftAnchor(messangerList.getFriendsList(), 0.0);
		messangerList.getAnchorPane().setRightAnchor(messangerList.getFriendsList(), 0.0);
		messangerList.getAnchorPane().getChildren().add(messangerList.getFriendsList());
		
		Messanger_FriendsList controller = friendsLoader.getController();
		controller.friendsList();
		controller.myProfile();
		controller.handleList();
		messangerList.initTestStage();
	}
	
	public void updateFriendsList(){
		Messanger_FriendsList controller = friendsLoader.getController();
		controller.friendsList();
		controller.myProfile();
		controller.handleList();
	}
	
	FXMLLoader messageLoader = new FXMLLoader();
	
	@SuppressWarnings("static-access")
	public void initMessageList() throws IOException, ClassNotFoundException, SQLException{
		messageLoader.setLocation(Messanger_Main.class.getResource("view/Messanger_MessageList.fxml"));
		
		messangerList.setMessageList((AnchorPane) messageLoader.load());
		messangerList.getAnchorPane().setTopAnchor(messangerList.getMessageList(), messangerList.getAnchorPane().getChildren().get(1).getLayoutY()+50);
		messangerList.getAnchorPane().setBottomAnchor(messangerList.getMessageList(), 44.0);
		messangerList.getAnchorPane().setLeftAnchor(messangerList.getMessageList(), 0.0);
		messangerList.getAnchorPane().setRightAnchor(messangerList.getMessageList(), 0.0);
		messangerList.getAnchorPane().getChildren().add(messangerList.getMessageList());
		
		messangerList.getMessageList().setVisible(false);
		Messanger_MessageList controller = messageLoader.getController();
		controller.initMessageList();
		controller.handleList();
	}
	
	public void updateMessageList() throws ClassNotFoundException, SQLException{
		Messanger_MessageList controller = messageLoader.getController();
		controller.updateMessageList();
	}

	FXMLLoader addinfoLoader = new FXMLLoader();
	
	@SuppressWarnings("static-access")
	public void initAddinfoList() throws IOException{
		addinfoLoader.setLocation(Messanger_Main.class.getResource("view/Messanger_AddInfoList.fxml"));
		
		messangerList.setAddinfoList((AnchorPane) addinfoLoader.load());
		messangerList.getAnchorPane().setTopAnchor(messangerList.getAddinfoList(), messangerList.getAnchorPane().getChildren().get(1).getLayoutY()+50);
		messangerList.getAnchorPane().setBottomAnchor(messangerList.getAddinfoList(), 44.0);
		messangerList.getAnchorPane().setLeftAnchor(messangerList.getAddinfoList(), 0.0);
		messangerList.getAnchorPane().setRightAnchor(messangerList.getAddinfoList(), 0.0);
		messangerList.getAnchorPane().getChildren().add(messangerList.getAddinfoList());
		
		messangerList.getAddinfoList().setVisible(false);
		Messanger_AddinfoList controller = addinfoLoader.getController();
		controller.addinfoList();
	}
	
	public void updateAddinfoList(){
		Messanger_AddinfoList controller = addinfoLoader.getController();
		controller.addinfoList();
	}
}
