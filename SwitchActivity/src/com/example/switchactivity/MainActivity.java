package com.example.switchactivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	ViewFlipper viewFlipper = null;
	float startX;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		viewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper);
	}

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			break;
		case MotionEvent.ACTION_UP:

			if (event.getX() > startX) { // 向右滑动 
				viewFlipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.in_leftright));
				viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.out_leftright));
				viewFlipper.showNext();
			} else if (event.getX() < startX) { // 向左滑动
				viewFlipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.in_rightleft));
				viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.out_rightleft));
				viewFlipper.showPrevious();
			}
			break;
		}

		return super.onTouchEvent(event);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
