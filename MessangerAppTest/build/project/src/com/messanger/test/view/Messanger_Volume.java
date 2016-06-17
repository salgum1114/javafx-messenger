package com.messanger.test.view;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import com.messanger.test.Messanger_Main;

public class Messanger_Volume {
	
	private Stage volumeStage;
	
	public void volumeControl() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Messanger_Main.class.getResource("view/Messanger_Volume.fxml"));
		AnchorPane volumePane = (AnchorPane) loader.load();
		
		volumeStage = new Stage();
//		volumeStage.initModality(Modality.WINDOW_MODAL);
		volumeStage.initOwner(Messanger_Panel.messangerList.getPrimaryStage());
		volumeStage.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(volumePane);
		scene.setFill(Color.TRANSPARENT);
		volumeStage.setScene(scene);
	
		final FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), volumePane);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeIn.play();
		volumeStage.show();
		
		final FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), volumePane);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0);
		fadeOut.setDelay(Duration.millis(2000));
		fadeOut.play();	
		
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				volumeStage.close();
			}
		});
		
//		Timeline timeline = new Timeline(
//				new KeyFrame(
//						new Duration(100), 
//						new EventHandler<javafx.event.ActionEvent>(){
//							@Override
//							public void handle(javafx.event.ActionEvent event) {
//								volumeStage.show();
//								fadeIn.play();
//							}
//						}
//				),
//				new KeyFrame(
//						new Duration(100), 
//						new EventHandler<javafx.event.ActionEvent>(){
//
//							@Override
//							public void handle(javafx.event.ActionEvent event) {
//								fadeOut.play();
//							}
//						}
//				)
//		);
//		timeline.play();
	}
	
	public void volumeControl1() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Messanger_Main.class.getResource("view/Messanger_Volume1.fxml"));
		AnchorPane volumePane = (AnchorPane) loader.load();
		
		volumeStage = new Stage();
//		volumeStage.initModality(Modality.WINDOW_MODAL);
		volumeStage.initOwner(Messanger_Panel.messangerList.getPrimaryStage());
		volumeStage.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(volumePane);
		scene.setFill(Color.TRANSPARENT);
		volumeStage.setScene(scene);
	
		final FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), volumePane);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
		fadeIn.play();
		volumeStage.show();
		
		final FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), volumePane);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0);
		fadeOut.setDelay(Duration.millis(2000));
		fadeOut.play();	
		
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				volumeStage.close();
			}
		});
		
//		Timeline timeline = new Timeline(
//				new KeyFrame(
//						new Duration(100), 
//						new EventHandler<javafx.event.ActionEvent>(){
//							@Override
//							public void handle(javafx.event.ActionEvent event) {
//								volumeStage.show();
//								fadeIn.play();
//							}
//						}
//				),
//				new KeyFrame(
//						new Duration(100), 
//						new EventHandler<javafx.event.ActionEvent>(){
//
//							@Override
//							public void handle(javafx.event.ActionEvent event) {
//								fadeOut.play();
//							}
//						}
//				)
//		);
//		timeline.play();
	}
}
