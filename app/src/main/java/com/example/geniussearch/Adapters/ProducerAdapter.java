package com.example.geniussearch.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geniussearch.Pojos.Screen3.ProducerArtist;
import com.example.geniussearch.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProducerAdapter extends RecyclerView.Adapter<ProducerAdapter.ProducerViewHolder> {

    private List<ProducerArtist> producerArtistList;
    private OnProducerClickListener onProducerClickListener;

    public void setOnProducerClickListener(OnProducerClickListener onProducerClickListener) {
        this.onProducerClickListener = onProducerClickListener;
    }

    public interface OnProducerClickListener  {
        void onProducerClick (int id);
    }

    public void setProducerArtistList(List<ProducerArtist> producerArtistList) {
        this.producerArtistList = producerArtistList;
        notifyDataSetChanged();
    }

    public ProducerAdapter() {
        producerArtistList = new ArrayList<>();
    }

    @Override
    public ProducerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producer_item, parent, false);
        return new ProducerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProducerAdapter.ProducerViewHolder holder, int position) {
        ProducerArtist producer = producerArtistList.get(position);
        holder.textView.setText(producer.getName());
        Picasso.get().load(producer.getImageUrl()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onProducerClickListener != null) {
                    onProducerClickListener.onProducerClick(producer.getId());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return producerArtistList.size();
    }

    static class ProducerViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        private CardView cardView;

        public ProducerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewProducerName);
            imageView = itemView.findViewById(R.id.imageViewProducerImage);
            cardView = itemView.findViewById(R.id.cardiViewProducer);
        }
    }
}
