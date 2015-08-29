package com.example.sendsms;

import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private EditText messageNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		messageNumber=(EditText)findViewById(R.id.messageNumber);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sndMsg(View v)
	{
		String _messageNumber=messageNumber.getText().toString();
	    String messageText = "Hi , Just SMSed to say hello";
	    String sent = "SMS_SENT";
	    
	    PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
	            new Intent(sent), 0);
	    
	          registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                if(getResultCode() == Activity.RESULT_OK)
                {
                  Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "SMS could not sent",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }, new IntentFilter(sent));
	    
	    SmsManager sms = SmsManager.getDefault();
	    sms.sendTextMessage(_messageNumber, null, messageText, sentPI, null);
	}
}
