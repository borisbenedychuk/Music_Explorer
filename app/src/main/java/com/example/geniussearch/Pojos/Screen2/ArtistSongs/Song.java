package com.example.geniussearch.Pojos.Screen2.ArtistSongs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("full_title")
    @Expose
    private String fullTitle;
    @SerializedName("header_image_url")
    @Expose
    private String headerImageUrl;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("song_art_image_thumbnail_url")
    @Expose
    private String songArtImageThumbnailUrl;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_with_featured")
    @Expose
    private String titleWithFeatured;

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getHeaderImageUrl() {
        return headerImageUrl;
    }

    public void setHeaderImageUrl(String headerImageUrl) {
        this.headerImageUrl = headerImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongArtImageThumbnailUrl() {
        return songArtImageThumbnailUrl;
    }

    public void setSongArtImageThumbnailUrl(String songArtImageThumbnailUrl) {
        this.songArtImageThumbnailUrl = songArtImageThumbnailUrl;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleWithFeatured() {
        return titleWithFeatured;
    }

    public void setTitleWithFeatured(String titleWithFeatured) {
        this.titleWithFeatured = titleWithFeatured;
    }
}
