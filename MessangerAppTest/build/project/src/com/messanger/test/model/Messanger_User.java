package com.messanger.test.model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Messanger_User {

	private String personNumber;
	private Label personName = new Label();
	private Label[] personNames = new Label[10];
	private Label stateMessage = new Label();
	private Label personCount = new Label();
	private Button profileButton = new Button();
	
	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public Label getPersonName() {
		return personName;
	}

	public void setPersonName(Label personName) {
		this.personName = personName;
	}

	public Label[] getPersonNames() {
		return personNames;
	}

	public void setPersonNames(Label[] personNames) {
		this.personNames = personNames;
	}

	public Label getStateMessage() {
		return stateMessage;
	}
	
	public void setStateMessage(Label stateMessage) {
		this.stateMessage = stateMessage;
	}
	
	public Label getPersonCount() {
		return personCount;
	}
	
	public void setPersonCount(Label personCount) {
		this.personCount = personCount;
	}
	
	public Button getProfileButton() {
		return profileButton;
	}
	
	public void setProfileButton(Button profileButton) {
		this.profileButton = profileButton;
	}
}
