package com.company.Entity;

public class Single extends Song {

    private String releaseDate;
    private String rankings;

    public Single (){};

    public Single (String newName, String newGenre, int newDuration, String newReleaseDate, String newRankings){
        super(newName,newGenre,newDuration);
        releaseDate = newReleaseDate;
        rankings = newRankings;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRankings() {
        return rankings;
    }

    public void setRankings(String rankings) {
        this.rankings = rankings;
    }
}
