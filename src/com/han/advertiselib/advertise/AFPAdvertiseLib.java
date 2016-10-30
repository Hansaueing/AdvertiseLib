package com.han.advertiselib.advertise;

import com.alimama.config.MMUAdInfo;
import com.alimama.listener.MMUInterstitialListener;
import com.alimama.listener.MMUListener;
import com.alimama.listener.MMUWelcomeListener;
import com.alimama.mobile.sdk.MMUSDK;
import com.alimama.mobile.sdk.config.BannerController;
import com.alimama.mobile.sdk.config.BannerProperties;
import com.alimama.mobile.sdk.config.InsertController;
import com.alimama.mobile.sdk.config.InsertProperties;
import com.alimama.mobile.sdk.config.MMUSDKFactory;
import com.alimama.mobile.sdk.config.MmuProperties;
import com.alimama.mobile.sdk.config.WelcomeAdsListener;
import com.alimama.mobile.sdk.config.WelcomeController;
import com.alimama.mobile.sdk.config.WelcomeProperties;
import com.han.advertiselib.R;
import com.han.advertiselib.customadapter.InsertAdapter;
import com.han.advertiselib.customadapter.WelcomeAdapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public class AFPAdvertiseLib extends AllAdvertise {
	
	private Activity mActivity;
	private String mBannerId;
	private String mInsertId;
	private String mWelcomeId;
	
	private AFPAdvertiseLib(Activity activity,String bannerId,String insertId,String welcomeId){
		
	}
	
	private AFPAdvertiseLib afpAdvertiseLib;
	/**
	 * 实例化AFP广告类
	 * @param activity 需创建afp广告的activity对象
	 * @param bannerId	banner广告位Id
	 * @param insertId	insert广告位Id
	 * @param welcomeId	welcome广告位Id
	 * @return afp广告实例
	 */
	private MMUSDK mmusdk;
	public AFPAdvertiseLib getInstance(Activity activity,String bannerId,String insertId,String welcomeId){
		this.mActivity = activity;
		this.mBannerId = bannerId;
		this.mInsertId = insertId;
		this.mWelcomeId = welcomeId;
		mmusdk = MMUSDKFactory.getMMUSDK();
		if(afpAdvertiseLib == null){
			afpAdvertiseLib = new AFPAdvertiseLib(activity,bannerId,insertId,welcomeId);
		}
		return afpAdvertiseLib;
	}
	
	private BannerProperties bannerProperties;
	private BannerController<?> bannerController;
	MMUListener adsMogoListener = new MMUListener() {
		@Override
		public void onRequestAd() {
			
		}
		
		@Override
		public void onReceiveAd(ViewGroup arg0) {
			
		}
		
		@Override
		public void onRealClickAd() {
			
		}
		
		@Override
		public void onInitFinish() {
			
		}
		
		@Override
		public void onFailedReceiveAd() {
			
		}
		
		@Override
		public boolean onCloseAd() {
			return false;
		}
		
		@Override
		public void onClickAd() {
			
		}
	};
	@Override
	void showBannerAds() {
		ViewGroup nat = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.banner_layout, null);
		bannerProperties = new BannerProperties(mActivity, mBannerId, nat);
		bannerProperties.setStretch(true); 
		bannerProperties.setManualRefresh(false);
		bannerProperties.setMMUListener(adsMogoListener);
		bannerProperties.setAcct(MmuProperties.ACCT.VIEW);
		
		mmusdk.attach(bannerProperties);
		bannerController = bannerProperties.getController();
		
		bannerController.load();
		bannerController.show();
	}
	
	@Override
	void hideBannerAds() {
		if(bannerController!=null){
			return;
		}
		bannerController.close();
	}
	
	private InsertProperties insertProperties;
	private InsertController<?> insertController;
	MMUInterstitialListener insertAdsMogoListener = new MMUInterstitialListener() {
		
		@Override
		public void onShowInterstitialScreen() {
			
		}
		
		@Override
		public boolean onInterstitialStaleDated() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void onInterstitialRealClickAd() {
			
		}
		
		@Override
		public void onInterstitialCloseAd(boolean arg0) {
			
		}
		
		@Override
		public boolean onInterstitialClickCloseButton() {
			return false;
		}
		
		@Override
		public void onInterstitialClickAd() {
			
		}
		
		@Override
		public void onInitFinish() {
			
		}
	};
	@Override
	void showInsertAds() {
		insertProperties = new InsertProperties(mActivity, mInsertId);
		insertProperties.setShowMask(true); 
		insertProperties.setCanThrough(false); 
		insertProperties.setManualRefresh(false);
		insertProperties.setAcct(MmuProperties.ACCT.VIEW);
		insertProperties.addCustomAdapter(200, new InsertAdapter());
		insertProperties.setMMUInterstitialListener(insertAdsMogoListener);
	        
	    mmusdk.attach(insertProperties); 
	        
	    insertController =  insertProperties.getController(); 
	    insertController.loadAd();
	    insertController.show(true);
	    
	}
	@Override
	void hideInsertAds() {
		if(insertController==null){
			return;
		}
		insertController.cancel();
		insertController.close();
	}

	private WelcomeProperties welcomeProperties;
	private WelcomeController welcomeController;
	private MMUWelcomeListener welcomeAdsMogoListener = new MMUWelcomeListener() {
		
		@Override
		public void onWelcomeSucceed() {
			
		}
		
		@Override
		public void onWelcomeRealClickAd() {
			
		}
		
		@Override
		public void onWelcomeError(String arg0) {
			
		}
		
		@Override
		public void onWelcomeClose() {
			
		}
		
		@Override
		public void onWelcomeClickAd() {
			
		}
		
		@Override
		public void onRequestAdSuccess(MMUAdInfo arg0) {
			
		}
	};
	@Override
	void showWelcomeAds() {

		welcomeProperties = new WelcomeProperties(mActivity, mWelcomeId,
				1000, 3000,welcomeAdsMogoListener );
		welcomeProperties.setStyle(R.style.welcome_dialog_style);
		welcomeProperties.setAnimations(R.style.welcome_dialog_animation);
		
		ViewGroup handleContainer = (ViewGroup) mActivity.getLayoutInflater().inflate(
				R.layout.welcome, null);
		welcomeProperties.setWelcomeContainer(handleContainer);
		welcomeProperties.setAcct(MmuProperties.ACCT.VIEW);
		welcomeProperties.addCustomAdapter(200, new WelcomeAdapter());
//		properties.setWidth(720);  
//		properties.setHeight(1280);
        mmusdk.attach(welcomeProperties);
	}
	
	@Override
	void hideWelcomeAds() {
		
	}
	
}
