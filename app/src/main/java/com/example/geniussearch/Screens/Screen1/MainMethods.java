package com.example.geniussearch.Screens.Screen1;

import android.content.Context;

import com.example.geniussearch.Pojos.Screen1.Result;

import java.util.List;

public interface MainMethods {
    void showData (Context context, List<Result> results);
    void noNetwork ();
}
