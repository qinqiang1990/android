package com.qq.note;

import com.qq.note.provider.NotePad;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleCursorAdapter;

@SuppressLint("NewApi")
public class NoteActivity extends ListActivity implements LoaderCallbacks<Cursor>{

	SimpleCursorAdapter adapter;
	private String TAG="note";
	private static final String[] CALLLOG_PROJECTION = new String[] {
		CallLog.Calls._ID, CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME,
		CallLog.Calls.TYPE, CallLog.Calls.DATE };

	private static final String[] PROJECTION = new String[] {
		NotePad._ID, 
		NotePad.NOTE_NAME,
		NotePad.NOTE_AGE, 
		NotePad.NOTE_ID
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Intent intent = getIntent(); 
		if (intent.getData() == null) {
			intent.setData(NotePad.CONTENT_URI);
		}
		Button call=(Button)this.findViewById(R.id.call);
		call.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+10010));

				NoteActivity.this.startActivity(intent);


			}});
		//	cursor =  getContentResolver().query(getIntent().getData(),PROJECTION, null, null,null);
		// Cursor cursor = managedQuery(getIntent().getData(), PROJECTION, null, null,null);

		adapter = new SimpleCursorAdapter(NoteActivity.this, R.layout.noteslist_item, null,new String[] {NotePad.NOTE_NAME}, new int[] { android.R.id.text1 });

		setListAdapter(adapter);

		getLoaderManager().initLoader(0, null, NoteActivity.this);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add:
			// Launch activity to insert a new item
			startActivity(new Intent(Intent.ACTION_INSERT, getIntent().getData()));
		}
		return super.onOptionsItemSelected(item);
	}

	@Override

	protected void onListItemClick(ListView l, View v, int position, long id) {

		Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);

		String action = getIntent().getAction();

		startActivity(new Intent(Intent.ACTION_EDIT, uri));

	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onCreateLoader");
		CursorLoader cursor = new CursorLoader(NoteActivity.this,getIntent().getData(),PROJECTION, null, null,null);
		//CursorLoader cursor = new CursorLoader(NoteActivity.this,CallLog.Calls.CONTENT_URI, CALLLOG_PROJECTION, null, null,null);
		Log.d(TAG, "MyLoaderListener---------->onCreateLoader");

		return cursor;

	}

	@Override
	public void onLoadFinished(Loader<Cursor> cursor, Cursor data) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onLoadFinished");
		adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onLoaderReset");
		adapter.swapCursor(null);
	}

}
