package com.example.sergeevtf.api;

import com.example.sergeevtf.models.Body;
import com.example.sergeevtf.models.Film;

import java.security.Key;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FilmApi {
    String type = "TOP_100_POPULAR_FILMS";
    String KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b";

    @Headers({
            "accept: application/json",
            "X-API-KEY: " + KEY
    })
    @GET("films/top?type=" + type + "&page=1")
    Single<Body> getFilms();

    @Headers({
            "accept: application/json",
            "X-API-KEY: " + KEY
    })
    @GET("films/{id}")
    Single<Film> getFilm(@Path("id") Integer film);
}
