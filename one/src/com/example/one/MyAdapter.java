package com.example.one;

import java.util.List;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.support.v4.view.ViewPager;

public class MyAdapter extends PagerAdapter {

	List<View> viewLists;

	public MyAdapter(List<View> lists) {
		viewLists = lists;
	}

	@Override
	public int getCount() { // è·å¾—size
		// TODO Auto-generated method stub
		return viewLists.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(View view, int position, Object object) // é”?¯Item
	{
		((ViewPager) view).removeView(viewLists.get(position));
	}

	@Override
	public Object instantiateItem(View view, int position) // å®ä¾‹åŒ–Item
	{
		((ViewPager) view).addView(viewLists.get(position), 0);
		return viewLists.get(position);
	}

}