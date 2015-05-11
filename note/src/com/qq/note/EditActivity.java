package com.qq.note;
import com.qq.note.provider.NotePad;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends Activity {

	private static final String[] PROJECTION = new String[] {
		NotePad._ID, 
		NotePad.NOTE_NAME,
		NotePad.NOTE_AGE, 
		NotePad.NOTE_ID
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Uri uri=getIntent().getData();
		Cursor cursor=getContentResolver().query(uri, PROJECTION, null, null, null);
		String out="";
		if(cursor.moveToNext()) {  
			out+=cursor.getString(cursor.getColumnIndex(NotePad._ID))+"\n"; 
			out+=cursor.getString(cursor.getColumnIndex(NotePad.NOTE_ID))+"\n";
			out+=cursor.getString(cursor.getColumnIndex(NotePad.NOTE_NAME))+"\n";  
			out+=cursor.getString(cursor.getColumnIndex(NotePad.NOTE_AGE))+"\n";  
		}  
		cursor.close(); 
		TextView tv=(TextView)findViewById(R.id.out);
		tv.setText(out);
	}

}