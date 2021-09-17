package com.example.geniussearch.Screens.Screen2;

import com.example.geniussearch.Pojos.Screen2.ArtistInfo.ArtistInfo;
import com.example.geniussearch.Pojos.Screen2.ArtistSongs.ArtistSongsJSON;
import com.example.geniussearch.Pojos.Screen2.ArtistSongs.Song;

import java.util.List;

public interface ArtistMethods {
    void showArtistInfo (ArtistInfo artistInfo);
    void showArtistTopSongs (List<Song> songList);
    void noNetwork ();
    void showContent ();
}
