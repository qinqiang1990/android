package com.example.one.ui;

import java.util.ArrayList;
import java.util.List;

import com.example.one.MainActivity;
import com.example.one.R;

import android.annotation.SuppressLint;
import android.app.Activity; 
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;


import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ListView;


@SuppressLint("NewApi")
public  class MyLoaderActivity extends Activity  implements LoaderCallbacks<Cursor>{
	private static final String[] CALLLOG_PROJECTION = new String[] {
		CallLog.Calls._ID, CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME,
		CallLog.Calls.TYPE, CallLog.Calls.DATE };
	private ListView mListView;
	private MyCursorAdapter mAdapter;
	private String TAG="Loader";
	private String mCallLogShowType;
	private String INCOMING="1";
	private String OUTCOMING="2";
	private CursorLoader cursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myloader);
		initWidgets();
		initMyLoader();

	}


	private void initMyLoader() {
		// TODO Auto-generated method stub
		getLoaderManager().initLoader(0, null, this);


	}

	private void initWidgets() {
		// TODO Auto-generated method stub
		mListView = (ListView) findViewById(R.id.lv_list);
		mAdapter = new MyCursorAdapter(MyLoaderActivity.this, null);
		mListView.setAdapter(mAdapter);
		Button btn = (Button) findViewById(R.id.in);
		btn.setOnClickListener(new ButtonListener());
		btn = (Button) findViewById(R.id.out);
		btn.setOnClickListener(new ButtonListener());


	}


	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		cursor = new CursorLoader(MyLoaderActivity.this,
				CallLog.Calls.CONTENT_URI, CALLLOG_PROJECTION, null, null,
				CallLog.Calls.DEFAULT_SORT_ORDER);
		Log.d(TAG, "MyLoaderListener---------->onCreateLoader");

		return cursor;
	}


	@Override
	public void onLoadFinished(Loader<Cursor> cursor, Cursor data) {
		// TODO Auto-generated method stub

		mAdapter.swapCursor(data);
		Log.d(TAG,
				"MyLoaderListener---------->onLoadFinished data count = "
						+ data.getCount());
	}


	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		Log.d(TAG, "MyLoaderListener---------->onLoaderReset");
		mAdapter.swapCursor(null);
	}


	private void incomingCalllog() {
		//mCallLogShowType = INCOMING;
		String selection = CallLog.Calls.TYPE + "=?";
		String[] selectionArgs = new String[] { "1" };
		/*		Cursor incomingCursor = getContentResolver().query(
				CallLog.Calls.CONTENT_URI, CALLLOG_PROJECTION, selection,
				selectionArgs, CallLog.Calls.DEFAULT_SORT_ORDER);
		mAdapter.swapCursor(incomingCursor); */

		cursor = new CursorLoader(MyLoaderActivity.this,
				CallLog.Calls.CONTENT_URI, CALLLOG_PROJECTION, selection, selectionArgs,
				CallLog.Calls.DEFAULT_SORT_ORDER);
		Log.d(TAG, "MyLoaderActivity---------->incomingCalllog");

	}
	private void outcomingCalllog() {
		//mCallLogShowType = OUTCOMING;
		String selection = CallLog.Calls.TYPE + "=?";
		String[] selectionArgs = new String[] { "2" };
		/*		Cursor outcomingCursor = getContentResolver().query(
				CallLog.Calls.CONTENT_URI, CALLLOG_PROJECTION, selection,
				selectionArgs, CallLog.Calls.DEFAULT_SORT_ORDER);
					mAdapter.swapCursor(outcomingCursor);*/

		cursor = new CursorLoader(MyLoaderActivity.this,
				CallLog.Calls.CONTENT_URI, CALLLOG_PROJECTION, selection, selectionArgs,
				CallLog.Calls.DEFAULT_SORT_ORDER);
		Log.d(TAG, "MyLoaderActivity---------->outcomingCalllog");

	}

	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.in:
				mCallLogShowType = "1";
				//incomingCalllog();
				Intent intent = new Intent().setAction("android.basic.hello");
				System.out.println("sendBroadcast"); 

				sendBroadcast(intent); 
				break;
			case R.id.out:
				mCallLogShowType = "2";
				//outcomingCalllog();
				break;
			default:
				break;
			}

			cursor.onContentChanged();


		}
	}
}