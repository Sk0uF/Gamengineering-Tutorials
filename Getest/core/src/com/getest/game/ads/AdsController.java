package com.getest.game.ads;

public interface AdsController {
    public boolean isWifiConnected();
    public boolean isInterstitialLoaded();
    public void showBannerAd();
    public void hideBannerAd();
    public void showInterstitialAd(Runnable then);
}