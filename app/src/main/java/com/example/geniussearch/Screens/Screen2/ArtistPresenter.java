package com.example.geniussearch.Screens.Screen2;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.geniussearch.API.APIFactory;
import com.example.geniussearch.Pojos.Screen2.ArtistInfo.ArtistInfo;
import com.example.geniussearch.Pojos.Screen2.ArtistInfo.ArtistResponse;
import com.example.geniussearch.Pojos.Screen2.ArtistSongs.ArtistSongsJSON;
import com.example.geniussearch.Pojos.Screen2.ArtistSongs.Song;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ArtistPresenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Context context;
    private ArtistMethods methods;

    public ArtistPresenter(Context context, ArtistMethods methods) {
        this.context = context;
        this.methods = methods;
    }

    public void loadData(int id) {
        Log.d("TEST", String.valueOf(id));
        Disposable disposable = APIFactory.getInstance().apiServices()
                .getArtistInfo(String.valueOf(id), APIFactory.RAD_KEY, APIFactory.RAD_API_HOST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArtistResponse>() {
                    @Override
                    public void accept(ArtistResponse artistResponse) throws Exception {
                        ArtistInfo artistInfo = artistResponse.getResponse().getArtist();
                        JsonArray jsonArray = artistInfo.getDescription().getDom().getChildren();
                        methods.showArtistInfo(artistInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("TEST2", throwable.getMessage());
                        methods.noNetwork();
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void loadSongs(int id) {
        Log.d("TEST", String.valueOf(id));
        Disposable disposable = APIFactory.getInstance().apiServices()
                .getArtistSongs(String.valueOf(id),1, APIFactory.SORT_BY_POPULARITY, APIFactory.RAD_KEY, APIFactory.RAD_API_HOST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArtistSongsJSON>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void accept(ArtistSongsJSON artistSongsJSON) throws Exception {
                        List<Song> songs = artistSongsJSON.getResponse().getSongs();
                        if ( songs != null && songs.size() != 0) {
                            Log.d("TEST2", "" + songs.get(0).getId());
                            methods.showArtistTopSongs(songs.stream().limit(18).collect(Collectors.toList()));
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("TEST2", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void disposeDisposable() {
        compositeDisposable.dispose();
    }


    public String convertJsonToString (JsonArray jsonArray) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.get(i).isJsonPrimitive()) {
                builder.append(jsonArray.get(i).getAsString());
                builder.append("\n");
                builder.append("\n");
            } else {
                JsonElement jsonElement = jsonArray.get(i).getAsJsonObject().get("children");
                if (jsonElement != null) {
                    convertJsonToStringMainLoop(builder, jsonElement.getAsJsonArray());
                }
            }
        }
        return builder.toString();
    }

    public void convertJsonToStringMainLoop (StringBuilder builder, JsonArray jsonArray) {
        for (int l = 0; l < jsonArray.size(); l++) {
            if (jsonArray.get(l).isJsonPrimitive()) {
                builder.append(jsonArray.get(l).getAsString());
            } else {
                JsonElement jsonElement = jsonArray.get(l).getAsJsonObject().get("children");
                if (jsonElement != null) {
                    convertJsonToStringMainLoop(builder, jsonElement.getAsJsonArray());
                }
            }
        }
    }

}
