package com.messanger.test.view;

import java.io.Serializable;
import java.util.HashMap;

public class Messanger_MessageDialogHandlePool implements Serializable{

	private static final long serialVersionUID = 1L;
	
//	private List<Messanger_MessageDialogHandle> list;
	private HashMap<Integer, Messanger_MessageDialogHandle> map;
	
	public Messanger_MessageDialogHandlePool() {
		map = new HashMap<Integer, Messanger_MessageDialogHandle>();
		initList();
	}
	
	private void initList(){
		Messanger_MessageDialogHandle handle = null;
		for(int i = 0; i < Messanger_FriendsList.list.size(); i++){
			map.put(i, handle);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void startHandle(int select){
		map.get(select).resume();
	}
	
	public void addHandle(int select, Messanger_MessageDialogHandle handle){
		map.replace(select, handle);
		map.get(select).start();
	}
	
	@SuppressWarnings("deprecation")
	public void stopHandle(int select) throws InterruptedException{
		System.out.println(select);
		System.out.println(select + "정지 되었습니다. ");
		map.get(select).suspend();
	}
}
