package com.example.geniussearch.Pojos.Screen1;

import com.example.geniussearch.Pojos.Screen1.Hit;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }
}
