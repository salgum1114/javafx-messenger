package com.messanger.test.server;

public class Messanger_Abortable {
	
	public boolean done = false;
	
	public Messanger_Abortable(){
		init();
	}
	
	public void init(){
		done = false;
	}
	
	public boolean isDone(){
		return done;
	}
}
