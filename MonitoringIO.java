import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MonitoringIO {

    public static void main(String[] args) {
        System.out.println("Welcome to the Galamsey Monitoring program!");
        Scanner input=new Scanner(System.in);
        Monitoring monitor=new Monitoring();
        genMenu(monitor,input);



    }

    public static void genMenu(Monitoring monitor, Scanner input){
        System.out.println("Enter:\n\'1\' to select an existing observatory. " +
                "\n\'2\' to create a new observatory." +
                "\n\'3\' to view Galamsey statistics."+
                "\nAny other key to quit.");

        String choice=input.nextLine();
        while(choice.matches("[123]")){
            if(choice.equals("1")){
                showObservatory(monitor,input);
            }
            else if (choice.equals("2")){
                createObservatory(monitor,input);
            }
            else if(choice.equals("3")){
                genStatistics();
            }
            else {
                System.exit(0);
            }


            System.out.println();
            System.out.println("Enter:\n\'1\' to select an existing observatory. " +
                    "\n\'2\' to create a new observatory." +
                    "\n\'3\' to view Galamsey statistics."+
                    "\nAny other key to quit.");
            choice=input.next();
        }
    }

    public static void showObservatory(Monitoring monitor, Scanner input){
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
        System.out.println("Below is information on observatory " + curObs.getName() + ":");
        System.out.println(curObs);
    }

    public static void createObservatory(Monitoring monitor, Scanner input){
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
        while(!area.matches(("\\d+"))){
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
            System.out.println("test choice");
            Galamsey g=createGalamsey(input);
            o.addEvent(g);
            System.out.println("Enter another Galamsay event for " + o.getName()+"? Enter \'y\' for yes; any other key for no.");
            choice=input.nextLine();
        }
        o.saveToFile();//should be unnecessary; fix in constructor

    }

    public static Galamsey createGalamsey(Scanner input){
        System.out.println("Enter the Galamsey colour: ");
        String vegColour=input.nextLine();

        System.out.println("Enter the Galamsey latitude: ");
        String lat=input.nextLine();
        while(!lat.matches(("\\d+"))){
            System.out.println("Invalid input entered");
            lat=input.nextLine();
        }
        Double lt=Double.parseDouble(lat);

        System.out.println("Enter the Galamsey longitude: ");
        String lon=input.nextLine();
        while(!lon.matches(("\\d+"))){
            System.out.println("Invalid input entered");
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

    public static void genStatistics(){
        return;
    }
}
