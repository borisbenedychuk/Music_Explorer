package com.example.geniussearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geniussearch.Adapters.ArtistSongsAdapter;
import com.example.geniussearch.Adapters.ProducerAdapter;
import com.example.geniussearch.Adapters.RemixesAdapter;
import com.example.geniussearch.Adapters.SampledInSongsAdapter;
import com.example.geniussearch.Adapters.WriterAdapter;
import com.example.geniussearch.Pojos.Screen3.Album;
import com.example.geniussearch.Pojos.Screen3.Media;
import com.example.geniussearch.Pojos.Screen3.PrimaryArtist;
import com.example.geniussearch.Pojos.Screen3.ProducerArtist;
import com.example.geniussearch.Pojos.Screen3.Song;
import com.example.geniussearch.Pojos.Screen3.SongRelation;
import com.example.geniussearch.Pojos.Screen3.Stats;
import com.example.geniussearch.Pojos.Screen3.WriterArtist;
import com.example.geniussearch.Screens.Screen3.SongMethods;
import com.example.geniussearch.Screens.Screen3.SongPresenter;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import static com.example.geniussearch.R.drawable.art_placeholder;

public class SongActivity extends AppCompatActivity implements SongMethods {

    private ImageView imageViewSongArt;
    private TextView textViewSongName;
    private ImageView imageViewIsHot;
    private TextView textViewPageViews;
    private TextView textViewReleaseDate;
    private TextView textViewDescription;
    private TextView textViewArtistName;
    private ImageView imageViewArtistArt;
    private TextView textViewReadMoreSA;
    private TextView textViewPageViewLabel;
    private TextView textViewAlbumLabel;
    private TextView textViewProducersLabel;
    private TextView textViewWritersLabel;
    private TextView textViewSampledInLabel;
    private TextView textViewReleaseDateLabel;
    private TextView textViewAboutLabel;
    private TextView textViewRemixesLabel;
    private ConstraintLayout constraintLayoutAlbum;
    private boolean isExpanded = false;

    private ImageView imageViewYT;
    private ImageView imageViewSpotify;
    private ImageView imageViewSoundCl;

    private CardView cardViewArtist;
    private ImageView imageViewAlbum;
    private TextView textViewAlbum;

    private RecyclerView recyclerViewProducers;
    private ProducerAdapter producersArrayAdapter;

    private RecyclerView recyclerViewWriters;
    private WriterAdapter writerArrayAdapter;

    private RemixesAdapter remixesAdapter;
    private SampledInSongsAdapter sampledInSongsAdapter;
    private RecyclerView recyclerViewSampleIn;
    private RecyclerView recyclerViewRemixes;
    private CardView toastC;


    private ScrollView scrollViewSong;
    private ImageView imageViewLoading;
    private ProgressBar progressBar;

    private ImageView imageViewHomeButton;

    private int songId;

    private SongPresenter songPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Intent intentIn = getIntent();
        if (intentIn != null && intentIn.hasExtra("song_id")) {
            songId = intentIn.getIntExtra("song_id", 0);
        }
        songPresenter = new SongPresenter(this, this);
        cardViewArtist = findViewById(R.id.cardViewArtistSA);
        textViewReadMoreSA = findViewById(R.id.textViewReadMoreSA);
        toastC = findViewById(R.id.toast_error_card);
        textViewSongName = findViewById(R.id.textViewSongTitleSA);
        textViewPageViews = findViewById(R.id.textViewPageViewsSA);
        textViewReleaseDate = findViewById(R.id.textViewReleaseDateSA);
        textViewDescription = findViewById(R.id.textViewDescriptionSA);
        imageViewIsHot = findViewById(R.id.imageViewIsHotSA);
        imageViewSongArt = findViewById(R.id.imageViewSongArtSA);
        textViewAlbumLabel = findViewById(R.id.textViewAlbumLabel);
        textViewProducersLabel = findViewById(R.id.textViewProducers);
        textViewWritersLabel = findViewById(R.id.textViewWriters);
        textViewRemixesLabel = findViewById(R.id.textViewRemixes);
        textViewAboutLabel = findViewById(R.id.textViewAboutLabel);
        textViewReleaseDateLabel = findViewById(R.id.textViewRDLabel);
        textViewSampledInLabel = findViewById(R.id.textViewSampledIn);
        constraintLayoutAlbum = findViewById(R.id.constraintAlbumCover);
        imageViewArtistArt = findViewById(R.id.imageViewArtistSA);
        textViewArtistName = findViewById(R.id.textViewArtistNameSA);
        imageViewYT = findViewById(R.id.imageViewYT);
        imageViewSpotify = findViewById(R.id.imageViewSpot);
        imageViewSoundCl = findViewById(R.id.imageViewSoundCl);
        textViewAlbum = findViewById(R.id.textViewAlbumNameSA);
        imageViewAlbum = findViewById(R.id.imageViewAlbumArtSA);
        textViewPageViewLabel = findViewById(R.id.textViewPageViewsLabelSA);
        imageViewHomeButton = findViewById(R.id.imageViewHomeSong);
        recyclerViewProducers = findViewById(R.id.recyclerViewProducers);
        recyclerViewProducers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWriters = findViewById(R.id.recyclerViewWriters);
        recyclerViewWriters.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRemixes = findViewById(R.id.recyclerViewRemixes);
        recyclerViewSampleIn = findViewById(R.id.recyclerViewSampledIn);
        recyclerViewSampleIn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewRemixes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        progressBar = findViewById(R.id.progressBarSongLoading);
        imageViewLoading = findViewById(R.id.imageViewSongLoading);
        scrollViewSong = findViewById(R.id.scrollViewSong);
        imageViewIsHot.setVisibility(View.GONE);
        songPresenter.loadSongInfo(songId);

        imageViewHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOutHome = new Intent(SongActivity.this, MainActivity.class);
                intentOutHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentOutHome);
            }
        });

        textViewReadMoreSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isExpanded) {
                    ViewGroup.LayoutParams params = textViewDescription.getLayoutParams();
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    textViewReadMoreSA.setText(getString(R.string.read_more_Activated));
                    isExpanded = true;
                } else {
                    ViewGroup.LayoutParams params = textViewDescription.getLayoutParams();
                    params.height = convertDpToPx(150);
                    textViewReadMoreSA.setText(getString(R.string.read_more_Default));
                    isExpanded = false;
                }
                Log.d("TEST", "" + convertPxtoDp(textViewDescription.getMeasuredHeight()));
            }
        });
    }

    @Override
    public void showSongInformation(Song song) {
        textViewSongName.setText(song.getTitle());
        if (song.getReleaseDate() != null) {
            textViewReleaseDate.setText(song.getReleaseDate());
            textViewReleaseDate.setVisibility(View.VISIBLE);
            textViewReleaseDateLabel.setVisibility(View.VISIBLE);
        }
        Picasso.get().load(song.getBigImageUrl()).placeholder(art_placeholder).into(imageViewSongArt);
    }

    @Override
    public void noNetwork() {
        Toast toast = new Toast(this);
        toast.setView(getLayoutInflater().inflate(R.layout.toast_error, toastC));
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
        finish();
    }

    @Override
    public void showContent() {
        scrollViewSong.setVisibility(View.VISIBLE);
        imageViewLoading.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSongStats(Stats stats) {
        if (stats.getPageViews() > 1000) {
            textViewPageViews.setText(String.valueOf(new DecimalFormat("0,000").format(stats.getPageViews())));
            textViewPageViewLabel.setVisibility(View.VISIBLE);
        }
        if (stats.isHot()) {
            imageViewIsHot.setVisibility(View.VISIBLE);
        } else {
            imageViewIsHot.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSongDescription(String description) {
        if (description.length() > 500) {
            textViewDescription.setText(description);
            textViewAboutLabel.setVisibility(View.VISIBLE);
        } else if (description.length() < 500 && description.length() > 20) {
            textViewDescription.setText(description);
            ViewGroup.LayoutParams params = textViewDescription.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            textViewReadMoreSA.setVisibility(View.GONE);
            textViewAboutLabel.setVisibility(View.VISIBLE);

        } else {
            textViewDescription.setVisibility(View.GONE);
            textViewReadMoreSA.setVisibility(View.GONE);
        }
    }



    @Override
    public void showAlbumCover(Album album) {
        textViewAlbum.setVisibility(View.VISIBLE);
        textViewAlbum.setText(album.getName());
        constraintLayoutAlbum.setVisibility(View.VISIBLE);
        Picasso.get().load(album.getAlbumArtImageUrl()).into(imageViewAlbum);
        textViewAlbumLabel.setVisibility(View.VISIBLE);
        imageViewAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOutAlbum = new Intent(Intent.ACTION_VIEW, Uri.parse(album.getUrl()));
                startActivity(intentOutAlbum);
            }
        });

    }

    @Override
    public void showMediaLinks(List<Media> medias) {
        for (Media media : medias) {
            switch (media.getProvider()) {
                case "youtube":
                    imageViewYT.setVisibility(View.VISIBLE);
                    imageViewYT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intentOutYT = new Intent(Intent.ACTION_VIEW, Uri.parse(media.getUrl()));
                            startActivity(intentOutYT);
                        }
                    });
                    break;
                case "spotify":
                    imageViewSpotify.setVisibility(View.VISIBLE);
                    imageViewSpotify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intentOutSpotify = new Intent(Intent.ACTION_VIEW, Uri.parse(media.getUrl()));
                            startActivity(intentOutSpotify);
                        }
                    });

                    break;
                case "soundcloud":
                    imageViewSoundCl.setVisibility(View.VISIBLE);
                    imageViewSoundCl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intentOutSoundCl = new Intent(Intent.ACTION_VIEW, Uri.parse(media.getUrl()));
                            startActivity(intentOutSoundCl);
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public void showPrimaryArtist(PrimaryArtist primaryArtist) {
        textViewArtistName.setText(primaryArtist.getName());
        Picasso.get().load(primaryArtist.getImageUrl()).into(imageViewArtistArt);
        cardViewArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOut = new Intent(SongActivity.this, ArtistActivity.class);
                intentOut.putExtra("artist_id", primaryArtist.getId());
                startActivity(intentOut);
            }
        });


    }

    @Override
    public void showProducers(List<ProducerArtist> producerArtists) {
        Log.d("TEST6", "" + producerArtists.size());
        producersArrayAdapter = new ProducerAdapter();
        producersArrayAdapter.setProducerArtistList(producerArtists);
        recyclerViewProducers.setAdapter(producersArrayAdapter);
        if (producersArrayAdapter.getItemCount() > 0) {
            textViewProducersLabel.setVisibility(View.VISIBLE);
        }
        producersArrayAdapter.setOnProducerClickListener(new ProducerAdapter.OnProducerClickListener() {
            @Override
            public void onProducerClick(int id) {
                Intent intentOutProd = new Intent(SongActivity.this, ArtistActivity.class);
                intentOutProd.putExtra("artist_id", id);
                startActivity(intentOutProd);
            }
        });
    }

    @Override
    public void showSongRelation(List<SongRelation> songRelations) {
        Log.d("TEST6", "" + songRelations.size());
        for (SongRelation songRelation : songRelations) {
            if (songRelation.getType().equals("sampled_in")) {
                sampledInSongsAdapter = new SampledInSongsAdapter();
                sampledInSongsAdapter.setSongList(songRelation.getSongRelationPath());
                recyclerViewSampleIn.setAdapter(sampledInSongsAdapter);
                if (sampledInSongsAdapter.getItemCount() > 0) {
                    textViewSampledInLabel.setVisibility(View.VISIBLE);
                }
                sampledInSongsAdapter.setOnSongClickListener(new ArtistSongsAdapter.OnSongClickListener() {
                    @Override
                    public void onSongClick(int id) {
                        Intent intentOutSampledIn = new Intent(SongActivity.this, SongActivity.class);
                        intentOutSampledIn.putExtra("song_id", id);
                        startActivity(intentOutSampledIn);
                    }
                });
            } else if (songRelation.getType().equals("remixed_by")) {
                remixesAdapter = new RemixesAdapter();
                remixesAdapter.setSongList(songRelation.getSongRelationPath());
                recyclerViewRemixes.setAdapter(remixesAdapter);
                if (remixesAdapter.getItemCount() > 0) {
                    textViewRemixesLabel.setVisibility(View.VISIBLE);
                }
                remixesAdapter.setOnSongClickListener(new ArtistSongsAdapter.OnSongClickListener() {
                    @Override
                    public void onSongClick(int id) {
                        Intent intentOutRemixes = new Intent(SongActivity.this, SongActivity.class);
                        intentOutRemixes.putExtra("song_id", id);
                        startActivity(intentOutRemixes);
                    }
                });
            }
        }
    }

    @Override
    public void showWriterArtists(List<WriterArtist> writerArtists) {
        Log.d("TEST6", "" + writerArtists.size());
        writerArrayAdapter = new WriterAdapter();
        writerArrayAdapter.setWriters(writerArtists);
        recyclerViewWriters.setAdapter(writerArrayAdapter);
        if (writerArrayAdapter.getItemCount() > 0) {
            textViewWritersLabel.setVisibility(View.VISIBLE);
        }
        writerArrayAdapter.setOnWriterClickListener(new WriterAdapter.OnWriterClickListener() {
            @Override
            public void onWriterClick(int id) {
                Intent intentOutWrit = new Intent(SongActivity.this, ArtistActivity.class);
                intentOutWrit.putExtra("artist_id", id);
                startActivity(intentOutWrit);
            }
        });
    }

    private int convertPxtoDp(int px) {
        int density = getResources().getDisplayMetrics().densityDpi;
        return Math.round(px / (density / 160f));
    }

    private int convertDpToPx(int dp) {
        int density = getResources().getDisplayMetrics().densityDpi;
        return Math.round(dp * density / 160f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        songPresenter.disposeDisposable();
    }
}