package com.sec.android.ad.sample.usexml;

import com.sec.android.ad.AdHubView;
import com.sec.android.ad.AdNotificationListener;

import android.app.Activity;
import android.os.Bundle;

public class AdHubSample_UseXmlActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //if adhub:onlyXml="false"
/*      AdHubView adhubView = (AdHubView)findViewById(R.id.AdLayout);
        adhubView.setListener(new AdNotificationListener(){

			@Override
			public void onAdFailed(AdHubView arg0, Exception arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAdReceived(AdHubView arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        adhubView.startAd();
*/
    }
}