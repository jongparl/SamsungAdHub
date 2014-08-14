package com.sec.android.ad.sample.interstitial;

import com.sec.android.ad.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AdHubSample_InterstitialActivity extends Activity {
	
	private final String TAG = "my";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        AdHubInterstitial adHubInterstitial = new AdHubInterstitial(this, "xv0c000000001r");

        adHubInterstitial.setListener(new AdInterstitialListener() {			
			@Override
			public void onAdInterstitialReceived(AdHubInterstitial ad) {
				Toast.makeText(AdHubSample_InterstitialActivity.this, "onAdInterstitialReceived", Toast.LENGTH_SHORT).show();
        		Log.d(TAG, "onAdInterstitialReceived called");
			}
			
			@Override
			public void onAdInterstitialFailed(AdHubInterstitial ad, Exception e) {
				Toast.makeText(AdHubSample_InterstitialActivity.this, "onAdInterstitialFailed", Toast.LENGTH_SHORT).show();
        		Log.d(TAG, "onAdInterstitialFailed : " + e.toString());
			}
			
			@Override
			public void onAdInterstitialClosed(AdHubInterstitial ad) {
				Toast.makeText(AdHubSample_InterstitialActivity.this, "onAdInterstitialClosed", Toast.LENGTH_SHORT).show();
        		Log.d(TAG, "onAdInterstitialClosed called");
			}
		});
        
        adHubInterstitial.startAd();
    }
}