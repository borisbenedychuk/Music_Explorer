package com.example.geniussearch.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.geniussearch.Pojos.Screen1.Result;
import com.example.geniussearch.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchArrayAdapter extends ArrayAdapter <Result> {

    private Context context;
    private List <Result> results = new ArrayList<>();
    private OnArtistClickListener onArtistClickListener;
    private OnCardClickListener onCardClickListener;

    public void setOnArtistClickListener(OnArtistClickListener onArtistClickListener) {
        this.onArtistClickListener = onArtistClickListener;
    }

    public void setOnCardClickListener(OnCardClickListener onCardClickListener) {
        this.onCardClickListener = onCardClickListener;
    }

    public interface OnCardClickListener {
        void onCardClick (int id);
    }

    public interface OnArtistClickListener {
        void onArtistClick(int id);
    }

    public SearchArrayAdapter(Context context,  List<Result> resultList) {
        super(context, 0, resultList);
        this.context = context;
        results = resultList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }
        Result result = results.get(position);

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCardClickListener != null) {
                    onCardClickListener.onCardClick(result.getId());
                }
            }
        });

        ImageView imageViewSong = listView.findViewById(R.id.imageViewMark);
        TextView textViewSong = listView.findViewById(R.id.textViewSong);
        TextView textViewArtist = listView.findViewById(R.id.textViewArtist);

        textViewArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onArtistClickListener != null) {
                    onArtistClickListener.onArtistClick(result.getPrimaryArtist().getId());
                }
            }
        });

        Picasso.get().load(result.getSongArtImageUrl()).into(imageViewSong);
        textViewSong.setText(result.getTitle());
        textViewArtist.setText(result.getPrimaryArtist().getName());
        if (result.getPrimaryArtist().getName().length() < 20) {
            textViewArtist.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            textViewArtist.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        }


        return listView;
    }

}
