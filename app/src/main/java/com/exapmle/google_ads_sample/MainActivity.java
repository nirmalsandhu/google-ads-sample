package com.exapmle.google_ads_sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;


public class MainActivity extends Activity {

//	Initialize banner AdView
	private AdView bannerAdView;
	
//	Initialise Interstitial Ad
	private InterstitialAd mInterstitialAd;
	
//	Initialise button for ads
	Button interstitialAdBtn, rewardedAdBtn;
	
//	Initialise Rewarded Ad
	private RewardedAd mRewardedAd;
	
	private final String TAG = "MainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	//		calling method to initialize mobile ads sdk
		initializeMobileAdsSdk();

	//		assigning banner ad view
		bannerAdView = findViewById(R.id.bannerAdView);

	//		calling method to load banner ad
		loadBannerAd();
		
	//		assigning interstitial ad button
		interstitialAdBtn = findViewById(R.id.interstitialAdBtn);
		
	//		calling method to load interstitial Ad
		loadInterstitialAd();
		
	//		click to show interstitial ad
		interstitialAdBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showInterstitialAd();
			}
		});
		
//		assigning rewarded ad btn
		rewardedAdBtn = findViewById(R.id.rewardedAdBtn);
		
//		calling method to load rewarded ad
		loadRewardedAd();
		
//		click to show rewarded ad
		rewardedAdBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showRewardedAd();
			}
		});
	}
	
	//	initializing mobile ads sdk
	public void initializeMobileAdsSdk() {
		
		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {
			}
		});
	}
	
	//	loading banner ad
	public void loadBannerAd() {
		AdRequest adRequest = new AdRequest.Builder().build();
		bannerAdView.loadAd(adRequest);
		bannerAdView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				// Code to be executed when an ad finishes loading.
			}
			
			@Override
			public void onAdFailedToLoad(LoadAdError adError) {
				// Code to be executed when an ad request fails.
			}
			
			@Override
			public void onAdOpened() {
				// Code to be executed when an ad opens an overlay that
				// covers the screen.
			}
			
			@Override
			public void onAdClicked() {
				// Code to be executed when the user clicks on an ad.
			}
			
			@Override
			public void onAdLeftApplication() {
				// Code to be executed when the user has left the app.
			}
			
			@Override
			public void onAdClosed() {
				// Code to be executed when the user is about to return
				// to the app after tapping on an ad.
			}
		});
	}
	
//	load interstitial Ad
	public void loadInterstitialAd(){
		mInterstitialAd = new InterstitialAd(this);
		
//		replace ID below with your ID
		mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
		mInterstitialAd.loadAd(new AdRequest.Builder().build());
		
		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				// Code to be executed when an ad finishes loading.
			}
			
			@Override
			public void onAdFailedToLoad(LoadAdError adError) {
				// Code to be executed when an ad request fails.
			}
			
			@Override
			public void onAdOpened() {
				// Code to be executed when the ad is displayed.
			}
			
			@Override
			public void onAdClicked() {
				// Code to be executed when the user clicks on an ad.
			}
			
			@Override
			public void onAdLeftApplication() {
				// Code to be executed when the user has left the app.
			}
			
			@Override
			public void onAdClosed() {
				// Load the next interstitial.
				mInterstitialAd.loadAd(new AdRequest.Builder().build());
			}
		});
	}
	
//	show interstitial Ad on button click
	public void showInterstitialAd() {
		if (mInterstitialAd.isLoaded()) {
			mInterstitialAd.show();
		} else {
			Log.d("TAG", "The interstitial wasn't loaded yet.");
		}
	}
	
//	load rewarded Ad
	public void loadRewardedAd(){
		AdRequest adRequest = new AdRequest.Builder().build();
		
//		replaceId below with your ID
		RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
				adRequest, new RewardedAdLoadCallback(){
					@Override
					public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
						// Handle the error.
						Log.d(TAG, loadAdError.getMessage());
						mRewardedAd = null;
					}
					
					@Override
					public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
						mRewardedAd = rewardedAd;
						Log.d(TAG, "onAdFailedToLoad");
					}
				});
	}
	
//	show rewarded Ad on button click
	public void showRewardedAd(){
		mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
			@Override
			public void onAdShowedFullScreenContent() {
				// Called when ad is shown.
				Log.d(TAG, "Ad was shown.");
				mRewardedAd = null;
			}
			
			@Override
			public void onAdFailedToShowFullScreenContent(AdError adError) {
				// Called when ad fails to show.
				Log.d(TAG, "Ad failed to show.");
			}
			
			@Override
			public void onAdDismissedFullScreenContent() {
				// Called when ad is dismissed.
				// Don't forget to set the ad reference to null so you
				// don't show the ad a second time.
				Log.d(TAG, "Ad was dismissed.");
			}
		});
		if (mRewardedAd != null) {
			Activity activityContext = MainActivity.this;
			mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
				@Override
				public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
					// Handle the reward.
					Log.d("TAG", "The user earned the reward.");
					int rewardAmount = rewardItem.getAmount();
					String rewardType = rewardItem.getType();
				}
			});
		} else {
			Log.d("TAG", "The rewarded ad wasn't ready yet.");
		}
	}
}
