package com.example.geniussearch.Pojos.Screen2.ArtistInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistResponse {
    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
