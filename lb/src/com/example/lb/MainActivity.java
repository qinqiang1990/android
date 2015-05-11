package com.example.lb;

import com.example.lb.common.data.Config;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button one;
	private Button two;
	private Button three;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		one=(Button) this.findViewById(R.id.one);       
		two=(Button) this.findViewById(R.id.two);       
		three=(Button) this.findViewById(R.id.three); 
		setComponentListener();

	}

	private void setComponentListener() {
		// TODO Auto-generated method stub
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.one:
		{ 
			Intent intent=new Intent();
			intent.putExtra(Config.TITLE, Config.ONE);
			intent.setClass(this, Open.class);
			startActivity(intent);
			break;
		}
		case R.id.two:
		{ 
			Intent intent=new Intent();
			intent.putExtra(Config.TITLE, Config.TWO);
			intent.setClass(this, Open.class);
			startActivity(intent);
			break;
		}
		case R.id.three:
		{ 
			Intent intent=new Intent();
			intent.putExtra(Config.TITLE, Config.THREE);
			intent.setClass(this, Open.class);
			startActivity(intent);
			break;
		}
		}
	}

}
