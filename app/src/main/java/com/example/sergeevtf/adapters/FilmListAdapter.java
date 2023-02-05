package com.example.sergeevtf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.example.sergeevtf.R;
import com.example.sergeevtf.models.Film;

import java.util.ArrayList;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>{
    private ArrayList<Film> films;
    private OnItemClickListener listener;
    private final Context context;

    public FilmListAdapter(Context context) {
        films = new ArrayList<>();
        this.context = context;
    }

    public void setData(ArrayList<Film> films) { this.films = films; }
    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.film_item_recyclerview, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        if (films.size() > 0) {
            Film film = films.get(position);
            Glide.with(context)
                            .load(film.getImagePreviewUrl())
                                    .into(holder.film_item_imageIV);
            holder.film_item_titleTV.setText(film.getTitle());
            holder.film_item_genre_and_yearTV.setText(film.getGenre() + "(" + film.getYearOfRelease() + ")");
        }
    }

    @Override
    public int getItemCount() {
        if (films != null)
            return films.size();
        return 0;
    }

    class FilmViewHolder extends RecyclerView.ViewHolder {
        private final ImageView film_item_imageIV;
        private final TextView film_item_titleTV;
        private final TextView film_item_genre_and_yearTV;
        private final ImageView film_item_star;
        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            film_item_imageIV = itemView.findViewById(R.id.film_item_imageIV);
            film_item_titleTV = itemView.findViewById(R.id.film_item_titleTV);
            film_item_genre_and_yearTV = itemView.findViewById(R.id.film_item_genre_and_yearTV);
            film_item_star = itemView.findViewById(R.id.film_item_star);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(films.get(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Film film, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
