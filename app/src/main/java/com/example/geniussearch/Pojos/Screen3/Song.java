package com.example.geniussearch.Pojos.Screen3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Song {

    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("song_art_image_thumbnail_url")
    @Expose
    private String imageUrl;
    @SerializedName("song_art_image_url")
    @Expose
    private String bigImageUrl;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("release_date_for_display")
    @Expose
    private String releaseDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("song_art_primary_color")
    @Expose
    private String colorTheme1;
    @SerializedName("song_art_secondary_color")
    @Expose
    private String colorTheme2;
    @SerializedName("song_art_text_color")
    @Expose
    private String textTheme3;
    @SerializedName("album")
    @Expose
    private Album album;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("media")
    @Expose
    private List<Media> media;
    @SerializedName("primary_artist")
    @Expose
    private PrimaryArtist artist;
    @SerializedName("producer_artists")
    @Expose
    private List<ProducerArtist> producers;
    @SerializedName("song_relationships")
    @Expose
    private List<SongRelation> songRelations;
    @SerializedName("writer_artists")
    @Expose
    private List<WriterArtist> writerArtist;

    public List<Media> getMedia() {
        return media;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBigImageUrl() {
        return bigImageUrl;
    }

    public void setBigImageUrl(String bigImageUrl) {
        this.bigImageUrl = bigImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColorTheme1() {
        return colorTheme1;
    }

    public void setColorTheme1(String colorTheme1) {
        this.colorTheme1 = colorTheme1;
    }

    public String getColorTheme2() {
        return colorTheme2;
    }

    public void setColorTheme2(String colorTheme2) {
        this.colorTheme2 = colorTheme2;
    }

    public String getTextTheme3() {
        return textTheme3;
    }

    public void setTextTheme3(String textTheme3) {
        this.textTheme3 = textTheme3;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }


    public PrimaryArtist getArtist() {
        return artist;
    }

    public void setArtist(PrimaryArtist artist) {
        this.artist = artist;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<ProducerArtist> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducerArtist> producers) {
        this.producers = producers;
    }

    public List<SongRelation> getSongRelations() {
        return songRelations;
    }

    public void setSongRelations(List<SongRelation> songRelations) {
        this.songRelations = songRelations;
    }

    public List<WriterArtist> getWriterArtist() {
        return writerArtist;
    }

    public void setWriterArtist(List<WriterArtist> writerArtist) {
        this.writerArtist = writerArtist;
    }
}
