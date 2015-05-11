package com.example.one.ui;
 

 
import com.example.one.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class LearnFragment   extends FragmentActivity{
	 @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_learn_fragment);
	        
	        
	        //在程序中加入Fragment
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	        
	        TextFragment fragment = new TextFragment();
	        fragmentTransaction.add(R.id.linear, fragment);
	        fragmentTransaction.commit();
	    }
}
 