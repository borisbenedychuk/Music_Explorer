package com.example.geniussearch.Pojos.Screen1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hit {
    @SerializedName("highlights")
    @Expose
    private List<Object> highlights = null;
    @SerializedName("index")
    @Expose
    private String index;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("result")
    @Expose
    private Result result;

    public List<Object> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<Object> highlights) {
        this.highlights = highlights;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
