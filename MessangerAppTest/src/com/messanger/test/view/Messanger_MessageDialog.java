package com.messanger.test.view;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import com.messanger.test.Messanger_Main;
import com.messanger.test.model.Messanger_User;

public class Messanger_MessageDialog {
	
	public static Messanger_MessageDialogHandle handle;
	
	public void messageDialog(Messanger_User messangerFriends, int selectedIndex) throws IOException, ClassNotFoundException, SQLException, InterruptedException  {
		
		Messanger_Panel.messangerList.getTestStage().get(Messanger_FriendsList.selectedIndex).setTitle(messangerFriends.getPersonName().getText());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Messanger_Main.class.getResource("view/Messanger_MessageDialog.fxml"));
		AnchorPane dialogPane = (AnchorPane) loader.load();
		
		Scene scene = new Scene(dialogPane);
		scene.setFill(Color.TRANSPARENT);
		
		Messanger_Panel.messangerList.getTestStage().get(selectedIndex).setScene(scene);
		
		Messanger_MessageDialogController controller = loader.getController();

		handle = new Messanger_MessageDialogHandle(controller);
		
		controller.getPersonName().setText(messangerFriends.getPersonName().getText());
		controller.getStateMessage().setText(messangerFriends.getStateMessage().getText());
		controller.initContentList();
		controller.handleSelectedIndex(Messanger_Panel.messangerList.getTestStage().get(selectedIndex),selectedIndex);
		controller.handleMessage(Messanger_Panel.messangerList.getTestStage().get(selectedIndex));
		controller.handleExit(Messanger_Panel.messangerList.getTestStage().get(selectedIndex));
		controller.handleSelectStage(Messanger_Panel.messangerList.getTestStage().get(selectedIndex));
		controller.disableSelection();
		
		Messanger_Panel.messangerList.getTestStage().get(selectedIndex).show();
		Messanger_Panel.messangerList.getTestStage().get(selectedIndex).toFront();
		
		if(Messanger_Panel.messangerList.getTestStage().get(selectedIndex).isShowing()){
			Messanger_LoginController.pool.addHandle(selectedIndex, handle);
		}
	}
}
