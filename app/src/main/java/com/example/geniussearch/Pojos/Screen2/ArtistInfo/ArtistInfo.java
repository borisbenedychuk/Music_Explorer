package com.example.geniussearch.Pojos.Screen2.ArtistInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistInfo {
    @SerializedName("alternate_names")
    @Expose
    private List<String> alternate_names;
    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("header_image_url")
    @Expose
    private String header_image_url;
    @SerializedName("image_url")
    @Expose
    private String image_url;
    @SerializedName("is_meme_verified")
    @Expose
    private boolean is_meme_verified;
    @SerializedName("is_verified")
    @Expose
    private boolean is_verified;
    @SerializedName("name")
    @Expose
    private String name;

    public List<String> getAlternate_names() {
        return alternate_names;
    }

    public void setAlternate_names(List<String> alternate_names) {
        this.alternate_names = alternate_names;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getHeader_image_url() {
        return header_image_url;
    }

    public void setHeader_image_url(String header_image_url) {
        this.header_image_url = header_image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isIs_meme_verified() {
        return is_meme_verified;
    }

    public void setIs_meme_verified(boolean is_meme_verified) {
        this.is_meme_verified = is_meme_verified;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

