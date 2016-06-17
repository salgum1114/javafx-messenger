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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import com.messanger.test.Messanger_Connection;
import com.messanger.test.model.Messanger_Content;

public class Messanger_MessageList {
	
	@FXML
	private VBox vbox_messages;
	@FXML
	private ListView<Messanger_Content> listviewMessages;
	
	private ObservableList<Messanger_Content> data;
	private List<Messanger_Content> list;
	
	public void initMessageList() throws ClassNotFoundException, SQLException{
		listviewMessages.setItems(handleMessageList());
		listviewMessages.setCellFactory(new Callback<ListView<Messanger_Content>, ListCell<Messanger_Content>>() {
			
			@Override
			public ListCell<Messanger_Content> call(ListView<Messanger_Content> param) {
				return new MessageListCell();
			}
		});
	}
	
	public void updateMessageList() throws ClassNotFoundException, SQLException{
		listviewMessages.getItems().clear();
		listviewMessages.getItems().addAll(handleMessageList());
	}
	
	static class MessageListCell extends ListCell<Messanger_Content> {
		@SuppressWarnings("static-access")
		@Override
        public void updateItem(Messanger_Content messangerContent, boolean empty) {
            super.updateItem(messangerContent, empty);
            if(empty){
            	setText(null);
            	setGraphic(null);
            } else {
            	setText(null);
                
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(4);
                grid.setPadding(new Insets(0, 10, 0, 10));
                
                messangerContent.getRoomName().setPrefWidth(100);
                messangerContent.getContent().setPrefWidth(100);
                
                grid.add(messangerContent.getProfileButton(), 0, 0, 1, 2);
                grid.add(messangerContent.getRoomName(), 1, 0);
                grid.add(messangerContent.getContent(), 1, 1);
                
                grid.setHgrow(messangerContent.getContentDateLabel(), Priority.ALWAYS);
                grid.setHalignment(messangerContent.getContentDateLabel(), HPos.RIGHT);
                grid.add(messangerContent.getContentDateLabel(), 2, 0, 1, 2);
                setGraphic(grid);
                
            } 
        }
    }
	
    // 2015-03-31 18:00 메시지목록 가장 최근에 보낸 시간과 마지막 메시지로 뿌려줘야함.!!!!!!
    // 2015-04-02 16:02 메시지목록 수정 완료!!.
    /**
     * 메시지 목록을 생성하는 메소드.
     * 
     * @return ObservableList<Messanger_Content>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<Messanger_Content> handleMessageList() throws SQLException, ClassNotFoundException {
    	Messanger_Connection conn = new Messanger_Connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
    	list = new ArrayList<Messanger_Content>();
		
		String sql = "select c.content_date, c.content, c.content_room "
				+ "from employee_info e, (select max(content_date) content_date, content, content_room "
				+ "from content where content_room like ? group by content_date desc) as c "
				+ "group by c.content_room "
				+ "order by c.content_date desc";
		ps = conn.getConnection().prepareStatement(sql);
		ps.setString(1, "%"+Messanger_FriendsList.messangerMyProfile.getPersonNumber()+"%");
		rs = ps.executeQuery();
		
		while(rs.next()){
			Messanger_Content vo = new Messanger_Content();
			vo.getContentDateLabel().setText(rs.getString(1));
			vo.getContent().setText(rs.getString(2));
			vo.getRoom().setText(rs.getString(3));
			vo.getProfileButton().setText("hi");
			list.add(vo);
		}
		
		conn.getConnection().close();
		
		data = FXCollections.observableArrayList(subString(list));
		
		return data;
    }
    
    /**
     * 채팅방 인원에서 자신을 뺀 나머지 인원의 목록을 출력해주기 위한 SubString
     * 
     * @param list
     * @return  List<Messanger_Content>
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public List<Messanger_Content> subString(List<Messanger_Content> list) throws ClassNotFoundException, SQLException{
    	List<Messanger_Content> roomList = new ArrayList<Messanger_Content>();
    	List<String> subList = new ArrayList<String>();
    	
    	String sql = "select employee_name "
				+ "from employee_info "
				+ "where employee_id = ?";
    	Messanger_Connection conn = new Messanger_Connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
    	roomList = list;
    	
    	for(int i = 0; i < roomList.size(); i++){
    		if(roomList.get(i).getRoom().getText().startsWith(Messanger_FriendsList.messangerMyProfile.getPersonNumber())){
    			subList.add(roomList.get(i).getRoom().getText().replace(Messanger_FriendsList.messangerMyProfile.getPersonNumber()+",", ""));
    			roomList.get(i).getRoom().setText(subList.get(i));
    		} else{
    			subList.add(roomList.get(i).getRoom().getText().replaceFirst(","+Messanger_FriendsList.messangerMyProfile.getPersonNumber(), ""));
    			roomList.get(i).getRoom().setText(subList.get(i));
    		}
    		
    		ps = conn.getConnection().prepareStatement(sql);
    		ps.setString(1, roomList.get(i).getRoom().getText());
    		rs = ps.executeQuery();
    		
    		if(rs.next()){
    			roomList.get(i).getRoomName().setText(rs.getString(1));
    		}
    	}
    	
    	conn.getConnection().close();
    	
    	return roomList;
    }
    
   public static int selector;
    
    /**
     * 메인 Stage에서 처음 시작 위치와 움직인 위치의 좌표를 비교하기 위한 위한 변수 isX, isY
     */
    double isX = Messanger_Panel.messangerList.getPrimaryStage().getX();
    double isY = Messanger_Panel.messangerList.getPrimaryStage().getY();
    
    protected void handleList(){
    	listviewMessages.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount() == 2){
					selector = listviewMessages.getSelectionModel().getSelectedIndex();
					
					for(int i = 0; i < Messanger_FriendsList.list.size(); i++){
						if(Messanger_FriendsList.list.get(i).getPersonNumber().equals(listviewMessages.getItems().get(selector).getRoom().getText())){
							if(Messanger_FriendsList.messageDialogFlag.get(i) == false){
								Messanger_FriendsList.messageDialogFlag.set(i, true);
								
								if(Messanger_Panel.messangerList.getPrimaryStage().getX() == isX){
									Messanger_Panel.messangerList.getTestStage().get(i).setX(Messanger_Panel.messangerList.getPrimaryStage().getX()+500+Messanger_FriendsList.x);
									Messanger_Panel.messangerList.getTestStage().get(i).setY(Messanger_Panel.messangerList.getPrimaryStage().getY()+Messanger_FriendsList.y);
									Messanger_FriendsList.x += 20;
									Messanger_FriendsList.y += 20;
									try {
										Messanger_FriendsList.messageDialog.messageDialog(Messanger_FriendsList.list.get(i), i);
									} catch (ClassNotFoundException
											| IOException | SQLException
											| InterruptedException e) {
										e.printStackTrace();
									}
								} else{ // 움직인 좌표 x와 현재 위치 좌표 x를 비교하여 틀리면 새로운 위치에 채팅창 Stage를 생성.
									isX = Messanger_Panel.messangerList.getPrimaryStage().getX();
								    isY = Messanger_Panel.messangerList.getPrimaryStage().getY();
								    Messanger_FriendsList.x = 20.0; // 새로운 위치 좌표에서 다음 채팅창을 위해 x, y 좌표를 20씩 증가.
								    Messanger_FriendsList.y = 20.0; 
									Messanger_Panel.messangerList.getTestStage().get(i).setX(isX+500);
									Messanger_Panel.messangerList.getTestStage().get(i).setY(isY);
									try {
										Messanger_FriendsList.messageDialog.messageDialog(Messanger_FriendsList.list.get(i), i);
									} catch (ClassNotFoundException
											| IOException | SQLException
											| InterruptedException e) {
										e.printStackTrace();
									}
								}
							} else{
								if(Messanger_Panel.messangerList.getTestStage().get(i).isShowing() == false){
									Messanger_Panel.messangerList.getTestStage().get(i).show();
									Messanger_LoginController.pool.startHandle(i);
								} else{
									Messanger_Panel.messangerList.getTestStage().get(i).toFront();
								}
							}
						}
					}
				}
			}
		});
    }
    
	public Messanger_MessageList() throws ClassNotFoundException, SQLException{
		listviewMessages = new ListView<Messanger_Content>();
	}
}
