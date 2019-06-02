package com.company.Services;
import java.util.Scanner;
import com.company.Entity.*;
import java.util.ArrayList;
import java.io.*;
public class FileWriterService {

    public void writeToFile (ArrayList<Album>albums, int albumsNumber, ArrayList<Artist> artists, int artistsNumber, ArrayList<Producer> producers, int producersNumber)
            throws IOException {
        File albumsFile = new File("./src/Resources/Albums.csv");
        File artistsFile = new File ("./src/Resources/Artists.csv");
        File producersFile = new File ("./src/Resources/Producers.csv");
        File songsFile = new File ("./src/Resources/Songs.csv");
        File singlesFile = new File ("./src/Resources/Singles.csv");
        FileWriter albumWriter = new FileWriter(albumsFile);
        FileWriter producersWriter = new FileWriter(producersFile);
        FileWriter artistsWriter = new FileWriter(artistsFile);
        FileWriter songsWriter = new FileWriter(songsFile);
        FileWriter singlesWriter = new FileWriter(singlesFile);
        for(Producer producer:producers){
            producersWriter.append(producer.getName()+","+producer.getCompanyName()+","+producer.geteMail()+ ","+producer.getCNP()+"\n");
        }
        for(Artist artist:artists){
            artistsWriter.append(artist.getName()+","+artist.getStageName()+","+artist.geteMail()+","+artist.getCNP()+"\n");
            ArrayList<Album> artistAlbums = artist.getAlbums();
            ArrayList<Single> artistSingle = artist.getSingles();
            for(Album album: artistAlbums){
                albumWriter.append(artist.getName() + "," + album.getName()+","+album.getGenre()+","+album.getReleaseDate()+"\n");
                ArrayList<Song> albumSongs = album.getSongs();
                for(Song song: albumSongs){
                    songsWriter.append(album.getName()+ "," + song.getName()+ "," +song.getGenre()+","+song.getDuration()+"\n");
                }
            }
            for(Single single: artistSingle){
                singlesWriter.append(artist.getName() + "," + single.getName() + "," + single.getGenre()+ "," + single.getDuration() + ","
                        + single.getRankings() + "," + single.getReleaseDate()+ "\n");
            }
        }
        singlesWriter.flush();
        singlesWriter.close();
        songsWriter.flush();
        songsWriter.close();
        artistsWriter.flush();
        artistsWriter.close();
        producersWriter.flush();
        producersWriter.close();
        albumWriter.flush();
        albumWriter.close();
    }

    public ArrayList<Album> readAlbumsFromFile (String ArtistName) throws FileNotFoundException {
        File albumsFile = new File("./src/Resources/Albums.csv");
        Scanner inputFile = new Scanner(albumsFile);
        ArrayList<Album> albums = new ArrayList<Album>();
        while(inputFile.hasNext()){
            String data = inputFile.next();
            String[] arrStrings = data.split(",");
            if(arrStrings[0].equals(ArtistName)){
                System.out.println("da");
                Album newAlbum = new Album();
                newAlbum.setName(arrStrings[1]);
                newAlbum.setGenre(arrStrings[2]);
                newAlbum.setReleaseDate(arrStrings[3]);
                albums.add(newAlbum);
            }
        }
        return albums;
    }

    public ArrayList<Artist> readArtistsFromFile () throws FileNotFoundException{
        File artistsFile = new File("./src/Resources/Artists.csv");
        Scanner inputFile = new Scanner(artistsFile);
        ArrayList<Artist> artists = new ArrayList<Artist>();
        while(inputFile.hasNext()){
            String data = inputFile.next();
            String[] parsedData = data.split(",");
            int foo =  Integer.parseInt(parsedData[3]);
            Artist newArtist = new Artist(parsedData[1],parsedData[2],parsedData[2],foo);
            artists.add(newArtist);
        }
        return artists;
    }

    public ArrayList<Song> readSongsFromFile (String albumName) throws FileNotFoundException{
        File songsFile = new File("./src/Resources/Songs.csv");
        Scanner inputFile = new Scanner(songsFile);
        ArrayList<Song> songs  = new ArrayList<Song>();
        while(inputFile.hasNext()){
            String data = inputFile.next();
            String[] parsedData = data.split(",");
            if(albumName.equals(parsedData[0])){
                int duration = Integer.parseInt(parsedData[3]);
                Song newSong = new Song(parsedData[1], parsedData[2], duration);
                songs.add(newSong);
            }
        }
        return songs;
    }

    public ArrayList<Single> readSinglesFromFile (String artistName) throws FileNotFoundException{
        File singlesFile = new File("./src/Resources/Singles.csv");
        Scanner inputFile = new Scanner(singlesFile);
        ArrayList<Single> singles = new ArrayList<Single>();
        while(inputFile.hasNext()){
            String data = inputFile.next();
            String[] parsedData = data.split(",");
            System.out.println(parsedData[0]+ " " + artistName);
            if(artistName.equals(parsedData[0])){

                int duration = Integer.parseInt(parsedData[3]);
                Single newSingle = new Single(parsedData[1],parsedData[2],duration,parsedData[4],parsedData[5]);
                singles.add(newSingle);
            }
        }
        return singles;
    }

    public ArrayList<Producer> readProducersFromFile() throws FileNotFoundException{
        File producersFile = new File("./src/Resources/Producers.csv");
        Scanner inputFile = new Scanner(producersFile);
        ArrayList<Producer> producers = new ArrayList<Producer>();
        while(inputFile.hasNext()){
            String data = inputFile.next();
            String[] parsedData = data.split(",");
            int CNP = Integer.parseInt(parsedData[3]);
            Producer newProducer = new Producer(parsedData[0],parsedData[1],parsedData[2],CNP);
            producers.add(newProducer);
        }
        return producers;
    }
}
