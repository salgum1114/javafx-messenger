package com.messanger.test.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

public class Messanger_AddinfoList {
	@FXML
	private VBox vbox_friends;
	
	@FXML
	private ListView<String> listview_friends = new ListView<String>();
	
	private ObservableList<String> data = FXCollections.observableArrayList("blue", "brown", "brown", "brown", "brown", "brown", "brown", "brown", "brown"
			, "brown", "brown", "brown", "brown", "brown", "brown", "brown", "brown", "brown", "brown"
			, "brown", "brown", "brown", "brown", "brown", "brown", "brown", "brown", "brown", "brown", "brown");
	
	public void addinfoList(){
		listview_friends.setItems(data);
		listview_friends.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ColorRectCell();
			}
		});
	}
	
	static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            }
        }
    }
	
	public Messanger_AddinfoList(){
		
	}
}
