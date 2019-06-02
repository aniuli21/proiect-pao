package com.company.Entity;

import com.company.Info;

public class Song implements Info {

    private String genre;
    private String name;
    private int duration;

    public Song() {};

    public Song(String newName, String newGenre, int newDuration){
        genre = newGenre;
        name = newName;
        duration = newDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
