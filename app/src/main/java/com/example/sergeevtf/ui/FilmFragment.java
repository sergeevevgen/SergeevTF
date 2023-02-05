package com.example.sergeevtf.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sergeevtf.App;
import com.example.sergeevtf.R;
import com.example.sergeevtf.adapters.FilmListAdapter;
import com.example.sergeevtf.models.Film;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class FilmFragment extends Fragment {

    private App app;
    private Film film;
    CompositeDisposable disposable = new CompositeDisposable();
    private ImageView iv;
    private TextView tv_title;
    private TextView tv_description;
    private TextView film_textView_genre;
    private TextView film_textView_country;

    public FilmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_film, container, false);

        iv = view.findViewById(R.id.film_imageView);

        tv_title = view.findViewById(R.id.film_textView_title);
        tv_description = view.findViewById(R.id.film_textView_description);
        film_textView_genre = view.findViewById(R.id.film_textView_genre);
        film_textView_country = view.findViewById(R.id.film_textView_country);

        return view;
    }

    private void setData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            film.setId(bundle.getInt("filmId"));
            restore();
        }
    }

    private void restore() {
        disposable.add(app.getFilmService().getApi().getFilm(film.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<Film, Throwable>() {
                    @Override
                    public void accept(Film film_k, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            Toast.makeText(getContext(), "Нет подключения к сети", Toast.LENGTH_SHORT).show();
                        } else {
                            film = film_k;
                            Glide.with(getContext())
                                    .load(film.getImageUrl())
                                    .into(iv);
                            tv_title.setText(film.getTitle());
                            tv_description.setText(film.getDescription());
                            film_textView_genre.setText(film.getGenre().toString());
                            film_textView_country.setText(film.getCountry().toString());
                        }
                    }
                }));
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}