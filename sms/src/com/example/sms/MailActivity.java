package com.example.sms;

import java.util.ArrayList;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;

@SuppressLint("NewApi")
public class MailActivity extends Activity {
//implements LoaderCallbacks<List<MailMessage>>{

	private PullToRefreshListView listview;
	private ImageAdapter adapter;
	private ArrayList<MailMessage> list=new ArrayList<MailMessage>();
	private String TAG="Mail";
	private MailLoader loader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Thread t=new Thread(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Mail.setmail();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			};
			t.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	listview = new PullToRefreshListView(this);
		listview.setBackgroundColor(Color.GRAY);
		setContentView(listview);
		adapter = new ImageAdapter(MailActivity.this,list);
	 	listview.setAdapter(adapter);
		listview.setMode(Mode.PULL_DOWN_TO_REFRESH);
		listview.setOnRefreshListener(new OnRefreshListener<ListView>(){

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub

				new FinishRefresh().execute();
			}});
		getLoaderManager().initLoader(0, null, MailActivity.this);

		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MailActivity.this, MailDetail.class);//从一个activity跳转到另一个activity  
				intent.putExtra("Message",adapter.getItem(position-1).getId());//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
				startActivity(intent);  

			}});*/
		//new MailTask(listview).execute();

	}
/*
	private class FinishRefresh extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			loader.onContentChanged();
			return null;
		}

		@Override
		protected void onPostExecute(Void result){
			listview.onRefreshComplete();
		}
	}

	class MailTask  extends AsyncTask {


		private ListView listview;

		public MailTask(ListView listview) {
			this.listview = listview;

		}



		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			getmail();
			return null;
		}	

		@Override
		protected void onProgressUpdate(Object... values) {
			// TODO Auto-generated method stub
			adapter.setList(list);
			adapter.notifyDataSetChanged();
		}


		public void getmail()
		{

			Properties props = new Properties();
			props.put( "mail.smtp.auth", "true" );

			Session session = Session. getDefaultInstance(props);

			try {
				Store store = session.getStore( "pop3");
				store.connect( "pop.qq.com","1247686880@qq.com" ,"lovemyself" );
				Folder folder = store.getFolder( "INBOX");
				folder.open(Folder. READ_ONLY);
				Message msgs[] = folder.getMessages();
				int count = msgs .length ;

				Log.i(TAG,"Message Count:" +count );

				for(int i =0;i <count ;i ++){
					MimeMessage msg =  (MimeMessage) msgs[ i];
					MailMessage mail=new MailMessage();
					mail.setId(msg.getMessageID());
					mail.setSubject( MimeUtility.decodeText(msg.getSubject()));
					String stradd="";
					Address[] address=msg.getFrom();
					for(int j=0;j<address.length;j++)
					{
						stradd+= MimeUtility.decodeText(address[j].toString()); 
					}
					mail.setFrom(stradd.toString());

					list.add(mail);
					publishProgress(); // 通知去更新UI
				}
				folder.close( true);

			} catch ( Exception mex ) {
				Log.e(TAG,"send failed, exception: " + mex );
			}

		}


	}


*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

/*

	@Override
	public Loader<List<MailMessage>> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		loader=new MailLoader(MailActivity.this);
		return loader;
	}



	@Override
	public void onLoadFinished(Loader<List<MailMessage>> mail,
			List<MailMessage> list) {
		// TODO Auto-generated method stub
		adapter.setList(list);
	}



	@Override
	public void onLoaderReset(Loader<List<MailMessage>> loader) {
		// TODO Auto-generated method stub
		adapter.setList(list);
	}
*/





}
