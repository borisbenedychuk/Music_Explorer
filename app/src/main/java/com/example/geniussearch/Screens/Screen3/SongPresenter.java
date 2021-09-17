package com.example.geniussearch.Screens.Screen3;

import android.content.Context;
import android.util.Log;

import com.example.geniussearch.API.APIFactory;
import com.example.geniussearch.API.APIServices;
import com.example.geniussearch.Pojos.Screen3.Album;
import com.example.geniussearch.Pojos.Screen3.Media;
import com.example.geniussearch.Pojos.Screen3.PrimaryArtist;
import com.example.geniussearch.Pojos.Screen3.ProducerArtist;
import com.example.geniussearch.Pojos.Screen3.Song;
import com.example.geniussearch.Pojos.Screen3.SongInfo;
import com.example.geniussearch.Pojos.Screen3.SongRelation;
import com.example.geniussearch.Pojos.Screen3.Stats;
import com.example.geniussearch.Pojos.Screen3.WriterArtist;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SongPresenter {

    private Context context;
    private SongMethods songMethods;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable disposable;

    public SongPresenter(Context context, SongMethods songMethods) {
        this.songMethods = songMethods;
        this.context = context;
    }


    public void loadSongInfo (int id) {
        disposable = APIFactory.getInstance().apiServices().getSongInfo(String.valueOf(id), APIFactory.RAD_KEY, APIFactory.RAD_API_HOST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SongInfo>() {
                    @Override
                    public void accept(SongInfo songInfo) throws Exception {
                        Song song = songInfo.getResponse().getSong();
                        songMethods.showSongInformation(song);
                        Stats stats = song.getStats();
                        if (stats != null) {
                            songMethods.showSongStats(stats);
                        }
                        JsonArray children = song.getDescription().getDom().getChildren();
                        String description  = convertJsonToString(children);
                        List<Media> medias = song.getMedia();
                        Album album = song.getAlbum();
                        if (album != null) {
                            songMethods.showAlbumCover(album);
                        }
                        List<ProducerArtist> producerArtists = song.getProducers();
                        if (producerArtists != null && producerArtists.size() > 0) {
                            songMethods.showProducers(producerArtists);
                        }
                        List<WriterArtist> writerArtists = song.getWriterArtist();
                        if (writerArtists != null && writerArtists.size() > 0) {
                            songMethods.showWriterArtists(writerArtists);
                        }
                        List<SongRelation> songRelations = song.getSongRelations();
                        if (songRelations != null && songRelations.size() > 0) {
                            songMethods.showSongRelation(songRelations);
                        }
                        songMethods.showSongDescription(description);
                        songMethods.showMediaLinks(medias);
                        PrimaryArtist primaryArtist = song.getArtist();
                        songMethods.showPrimaryArtist(primaryArtist);
                        songMethods.showContent();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("TEST3", throwable.getMessage());
                        songMethods.noNetwork();
                    }
                });
        compositeDisposable.add(disposable);
    }

    public String convertJsonToString (JsonArray array) {
        StringBuilder builder = new StringBuilder();
        convertJsonToStringMainLoop(builder, array);
        return builder.toString();
    }

    public void convertJsonToStringMainLoop (StringBuilder builder, JsonArray jsonArray) {
        for (int l = 0; l < jsonArray.size(); l++) {
            if (jsonArray.get(l).isJsonPrimitive()) {
                String string = jsonArray.get(l).getAsString();
                if (!string.startsWith("http"))
                builder.append(string);
            } else {
                JsonElement jsonElement = jsonArray.get(l).getAsJsonObject().get("children");
                if (jsonElement != null)
                convertJsonToStringMainLoop(builder, jsonElement.getAsJsonArray());
            }
        }
    }
    public void disposeDisposable () {
        compositeDisposable.dispose();
    }

}
