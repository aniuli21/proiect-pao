package com.company;

import com.company.Entity.*;
import com.company.Services.*;
import java.io.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int option = 0;

        TimestampFunction timestampFunction = new TimestampFunction();
        Scanner scanner = new Scanner(System.in);
        MusicAPP musicAPP = MusicAPP.getMusicAppService();
        musicAPP.getDataFromFile();
        System.out.print("1 to add Artist\n" +
                "2 to add Producer \n" +
                "3 to Display All artists\n" +
                "4 to Display All Producers\n" +
                "5 to Search a producer\n" +
                "6 to Search an artist\n" +
                "7 to Search an album\n" +
                "8 to add Song to album"+
                "9 to add an album\n"+
                "10 to print the albums\n");
        option = scanner.nextInt();
        scanner.nextLine();
        while (option != 0) {
            Date currentDate = new Date();
            switch (option) {
                case 1:

                    timestampFunction.SetTimestamp("Add Artist", currentDate.getTime());
                    System.out.println("Introduceti Nume, Nume de scena, Email , CNP");
                    String name = scanner.nextLine();
                    String stageName = scanner.nextLine();
                    String email = scanner.nextLine();
                    int cnp = scanner.nextInt();
                    scanner.nextLine();
                    Artist newArtist = new Artist(name, stageName, email, cnp);
                    System.out.println("Adaugati albume da/nu?");
                    String addAlbumOption = scanner.nextLine();
                    if (addAlbumOption.equals("da")) {
                        System.out.println("Alegeti numele, Genul si data de lansare a albumului");
                        Album newAlbum = new Album();
                        String albumName = scanner.nextLine();
                        String genre = scanner.nextLine();
                        String releaseDate = scanner.nextLine();
                        newAlbum.setName(albumName);
                        newAlbum.setGenre(genre);
                        newAlbum.setReleaseDate(releaseDate);
                        System.out.println("Introduceti numarul de cantece din acest album");
                        int numberOfSongs = scanner.nextInt();
                        scanner.nextLine();
                        newAlbum.addSongs(numberOfSongs);
                        newArtist.addAlbums(newAlbum);
                        musicAPP.addAlbums(newAlbum);
                    }
                    System.out.println("Adaugati single-uri?");
                    String addSingleOption = scanner.nextLine();
                    if (addSingleOption.equals("da")) {
                        System.out.println("Cate single-uri sunt?");
                        int numberOfSingles = scanner.nextInt();
                        scanner.nextLine();
                        for (int i = 0; i < numberOfSingles; i++) {
                            System.out.println("Introduce numele,genul,durata, data lansarii si rankingul: ");
                            String singleName = scanner.nextLine();
                            String singleGenre = scanner.nextLine();
                            int singleDuration = scanner.nextInt();
                            scanner.nextLine();
                            String singleDate = scanner.nextLine();
                            String singleRankings = scanner.nextLine();
                            Single newSingle = new Single(singleName, singleGenre, singleDuration, singleDate, singleRankings);
                            newArtist.addSingle(newSingle);
                        }
                    }
                    musicAPP.addArtists(newArtist);
                    break;
                case 2:
                    timestampFunction.SetTimestamp("Add Producer", currentDate.getTime());
                    System.out.println("Introduceti Nume,Numele Companiei, eMail, CNP");
                    String producerName = scanner.nextLine();
                    String companyName = scanner.nextLine();
                    String producerEmail = scanner.nextLine();
                    int producerCnp = scanner.nextInt();
                    scanner.nextLine();
                    Producer newProducer = new Producer(producerName, companyName, producerEmail, producerCnp);
                    musicAPP.addProducers(newProducer);
                    break;
                case 3:
                    timestampFunction.SetTimestamp("Display all Artists", currentDate.getTime());
                    ArrayList<Artist> artists = musicAPP.getArtists();
                    for (Artist artist: artists) {
                        System.out.println("Stage Name:" + artist.getStageName() + "\nEmail: " + artist.geteMail() + "\n");
                        System.out.println("Albume:");
                        ArrayList<Album> albums = artist.getAlbums();
                        for (Album album:albums) {
                            System.out.println("Album name: " + album.getName() + "\nAlbum genre: " + album.getGenre() +
                                    "\nAlbum release date: " + album.getReleaseDate());
                            ArrayList<Song> songs = album.getSongs();
                            System.out.println(album.getNumberOfSongs());
                            for (Song song:songs) {
                                System.out.println("Song name: " + song.getName() + " Song Genre: " + song.getGenre() +
                                        "Song duration: " + song.getDuration());
                            }
                        }
                        System.out.println("Singles:");
                        ArrayList<Single> singles = artist.getSingles();
                        for (Single single:singles) {
                            System.out.println("Single name: " + single.getName() + "Single Genre: " + single.getGenre() +
                                    "Ranking: " + single.getRankings() + "Single Release Date:" + single.getReleaseDate()
                                    + "Duration:" + single.getDuration());
                        }
                    }
                    break;
                case 4:
                    timestampFunction.SetTimestamp("Display all Producers", currentDate.getTime());
                    ArrayList<Producer> producers = musicAPP.getProducers();
                    for (Producer producer: producers) {
                        System.out.println("Numele Companiei:" + producer.getCompanyName() + "E-mail: " + producer.geteMail());
                        ArrayList<Artist> artistiSemnati = producer.getArtists();
                        for (Artist artist: artistiSemnati) {
                            System.out.println("Numele artist: " + artist.getStageName() + "\n");
                        }
                    }
                    break;
                case 5:
                    timestampFunction.SetTimestamp("Search a producer", currentDate.getTime());
                    System.out.println("Search a producer");
                    String producerToSearch = scanner.nextLine();
                    musicAPP.serchProducer(producerToSearch);
                    break;
                case 6:
                    timestampFunction.SetTimestamp("Search an artist", currentDate.getTime());
                    System.out.println("Search an artist");
                    String artistToSearch = scanner.nextLine();
                    musicAPP.searchArtists(artistToSearch);
                    break;
                case 7:
                    timestampFunction.SetTimestamp("Search an album", currentDate.getTime());
                    System.out.println("Search an album");
                    String albumToSearch = scanner.nextLine();
                    musicAPP.searchAlbums(albumToSearch);
                    break;
                case 8:
                    timestampFunction.SetTimestamp("Add song", currentDate.getTime());
                    System.out.println("Name an artist and and album that you want to add the new song to:");
                    String artistName = scanner.nextLine();
                    String albumName = scanner.nextLine();
                    System.out.println("Introduce numele, genul si durata cantecului(in secunde)");
                    String songName = scanner.nextLine();
                    String genre = scanner.nextLine();
                    int duration = scanner.nextInt();
                    scanner.nextLine();
                    Song newSong = new Song(songName,genre,duration);
                    musicAPP.addSongToAlbum(newSong,albumName,artistName);
                    break;
                case 9:
                    timestampFunction.SetTimestamp("Add Album", currentDate.getTime());
                    musicAPP.addAlbum();
                    break;
                case 10:
                    timestampFunction.SetTimestamp("Print albums", currentDate.getTime());
                    musicAPP.printAlbums();
            }
            System.out.print("1 to add Artist\n" +
                    "2 to add Producer \n" +
                    "3 to Display All artists\n" +
                    "4 to Display All Producers\n" +
                    "5 to Search a producer\n" +
                    "6 to Search an artist\n" +
                    "7 to Search an album\n"+
                    "8 to add a song to an album\n" +
                    "9 to add an album\n"+
                    "10 to print the albums\n");
            option = scanner.nextInt();
            if(option == 0){
                FileWriterService writer = new FileWriterService();
                ArrayList<Album> albumsToWrite = musicAPP.getAlbums();
                ArrayList<Artist> artistsToWrite = musicAPP.getArtists();
                ArrayList<Producer> producersToWrite = musicAPP.getProducers();
                int albumsNumber = musicAPP.getAlbumsNumber();
                int artistNumber = musicAPP.getArtistsNumber();
                int producersNumber = musicAPP.getProducersNumber();
                writer.writeToFile(albumsToWrite,
                        albumsNumber,
                        artistsToWrite,
                        artistNumber,
                        producersToWrite,
                        producersNumber);
                break;
            }
            scanner.nextLine();
        }
    }
}
