package com.messanger.test.view;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import com.messanger.test.Messanger_Connection;
import com.messanger.test.model.Messanger_User;

public class Messanger_FriendsList {
	
	@FXML
	private VBox vbox_myProfile;
	@FXML
	private VBox vbox_friends;
	@FXML
	private ListView<Messanger_User> listviewFriends;
	@FXML
	private ListView<Messanger_User> listviewMyProfile;

	public static Messanger_User messangerMyProfile = new Messanger_User();
	
	public static List<Messanger_User> list;
	
	private ObservableList<Messanger_User> data;
	private ObservableList<Messanger_User> data1;
	
	public static List<Boolean> messageDialogFlag;
	
	 private Messanger_Connection conn = new Messanger_Connection();
	 private PreparedStatement ps = null;
	 private ResultSet rs = null;
	
	public void myProfile(){
		listviewMyProfile.setItems(data);
		listviewMyProfile.setCellFactory(new Callback<ListView<Messanger_User>, ListCell<Messanger_User>>() {

			@Override
			public ListCell<Messanger_User> call(ListView<Messanger_User> param) {
				return new FriendsListCell();
			}
		});
	}
	
	public void friendsList(){
		listviewFriends.setItems(data1);
		listviewFriends.setCellFactory(new Callback<ListView<Messanger_User>, ListCell<Messanger_User>>() {

			@Override
			public ListCell<Messanger_User> call(ListView<Messanger_User> param) {
				return new FriendsListCell();
			}
		});
	}
	
	public class FriendsListCell extends ListCell<Messanger_User> {
        @Override
        public void updateItem(Messanger_User messangerFriends, boolean empty) {
            super.updateItem(messangerFriends, empty);
            if(empty){
            	setText(null);
            	setGraphic(null);
            } else {
            	setText(null);
                
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(4);
                grid.setPadding(new Insets(0, 10, 0, 10));
                
                grid.add(messangerFriends.getProfileButton(), 0, 0, 1, 2);
                grid.add(messangerFriends.getPersonName(), 1, 0);
                grid.add(messangerFriends.getStateMessage(), 1, 1);
                
                setGraphic(grid);
            } 
        }
    }
	
//	private int isMessage;
//	
//	protected boolean isMessage() throws ClassNotFoundException, SQLException{
//    	String sql = "select count(distinct content_room) as isContent from content where content_room like '110389,110397'";
//    	ps = conn.getConnection().prepareStatement(sql);
//		ps.setString(1, messangerMyProfile.getPersonNumber());
//		rs = ps.executeQuery();
//		
//		if(rs.next()){
//			isMessage = rs.getInt(1);
//		}
//		
//		if(isMessage == 1){
//			return true;
//		} else{
//			return false;
//		}
//	}
	
    /**
     * ä��â Stage�� ���� ���� ��ü ����
     */
    public static Messanger_MessageDialog messageDialog = new Messanger_MessageDialog();
    
    /**
     * FriendsList���� ���õ� index�� ���� ���� (����)
     */
    public static int selectedIndex;

    /**
     * ä��â Stage�� x, y�� ��ǥ�� +20�� ���ؼ� ���� ���� ���� x, y 
     */
    public static double x, y;
    
    /**
     * ���� Stage���� ó�� ���� ��ġ�� ������ ��ġ�� ��ǥ�� ���ϱ� ���� ���� ���� isX, isY
     */
    double isX = Messanger_Panel.messangerList.getPrimaryStage().getX();
    double isY = Messanger_Panel.messangerList.getPrimaryStage().getY();
    
    protected void handleList(){
    	listviewFriends.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event){
				if(event.getClickCount() == 2){
					try {
						// 2015-03-31 10:15 ä��â �ε������� �ذ�����!.
						// 2015-04-02 17:21 DB�� �ش� ����� ä���� ������ flag -> true ���� �� ����, ä���� ������ flag -> false ���� ���� ���� �ذ��ؾ���!!.  
						selectedIndex = listviewFriends.getSelectionModel().getSelectedIndex();
						if(messageDialogFlag.get(selectedIndex) == false){
							messageDialogFlag.set(selectedIndex, true);
							
							// ������ ��ǥ x�� ���� ��ġ ��ǥ x�� ���Ͽ� ������ x, y�� 20�� �����ϸ鼭 ä��â Stage�� ����. 
							if(Messanger_Panel.messangerList.getPrimaryStage().getX() == isX){
								Messanger_Panel.messangerList.getTestStage().get(selectedIndex).setX(Messanger_Panel.messangerList.getPrimaryStage().getX()+500+x);
								Messanger_Panel.messangerList.getTestStage().get(selectedIndex).setY(Messanger_Panel.messangerList.getPrimaryStage().getY()+y);
								x += 20;
								y += 20;
								messageDialog.messageDialog(list.get(selectedIndex), selectedIndex);
							} else{ // ������ ��ǥ x�� ���� ��ġ ��ǥ x�� ���Ͽ� Ʋ���� ���ο� ��ġ�� ä��â Stage�� ����.
								isX = Messanger_Panel.messangerList.getPrimaryStage().getX();
							    isY = Messanger_Panel.messangerList.getPrimaryStage().getY();
							    x = 20.0; // ���ο� ��ġ ��ǥ���� ���� ä��â�� ���� x, y ��ǥ�� 20�� ����.
							    y = 20.0; 
								Messanger_Panel.messangerList.getTestStage().get(selectedIndex).setX(isX+500);
								Messanger_Panel.messangerList.getTestStage().get(selectedIndex).setY(isY);
								messageDialog.messageDialog(list.get(selectedIndex), selectedIndex);
							}
						} else{
							if(Messanger_Panel.messangerList.getTestStage().get(selectedIndex).isShowing() == false){
								Messanger_Panel.messangerList.getTestStage().get(selectedIndex).show();
								Messanger_LoginController.pool.startHandle(selectedIndex);
							} else{
								Messanger_Panel.messangerList.getTestStage().get(selectedIndex).toFront();
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
    }
    
    private void handleProfile(Messanger_User messangerFriends){
    	messangerFriends.getProfileButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
    		
			@Override
			public void handle(MouseEvent event) {
				Messanger_Profile profile = new Messanger_Profile();
		    	try {
		    		if(Messanger_Panel.messangerList.getProfileStage() != null){
		    			Messanger_Panel.messangerList.getProfileStage().close();
		    		}
					profile.profileDialog(messangerFriends);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
    }
	
	public Messanger_FriendsList() throws ClassNotFoundException, SQLException{
		
		listviewMyProfile = new ListView<Messanger_User>();
		listviewFriends = new ListView<Messanger_User>();
		
		list = new ArrayList<Messanger_User>();
		messageDialogFlag = new ArrayList<Boolean>();
		
		String sql = "select employee_name, employee_statemessage, employee_id from employee_info where not employee_id = ? order by employee_name asc";
		ps = conn.getConnection().prepareStatement(sql);
		ps.setString(1, messangerMyProfile.getPersonNumber());
		rs = ps.executeQuery();
		
		while(rs.next()){
			Messanger_User vo = new Messanger_User();
			vo.getPersonName().setText(rs.getString(1));
			vo.getStateMessage().setText(rs.getString(2));
			vo.setPersonNumber(rs.getString(3));
			vo.getProfileButton().setText("hi");
			list.add(vo);
		}
		
		conn.getConnection().close();
		
		sql = "select employee_name, employee_statemessage from employee_info where employee_id = ?";
		ps = conn.getConnection().prepareStatement(sql);
		ps.setString(1, messangerMyProfile.getPersonNumber());
		rs = ps.executeQuery();
		
		if(rs.next()){
			messangerMyProfile.getProfileButton().setText("hi");
			messangerMyProfile.getPersonName().setText(rs.getString(1));
			messangerMyProfile.getStateMessage().setText(rs.getString(2));
		}
		
		conn.getConnection().close();
		
		data = FXCollections.observableArrayList(messangerMyProfile);
		data1 = FXCollections.observableArrayList(list);

		for(int i = 0; i < list.size(); i++){
			messageDialogFlag.add(i, false);
		}
		
		handleProfile(messangerMyProfile);
		for(int i = 0 ; i < list.size(); i++){
			if(list.get(i).getProfileButton().onMouseClickedProperty() != null){
				handleProfile(list.get(i));
			}else{
				break;
			}
		}
	}
}
