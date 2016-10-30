package com.han.advertiselib.customadapter;

import org.json.JSONObject;

import com.alimama.config.custom.MMUInterstitialCustomAdapter;
import com.baidu.mobads.AdView;
import com.baidu.mobads.InterstitialAd;
import com.baidu.mobads.InterstitialAdListener;

public class InsertAdapter extends MMUInterstitialCustomAdapter{

	private InterstitialAd interAd;
	
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
			interAd = new InterstitialAd(getMMUActivity(),AdPlaceID);
			interAd.setListener(new InterstitialAdListener() {

				@Override
				public void onAdReady() {
					notifyMMUAdRequestAdSuccess();
				}

				@Override
				public void onAdPresent() {
					notifyMMUAdShowSuccess();
				}

				@Override
				public void onAdFailed(String arg0) {
					notifyMMUAdRequestAdFail();
				}

				@Override
				public void onAdDismissed() {
					notifyMMUAdCloseed();
				}

				@Override
				public void onAdClick(InterstitialAd arg0) {
					notifyMMUAdClicked();

				}
			});
			interAd.loadAd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

	@Override
	public void startShowAd() {
		if(interAd != null){
			interAd.showAd(getMMUActivity());
		}
	}

}
