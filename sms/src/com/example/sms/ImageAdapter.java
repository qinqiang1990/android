package com.example.sms;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class ImageAdapter extends BaseAdapter {


	private Context context;
	private List<MailMessage> list;
	public ImageAdapter(Context context,List<MailMessage> list) {
		this.context=context;
		this.list=list;
	}
	public Context getContext() {
		return context;
	}



	public void setContext(Context context) {
		this.context = context;
	}



	public List<MailMessage> getList() {
		return list;
	}



	public void setList(List<MailMessage> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}
	



	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public MailMessage getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv = new TextView(context);
		MailMessage mail = list.get(position);
		String msg=mail.getFrom()+"\n"+mail.getSubject();
		tv.setText(msg);
		return tv;    

	}

}
