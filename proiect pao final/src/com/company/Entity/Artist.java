package com.company.Entity;

import java.util.ArrayList;

public class Artist extends Person {
    private ArrayList<Album> albums = new ArrayList<Album>();
    private ArrayList<Single> singles = new ArrayList<Single>();
    private String stageName;
    private int albumNumber = 0;
    private int singlesNumber = 0;

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public void setSingles(ArrayList<Single> singles) {
        this.singles = singles;
    }

    public int getAlbumNumber() {
        return albumNumber;
    }

    public int getSinglesNumber() {
        return singlesNumber;
    }

    public Artist (String newName, String newStageName, String newEmail, int newCNP){
        this.stageName = newStageName;
        this.name = newName;
        this.eMail = newEmail;
        this.CNP = newCNP;
    }

    public void addSingle (Single newSingle) {
        singles.add(newSingle);
        singlesNumber++;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void addAlbums(Album newAlbum) {
        albums.add(newAlbum);
        albumNumber++;
    }

    public String getStageName() {
        return stageName;
    }

    public ArrayList<Single> getSingles() {
        return singles;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}
