package com.example.recyclerviewwithdifferentcardview.models;

public class Ads {
    private String adsTitle;
    private int adsImage;

    public String getAdsTitle() {
        return adsTitle;
    }

    public int getAdsImage() {
        return adsImage;
    }

    public Ads(String adsTitle, int adsImage) {
        this.adsTitle = adsTitle;
        this.adsImage = adsImage;
    }
}
