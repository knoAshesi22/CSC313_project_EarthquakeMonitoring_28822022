package GUI;

//import com.sun.deploy.util.StringUtils;

//import Console.Galamsey;
//import Console.Monitoring;
//import Console.Observatory;

import java.time.Year;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MonitoringIO {

    static Monitoring monitor=new Monitoring();
    static Scanner input=new Scanner(System.in);

    public static void main(String[] args) {
        genMenu();
    }

    public static void genMenu(){
        System.out.println("Welcome to the Galamsey Monitoring program!");
        System.out.println("Enter:\n\'1\' to select an existing observatory. " +
                "\n\'2\' to create a new observatory." +
                "\nAny other key to quit.");

        String choice=input.nextLine();
        while(choice.matches("[12]")){
            if(choice.equals("1")){
                showObservatory();
            }
            else if (choice.equals("2")){
                createObservatory();
            }


            System.out.println();
            System.out.println("Enter:\n\'1\' to select an existing observatory. " +
                    "\n\'2\' to create a new observatory." +
                    "\nAny other key to quit.");
            choice=input.nextLine();
        }
    }

    public static void showObservatory(){
        if(monitor.getObservatories().size()==0){
            System.out.println("No observatories currently available");
            return;
        }
        System.out.println("Below is a list of current observatory names:");

        System.out.println(monitor.getObservatoryNames());

        System.out.println("Please enter a number to select the observatory:");
        String ans=input.nextLine();
        while((!ans.matches("(\\d)+"))
                ||(Integer.parseInt(ans)<=0||Integer.parseInt(ans)>monitor.getObservatories().size())){
            System.out.println("Invalid input. Please enter a number from the above choices.");
            ans=input.next();
        }
        Observatory curObs=monitor.getObservatories().get(Integer.parseInt(ans)-1);
        System.out.println(("Thank you for selecting observatory " + curObs.getName()));

        System.out.println("Enter:" +
                "\n1.View full Observatory information" +
                "\n2.View summary Galamsey statistics" +
                "\n3.Edit Observatory information" +
                "\nAny other key to quit.");
        ans=input.nextLine();
        while(ans.matches("[123]")){
            if(ans.equals("1")){
                System.out.println("Below is information on observatory " + curObs.getName() + ":");
                System.out.println(curObs);
            }
            else if (ans.equals("2")){
                genStatistics(curObs);
            }
            else if(ans.equals("3")){

            }
            else return;
            System.out.println("Enter a new choice:");
            ans=input.nextLine();
        }
    }

    public static void createObservatory(){
        System.out.println("Enter the Observatory name: ");
        Set<String> setnames=new HashSet<String>();
        Boolean exists=false;
        for (Observatory o:monitor.getObservatories()) {
            setnames.add(o.getName());
        }
        String name=input.nextLine();
        while(name.length()==0){
            name=input.nextLine();
        }
        while (setnames.contains(name)){
            //should be unnecessary; fix later
            System.out.println("Name already exists. Please, enter another name: ");
            name=input.nextLine();
        }
        System.out.println("Enter the Observatory country: ");
        String country=input.nextLine();

        System.out.println("Enter the Observatory year of creation: ");
        String year=input.nextLine();
        while(year.length()!=4 &&!year.matches(("\\d\\d\\d\\d"))){
            System.out.println("Invalid input entered");
            year=input.nextLine();
        }

        System.out.println("Enter the Observatory area, in km: ");
        String area=input.nextLine();
        while(!area.matches(("(\\d+)(.\\d+)?"))){
            System.out.println("Invalid input entered");
            area=input.nextLine();
        }

        Observatory o=new Observatory(name,country,Year.parse(year),Double.parseDouble(area));

        System.out.println("Enter a Galamsay event for " + o.getName()+"? Enter \'y\' for yes; any other key for no.");
        String choice=input.nextLine();
        while (choice.length()==0){
            choice=input.nextLine();
        }
        while(choice.equals("y")){
            Galamsey g=createGalamsey();
            o.addEvent(g);
            System.out.println("Enter another Galamsay event for " + o.getName()+"? Enter \'y\' for yes; any other key for no.");
            choice=input.nextLine();
        }
        o.saveToFile();//should be unnecessary; fix in constructor
        //Possible bug with numbers as o.name??
        monitor=new Monitoring();
    }

    public static Galamsey createGalamsey(){
        System.out.println("Enter the Galamsey colour: ");
        String vegColour=input.nextLine();

        System.out.println("Enter the Galamsey latitude: ");
        String lat=input.nextLine();
        while(!lat.matches(("(-)?\\d+(.\\d+)?"))||Math.abs(Double.parseDouble(lat))>90){
            System.out.println("Invalid input entered. Ebter a decimal between -90 and 90: ");
            lat=input.nextLine();
        }
        Double lt=Double.parseDouble(lat);

        System.out.println("Enter the Galamsey longitude: ");
        String lon=input.nextLine();

        while(!lon.matches(("(-)?\\d+(.\\d+)?"))||Math.abs(Double.parseDouble(lon))>180){
            System.out.println("Invalid input entered. Ebter a decimal between -180 and 180: ");
            lon=input.nextLine();
        }

        Double ln=Double.parseDouble(lon);

        System.out.println("Enter the Galamsey year: ");
        String year=input.nextLine();
        while(year.length()!=4 &&!year.matches(("\\d\\d\\d\\d"))){
            System.out.println("Invalid input entered");
            year=input.nextLine();
        }
        Year y= Year.parse(year);

        Galamsey g=new Galamsey(vegColour,lt,ln,y);

        return g;
    }

    public static void genStatistics(Observatory o){
        System.out.println("Largest colour value of Observatory " + o.getName() + ": " + o.getLargest());
        System.out.println("Smallest colour value of Observatory " + o.getName() + ": " + o.getSmallest());
        System.out.println("Average colour value of Observatory " + o.getName() + ": " + o.getAverageColor());

        System.out.println();
        System.out.println("Enter a number between 0 and 3 (inclusive)");
        String ans=input.nextLine();
        while(!ans.matches("[0123]")){
            System.out.println("Invalid input.Enter a number between 0 and 3 (inclusive)");
            ans=input.nextLine();
        }
        System.out.println("List of Galamsey in Observatory with color value greater than "+Integer.parseInt(ans)+" : ");
        for (Galamsey g: o.colValGreaterThan(Integer.parseInt(ans))) {
            System.out.println(g);
        }
        System.out.println();
        return;
    }
}