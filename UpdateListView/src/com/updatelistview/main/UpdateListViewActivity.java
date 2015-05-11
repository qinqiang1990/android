package com.updatelistview.main;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class UpdateListViewActivity extends Activity {

	private Button addBtn;
	private Button deleteBtn;
	private Button editBtn;
	private Button queryBtn;
	private ListView listview;

	private SimpleAdapter listItemAdapter;
	private ArrayList<HashMap<String, Object>> listItem = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		addBtn = (Button) findViewById(R.id.add_id);
		deleteBtn = (Button) findViewById(R.id.delete_id);
		editBtn = (Button) findViewById(R.id.edit_id);
		queryBtn = (Button) findViewById(R.id.query_id);
		listview = (ListView) findViewById(R.id.show_result);

		init();


		addBtn.setOnClickListener(addClick);
		deleteBtn.setOnClickListener(deleteClick);
		editBtn.setOnClickListener(editClick);
		queryBtn.setOnClickListener(queryClick);

	}


	OnClickListener addClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub


			LayoutInflater inflater = (LayoutInflater) UpdateListViewActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final LinearLayout layout = (LinearLayout) inflater.inflate(
					R.layout.input_add, null);



			new AlertDialog.Builder(UpdateListViewActivity.this)

			.setTitle("title")

			.setIcon(android.R.drawable.ic_dialog_info)

			.setMessage("message")
			.setView(layout)
			.setPositiveButton("ok",
					new DialogInterface.OnClickListener() {
				public void onClick(
						DialogInterface dialoginterface, int i) {

					EditText inputStringr = (EditText) layout
							.findViewById(R.id.input_add_string);

					String str = inputStringr.getText()
							.toString();

					if (str == null || str.equals("")) {

						Toast.makeText(getApplicationContext(),
								"null", Toast.LENGTH_SHORT)
								.show();
					} else {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("viewspot", str);
						map.put("add", R.drawable.right);
						listItem.add(0, map);
						// listItem.add(map);
						listItemAdapter.notifyDataSetChanged();
						Toast.makeText(
								UpdateListViewActivity.this,
								"add:" + str + "",
								Toast.LENGTH_SHORT).show();

					}

				}
			})
			.setNegativeButton("cancel",
					new DialogInterface.OnClickListener() {  
				public void onClick(
						DialogInterface dialoginterface, int i) {
					Toast.makeText(UpdateListViewActivity.this,
							"cancel", Toast.LENGTH_SHORT)
							.show();

				}
			}).show();

		}
	};


	OnClickListener deleteClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			/*
			 * listItem.clear(); 
			 * listItemAdapter.notifyDataSetChanged();
			 */


			LayoutInflater inflater = (LayoutInflater) UpdateListViewActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final LinearLayout layout = (LinearLayout) inflater.inflate(
					R.layout.input_delete, null);



			new AlertDialog.Builder(UpdateListViewActivity.this)

			.setTitle("title")

			.setIcon(android.R.drawable.ic_dialog_info)

			.setMessage("message")
			.setView(layout)
			.setPositiveButton("ok",
					new DialogInterface.OnClickListener() {
				public void onClick(
						DialogInterface dialoginterface, int i) {

					EditText inputNumber = (EditText) layout
							.findViewById(R.id.input_delete_number);

					String str = inputNumber.getText()
							.toString();

					if (str == null || str.equals("")) {

						Toast.makeText(getApplicationContext(),
								"null", Toast.LENGTH_SHORT)
								.show();
					} else {
						int number = Integer.valueOf(str);

						int size = listItem.size();


						if (number >= size) {
							Toast.makeText(
									getApplicationContext(),
									"null",
									Toast.LENGTH_SHORT).show();

						} else {

							String value = listItem.get(number)
									.toString();
							listItem.remove(number);
							listItemAdapter.notifyDataSetChanged();
							Toast.makeText(
									UpdateListViewActivity.this,
									"ok:" + value + "",
									Toast.LENGTH_SHORT).show();

						}
					}

				}
			})
			.setNegativeButton("cal",
					new DialogInterface.OnClickListener() { /* 璁剧疆璺冲嚭绐楀彛鐨勮繑鍥炰簨浠�*/
				public void onClick(
						DialogInterface dialoginterface, int i) {
					Toast.makeText(UpdateListViewActivity.this,
							"鍙栨秷浜嗗垹闄ゆ暟鎹", Toast.LENGTH_SHORT)
							.show();

				}
			}).show();

		}
	};
	 
	OnClickListener editClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 
			LayoutInflater inflater = (LayoutInflater) UpdateListViewActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final LinearLayout layout = (LinearLayout) inflater.inflate(
					R.layout.input_edit, null);
 
			new AlertDialog.Builder(UpdateListViewActivity.this)
			/* */
			.setTitle("淇敼涓�潯鏁版嵁")
			/* */
			.setIcon(android.R.drawable.ic_dialog_info)
			/* */
			.setMessage("璇疯緭鍏ヤ慨鏀圭殑绱㈠紩鍙婂唴瀹")
			.setView(layout)
			.setPositiveButton("纭畾",
					new DialogInterface.OnClickListener() {
				public void onClick(
						DialogInterface dialoginterface, int i) {

					EditText inputEditNumber = (EditText) layout
							.findViewById(R.id.input_edit_number);

					String numberStr = inputEditNumber
							.getText().toString();

					EditText inputEditString = (EditText) layout
							.findViewById(R.id.input_edit_string);

					String editStr = inputEditString.getText()
							.toString();

					if (numberStr == null
							|| numberStr.equals("")) {

						Toast.makeText(getApplicationContext(),
								"璇疯緭鍏ヨ淇敼鐨勭储寮", Toast.LENGTH_SHORT)
								.show();
					} else {
						int number = Integer.valueOf(numberStr);

						int size = listItem.size();
 
						if (number >= size) {
							Toast.makeText(
									getApplicationContext(),
									"娌℃湁鎵惧埌淇敼鐨勬暟鎹储寮",
									Toast.LENGTH_SHORT).show();

						} else {

							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("viewspot", editStr);
							map.put("add", R.drawable.right);

							listItem.set(number, map);
							listItemAdapter
							.notifyDataSetChanged();

							Toast.makeText(
									UpdateListViewActivity.this,
									"鏁版嵁淇敼涓�" + editStr + "",
									Toast.LENGTH_SHORT).show();

						}
					}

				}
			})
			.setNegativeButton("鍙栨秷",
					new DialogInterface.OnClickListener() {  
				public void onClick(
						DialogInterface dialoginterface, int i) {
					Toast.makeText(UpdateListViewActivity.this,
							"鍙栨秷浜嗕慨鏀规暟鎹", Toast.LENGTH_SHORT)
							.show();

				}
			}).show();

		}
	};

	 
	OnClickListener queryClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		 
			listItemAdapter.notifyDataSetChanged();
		}
	};

	private void init() {

		listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 12; i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("viewspot", "北京故宫 颐和园" + i);
			map.put("add", R.drawable.right);
			listItem.add(map);
		}
		listItemAdapter = new SimpleAdapter(getApplicationContext(), listItem
				,R.layout.items, new String[] { "viewspot", "add" }, new int[] {
			R.id.viewspot, R.id.add });
		listview.setAdapter(listItemAdapter);

	}

}
