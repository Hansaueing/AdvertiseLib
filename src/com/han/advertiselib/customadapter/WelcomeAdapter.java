package com.han.advertiselib.customadapter;

import org.json.JSONObject;

import android.app.Dialog;

import com.alimama.config.custom.MMUWelcomeCustomAdapter;
import com.baidu.mobads.AdView;
import com.baidu.mobads.SplashAd;
import com.baidu.mobads.SplashAdListener;

public class WelcomeAdapter extends MMUWelcomeCustomAdapter{

	private Dialog dialog;
	@Override
	public void onFinishClearCache() {
		
	}

	@Override
	public void startRequestAd() {
		try {
			JSONObject json = new JSONObject(getAPPID());
			String AdPlaceID = json.getString("AdPlaceID");
			String AppID = json.getString("AppID");
			AdView.setAppSid(getMMUActivity(), AppID);
			new SplashAd(getMMUActivity(),getAdView(), new SplashAdListener() {
				
				@Override
				public void onAdPresent() {
					notifyMMUAdShowSuccess();
				}
				
				@Override
				public void onAdFailed(String arg0) {
					notifyMMUAdRequestAdFail();
					if(dialog != null && dialog.isShowing()){
						dialog.dismiss();
					}
				}
				
				@Override
				public void onAdDismissed() {
					notifyMMUAdCloseed();
					if(dialog != null && dialog.isShowing()){
						dialog.dismiss();
					}
				}
				
				@Override
				public void onAdClick() {
					notifyMMUAdClicked();
				}
			}, AdPlaceID, true);
			dialog = new Dialog(getMMUActivity(),android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
			dialog.setContentView(getViewContainer());
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
