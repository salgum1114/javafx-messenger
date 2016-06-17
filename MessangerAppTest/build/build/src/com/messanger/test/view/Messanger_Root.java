package com.messanger.test.view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Messanger_Root {
	
	@FXML
	private void handleFriendsList() throws IOException{
		Messanger_Panel.messangerList.getFriendsList().setVisible(true);
		Messanger_Panel.messangerList.getMessageList().setVisible(false);
		Messanger_Panel.messangerList.getAddinfoList().setVisible(false);
	}
	
	@FXML
	private void handleMessageList() throws IOException{
		Messanger_Panel.messangerList.getFriendsList().setVisible(false);
		Messanger_Panel.messangerList.getMessageList().setVisible(true);
		Messanger_Panel.messangerList.getAddinfoList().setVisible(false);
	}
	
	@FXML
	private void handleAddInfoList() throws IOException{
		Messanger_Panel.messangerList.getFriendsList().setVisible(false);
		Messanger_Panel.messangerList.getMessageList().setVisible(false);
		Messanger_Panel.messangerList.getAddinfoList().setVisible(true);
	}
	
	@FXML
	private Button volumeControl = new Button();
	
	private Messanger_Volume volumeController = new Messanger_Volume();
	int volume_flag = 1;
	
	@FXML
	private void handleVolume() throws IOException{
		if(volume_flag == 1){
			volumeControl.getStyleClass().removeAll("decoration-button-volume");
			volumeController.volumeControl1();
			volumeControl.getStyleClass().add("decoration-button-volumeMute");
			volume_flag = 0;
			
		}else{
			volumeControl.getStyleClass().removeAll("decoration-button-volumeMute");
			volumeController.volumeControl();
			volumeControl.getStyleClass().add("decoration-button-volume");
			volume_flag = 1;
		}
	}
	
	double x, y;
	
	@FXML
	protected void handleRectanglePressed(MouseEvent event){
		this.x = Messanger_Panel.messangerList.getPrimaryStage().getX() - event.getScreenX();
		this.y = Messanger_Panel.messangerList.getPrimaryStage().getY() - event.getScreenY();
	}
	
	@FXML
    protected void handleRectangleReleased(MouseEvent event) {
		Messanger_Panel.messangerList.getPrimaryStage().setX(event.getScreenX());
		Messanger_Panel.messangerList.getPrimaryStage().setY(event.getScreenY());
    }

    @FXML
    protected void handleRectangleDragged(MouseEvent event) {
    	Messanger_Panel.messangerList.getPrimaryStage().setX(event.getScreenX() + this.x);
    	Messanger_Panel.messangerList.getPrimaryStage().setY(event.getScreenY() + this.y);
    }
    
    int windowMaxFlag = 1;
    double bound_width, bound_height;
    double bound_x, bound_y;
    @FXML
    private void handleWindowMax(){
    	if(windowMaxFlag == 1){
    		bound_width = Messanger_Panel.messangerList.getPrimaryStage().getWidth();
    		bound_height = Messanger_Panel.messangerList.getPrimaryStage().getHeight();
    		bound_x = Messanger_Panel.messangerList.getPrimaryStage().getX();
    		bound_y = Messanger_Panel.messangerList.getPrimaryStage().getY();
    		Messanger_Panel.messangerList.getPrimaryStage().setX(0);
    		Messanger_Panel.messangerList.getPrimaryStage().setY(0);
    		Messanger_Panel.messangerList.getPrimaryStage().setWidth(Screen.getPrimary().getVisualBounds().getWidth());
    		Messanger_Panel.messangerList.getPrimaryStage().setHeight(Screen.getPrimary().getVisualBounds().getHeight());
    		windowMaxFlag = 0;
    	} else{
    		Messanger_Panel.messangerList.getPrimaryStage().setWidth(bound_width);
    		Messanger_Panel.messangerList.getPrimaryStage().setHeight(bound_height);
    		Messanger_Panel.messangerList.getPrimaryStage().setX(bound_x);
    		Messanger_Panel.messangerList.getPrimaryStage().setY(bound_y);
    		windowMaxFlag = 1;
    	}
    }
    
    @FXML
    private void handleWindowMin(){
    	if(!Platform.isFxApplicationThread()){
    		Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					_minimize();
				}
			});
    	} else{
    		_minimize();
    	}
    }
    
    private void _minimize(){
    	Stage stage = Messanger_Panel.messangerList.getPrimaryStage();
    	stage.setIconified(true);
    }
    
    @FXML
    private void handleTray(){

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Messanger_Panel.messangerList.getPrimaryStage().hide();
			}
		});
    }
}
