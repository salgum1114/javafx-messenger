package com.messanger.test.model;

import java.util.ArrayList;

import com.messanger.test.view.Messanger_FriendsList;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Messanger_PanelModel {
	private AnchorPane friendsList;
	private AnchorPane messageList;
	private AnchorPane addinfoList;
	private AnchorPane anchorPane;
	private AnchorPane loginPane;
	private AnchorPane confirmPane;
	
	private Stage confirmStage;
	private Stage loginStage;
	private Stage primaryStage;
	private Stage messageStage;
	private Stage profileStage;
	private ArrayList<Stage> testStage;
	
	public Messanger_PanelModel(){
		anchorPane = new AnchorPane();
		loginPane = new AnchorPane();
		testStage = new ArrayList<Stage>();
		
	}
	
	
	public void initTestStage(){
		for(int i = 0; i < Messanger_FriendsList.list.size(); i++){
			testStage.add(i, new Stage());
			testStage.get(i).initStyle(StageStyle.TRANSPARENT);
		}
	}
	
	public Stage getConfirmStage() {
		return confirmStage;
	}


	public void setConfirmStage(Stage confirmStage) {
		this.confirmStage = confirmStage;
	}


	public AnchorPane getConfirmPane() {
		return confirmPane;
	}


	public void setConfirmPane(AnchorPane confirmPane) {
		this.confirmPane = confirmPane;
	}


	public Stage getLoginStage() {
		return loginStage;
	}


	public void setLoginStage(Stage loginStage) {
		this.loginStage = loginStage;
	}


	public ArrayList<Stage> getTestStage() {
		return testStage;
	}

	public void setTestStage(ArrayList<Stage> testStage) {
		this.testStage = testStage;
	}

	public Stage getProfileStage() {
		return profileStage;
	}

	public void setProfileStage(Stage profileStage) {
		this.profileStage = profileStage;
	}

	public Stage getMessageStage() {
		return messageStage;
	}

	public void setMessageStage(Stage messageStage) {
		this.messageStage = messageStage;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}
	public void setAnchorPane(AnchorPane anchorPane) {
		this.anchorPane = anchorPane;
	}
	public AnchorPane getFriendsList() {
		return friendsList;
	}
	public void setFriendsList(AnchorPane friendsList) {
		this.friendsList = friendsList;
	}
	public AnchorPane getMessageList() {
		return messageList;
	}
	public void setMessageList(AnchorPane messageList) {
		this.messageList = messageList;
	}
	public AnchorPane getAddinfoList() {
		return addinfoList;
	}
	public void setAddinfoList(AnchorPane addinfoList) {
		this.addinfoList = addinfoList;
	}
	public AnchorPane getLoginPane() {
		return loginPane;
	}
	public void setLoginPane(AnchorPane loginPane) {
		this.loginPane = loginPane;
	}
	
}
