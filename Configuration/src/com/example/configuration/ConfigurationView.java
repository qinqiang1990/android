package com.example.configuration;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class ConfigurationView extends View {

	Paint paint;
	Paint font_Paint;
	Context context;
	boolean animMode;
	int top;
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawRect(10, top, 40, 180, paint);
	}

	public ConfigurationView(Context context,boolean animMode) {
		super(context);
		this.context=context;
		this.animMode=animMode;
		init();
	}


	// 初始化
	private void init(){
		// 数值初始化
		paint = new Paint();
		paint.setARGB(255, (int)(Math.random()*255),(int)(Math.random()*255), (int)(Math.random()*255));
		
		font_Paint = new Paint();
		font_Paint.setARGB(255, (int)(Math.random()*255),(int)(Math.random()*255), (int)(Math.random()*255));
		
		top=10;
		if(animMode){
			top=180;
			thread.start();
		}
	}

	Thread thread=new Thread(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(animMode)
			{
				if((int) (Math.random()*100)==99)
				{
					animMode=false;
				}
				font_Paint.setARGB(255, (int)(Math.random()*255),(int)(Math.random()*255), (int)(Math.random()*255));
				
				paint.setARGB(255, (int)(Math.random()*255),(int)(Math.random()*255), (int)(Math.random()*255));
				
				if(top==10)
				{
					top=180;
				}
				else
				{
					top-=10;
				}
				Message msg=new Message();
				msg.what=1;
				handler.sendMessage(msg);
				try {
					
					Thread.sleep((long) (Math.random()*100));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};

	Handler handler=new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what)
			{
				case 1:break;
			}
			invalidate();
		}
	};

}
