package com.messanger.test.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Messanger_ProfileController {
	
	@FXML
	private Label personName;
	@FXML
	private Label stateMessage;
	@FXML
	private Circle imageButton = new Circle();
	
	double x, y;
	
    public Label getPersonName() {
		return personName;
	}

	public void setPeronName(Label peronName) {
		this.personName = peronName;
	}

	public Label getStateMessage() {
		return stateMessage;
	}

	public void setStateMessage(Label stateMessage) {
		this.stateMessage = stateMessage;
	}

	@FXML
	public void handleImageButton(){
	}
	
	@FXML
	protected void handleRectanglePressed(MouseEvent event){
		this.x = Messanger_Panel.messangerList.getProfileStage().getX() - event.getScreenX();
		this.y = Messanger_Panel.messangerList.getProfileStage().getY() - event.getScreenY();
	}
	
	@FXML
    protected void handleRectangleReleased(MouseEvent event) {
		Messanger_Panel.messangerList.getProfileStage().setX(event.getScreenX());
		Messanger_Panel.messangerList.getProfileStage().setY(event.getScreenY());
    }

    @FXML
    protected void handleRectangleDragged(MouseEvent event) {
    	Messanger_Panel.messangerList.getProfileStage().setX(event.getScreenX() + this.x);
    	Messanger_Panel.messangerList.getProfileStage().setY(event.getScreenY() + this.y);
    }
    
    @FXML
    protected void handleExit(){
    	Messanger_Panel.messangerList.getProfileStage().close();
    	Messanger_Panel.messangerList.setProfileStage(null);
    }
}
