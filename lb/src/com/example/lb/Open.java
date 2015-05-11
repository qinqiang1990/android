package com.example.lb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.lb.common.data.Config;
import com.example.lb.common.data.ListViewPageAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Open extends Activity  {
	private String result;
	private ListView listview; 
	private ListViewPageAdapter fa;
	
	private Button select;

	// 创建数据.要展现的
	public List<HashMap<String,Object>> createData() {
		List<HashMap<String,Object>> datas = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < 20; i++) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("infotime", "人生的辉煌在于_" + i);
			map.put("cbox", false);

			datas.add(map);
		}
		return datas;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.open);

		listview=(ListView)this.findViewById(R.id.listview);

		Intent intent=getIntent();
		result=intent.getStringExtra(Config.TITLE);

		if(result.equals(Config.ONE)){
			fa=new ListViewPageAdapter(this, createData());
			listview.setAdapter(fa);

		/*	listview.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					HashMap<String,Object> map=(HashMap<String, Object>) fa.getItem(position);
					Toast.makeText(Open.this, map.toString(), Toast.LENGTH_SHORT).show();
				}

			});*/
			/*fa=new SimpleAdapter(this,createData(),R.layout.common,new String[]{"infotime"},new int[]{R.id.infotime});
			ArrayAdapter fa = new ArrayAdapter(this,android.R.layout.simple_list_item_checked,createData()); 

            listview.setAdapter(fa);

            listview.setItemsCanFocus(false);
            listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            listview.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					listview.setItemChecked(position, true);
				}

             });*/
		}
		if(result.equals(Config.TWO)){
			//fa=new ListViewPageAdapter(this, createknowledgeData(),createData2(),createChecked());
			//listview.setAdapter(fa);
		}
		//	one=(Button) this.findViewById(R.id.one);       
		//	two=(Button) this.findViewById(R.id.two);       
		//	three=(Button) this.findViewById(R.id.three); 

		select=(Button) this.findViewById(R.id.select);   
		select.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fa.setAllItemsChcekd();
			}});
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
