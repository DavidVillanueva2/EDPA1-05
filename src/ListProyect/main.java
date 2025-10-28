import java.util.*;
import java.io.*;
class Sesion5{
    public static void main(String []args)throws IOException{
        System.out.println("Directorio de trabajo: "+System.getProperty("user.dir"));
        ArrayList<Artista> day1Main = new ArrayList<>();
        ArrayList<Artista> day2Main = new ArrayList<>();
        ArrayList<Artista> day3Main = new ArrayList<>();
        ArrayList<Artista> day1River = new ArrayList<>();
        ArrayList<Artista> day2River = new ArrayList<>();
        ArrayList<Artista> day3River = new ArrayList<>();
        SequentialFile <Artista>file = new SequentialFile<>("artists.csv");
        String line = file.readLine();
        while((line=file.readLine())!=null){
            Artista artista=new Artista();
            artista.readData(line);
            if(artista.getDay() == 1 && artista.getStage().equals("Main")){
                day1Main.add(artista);
            }else if(artista.getDay()==1 && artista.getStage().equals("River")){
                day1River.add(artista);
            }else if(artista.getDay() == 2 && artista.getStage().equals("Main")){
                day2Main.add(artista);
            }else if(artista.getDay()==2 && artista.getStage().equals("River")){
                day2River.add(artista);
            }else if(artista.getDay() == 3 && artista.getStage().equals("Main")){
                day3Main.add(artista);
            }else if(artista.getDay()==3 && artista.getStage().equals("River")){
                day3River.add(artista);
            }
        }
        sortList(day1Main);
        sortList(day1River);
        sortList(day2Main);
        sortList(day2River);
        sortList(day3Main);
        sortList(day3River);
        System.out.print("Stage Main. Day 1:\n"+printSchedulle(day1Main, 14, 0));
        System.out.print("Stage River. Day 1:\n"+printSchedulle(day1River, 13, 30));
        System.out.print("Stage Main. Day 2:\n"+printSchedulle(day2Main, 14, 0));
        System.out.print("Stage River. Day 2:\n"+printSchedulle(day2River, 13, 30));
        System.out.print("Stage Main. Day 3:\n"+printSchedulle(day3Main, 14, 0));
        System.out.print("Stage River. Day 3:\n"+printSchedulle(day3River, 13, 30));
        int minutes;
        minutes= totalDurationDay(day1Main,day1River);
        System.out.println("Total music dutation day 1: "+minutes/60+" hours and "+minutes%60+" minutes.");
        minutes= totalDurationDay(day2Main,day2River);
        System.out.println("Total music dutation day 2: "+minutes/60+" hours and "+minutes%60+" minutes.");
        minutes= totalDurationDay(day3Main,day3River);
        System.out.println("Total music dutation day 3: "+minutes/60+" hours and "+minutes%60+" minutes.");
        System.out.println("Average popularity day 1 in stage Main: "+averagePopularity(day1Main));
        System.out.println("Average popularity day 1 in stage River: "+averagePopularity(day1River));
        System.out.println("Average popularity day 2 in stage Main: "+averagePopularity(day2Main));
        System.out.println("Average popularity day 2 in stage River: "+averagePopularity(day2River));
        System.out.println("Average popularity day 3 in stage Main: "+averagePopularity(day3Main));
        System.out.println("Average popularity day 3 in stage River: "+averagePopularity(day3River));
        if(day1Main.get(day1Main.size()-1).getPopularity() < day1River.get(day1River.size()-1).getPopularity()){
            System.out.println("Main artist of day 1: "+day1River.get(day1River.size()-1).getArtist());
        }else{
            System.out.println("Main artist of day 1: "+day1Main.get(day1Main.size()-1).getArtist());
        }
        if(day2Main.get(day2Main.size()-1).getPopularity() < day2River.get(day2River.size()-1).getPopularity()){
            System.out.println("Main artist of day 2: "+day2River.get(day2River.size()-1).getArtist());
        }else{
            System.out.println("Main artist of day 2: "+day2Main.get(day2Main.size()-1).getArtist());
        }
        if(day3Main.get(day3Main.size()-1).getPopularity() < day3River.get(day3River.size()-1).getPopularity()){
            System.out.println("Main artist of day 3: "+day3River.get(day3River.size()-1).getArtist());
        }else{
            System.out.println("Main artist of day 3: "+day3Main.get(day3Main.size()-1).getArtist());
        }
    }
    /********
     * 
     * Method's name: sortList.
     * Name of the original author: Marcos
     * Description on the method: It makes an order in the least from the least popular and shortest duration to the most popular and longest duration and in alphabetical order.
     * Calling arguments: The argument is the list of artists that we are going to sort.
     * Return value: there is not a return value.
     * 
     */
    public static void sortList(ArrayList<Artista>list){
        Artista artist1, artist2;
        for(int i=0; i<list.size(); i++){
            for(int j=0; j<list.size()-1; j++){
                artist1 = list.remove(j);
                artist2 = list.remove(j);
                if(artist1.compareTo(artist2)>0){
                    list.add(j, artist2);
                    list.add(j+1, artist1);
                }else{
                    list.add(j, artist1);
                    list.add(j+1, artist2);
                }
            }
        }
    }
    /********
     * 
     * Method's name: printSchedulle.
     * Name of the original author: Marcos
     * Description on the method: It prints the schedulle of the different stages in the different days of the festival.
     * Calling arguments: It receives the list of artists to print and the hour and minute when the concert is going to starts in an stage in a day.
     * Return value: It returns a schedulle of an stage in a day.
     * 
     */
    public static String printSchedulle(ArrayList<Artista>list, int initialHour, int initialMinutes){
        int hour = initialHour;
        int minutes = initialMinutes;
        Artista artist;
        String result = "";
        String addzero; //For adding a zero when we show an hour with 0 minutes (example: 14:00, 15:00).
        for(int i=0; i<list.size(); i++){
            artist = list.get(i);
            addzero=(minutes==0)?":0":":";
            result+=hour+addzero+minutes+"\n"+artist.toString()+"\n";
            hour=(hour+(artist.getDuration()+30)/60)%24;
            minutes=(minutes+artist.getDuration()+30)%60;
        }
        return result;
    }
    /********
     * 
     * Method's name: totalDurationDay.
     * Name of the original author: Marcos
     * Description on the method: It prints the sum of the duration of the different concerts in a day.
     * Calling arguments: It receives the two lists of the two stages in a day in order to obtain the total duration of the concerts in a day.
     * Return value: It returns the total duration of the concerts in a day in minutes.
     * 
     */
    public static int totalDurationDay(ArrayList<Artista>listMain, ArrayList<Artista> listRiver){
        int minutes=0;
        for(int i=0; i<listMain.size(); i++){
            minutes += listMain.get(i).getDuration();
        }
        for(int i=0; i<listRiver.size(); i++){
            minutes += listRiver.get(i).getDuration();
        }
        return minutes;
    }
    /********
     * 
     * Method's name: averagePopularity.
     * Name of the original author: Marcos
     * Description on the method: It prints the average popularity in a stage in a day.
     * Calling arguments: The list of artists of a stage in a day.
     * Return value: The average popularity of a stage in a day.
     * 
     */
    public static int averagePopularity(ArrayList<Artista>list){
        int popularity=0;
        for(int i=0; i<list.size(); i++){
            popularity += list.get(i).getPopularity();
        }
        popularity = popularity/list.size();
        return popularity;
    }
    
}
