package com.example.geniussearch.Pojos.Screen2.ArtistInfo;

import com.example.geniussearch.Pojos.Screen2.ArtistInfo.ArtistInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response{
    @SerializedName("artist")
    @Expose
    private ArtistInfo artist;

    public ArtistInfo getArtist() {
        return artist;
    }

    public void setArtist(ArtistInfo artist) {
        this.artist = artist;
    }
}
