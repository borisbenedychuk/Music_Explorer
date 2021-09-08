package com.example.geniussearch.API;

import com.example.geniussearch.Pojos.Screen1.SearchResult;
import com.example.geniussearch.Pojos.Screen2.ArtistInfo.ArtistResponse;
import com.example.geniussearch.Pojos.Screen2.ArtistSongs.ArtistSongsJSON;
import com.example.geniussearch.Pojos.Screen3.SongInfo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServices {


    @GET("search")
    Single<SearchResult> getSearchResult(@Query("q") String input,
                                                 @Header(RAD_KEY_PARAM) String radKey,
                                                 @Header(RAD_API_HOST_PARAM) String radHost);


    String RAD_KEY_PARAM = "x-rapidapi-key";
    String RAD_API_HOST_PARAM = "x-rapidapi-host";

    @GET("artists/{id}")
    Single<ArtistResponse> getArtistInfo (@Path("id") String id,
                                              @Header(RAD_KEY_PARAM) String radKey,
                                              @Header(RAD_API_HOST_PARAM) String radHost);


    @GET("artists/{id}/songs")
    Single<ArtistSongsJSON> getArtistSongs (@Path("id") String id,
                                                @Query("page") int page,
                                                @Query("sort") String sort,
                                                @Header(RAD_KEY_PARAM) String radKey,
                                                @Header(RAD_API_HOST_PARAM) String radHost);


    @GET("songs/{id}")
    Single<SongInfo> getSongInfo (@Path("id") String id,
                                      @Header(RAD_KEY_PARAM) String radKey,
                                      @Header(RAD_API_HOST_PARAM) String radHost);
}
