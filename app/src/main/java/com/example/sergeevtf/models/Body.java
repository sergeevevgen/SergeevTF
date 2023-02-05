package com.example.sergeevtf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Body {
    @SerializedName("pagesCount")
    @Expose
    private Integer pagesCount;

    @SerializedName("films")
    @Expose
    private List<Film> films;

    public Body(Integer pagesCount, List<Film> films) {
        this.pagesCount = pagesCount;
        this.films = films;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
