package com.example.geniussearch.Screens.Screen3;

import com.example.geniussearch.Pojos.Screen3.Album;
import com.example.geniussearch.Pojos.Screen3.Description;
import com.example.geniussearch.Pojos.Screen3.Media;
import com.example.geniussearch.Pojos.Screen3.PrimaryArtist;
import com.example.geniussearch.Pojos.Screen3.ProducerArtist;
import com.example.geniussearch.Pojos.Screen3.Song;
import com.example.geniussearch.Pojos.Screen3.SongRelation;
import com.example.geniussearch.Pojos.Screen3.Stats;
import com.example.geniussearch.Pojos.Screen3.WriterArtist;

import java.util.List;

public interface SongMethods {

    void showSongInformation (Song song);
    void showSongStats (Stats stats);
    void showSongDescription (String description);
    void showAlbumCover (Album album);
    void showMediaLinks (List<Media> media);
    void showPrimaryArtist (PrimaryArtist primaryArtist);
    void showProducers (List<ProducerArtist> producerArtists);
    void showSongRelation (List<SongRelation> songRelations);
    void showWriterArtists (List <WriterArtist> writerArtists);
    void noNetwork ();
    void showContent ();
}
