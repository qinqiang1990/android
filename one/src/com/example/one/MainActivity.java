package com.example.one;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);

	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.save:
			Toast.makeText(getApplicationContext(), "save", Toast.LENGTH_LONG).show();
			break;
		case R.id.edit:
			Toast.makeText(getApplicationContext(), "edit", Toast.LENGTH_LONG).show();
			break;
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP	| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			Toast.makeText(getApplicationContext(), "android.R.id.home", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}



	private ViewPager viewPager;
	private ImageView imageView;
	private List<View> lists = new ArrayList<View>();
	private MyAdapter myAdapter;
	private Bitmap cursor;
	private int offSet;
	private int currentItem;
	private Matrix matrix = new Matrix();
	private int bmWidth;
	private Animation animation;
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageView = (ImageView) findViewById(R.id.cursor);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);

		lists.add(getLayoutInflater().inflate(R.layout.layout1, null));
		lists.add(getLayoutInflater().inflate(R.layout.layout2, null));
		lists.add(getLayoutInflater().inflate(R.layout.layout3, null));

		initeCursor();

		myAdapter = new MyAdapter(lists);

		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(myAdapter);


		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) { 
				// TODO Auto-generated method stub
				switch (arg0) {
				case 0:
					if (currentItem == 1) {
						animation = new TranslateAnimation(
								offSet * 2 + bmWidth, 0, 0, 0);
					} else if (currentItem == 2) {
						animation = new TranslateAnimation(offSet * 4 + 2
								* bmWidth, 0, 0, 0);
					}
					break;
				case 1:
					if (currentItem == 0) {
						animation = new TranslateAnimation(0, offSet * 2
								+ bmWidth, 0, 0);
					} else if (currentItem == 2) {
						//TODO
						animation = new TranslateAnimation(2 * offSet + 2
								* bmWidth, offSet * 2 + bmWidth, 0, 0);
					}
					break;
				case 2:
					if (currentItem == 0) {
						animation = new TranslateAnimation(0, 4 * offSet + 2
								* bmWidth, 0, 0);
					} else if (currentItem == 1) {
						animation = new TranslateAnimation(
								offSet * 2 + bmWidth, 4 * offSet + 2 * bmWidth,
								0, 0);
					}
				}
				currentItem = arg0;

				animation.setDuration(500);
				animation.setFillAfter(true);
				imageView.startAnimation(animation);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		textView1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(0);
			}
		});

		textView2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(1);
			}
		});

		textView3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(2);
			}
		});
	}


	private void initeCursor() {
		cursor = BitmapFactory.decodeResource(getResources(), R.drawable.cursor);
		bmWidth = cursor.getWidth();

		DisplayMetrics dm;
		dm = getResources().getDisplayMetrics();

		offSet = (dm.widthPixels - 3 * bmWidth) / 6;
		matrix.setTranslate(offSet, 0);
		imageView.setImageMatrix(matrix); 
		currentItem = 0;
	}
}