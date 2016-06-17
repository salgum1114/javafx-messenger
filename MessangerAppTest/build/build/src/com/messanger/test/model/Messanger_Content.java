package com.messanger.test.model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Messanger_Content {

	private Label senderName = new Label();
	private Label roomName = new Label();
	private String senderNumber;
	private Label room = new Label();
	private Label content = new Label();
	private String contentDate;
	private Label contentDateLabel = new Label();
	private Button profileButton = new Button();
	private String contentFlag;
	

	public Label getRoomName() {
		return roomName;
	}
	public void setRoomName(Label roomName) {
		this.roomName = roomName;
	}
	public Label getContentDateLabel() {
		return contentDateLabel;
	}
	public void setContentDateLabel(Label contentDateLabel) {
		this.contentDateLabel = contentDateLabel;
	}
	public String getContentDate() {
		return contentDate;
	}
	public void setContentDate(String contentDate) {
		this.contentDate = contentDate;
	}
	public String getSenderNumber() {
		return senderNumber;
	}
	public void setSenderNumber(String senderNumber) {
		this.senderNumber = senderNumber;
	}
	public Label getRoom() {
		return room;
	}
	public void setRoom(Label room) {
		this.room = room;
	}
	public Label getSenderName() {
		return senderName;
	}
	public void setSenderName(Label senderName) {
		this.senderName = senderName;
	}
	public String getContentFlag() {
		return contentFlag;
	}
	public void setContentFlag(String contentFlag) {
		this.contentFlag = contentFlag;
	}
	public Label getContent() {
		return content;
	}
	public void setContent(Label content) {
		this.content = content;
	}
	public Button getProfileButton() {
		return profileButton;
	}
	public void setProfileButton(Button profileButton) {
		this.profileButton = profileButton;
	}
}
