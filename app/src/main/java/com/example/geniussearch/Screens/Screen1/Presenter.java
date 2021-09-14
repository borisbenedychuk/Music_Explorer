package com.example.geniussearch.Screens.Screen1;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.geniussearch.API.APIFactory;
import com.example.geniussearch.Pojos.Screen1.Hit;
import com.example.geniussearch.Pojos.Screen1.Response;
import com.example.geniussearch.Pojos.Screen1.Result;
import com.example.geniussearch.Pojos.Screen1.SearchResult;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import rx.Observable;

public class Presenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Context context;
    private MainMethods methods;
    private Disposable disposable;




    public Presenter(Context context, MainMethods methods) {
        this.context = context;
        this.methods = methods;
    }

    public void loadData (String str) {
        disposable = APIFactory.getInstance().apiServices()
                .getSearchResult(str, APIFactory.RAD_KEY, APIFactory.RAD_API_HOST)
                .subscribeOn(Schedulers.io())
                .map(SearchResult::getResponse)
                .map(Response::getHits)
                .map(new Function<List<Hit>, List<Result>>() {
                    @Override
                    public List<Result> apply(@NonNull List<Hit> hits) throws Exception {
                        List<Result> results = new ArrayList<>();
                        for (Hit hit: hits) {
                            results.add(hit.getResult());
                        }
                        return results;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Result>>() {
                    @Override
                    public void accept(List<Result> results) throws Exception {
                        methods.showData(context, results);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("TEST5", throwable.getMessage());
                        methods.noNetwork();
                    }
                });
        compositeDisposable.add(disposable);


    }

    public void disposeDisposable () {
        compositeDisposable.clear();
    }
}
