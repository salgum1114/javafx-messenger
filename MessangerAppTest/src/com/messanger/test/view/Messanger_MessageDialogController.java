package com.messanger.test.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import com.messanger.test.Messanger_Connection;
import com.messanger.test.model.Messanger_Content;

public class Messanger_MessageDialogController {
	@FXML
	private Label personName;
	@FXML
	private Label[] personNames;
	@FXML
	private Label stateMessage;
	@FXML
	private Label personCount;
	@FXML
	private Button profileButton;
	@FXML
	private TextArea messageTextarea;
	@FXML
	private Button sendButton;
	@FXML
	private Button exitButton;
	@FXML
	private ListView<Messanger_Content> listviewContent;
	
	private ArrayList<Messanger_Content> list;
	
    private int index;
    
	public Label getPersonName() {
		return personName;
	}

	public Label getStateMessage() {
		return stateMessage;
	}

	public Messanger_MessageDialogController() throws ClassNotFoundException, SQLException{
		for(int i = 0; i < 5; i++){
			x = 0.0;
			y = 0.0;
		}
	}
	
	public String sumString(){
		List<String> sortRoom = new ArrayList<String>();
		String sumString = new String();
		
		sortRoom.add(Messanger_FriendsList.messangerMyProfile.getPersonNumber());
		sortRoom.add(Messanger_FriendsList.list.get(index).getPersonNumber());
		
		Collections.sort(sortRoom);
		
		for(int i = 0; i < sortRoom.size(); i++){
			if(i < sortRoom.size()-1){
				sumString += sortRoom.get(i) + ",";
			} else{
				sumString += sortRoom.get(i);
			}
		}
		
		return sumString;
	}
	
	public ObservableList<Messanger_Content> handleContent() throws ClassNotFoundException, SQLException{
		ObservableList<Messanger_Content> data;
		Messanger_Connection conn = new Messanger_Connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		list = new ArrayList<Messanger_Content>();
		
		String sql = "select c.content, e.employee_name as sender, c.content_room as room, e.employee_id, c.content_date "
				+ "from content c, employee_info e "
				+ "where c.content_send = e.employee_id and c.content_room = ? " 
				+ "order by c.content_date asc;";
		
		ps = conn.getConnection().prepareStatement(sql);
		ps.setString(1, sumString());
		rs = ps.executeQuery();
		
		while(rs.next()){
			Messanger_Content vo = new Messanger_Content();
			vo.getContent().setText(rs.getString(1));
			vo.getSenderName().setText(rs.getString(2));
			vo.getRoom().setText(rs.getString(3));
			vo.setSenderNumber(rs.getString(4));
			vo.setContentDate(rs.getString(5));
			vo.getProfileButton().setText("hi");
			list.add(vo);
		}
		
		rs.close();
		ps.close();
		conn.getConnection().close();
		
		data = FXCollections.observableArrayList(list);
		
		return data;
	}

	/**
	 * 대화창에 메시지를 불러오는 메소드.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void initContentList() throws ClassNotFoundException, SQLException{
		if(handleContent().isEmpty()){
			listviewContent.setItems(null);
			return;
		} else{
			listviewContent.setItems(handleContent());
			listviewContent.setCellFactory(new Callback<ListView<Messanger_Content>, ListCell<Messanger_Content>>() {
				
				@Override
				public ListCell<Messanger_Content> call(ListView<Messanger_Content> param) {
					return new contentListCell();
				}
			});			
			listviewContent.scrollTo(listviewContent.getItems().size());
		}
	}
	
	/**
	 * 대화창의 메시지를 업데이트 해주는 메소드.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void updateContentList() throws ClassNotFoundException, SQLException{
		ObservableList<Messanger_Content> data = handleContent();
		
		if(data.isEmpty()){
			listviewContent.setItems(null);
			return ;
		} else{
			if(listviewContent.getItems() == null){
				initContentList();
			} else{
				if(data.get(data.size()-1).getContentDate().equals(listviewContent.getItems().get(listviewContent.getItems().size()-1).getContentDate())){
					return ;
				} else{
					listviewContent.getItems().clear();
					listviewContent.getItems().addAll(data);
				}
			}
		}
	}
	
	public class contentListCell extends ListCell<Messanger_Content> {
        @Override
        public void updateItem(Messanger_Content messangerContent, boolean empty) {
            super.updateItem(messangerContent, empty);
            if(empty){
            	setText(null);
            	setGraphic(null);
            } else {
            	
//            	setStyle("-fx-background-color : white;");
            	
            	// 친구목록에서 메시지를 불러오고 대화창에 출력해줌.
            	String personNumber = Messanger_FriendsList.messangerMyProfile.getPersonNumber();
            	if(messangerContent.getSenderNumber().equals(personNumber)){
            		setText(null);
            		
            		GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(4);
                    grid.setPadding(new Insets(0, 10, 0, 10));
                    grid.setAlignment(Pos.BASELINE_RIGHT);
                    
                    StringBuilder sb = new StringBuilder();
                    String str;
                    
                    if(messangerContent.getContent().getText().length() > 15){
                    	for(int i = 0; i < messangerContent.getContent().getText().length(); i++){
                    		if(sb.length() % 15 == 0){
                    			sb.append("\n");
                    		} else{
                    			sb.append(messangerContent.getContent().getText().charAt(i));
                    		}
                    	}
                    	
                       	str = sb.toString();
                       	messangerContent.getContent().setText(str);
                       	grid.add(messangerContent.getContent(), 1, 1);
                    } else{
                    	grid.add(messangerContent.getContent(), 1, 1);
                    }
                    
                    setGraphic(grid);
            	} else{
            		setText(null);
            		
            		GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(4);
                    grid.setPadding(new Insets(0, 10, 0, 10));
                    
                    grid.add(messangerContent.getProfileButton(), 0, 0, 1, 2);
                    grid.add(messangerContent.getSenderName(), 1, 0);
                    
                    StringBuilder sb = new StringBuilder();
                    String str;
                    
                    if(messangerContent.getContent().getText().length() > 15){
                    	for(int i = 0; i < messangerContent.getContent().getText().length(); i++){
                    		if(sb.length() % 15 == 0){
                    			sb.append("\n");
                    		} else{
                    			sb.append(messangerContent.getContent().getText().charAt(i));
                    		}
                    	}
                    	
                       	str = sb.toString();
                       	messangerContent.getContent().setText(str);
                       	grid.add(messangerContent.getContent(), 1, 1);
                    } else{
                    	grid.add(messangerContent.getContent(), 1, 1);
                    }
                    
                    setGraphic(grid);
            	}
            } 
        }
    }
	
	public void handleCount() throws SQLException{
		Messanger_Connection conn = new Messanger_Connection();
		
		PreparedStatement ps = null;
		
		String sql = "update content set content_flag = content_flag-1 where content_room = ?";
		ps = conn.getConnection().prepareStatement(sql);
		ps.setString(1, sumString());
		ps.execute();

		ps.close();
		conn.getConnection().close();
	}
	
	/**
	 * 메시지를 보내기 위한 handle메소드
	 */
	public synchronized void handleMessage(Stage stage) {
		Messanger_Connection conn = new Messanger_Connection();
		
		sendButton.setDisable(true);
		
		sendButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					PreparedStatement ps = null;
					
					String sql = "insert into content(content_date, content, content_send, content_room) values(now(), ?, ?, ?)";
					ps = conn.getConnection().prepareStatement(sql);
					ps.setString(1, messageTextarea.getText());
					ps.setString(2, Messanger_FriendsList.messangerMyProfile.getPersonNumber());
					ps.setString(3, sumString());
					ps.execute();

					ps.close();
					conn.getConnection().close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				sendButton.setDisable(true);
				messageTextarea.clear();
				messageTextarea.requestFocus();
				try {
					updateContentList();
					listviewContent.scrollTo(listviewContent.getItems().size());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				event.consume();
			}
		});
		
		messageTextarea.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.BACK_SPACE){
					if(messageTextarea.getText().trim().length() == 1){
						sendButton.setDisable(true);
						
						if(event.getCode() == KeyCode.ENTER){
							event.consume();
						}
					}
				} else{
					if(event.getCode() == KeyCode.ESCAPE){
							stage.close();
							Messanger_FriendsList.messageDialogFlag.add(index, false);
					}
					
					sendButton.setDisable(false);
					
					if(event.getCode() == KeyCode.ENTER){
						try {
							PreparedStatement ps = null;
							
							String sql = "insert into content(content_date, content, content_send, content_room) values(now(), ?, ?, ?)";
							ps = conn.getConnection().prepareStatement(sql);
							ps.setString(1, messageTextarea.getText());
							ps.setString(2, Messanger_FriendsList.messangerMyProfile.getPersonNumber());
							ps.setString(3, sumString());
							ps.execute();
							
							ps.close();
							conn.getConnection().close();
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
						sendButton.setDisable(true);
						messageTextarea.clear();
						try {
							updateContentList();
							listviewContent.scrollTo(listviewContent.getItems().size());
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
						event.consume();
					} 
				}
			}
		});
	}
	
	public double x, y;
	
    protected void handleSelectStage(Stage stage){
    	stage.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				x = stage.getX() - event.getScreenX();
				y = stage.getY() - event.getScreenY();
			}
		});
    	
    	stage.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() + x);
		    	stage.setY(event.getScreenY() + y);
			}
		});
    }
    
    private final EventHandler<KeyEvent> changeByKeyboardEvent = new EventHandler<KeyEvent>() {
    	@Override
    	public void handle(final KeyEvent event) {
    		if (event.getCode().isArrowKey()) {
    			event.consume();
    		}
    	}
    };
    
    private final EventHandler<MouseEvent> changeByMouseEvent = new EventHandler<MouseEvent>() {
    	@Override
    	public void handle(final MouseEvent event) {
    		event.consume();
    	}
    };
    
    public void disableSelection(){
    	listviewContent.addEventFilter(MouseEvent.MOUSE_PRESSED, changeByMouseEvent);
    	listviewContent.addEventFilter(KeyEvent.KEY_PRESSED, changeByKeyboardEvent);
    }
    
    protected void handleExit(Stage stage){
    	Messanger_Panel.messangerList.getTestStage().get(index).setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				System.out.println(index);
				try {
					Messanger_LoginController.pool.stopHandle(index);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stage.hide();
			}
		});
    	
    	exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(index);
				try {
					Messanger_LoginController.pool.stopHandle(index);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stage.hide();
			}
		});
    	
//    	stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent event) {
//				if(event.getCode() == KeyCode.ESCAPE){
//					System.out.println(index);
//					try {
//						Messanger_LoginController.pool.stopHandle(index);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					stage.hide();
//				}
//			}
//		});
    }
    
    /**
     * 선택된 대화창의 index를 주는 메소드 
     * 
     * @param stage
     * @param selectedIndex
     */
    public void handleSelectedIndex(Stage stage, int selectedIndex){
    	index = selectedIndex;
    	stage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				index = selectedIndex;
				System.out.println(index);
			}
		});
    	
    	listviewContent.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				index = selectedIndex;
				System.out.println(index);
			}
		});
    }
}
