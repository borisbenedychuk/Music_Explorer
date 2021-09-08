package com.example.geniussearch.Pojos.Screen1;

import com.example.geniussearch.Pojos.Screen1.PrimaryArtist;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("full_title")
    @Expose
    private String fullTitle;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("primary_artist")
    @Expose
    private PrimaryArtist primaryArtist;

    @SerializedName("song_art_image_url")
    @Expose
    private String songArtImageUrl;


    public String getSongArtImageUrl() {
        return songArtImageUrl;
    }

    public void setSongArtImageUrl(String songArtImageUrl) {
        this.songArtImageUrl = songArtImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PrimaryArtist getPrimaryArtist() {
        return primaryArtist;
    }

    public void setPrimaryArtist(PrimaryArtist primaryArtist) {
        this.primaryArtist = primaryArtist;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }


}
