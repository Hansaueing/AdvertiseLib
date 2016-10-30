package com.han.advertiselib.customadapter;

import org.json.JSONObject;

import com.alimama.config.custom.MMUBannerCustomAdapter;
import com.baidu.mobads.AdService;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;

import android.view.View;
import android.widget.RelativeLayout;

public class BannerAdapter extends MMUBannerCustomAdapter{

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
			final RelativeLayout adViewrelativeLayout = new RelativeLayout(getMMUActivity());
			RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			

			adViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			AdService mAdService = new AdService(getMMUActivity(), adViewrelativeLayout, adViewLayoutParams, new AdViewListener() {
				
				@Override
				public void onAdSwitch() {
					
				}
				
				@Override
				public void onAdShow(JSONObject arg0) {
					adViewrelativeLayout.setVisibility(View.VISIBLE);
					notifyMMUAdRequestAdSuccess();
				}
				
				@Override
				public void onAdReady(AdView arg0) {
					
				}
				
				@Override
				public void onAdFailed(String arg0) {
					notifyMMUAdRequestAdFail();
				}
				
				@Override
				public void onAdClick(JSONObject arg0) {
					
				}

				@Override
				public void onAdClose(JSONObject arg0) {
					
				}
			},AdSize.Banner,AdPlaceID);
			adViewrelativeLayout.setVisibility(View.INVISIBLE);				
			addAdView(adViewrelativeLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
