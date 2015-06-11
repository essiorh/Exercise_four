package com.example.ilia.exercise_four.models;

/**
 * Created by ilia on 11.06.15.
 */
public class ItemList {
    private String mVersion;
    private boolean mFavorite;
    private int mResId;
    private String mUrl;
    public ItemList(){
    }
    public ItemList(String version,boolean favorite,int resId,String url){
        mVersion=version;
        mFavorite=favorite;
        mResId=resId;
        mUrl=url;
    }


    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        this.mVersion = mVersion;
    }

    public boolean getFavorite() {
        return mFavorite;
    }

    public void setFavorite(boolean favorite) {
        this.mFavorite = favorite;
    }

    public int getResId() {
        return mResId;
    }

    public void setResId(int resId) {
        this.mResId = resId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
