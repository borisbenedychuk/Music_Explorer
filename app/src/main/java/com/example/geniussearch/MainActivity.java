package com.example.geniussearch;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.geniussearch.Adapters.SearchArrayAdapter;
import com.example.geniussearch.Pojos.Screen1.Result;
import com.example.geniussearch.Screens.Screen1.MainMethods;
import com.example.geniussearch.Screens.Screen1.Presenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainMethods {

    private SearchView searchViewMain;
    private ListView listView;
    private SearchArrayAdapter adapter;
    private TextView textViewSearchLabel;
    private ImageView imageViewLogo;
    private TextView textViewPowered;
    private Disposable disposable;
    private ProgressBar progressBar;
    private CardView toastC;

    private Presenter presenter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchViewMain = findViewById(R.id.searchViewMain);
        textViewSearchLabel = findViewById(R.id.textViewSearchLabel);
        progressBar = findViewById(R.id.progressBarMA);
        toastC = findViewById(R.id.toast_error_card);
        textViewPowered = findViewById(R.id.textViewPoweredLabel);
        imageViewLogo = findViewById(R.id.imageViewLogoMain);
        int id = getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchText = findViewById(id);
        searchText.setTypeface(getResources().getFont(R.font.display_reg));
        listView = findViewById(R.id.listViewSearchMain);
        presenter = new Presenter(this, MainActivity.this);
        Observable<String> queryString = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                        searchViewMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                if (!emitter.isDisposed()) {
                                    emitter.onNext(query);
                                }
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                if (!emitter.isDisposed()) {
                                    emitter.onNext(newText);
                                }
                                return false;
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .switchMap(new Function<String, ObservableSource<? extends String>>() {
                    @Override
                    public ObservableSource<? extends String> apply(@NonNull String s) throws Exception {
                        presenter.disposeDisposable();
                        return Observable.just(s);
                    }
                });

        disposable = queryString.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (!s.isEmpty()) {
                    listView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    presenter.loadData(s);
                    textViewPowered.setVisibility(View.GONE);
                    textViewSearchLabel.setVisibility(View.GONE);
                    imageViewLogo.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                    textViewPowered.setVisibility(View.VISIBLE);
                    textViewSearchLabel.setVisibility(View.VISIBLE);
                    imageViewLogo.setVisibility(View.VISIBLE);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("TEST6", throwable.getMessage());
            }
        });

    }

    public void showData(Context context, List<Result> resultList) {
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        adapter = new SearchArrayAdapter(this, resultList);
        listView.setAdapter(adapter);
        adapter.setOnArtistClickListener(new SearchArrayAdapter.OnArtistClickListener() {
            @Override
            public void onArtistClick(int id) {
                Intent intent = new Intent(context, ArtistActivity.class);
                intent.putExtra("artist_id", id);
                startActivity(intent);

            }
        });
        adapter.setOnCardClickListener(new SearchArrayAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id) {
                Intent intent = new Intent(context, SongActivity.class);
                intent.putExtra("song_id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.disposeDisposable();
        disposable.dispose();
    }

    @Override
    public void noNetwork() {
        Toast toast = new Toast(this);
        toast.setView(getLayoutInflater().inflate(R.layout.toast_error, toastC));
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        textViewPowered.setVisibility(View.VISIBLE);
        textViewSearchLabel.setVisibility(View.VISIBLE);
        imageViewLogo.setVisibility(View.VISIBLE);
    }
}