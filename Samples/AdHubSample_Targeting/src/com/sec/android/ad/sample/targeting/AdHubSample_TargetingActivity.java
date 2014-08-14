package com.sec.android.ad.sample.targeting;

import com.sec.android.ad.AdHubView;
import com.sec.android.ad.AdNotificationListener;
import com.sec.android.ad.info.AdSize;
import com.sec.android.ad.targeting.UserProfile;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class AdHubSample_TargetingActivity extends Activity {
	
	private final String TAG = "my";
	
	LocationManager mLocManager;
	AdHubView mAdHubView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mAdHubView = (AdHubView)findViewById(R.id.AdLayout);
        mAdHubView.init(this, "xv0c000000001s", AdSize.BANNER_640x100);
        mAdHubView.setRefreshRate(15*1000);
        
        mAdHubView.setListener(new AdNotificationListener() {
        	public void onAdReceived(AdHubView ad) {
        		// TODO Auto-generated method stub
        		Toast.makeText(AdHubSample_TargetingActivity.this, "onAdReceived", Toast.LENGTH_SHORT).show();
        		Log.d(TAG, "onAdReceived called");
        	}
        	public void onAdFailed(AdHubView ad, Exception arg1) {
        		// TODO Auto-generated method stub
        		Toast.makeText(AdHubSample_TargetingActivity.this, "onAdFailed: "+arg1.toString() , Toast.LENGTH_SHORT).show();
        		Log.d(TAG, "onAdFailed called: " + arg1.toString());
        	}
        });
        
        UserProfile user = new UserProfile();
        user.setGender(UserProfile.FEMALE);
        user.setAge(22);
        user.setInterests("internet");
        user.setInterests("car sales");
        user.setInterests("english");
        
        mAdHubView.setUserProfile(user);
        
        //Location
        Location location;
        if((location = locationSetting()) != null){
        	mAdHubView.setLocation(location.getLatitude(), location.getLongitude());
        	Log.e(TAG, "lat: " + location.getLatitude() + " longi: " + location.getLongitude());
        	//setGeoCoder
        	handler.sendEmptyMessageDelayed(0, 100);
        }
        
        mAdHubView.startAd();
    }
    
    Handler handler = new Handler(){
    	public void handleMessage(Message msg){
    		mAdHubView.setGeoCoder();
		}
    };
    
    private Location locationSetting(){
    	String bestProvider;
    	
    	Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(true); 
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        
        mLocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        bestProvider = mLocManager.getBestProvider(criteria, true);
        if(bestProvider == null){
        	return null;
        }

        mLocManager.requestLocationUpdates(bestProvider, 3*60*1000, 50, mLocationListener);
        
        return mLocManager.getLastKnownLocation(bestProvider);
    }
    
    LocationListener mLocationListener = new LocationListener(){
    	@Override
		public void onLocationChanged(Location loc) {
			// TODO Auto-generated method stub
    		mAdHubView.setLocation(loc.getLatitude(), loc.getLongitude());
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
	};    
}