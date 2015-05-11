package com.example.sms;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.search.MessageIDTerm;
import javax.mail.search.SearchTerm;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class MailDetail extends Activity {


	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tv=new TextView(MailDetail.this);

		setContentView(tv);
		new ImageLoadTask(this.getIntent(),tv).execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}





	public class ImageLoadTask extends AsyncTask<Void, Void, Void> {


		private String content="";
		private Intent intent;
		private TextView tv;
		public ImageLoadTask(Intent intent,TextView tv) {
			// TODO Auto-generated constructor stub
			this.intent=intent;
			this.tv=tv;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String msg_id=intent.getStringExtra("Message");




			// TODO Auto-generated method stub
			ArrayList<MailMessage> list=new  ArrayList<MailMessage>();
			Properties props = new Properties();
			props.put( "mail.smtp.auth", "true" );

			Session session = Session. getDefaultInstance(props);

			try {
				Store store = session.getStore( "pop3");
				store.connect( "pop.qq.com","1247686880@qq.com" ,"lovemyself" );
				Folder folder = store.getFolder( "INBOX");
				folder.open(Folder. READ_WRITE);

				SearchTerm term = new MessageIDTerm(msg_id);
				Message msgs[] = folder.search(term);
				int count = msgs .length ;

				for(int i =0;i <count ;i ++){
					MimeMessage msg =  (MimeMessage) msgs[ i];
					MailMessage mail=new MailMessage();
					content+=MimeUtility.decodeText(msg.getSubject())+"\n";
					Address[] address=msg.getFrom();
					for(int j=0;j<address.length;j++)
					{
						content+= MimeUtility.decodeText(address[j].toString()); 
					}
					content+="\n";

					if(!msg.getFolder().isOpen()) //判断是否open  
						msg.getFolder().open(Folder.READ_WRITE); //如果close，就重新open 
						getMailContent(msg);
					content+="\n";

				}
				folder.close( true);

			} catch ( Exception mex ) { }



			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			tv.setText(content); 
		}


		public void getMailContent(Part part) throws Exception {


			String contentType = part .getContentType();

			int nameindex = contentType .indexOf("name");
			boolean conname = false;
			if(nameindex != -1){
				conname = true;
			}
			System. out.println("CONTENTTYPE:" +contentType );
			if(part .isMimeType("text/plain")&&! conname){
				content+=(String) part.getContent();
			} else if (part .isMimeType("text/html")&&! conname){
				content+=(String) part.getContent();
			} else if (part .isMimeType("multipart/*")){
				Multipart multipart = (Multipart) part .getContent();
				int count = multipart .getCount();
				for(int i =0;i <count ;i ++){
					getMailContent( multipart.getBodyPart(i));
				}
			} else if (part .isMimeType("message/rfc822")){
				getMailContent((Part) part.getContent());
			}

		}


	}
}
