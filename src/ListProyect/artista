/**********
 * 
 * Class' name: Artista
 * Author's name: Marcos
 * Creation date: 18/10/2025
 * Class version: First version.
 * Class description: This class represents an artist.
 * 
 * 
 ***********/
public class Artista implements Comparable<Artista>, DefineTipeT {
    private String artist, genre;
    private int day;
    private String stage;
    private int duration, popularity; 

    public Artista() {} //For avoiding compilation errors when we create an object in the main function
    /********
     * 
     * Method's name: Artist.
     * Name of the original author: David
     * Description on the method: It is the constructor method.
     * Calling arguments: The characteristics of the artist who will be registered.
     * Return value: there is not a return value.
     * 
     */
    public Artista(String artist, String genre, int day, String stage, int duration, int popularity) {
        this.artist = artist;
        this.genre = genre;
        this.day = day;
        this.stage = stage;
        this.duration = duration;
        this.popularity = popularity;
    }
    /********
     * 
     * Method's name: readData.
     * Name of the original author: Marcos
     * Description on the method: It read the data obtained from a file and brings it to the artist.
     * Calling arguments: The information which we have obtained from a file.
     * Return value: there is not a return value.
     * 
     */
    public void readData(String line) {
        String[] a = line.split(";"); // We split by the ; in the file
        this.artist = a[0];
        this.genre = a[1];
        this.day = Integer.parseInt(a[2]);
        this.stage = a[3];
        this.duration = Integer.parseInt(a[4]); //We converts the strings to integer numbers.
        this.popularity = Integer.parseInt(a[5]);
    }
    //There are different functions that are useful to obtain the information of an artist.
    public String getArtist() {
        return artist;
    }
    public String getGenre() {
        return genre;
    }
    public int getDay() {
        return day;
    }
    public String getStage() {
        return stage;
    }
    public int getDuration() {
        return duration;
    }
    public int getPopularity(){
        return popularity;
    }
    /********
     * 
     * Method's name: SequentialFile.
     * Name of the original author: David
     * Description on the method:  constructor method
     * Calling arguments: 
     * Return value: there is not a return value.
     * 
     */
    @Override
    public String toString() {
        return "Artist: " + artist +"\tGenre: " + genre +"\tDay: " + day +"\tStage: " + stage +"\tDuration (minutes): " + duration + "\tPopularity: " + popularity;
    }
    @Override
    public int compareTo(Artista otro) {
        int result = Integer.compare(popularity, otro.getPopularity());
        if (result != 0) return result;
        result = Integer.compare(duration, otro.getDuration());
        if (result != 0) return result;
        return artist.compareToIgnoreCase(otro.getArtist());
    }
}
