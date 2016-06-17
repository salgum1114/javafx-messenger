package com.messanger.test.view;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import com.messanger.test.Messanger_Connection;
import com.messanger.test.Messanger_Main;

public class Messanger_LoginController{

	@FXML
	private Label confirmLabel;
	@FXML
	private Button loginButton;
	@FXML
	private TextField id;
	@FXML
	private PasswordField password;
	
	public static Messanger_MessageDialogHandlePool pool;
	
	double x, y;

	@FXML
	private void handleConfirm(){
		Messanger_Panel.messangerList.getConfirmStage().close();
		Messanger_Panel.messangerList.setConfirmStage(null);
	}
	
	@FXML
	protected void handleRectanglePressed(MouseEvent event){
		this.x = Messanger_Panel.messangerList.getLoginStage().getX() - event.getScreenX();
		this.y = Messanger_Panel.messangerList.getLoginStage().getY() - event.getScreenY();
	}
	
    @FXML
    protected void handleRectangleDragged(MouseEvent event) {
    	Messanger_Panel.messangerList.getLoginStage().setX(event.getScreenX() + this.x);
    	Messanger_Panel.messangerList.getLoginStage().setY(event.getScreenY() + this.y);
    }

    @FXML
    protected void handleExit(){
    	Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				Platform.exit();
				System.exit(0);				
			}
		});
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
    	Stage stage = Messanger_Panel.messangerList.getLoginStage();
    	stage.setIconified(true);
    }
    
	/**
     * 로그인을 handle하는 메소드
     * 
     * @param primaryStage
     */
    public void handleLogin(Stage primaryStage){
    	if(password.getText().equals("")){
    		loginButton.setDisable(true);
    	}
    	
    	loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					if(isSuccess() == true){
						try {
							Messanger_Panel.messangerList.getLoginStage().close();
							Messanger_Main.messangerPanel.mainStage(primaryStage);
							Messanger_Main.messangerPanel.initFriendsList();
							Messanger_Main.messangerPanel.initMessageList();
							Messanger_Main.messangerPanel.initAddinfoList();
							
							Messanger_MessageListHandle handle = new Messanger_MessageListHandle();
							handle.start();
							
							pool = new Messanger_MessageDialogHandlePool();
						} catch (IOException e) {
							e.printStackTrace();
						}
						Messanger_Main.messangerPanel.systemTrayMain();
					} else{
						try {
							Messanger_Main.messangerPanel.confirmStage();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
    	
    	password.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(password.getText().equals("")){
					loginButton.setDisable(true);
				} else{
					loginButton.setDisable(false);
					
					if(event.getCode() == KeyCode.ENTER){
						try {
							if(isSuccess() == true){
								try {
									Messanger_Panel.messangerList.getLoginStage().close();
									Messanger_Main.messangerPanel.mainStage(primaryStage);
									Messanger_Main.messangerPanel.initFriendsList();
									Messanger_Main.messangerPanel.initMessageList();
									Messanger_Main.messangerPanel.initAddinfoList();
									
									Messanger_MessageListHandle handle = new Messanger_MessageListHandle();
									handle.start();
									
									pool = new Messanger_MessageDialogHandlePool();
								} catch (IOException e) {
									e.printStackTrace();
								}
								
								Messanger_Main.messangerPanel.systemTrayMain();
							} else{
								try {
									Messanger_Main.messangerPanel.confirmStage();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
    }
    
    /**
     * 로그인의 성공 여부를 비교하는 메소드
     * 
     * @return boolean
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean isSuccess() throws SQLException, ClassNotFoundException{
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Messanger_Connection conn = new Messanger_Connection();
    
    	if(id.getText().equals("") && password.getText().equals("")){
    		return false;
    	} else{
    		int count = 0;
    	   	String sql = "select count(employee_name) as name, employee_id from employee_security where employee_email = ? and employee_password = ?";
    		ps = conn.getConnection().prepareStatement(sql);
    		ps.setString(1, id.getText());
    		ps.setString(2, password.getText());
    		rs = ps.executeQuery();
    		
   			if(rs.next()){
   				count = rs.getInt(1);
   				Messanger_FriendsList.messangerMyProfile.setPersonNumber(rs.getString(2));
   			}
   			conn.getConnection().close();
   			
   			if(count == 1){
   				return true;
   			} else{
   				return false;
   			}
    	}
    }
}
