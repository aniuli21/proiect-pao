package com.company.Entity;

import java.util.ArrayList;

public class Band {
    private ArrayList<Album> albums = new ArrayList<Album>();
    private ArrayList<Single> singles = new ArrayList<Single>();
    private String BandName;

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void AddSingles (Single newSingle){
        singles.add(newSingle);
    }

    public void AddAlbum(Album newAlbum){
        albums.add(newAlbum);
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public ArrayList<Single> getSingles() {
        return singles;
    }

    public void setSingles(ArrayList<Single> singles) {
        this.singles = singles;
    }

    public String getBandName() {
        return BandName;
    }

    public void setBandName(String bandName) {
        BandName = bandName;
    }
}
