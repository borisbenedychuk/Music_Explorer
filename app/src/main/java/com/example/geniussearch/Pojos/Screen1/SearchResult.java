package com.example.geniussearch.Pojos.Screen1;

import com.example.geniussearch.Pojos.Screen1.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResult {


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
