package com.company.Services;

import com.company.Entity.*;
import com.company.Services.FileWriterService;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class MusicAPP {

    private ArrayList<Artist> artists = new ArrayList<Artist>();
    private ArrayList<Producer> producers = new ArrayList<Producer>();
    private ArrayList<Album> albums = new ArrayList<Album>();

    private int producersNumber = 0;
    private int artistsNumber = 0;
    private int albumsNumber = 0;
    private static MusicAPP instance;

    public int getProducersNumber() {
        return producersNumber;
    }

    public int getAlbumsNumber() {
        return albumsNumber;
    }

    public void getDataFromFile() throws FileNotFoundException {
        FileWriterService  fileReader = new FileWriterService();
        artists = fileReader.readArtistsFromFile();
        for(Artist artist: artists){
            ArrayList<Album> currentArtistAlbum = fileReader.readAlbumsFromFile(artist.getName());
            for(Album album:currentArtistAlbum){
                ArrayList<Song> currentAlbumSongs = fileReader.readSongsFromFile(album.getName());
                album.setSongs(currentAlbumSongs);
            }
            artist.setAlbums(currentArtistAlbum);
            albums.addAll(currentArtistAlbum);
            ArrayList<Single> currentArtistSingles = fileReader.readSinglesFromFile(artist.getName());
            artist.setSingles(currentArtistSingles);
        }
        producers = fileReader.readProducersFromFile();
    }
    public void printAlbums(){
        for(Album album: albums){
           System.out.println(album.getName()+","+album.getGenre()+","+album.getReleaseDate());
        }
    }
    public static MusicAPP getMusicAppService()
    {
        if (instance == null)
        {
            instance = new MusicAPP();
        }
        return instance;
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public int serchProducer(String searchedCompany){
        for(Producer producer:producers){
            if(searchedCompany.equals(producer.getCompanyName())){
                ArrayList<Artist>  artistiSemnati = producer.getArtists();
                for(Artist artist:artistiSemnati){
                    System.out.println("Numele artist: " + artist.getStageName()+ "\n");
                }
                return 0;
            }
        }
        return 1;
    }

    public void addSongToAlbum(Song newSong, String albumName, String artistStageName){
        int gasit = 0;
        for(Artist artist:artists){
            if(artist.getStageName().equals(artistStageName)){
                gasit = 1;
                ArrayList<Album> albumsOfArtist = artist.getAlbums();
                for(Album albumOfArtist: albumsOfArtist){
                    System.out.println(albumOfArtist.getName());
                    if(albumOfArtist.getName().equals(albumName)){
                        gasit=2;
                        int numberOfSongs = albumOfArtist.getNumberOfSongs();
                        if(numberOfSongs == 20){
                            System.out.println("No room for more songs");
                        }
                        else {
                            ArrayList<Song> songs = albumOfArtist.getSongs();
                            songs.add(newSong);
                            albumOfArtist.setNumberOfSongs(numberOfSongs+1);
                            albumOfArtist.setSongs(songs);
                            for(Album album: albums){
                                if(album.getName().equals(albumName)){
                                    album = albumOfArtist;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(gasit ==0){
            System.out.println("Artistul nu a fost gasit");
        }
        if(gasit == 1){
            System.out.println("Albumul nu a fost gasit");
        }
    }

    public void addAlbum (){
        System.out.println("Al cui este albumul:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        boolean gasit = false;
        for(Artist artist: artists){
            if(artist.getStageName().equals(name)){
                gasit = true;
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
                artist.addAlbums(newAlbum);
                albums.add(newAlbum);
                albumsNumber++;
            }
        }
        if(!gasit){
            System.out.println("Nu exista acest artist");
        }
    }

    public int searchArtists(String artistName){
        for(Artist artist : artists){
            if(artist.getStageName().equals(artistName)){
                System.out.println("Stage Name:" + artist.getStageName()+ "\nEmail: " + artist.geteMail() + "\n");
                System.out.println("Albume:");
                ArrayList<Album> albumsOfArtist = artist.getAlbums();
                for(Album album:albumsOfArtist){
                    System.out.println("Album name: " + album.getName() + "\nAlbum genre: " + album.getGenre() +
                            "\nAlbum release date: " + album.getReleaseDate());
                    ArrayList<Song> songsInAlbum = album.getSongs();
                    for(Song song : songsInAlbum){
                        System.out.println("Song name: " + song.getName()+" Song Genre: " + song.getGenre()+
                                "Song duration: " + song.getDuration());
                    }

                }
                System.out.println("Singles:");
                ArrayList<Single> singles= artist.getSingles();
                for(Single single: singles){
                    System.out.println("Single name: " + single.getName()+ "Single Genre: " +single.getGenre()+
                            "Ranking: "+single.getRankings()+ "Single Release Date:" +single.getReleaseDate()
                            +"Duration:" +single.getDuration());
                }
                return 0;
            }
        }
        return 1;
    }

    public int searchAlbums(String albumName){
        for(Album album :albums){
            if(album.getName().equals(albumName)){
                System.out.println("Album name: " + album.getName() + "\nAlbum genre: " + album.getGenre() +
                        "\nAlbum release date: " + album.getReleaseDate());
                ArrayList<Song> songs = album.getSongs();
                for(Song song: songs){
                    System.out.println("Song name: " + song.getName()+" Song Genre: " + song.getGenre()+
                            "Song duration: " + song.getDuration());
                }
                return 0;
            }
        }
        return 1;
    }

    public void addProducers(Producer producers) {
        this.producers.add(producers);
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void addAlbums(Album album){
        albums.add(album);
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void addArtists(Artist artists)
    {
        this.artists.add(artists);
        artistsNumber++;
    }

    public int getArtistsNumber() {
        return artistsNumber;
    }

    public void setArtistsNumber(int artistsNumber) {
        this.artistsNumber = artistsNumber;
    }
}
