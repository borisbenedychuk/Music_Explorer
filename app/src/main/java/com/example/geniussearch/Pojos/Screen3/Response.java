package com.example.geniussearch.Pojos.Screen3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("song")
    @Expose
    private Song song;
    public Song getSong() {
        return song;
    }
    public void setSong(Song song) {
        this.song = song;
    }
}
