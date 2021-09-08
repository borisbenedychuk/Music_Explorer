package com.example.geniussearch.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geniussearch.Pojos.Screen3.WriterArtist;
import com.example.geniussearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WriterAdapter extends RecyclerView.Adapter<WriterAdapter.WriterViewHolder> {

    private List<WriterArtist> writers;
    private Context context;
    private OnWriterClickListener onWriterClickListener;

    public void setOnWriterClickListener(OnWriterClickListener onWriterClickListener) {
        this.onWriterClickListener = onWriterClickListener;
    }

    public interface OnWriterClickListener {
        void onWriterClick (int id);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setWriters(List<WriterArtist> writers) {
        this.writers = writers;
        notifyDataSetChanged();
    }


    @Override
    public WriterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.writer_item, parent, false);
        return new WriterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WriterAdapter.WriterViewHolder holder, int position) {
        WriterArtist writerArtist = writers.get(position);
        holder.textView.setText(writerArtist.getName());
        Picasso.get().load(writerArtist.getImageUrl()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWriterClickListener != null) {
                    onWriterClickListener.onWriterClick(writerArtist.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return writers.size();
    }

    static class WriterViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imageView;
        private TextView textView;

        public WriterViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewWriter);
            imageView = itemView.findViewById(R.id.imageViewWriterImage);
            textView = itemView.findViewById(R.id.textViewWriterName);
        }
    }
}
