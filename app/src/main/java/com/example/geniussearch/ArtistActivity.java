package com.example.geniussearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geniussearch.Adapters.ArtistSongsAdapter;
import com.example.geniussearch.Pojos.Screen2.ArtistInfo.ArtistInfo;
import com.example.geniussearch.Pojos.Screen2.ArtistSongs.Song;
import com.example.geniussearch.Screens.Screen2.ArtistMethods;
import com.example.geniussearch.Screens.Screen2.ArtistPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class ArtistActivity extends AppCompatActivity implements ArtistMethods {

    private ImageView imageViewBG;
    private ImageView imageViewPRPict;
    private TextView textViewArtistName;
    private TextView textViewAltNames;
    private TextView textViewDescription;
    private TextView textViewReadMore;
    private TextView textViewMPSLabel;
    private RecyclerView recyclerViewArtistSongs;
    private TextView textViewAbout;
    private CardView toastC;

    private ArtistPresenter artistPresenter;
    private ArtistSongsAdapter adapter;

    private ProgressBar progressBarLoading;
    private ImageView imageViewLoading;
    private ScrollView scrollViewArtist;

    private ImageView imageViewHomeButton;

    private Boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        Intent intentIn = getIntent();
        int id = intentIn.getIntExtra("artist_id", 0);
        imageViewBG = findViewById(R.id.imageViewABG);
        toastC = findViewById(R.id.toast_error_card);
        imageViewPRPict = findViewById(R.id.imageViewProfilePicture);
        textViewArtistName = findViewById(R.id.textViewArtistName);
        textViewAltNames = findViewById(R.id.textViewAleternateNames);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewReadMore = findViewById(R.id.textViewReadMore);
        textViewAbout = findViewById(R.id.textViewAboutLabelAA);
        textViewMPSLabel = findViewById(R.id.textViewMPSLAbel);
        recyclerViewArtistSongs = findViewById(R.id.recyclerViewArtistSongs);
        imageViewLoading = findViewById(R.id.imageViewArtistLoading);
        progressBarLoading = findViewById(R.id.progressBarArtistLoading);
        scrollViewArtist = findViewById(R.id.scrollViewArtist);
        imageViewHomeButton = findViewById(R.id.imageViewHomeArtist);
        adapter = new ArtistSongsAdapter();
        recyclerViewArtistSongs.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewArtistSongs.setAdapter(adapter);
        artistPresenter = new ArtistPresenter(this, ArtistActivity.this);
        artistPresenter.loadData(id);
        artistPresenter.loadSongs(id);
        imageViewHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOutHome = new Intent(ArtistActivity.this, MainActivity.class);
                intentOutHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentOutHome);
            }
        });

        textViewReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = textViewDescription.getLayoutParams();
                if (!isExpanded) {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    textViewReadMore.setText(getString(R.string.read_more_Activated));
                    isExpanded = true;
                } else if (isExpanded) {
                    params.height = convertDpToPx(150);
                    textViewReadMore.setText(getString(R.string.read_more_Default));
                    isExpanded = false;
                }
                Log.d("TEST", "" + convertPxtoDp(textViewDescription.getMeasuredHeight()));
            }
        });
        adapter.setOnSongClickListener(new ArtistSongsAdapter.OnSongClickListener() {
            @Override
            public void onSongClick(int id) {
                Intent intentOut = new Intent(ArtistActivity.this, SongActivity.class);
                intentOut.putExtra("song_id", id);
                startActivity(intentOut);
            }
        });



    }

    @Override
    public void showArtistInfo(ArtistInfo artistInfo) {
        if (artistInfo != null) {
            Picasso.get()
                    .load(artistInfo.getImage_url())
                    .placeholder(R.drawable.art_placeholder)
                    .into(imageViewPRPict);
            Picasso.get()
                    .load(artistInfo.getHeader_image_url())
                    .placeholder(R.drawable.bg_placeholder)
                    .centerCrop()
                    .resize(400, 300)
                    .transform(new BlurTransformation(this, 2))
                    .into(imageViewBG);
            textViewArtistName.setText(artistInfo.getName());
            List<String> altNames = artistInfo.getAlternate_names();
            StringBuilder builder = new StringBuilder();
            if (altNames.size() > 0) {
                builder.append("AKA ");
                for (int i = 0; i < altNames.size(); i++) {
                    if (i == altNames.size() - 1) {
                        builder.append(altNames.get(i));
                    } else {
                        builder.append(altNames.get(i) + ", ");
                    }
                }
            }
            if (!builder.toString().isEmpty()) {
                textViewAltNames.setText(builder.toString());
            }
            String artistDescription = artistPresenter.convertJsonToString(artistInfo.getDescription().getDom().getChildren()).split("Дискография")[0].split("Контакты")[0];
            if (artistDescription.length() > 500) {
                textViewDescription.setText(artistDescription);
                textViewAbout.setVisibility(View.VISIBLE);
                textViewDescription.setVisibility(View.VISIBLE);
                textViewReadMore.setVisibility(View.VISIBLE);
            } else if (artistDescription.length() < 500 && artistDescription.length() > 20) {
                ViewGroup.LayoutParams params = textViewDescription.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                textViewDescription.setText(artistDescription);
                textViewAbout.setVisibility(View.VISIBLE);
                textViewDescription.setVisibility(View.VISIBLE);
                textViewReadMore.setVisibility(View.GONE);
            } else {
                textViewDescription.setVisibility(View.GONE);
                textViewReadMore.setVisibility(View.GONE);
                textViewAbout.setVisibility(View.GONE);
            }
        }
        scrollViewArtist.setVisibility(View.VISIBLE);
        imageViewLoading.setVisibility(View.GONE);
        progressBarLoading.setVisibility(View.GONE);

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
    public void showArtistTopSongs(List<Song> songList) {
        adapter.setSongList(songList);
        textViewMPSLabel.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        artistPresenter.disposeDisposable();

    }

    private int convertPxtoDp(int px) {
        int density = getResources().getDisplayMetrics().densityDpi;
        return Math.round(px / (density / 160f));
    }

    private int convertDpToPx(int dp) {
        int density = getResources().getDisplayMetrics().densityDpi;
        return Math.round(dp * density / 160f);
    }
}