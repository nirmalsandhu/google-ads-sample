package com.exapmle.google_ads_sample;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MyApplication extends Application {
	
	private static AppOpenManager appOpenManager;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
//		Initialise Mobile Ads Sdk
		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {
			}
		});
		
		appOpenManager = new AppOpenManager(this);
	}
}
