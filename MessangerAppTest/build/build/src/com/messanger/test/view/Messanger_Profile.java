package com.messanger.test.view;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.messanger.test.Messanger_Main;
import com.messanger.test.model.Messanger_User;

public class Messanger_Profile {
	
	public void profileDialog(Messanger_User messangerFriends) throws IOException{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Messanger_Main.class.getResource("view/Messanger_Profile.fxml"));
		AnchorPane profilePane = (AnchorPane) loader.load();
		
		Messanger_Panel.messangerList.setProfileStage(new Stage());
		Messanger_Panel.messangerList.getProfileStage().initOwner(Messanger_Panel.messangerList.getPrimaryStage());
		Messanger_Panel.messangerList.getProfileStage().initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(profilePane);
		scene.setFill(Color.TRANSPARENT);
		Messanger_Panel.messangerList.getProfileStage().setScene(scene);
		
		Messanger_ProfileController controller = loader.getController();
		
		controller.getPersonName().setText(messangerFriends.getPersonName().getText());
		controller.getStateMessage().setText(messangerFriends.getStateMessage().getText());
		
		Messanger_Panel.messangerList.getProfileStage().show();
		
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getScreenX() > Messanger_Panel.messangerList.getProfileStage().getX()){
					Messanger_Panel.messangerList.getProfileStage().close();
			    	Messanger_Panel.messangerList.setProfileStage(null);
				}
			}
		});
	}
}
