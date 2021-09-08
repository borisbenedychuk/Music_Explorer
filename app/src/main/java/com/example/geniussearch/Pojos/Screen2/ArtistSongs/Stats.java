package com.example.geniussearch.Pojos.Screen2.ArtistSongs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("hot")
    @Expose
    private boolean hot;

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }
}
