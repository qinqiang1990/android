package com.example.configuration;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout;

public class Configuration extends Activity {

	ConfigurationView view1,view2,view3,view4,view5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Uri myUri = NotePad.CONTENT_URI;
		ContentValues values = new ContentValues();
		values.put(NotePad.NOTE_AGE, "99");
		values.put(NotePad.NOTE_ID, "102");
		values.put(NotePad.NOTE_NAME, "qinqiang");        
		getContentResolver().insert(myUri, values);
	
		
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.configLayout);
		view1 = new ConfigurationView(this,true);
		layout.addView(view1, new LayoutParams(50, LayoutParams.FILL_PARENT));
		view2 = new ConfigurationView(this,true);
		layout.addView(view2, new LayoutParams(50, LayoutParams.FILL_PARENT));
		view3 = new ConfigurationView(this,true);
		layout.addView(view3, new LayoutParams(50, LayoutParams.FILL_PARENT));
		view4 = new ConfigurationView(this,true);
		layout.addView(view4, new LayoutParams(50, LayoutParams.FILL_PARENT));
		view5 = new ConfigurationView(this,true);
		layout.addView(view5, new LayoutParams(50, LayoutParams.FILL_PARENT));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
