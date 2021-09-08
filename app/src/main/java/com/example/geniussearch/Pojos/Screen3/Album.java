package com.example.geniussearch.Pojos.Screen3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {
    @SerializedName("cover_art_url")
    @Expose
    private String albumArtImageUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;

    public String getAlbumArtImageUrl() {
        return albumArtImageUrl;
    }

    public void setAlbumArtImageUrl(String albumArtImageUrl) {
        this.albumArtImageUrl = albumArtImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
