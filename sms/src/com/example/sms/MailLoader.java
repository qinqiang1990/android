package com.example.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;



@SuppressLint("NewApi")
public class MailLoader extends AsyncTaskLoader<List<MailMessage>> {

	@Override
	protected void onStartLoading() {
		// TODO Auto-generated method stub

		forceLoad();

	}

	private static final String TAG = "MailLoader"; 

	public MailLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MailMessage> loadInBackground() {
		// TODO Auto-generated method stub
		ArrayList<MailMessage> list=new  ArrayList<MailMessage>();
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

				list.add(0,mail);

			}
			folder.close( true);

		} catch ( Exception mex ) {
			Log.e(TAG,"send failed, exception: " + mex );
		}

		return list;
	}


}
