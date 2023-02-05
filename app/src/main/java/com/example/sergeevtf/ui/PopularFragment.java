package com.example.sergeevtf.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sergeevtf.App;
import com.example.sergeevtf.R;
import com.example.sergeevtf.adapters.FilmListAdapter;
import com.example.sergeevtf.models.Body;
import com.example.sergeevtf.models.Film;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularFragment extends Fragment {

    private FilmListAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Film> films;
    private App app;

    CompositeDisposable disposable = new CompositeDisposable();

    public PopularFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_popular, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_films_popular);

        setRecyclerView();
        // Inflate the layout for this fragment
        return view;
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new FilmListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        restore();

        //при долгом нажатии на элемент открывается фрагмент его редактирования
        adapter.setOnItemClickListener(new FilmListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Film film, int position) {
                Bundle bundle = new Bundle();
                Integer id = film.getId();
                bundle.putInt("filmId", id);
                FilmFragment filmFragment = new FilmFragment();
                filmFragment.setArguments(bundle);
                replaceFragment(filmFragment);
            }
        });
    }

    private void restore() {
        disposable.add(app.getFilmService().getApi().getFilms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<List<Film>, Throwable>() {
                    @Override
                    public void accept(List<Film> films, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            Toast.makeText(getContext(), "Нет подключения к сети", Toast.LENGTH_SHORT).show();
                        } else {
                            adapter.setData((ArrayList<Film>) films);
                        }
                    }
                }));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction();

        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}