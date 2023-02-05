package com.example.sergeevtf;

import android.app.Application;

import com.example.sergeevtf.api.FilmService;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {
    private FilmService filmService;

    @Override
    public void onCreate() {
        super.onCreate();

        filmService = new FilmService();
    }

    public FilmService getFilmService() {
        return filmService;
    }
}
