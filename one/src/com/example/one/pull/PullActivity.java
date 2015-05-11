package com.example.one.pull;

import android.app.Activity;  
import android.database.Cursor;  
import android.net.Uri;  
import android.os.Bundle;  
import android.os.Handler;  
import android.os.Message;  
import android.provider.*;  
import android.util.Log;  
import android.widget.EditText;  
import android.widget.TextView; 
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.one.R;
import com.example.one.observer.AirplaneContentObserver;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

@SuppressLint("NewApi")
public class PullActivity extends Activity implements TabListener{

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		FragmentManager fm = this.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment prev = fm.findFragmentByTag("dialog_about");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);
		new AboutFragment().show(ft, "dialog_about");

		/*     switch(item.getItemId())
		{

		case R.id.save: new AboutFragment().show(ft, "dialog_about");break;
		case R.id.edit:	Toast.makeText(this, "edit", Toast.LENGTH_LONG).show();break;
		}*/
		return super.onContextItemSelected(item);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);

		//搜索视窗，因为showAsAction="ifRoom"，所以图三中出现了搜索按钮  
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search)  
				.getActionView();  

		searchView.setQueryHint( "Search for countries…" );
		searchView.setOnQueryTextListener(new OnQueryTextListener(){

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				Toast.makeText(PullActivity.this, "TextChange", Toast.LENGTH_LONG).show();
				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				Toast.makeText(PullActivity.this, "TextSubmit", Toast.LENGTH_LONG).show();
				return false;
			}});
		//分享视窗，因为showAsAction="never"，所以只能在溢出菜单中才看见到  
		ShareActionProvider mShareActionProvider = (ShareActionProvider) menu  
				.findItem(R.id.menu_share).getActionProvider();  
		Intent share = new Intent(Intent.ACTION_SEND);  

		share.setType("text/plain");
		String extraText="给大家介绍一个好网站，www.jcodecraeer.com";
		share.putExtra(Intent.EXTRA_TEXT, extraText);
		share.putExtra(Intent.EXTRA_SUBJECT, "subject");
		share.putExtra(Intent.EXTRA_TITLE, "title");

		mShareActionProvider.setShareIntent(share); 
		return super.onCreateOptionsMenu(menu);

	}


	private ArrayList<String> mListItems;
	private PullToRefreshListView mListView;
	private SampleListAdapter mAdapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull);

		AirplaneContentObserver   airplaneCO = new AirplaneContentObserver(this,null);  

		Uri airplaneUri = Settings.System.getUriFor(Settings.System.AIRPLANE_MODE_ON);  
		// 注册内容观察者  
		getContentResolver().registerContentObserver(airplaneUri, false, airplaneCO);  

		final ActionBar bar = getActionBar();

		//mViewPager.setPageMarginDrawable(R.drawable.grey_border_inset_lr);
		//mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.page_margin_width));
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.addTab(bar.newTab()
				.setText("TITLE")
				.setTabListener(this));
		bar.addTab(bar.newTab()
				.setText("COMMENT")
				.setTabListener(this));


		mListItems = new ArrayList<String>();
		for (int i = 1; i <= 10; i++) {
			mListItems.add("Item " + Integer.toString(i));
		}


		mListView = (PullToRefreshListView) findViewById(R.id.list_view);
		mAdapter = new SampleListAdapter();
		mListView.setAdapter(mAdapter);


		mListView.setMode(Mode.BOTH);
		mListView.setOnRefreshListener(new OnRefreshListener2<ListView>(){


			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				mListItems = new ArrayList<String>();  
				for (int i = 1; i <= 10; i++) {
					mListItems.add("Item " + Integer.toString(i));
				}
				//mAdapter.notifyDataSetChanged();
				new FinishRefresh().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				int count = mListItems.size();
				mListItems.add("Item " + Integer.toString(++count));
				//	mAdapter.notifyDataSetChanged();
				new FinishRefresh().execute();
			}
		});


	}

	private class SampleListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mListItems.size();
		}

		@Override
		public Object getItem(int index) {
			return mListItems.get(index);
		}

		@Override
		public long getItemId(int index) {
			return index;
		}

		@Override
		public View getView(int index, View view, ViewGroup arg2) {
			if(view == null){
				LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.list_item, null);
			}
			TextView textView = (TextView)view.findViewById(R.id.listItemText);
			textView.setText(mListItems.get(index));
			return view;
		}
	}

	private class FinishRefresh extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result){
			mListView.onRefreshComplete();
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
