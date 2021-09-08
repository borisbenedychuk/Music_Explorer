package com.example.geniussearch.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geniussearch.Pojos.Screen3.SongRelationPath;
import com.example.geniussearch.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RemixesAdapter extends RecyclerView.Adapter<RemixesAdapter.RemixesViewHolder>{


    private List<SongRelationPath> songList;
    private ArtistSongsAdapter.OnSongClickListener onSongClickListener;

    public void setOnSongClickListener(ArtistSongsAdapter.OnSongClickListener onSongClickListener) {
        this.onSongClickListener = onSongClickListener;
    }

    public RemixesAdapter() {
        songList = new ArrayList<>();
    }

    public void setSongList(List<SongRelationPath> songList) {
        this.songList = songList;
        notifyDataSetChanged();
    }

    public interface  OnSongClickListener {
        void onSongClick (int id);
    }

    @NonNull
    @Override
    public RemixesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_song_item_sa, parent, false);
        return new RemixesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RemixesViewHolder holder, int position) {
        SongRelationPath song = songList.get(position);
        holder.textViewTitle.setText(song.getTitle());
        Picasso.get().load(song.getSongArtImageUrl()).resize(300, 300).into(holder.imageViewSongArtSA);
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

    static class RemixesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewSongArtSA;
        private TextView textViewTitle;

        public RemixesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSongArtSA = itemView.findViewById(R.id.imageViewSongSA);
            textViewTitle = itemView.findViewById(R.id.textViewSongTitleSA);
        }

    }
}
