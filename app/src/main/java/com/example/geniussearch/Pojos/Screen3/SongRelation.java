package com.example.geniussearch.Pojos.Screen3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SongRelation {
    @SerializedName("relationship_type")
    @Expose
    private String type;

    @SerializedName("songs")
    @Expose
    private List<SongRelationPath> songRelationPath;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SongRelationPath> getSongRelationPath() {
        return songRelationPath;
    }

    public void setSongRelationPath(List<SongRelationPath> songRelationPath) {
        this.songRelationPath = songRelationPath;
    }
}
