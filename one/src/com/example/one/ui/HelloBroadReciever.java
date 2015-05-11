package com.example.one.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HelloBroadReciever  extends BroadcastReceiver{
	private String name;
	public HelloBroadReciever(String name){ 
		System.out.println("HelloBroadReciever"+name); 
		this.name=name;	  
	}
	public HelloBroadReciever(){ 
		System.out.println("HelloBroadReciever"+name); 
	
	}
	private void shot()
	{ 
		System.out.println("shot"+name); 
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onReceive"); 
	//	shot();
	}

}
