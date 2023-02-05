package com.example.sergeevtf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Film {
    @SerializedName("filmId")
    @Expose
    private Integer id;
    @SerializedName("nameRu")
    @Expose
    private String title;
    @SerializedName("genres")
    @Expose
    private List<String> genre;
    @SerializedName("posterUrlPreview")
    @Expose
    private String imagePreviewUrl;
    @SerializedName("posterUrl")
    @Expose
    private String imageUrl;
    @SerializedName("year")
    @Expose
    private String yearOfRelease;
    @SerializedName("posterUrlPreview")
    @Expose
    private String description;
    @SerializedName("countries")
    @Expose
    private List<String> country;

    public Film(String title, List<String> genre, String imagePreviewUrl, String imageUrl, String yearOfRelease, String description, List<String> country) {
        this.title = title;
        this.genre = genre;
        this.imagePreviewUrl = imagePreviewUrl;
        this.imageUrl = imageUrl;
        this.yearOfRelease = yearOfRelease;
        this.description = description;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getImagePreviewUrl() {
        return imagePreviewUrl;
    }

    public void setImagePreviewUrl(String imagePreviewUrl) {
        this.imagePreviewUrl = imagePreviewUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }
}
