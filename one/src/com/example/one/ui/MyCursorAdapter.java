package com.example.one.ui;

import com.example.one.R;

import android.content.Context;
import android.database.Cursor; 
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView; 
import android.support.v4.widget.CursorAdapter;

public class MyCursorAdapter extends CursorAdapter {

	public MyCursorAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		if (cursor == null)
			return;
		final String id = cursor.getString(0);
		String number = cursor.getString(1);
		String name = cursor.getString(2);
		int type = cursor.getInt(3);
		String date = cursor.getString(4);
		TextView tv1 = (TextView) view.findViewById(R.id.tv1);
		TextView tv2 = (TextView) view.findViewById(R.id.tv2);
		tv1.setText("id:"+id+" number:"+number);
		tv2.setText("name:"+name+" tyep"+type+" date:"+date);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		return inflater.inflate(R.layout.example_fragment_layout, parent, false);

	}

}