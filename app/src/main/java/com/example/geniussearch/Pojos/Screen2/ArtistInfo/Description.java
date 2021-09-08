package com.example.geniussearch.Pojos.Screen2.ArtistInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description {
    @SerializedName("dom")
    @Expose
    private Dom dom;


    public Dom getDom() {
        return dom;
    }

    public void setDom(Dom dom) {
        this.dom = dom;
    }
}
