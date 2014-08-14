package com.sec.android.ad.sample.banner;

import com.sec.android.ad.AdHubView;
import com.sec.android.ad.AdNotificationListener;
import com.sec.android.ad.info.AdSize;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AdHubSampleActivity extends Activity {
	
	private final String TAG = "my";
	
    /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        AdHubView adhubView;
        
        adhubView = (AdHubView)findViewById(R.id.AdLayout);
        adhubView.init(this, "xv0c000000001p", AdSize.BANNER_640x100);
        adhubView.setRefreshRate(15*1000);
        
        adhubView.setListener(new AdNotificationListener() {
        	public void onAdReceived(AdHubView arg0) {
        		// TODO Auto-generated method stub
        		Toast.makeText(AdHubSampleActivity.this, "onAdReceived", Toast.LENGTH_SHORT).show();
        		Log.d(TAG, "onAdReceived called");
        	}
        	public void onAdFailed(AdHubView arg0, Exception arg1) {
        		// TODO Auto-generated method stub
        		Toast.makeText(AdHubSampleActivity.this, "onAdFailed: "+arg1.toString() , Toast.LENGTH_SHORT).show();
        		Log.d(TAG, "onAdFailed called: " + arg1.toString());
        	}
        });
        
        adhubView.startAd();
    }
}