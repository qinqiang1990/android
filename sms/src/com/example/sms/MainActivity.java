package com.example.sms;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final static String SEND_ACTION      = "send";
	private final static String DELIVERED_ACTION = "delivered";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final SmsManager s = SmsManager.getDefault();
		final PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SEND_ACTION),
				PendingIntent.FLAG_CANCEL_CURRENT);
		final PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED_ACTION),
				PendingIntent.FLAG_CANCEL_CURRENT);
		// �������
		registerReceiver(new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "Send Success!", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(), "Send Failed because generic failure cause.",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), "Send Failed because service is currently unavailable.",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), "Send Failed because no pdu provided.", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(), "Send Failed because radio was explicitly turned off.",
							Toast.LENGTH_SHORT).show();
					break;
				default:
					Toast.makeText(getBaseContext(), "Send Failed.", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(SEND_ACTION));

		// �Է��������
		registerReceiver(new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "Delivered Success!", Toast.LENGTH_SHORT).show();
					break;
				default:
					Toast.makeText(getBaseContext(), "Delivered Failed!", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(DELIVERED_ACTION));

		this.findViewById(R.id.sendButton).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ���Ͷ��ţ�sentPI��deliveredPI���ֱ��ڶ��ŷ��ͳɹ��ͶԷ����ܳɹ�ʱ���㲥
				s.sendTextMessage("10010", null, "hello", sentPI, deliveredPI);
				
			}});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
