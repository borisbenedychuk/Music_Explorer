package com.example.geniussearch.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geniussearch.Pojos.Screen2.ArtistSongs.Song;
import com.example.geniussearch.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArtistSongsAdapter extends RecyclerView.Adapter<ArtistSongsAdapter.ArtistSongViewHolder> {


    private List<Song> songList;
    private OnSongClickListener onSongClickListener;

    public void setOnSongClickListener(OnSongClickListener onSongClickListener) {
        this.onSongClickListener = onSongClickListener;
    }

    public ArtistSongsAdapter() {
        songList = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSongList(List<Song> songList) {
        this.songList = songList;
        notifyDataSetChanged();
    }

    public interface  OnSongClickListener {
        void onSongClick (int id);
    }

    @NonNull
    @Override
    public ArtistSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_song_item_aa, parent, false);
        return new ArtistSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ArtistSongsAdapter.ArtistSongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.textViewTitle.setText(song.getTitle());
        Picasso.get().load(song.getSongArtImageThumbnailUrl()).resize(300, 300).into(holder.imageViewSongArtAA);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSongClickListener != null) {
                    onSongClickListener.onSongClick(song.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    static class ArtistSongViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewSongArtAA;
        private TextView textViewTitle;

        public ArtistSongViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSongArtAA = itemView.findViewById(R.id.imageViewSongAA);
            textViewTitle = itemView.findViewById(R.id.textViewSongTitleAA);
        }

    }
}
