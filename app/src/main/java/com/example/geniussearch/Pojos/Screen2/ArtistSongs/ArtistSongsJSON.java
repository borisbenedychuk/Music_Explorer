package com.example.geniussearch.Pojos.Screen2.ArtistSongs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistSongsJSON {
    @SerializedName("response")
    @Expose
    private ResponseSongs response;

    public ResponseSongs getResponse() {
        return response;
    }

    public void setResponse(ResponseSongs response) {
        this.response = response;
    }
}
