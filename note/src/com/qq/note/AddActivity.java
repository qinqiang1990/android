package com.qq.note;
import com.qq.note.provider.NotePad;
import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class AddActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Uri myUri = NotePad.CONTENT_URI;
		ContentValues values = new ContentValues();
		values.put(NotePad.NOTE_AGE, "99");
		values.put(NotePad.NOTE_ID, "102");
		values.put(NotePad.NOTE_NAME, "qinqiang");        
		getContentResolver().insert(myUri, values);
		TextView tv=(TextView)findViewById(R.id.out);
		tv.setText("success");

	}

}