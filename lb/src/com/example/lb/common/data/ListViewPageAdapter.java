package com.example.lb.common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.lb.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class ListViewPageAdapter extends BaseAdapter {

	List<HashMap<String,Object>> datas = new ArrayList<HashMap<String,Object>>();
	private Context context;
	public ListViewPageAdapter(Context context,List datas)
	{
		this.context = context;
		this.datas=(List<HashMap<String, Object>>) datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater flater = LayoutInflater.from(context);
		View view = flater.inflate(R.layout.common, null);
		CheckBox cb = (CheckBox) view.findViewById(R.id.cbox);
		TextView tvtime = (TextView) view.findViewById(R.id.infotime);
		final HashMap<String, Object> map = datas.get(position);
		cb.setChecked((Boolean) map.get("cbox"));
		tvtime.setText((CharSequence) map.get("infotime"));
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				map.put("cbox",!(Boolean) map.get("cbox"));
			}});
		return view;
	}


	public void setAllItemsChcekd() {

		for(int i=0;i<datas.size();i++)
		{
			HashMap<String, Object>temp=	(HashMap<String, Object>)datas.get(i);
	 
			temp.put("cbox",!(Boolean) temp.get("cbox"));
		}

		notifyDataSetChanged();
	}

}
