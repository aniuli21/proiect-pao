package com.company.Entity;

import java.util.ArrayList;

public class Producer extends Person {

    private String companyName;
    private ArrayList<Artist> artists = new ArrayList<Artist>();
    private int artistNumber= 0;

    public Producer () {}
    public Producer(String newName,String newCompanyName,String newEmail,int newCNP){
        name = newName;
        companyName = newCompanyName;
        eMail = newEmail;
        CNP = newCNP;
    }

    public void addArtist (Artist newArtist){
        artists.add(newArtist);
        artistNumber++;
    }

    public void setCompanyName(String newCompanyName) {
        companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public int getArtistNumber() {
        return artistNumber;
    }
}
