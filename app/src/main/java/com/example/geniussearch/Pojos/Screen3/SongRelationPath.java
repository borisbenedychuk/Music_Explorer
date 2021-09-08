package com.example.geniussearch.Pojos.Screen3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongRelationPath {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("song_art_image_url")
    @Expose
    private String songArtImageUrl;

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

    public String getSongArtImageUrl() {
        return songArtImageUrl;
    }

    public void setSongArtImageUrl(String songArtImageUrl) {
        this.songArtImageUrl = songArtImageUrl;
    }
}
