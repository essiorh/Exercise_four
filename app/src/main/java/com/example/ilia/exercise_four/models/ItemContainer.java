package com.example.ilia.exercise_four.models;

/**
 * Created by ilia on 11.06.15.
 */
public class ItemContainer {
    private String mTitle;
    private int mIdRed;
    private boolean mFavorite;
    private String mUrl;

    public ItemContainer(String title, int idRed, boolean favorite, String url) {
        mTitle=title;
        mIdRed=idRed;
        mFavorite=favorite;
        mUrl=url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getIdRed() {
        return mIdRed;
    }

    public void setIdRed(int mIdRed) {
        this.mIdRed = mIdRed;
    }

    public boolean getFavorite() {
        return mFavorite;
    }

    public void setFavorite(boolean mFavorite) {
        this.mFavorite = mFavorite;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
