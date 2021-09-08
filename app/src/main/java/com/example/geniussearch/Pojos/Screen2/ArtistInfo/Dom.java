package com.example.geniussearch.Pojos.Screen2.ArtistInfo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dom {
    @SerializedName("children")
    @Expose
    private JsonArray children;

    public JsonArray getChildren() {
        return children;
    }

    public void setChildren(JsonArray children) {
        this.children = children;
    }
}
