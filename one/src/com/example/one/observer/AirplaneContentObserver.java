package com.example.one.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;
import android.database.ContentObserver;  
import android.net.Uri;  
import android.os.Handler;  
import android.provider.*;  
import android.provider.Settings.SettingNotFoundException;  
import android.util.Log;  

public class AirplaneContentObserver extends ContentObserver {

	private static String TAG = "AirplaneContentObserver" ;  

	private Context mContext;      
	private Handler mHandler ;  //此Handler用来更新UI线程  


	public AirplaneContentObserver(Context context,Handler handler) {
		super(handler);
		this.mContext=context;
		this.mHandler=handler;
	}

	@Override  
	public void onChange(boolean selfChange) {  
		Log.i(TAG, "-------------the airplane mode has changed-------------");  

		// 系统是否处于飞行模式下  
		try {  
			int isAirplaneOpen = Settings.System.getInt(mContext.getContentResolver(), Settings.System.AIRPLANE_MODE_ON);  
			Log.i(TAG, " isAirplaneOpen -----> " +isAirplaneOpen) ;  
		}  
		catch (SettingNotFoundException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  

	}  

}