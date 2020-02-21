package GUIv2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Observatory {

    private String name;
    private String country;
    private Year startYear;
    private double area;
    private ArrayList<Galamsey> events;


    private double largest=Double.NaN;
    private double smallest=Double.NaN;
    private double sumColor=Double.NaN;

    static final String fileName="observatories2.txt";



    public Observatory(String name, String country, Year startYear, double area) {
        this.name = name;
        this.country = country;
        this.startYear = startYear;
        this.area = area;
        this.events = new ArrayList<Galamsey>();
    }

    public Observatory(String name, String country, Year startYear, double area, ArrayList<Galamsey> events) {
        this.name = name;
        this.country = country;
        this.startYear = startYear;
        this.area = area;
        this.events = events;

    }

    public Observatory() {
        this.name ="";
        this.country = "";
        this.startYear = Year.parse("0000");
        this.area = 0;
        this.events = new ArrayList<Galamsey>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Year getStartYear() {
        return startYear;
    }

    public void setStartYear(Year startYear) {
        this.startYear = startYear;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public ArrayList<Galamsey> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Galamsey> events) {
        this.events = events;
        for (Galamsey event:events) {
            largest=largest>=event.getColourValue()?largest:event.getColourValue();
            smallest=smallest<=event.getColourValue()?smallest:event.getColourValue();
            sumColor=(((Double)sumColor).equals(Double.NaN))?event.getColourValue():sumColor+event.getColourValue();
        }
    }

    public double getLargest() {
        return largest;
    }

    public double getSmallest() {
        return smallest;
    }

    public double getAverageColor() {
        return (sumColor/events.size());
    }

    public void addEvent(Galamsey event){
        events.add(event);
        largest=largest>=event.getColourValue()?largest:event.getColourValue();
        smallest=smallest<=event.getColourValue()?smallest:event.getColourValue();
        sumColor=(((Double)sumColor).equals(Double.NaN))?event.getColourValue():sumColor+event.getColourValue();
    }

    public String fileFormat(){

        String details=name+":::"+country+" "+startYear.getValue()+" "+area;
        String gt;
        for (Galamsey i:events) {
            gt=i.getVegColour()+" "+i.getPosition().getLatitude()+" "+i.getPosition().getLongitude()+" "+i.getYear().getValue();
            details+="\t\t"+gt;
        }
        return details;
    }

    public void saveToDB(){

    }

    public void saveToFile(){

        try {
            File file=new File(fileName);
            file.createNewFile();
            String content = new String ( Files.readAllBytes( Paths.get(fileName) ) ).trim();
            PrintWriter writer;
            String pat="^"+name+":::+";
            pat=name+"\\:\\:\\:.+";

            if(content.contains(name+":::")){

                System.out.println(content);
                System.out.println();
                content=content.replaceAll(pat,fileFormat());
                writer=new PrintWriter(new FileOutputStream(fileName,false));
                writer.println(content);
            }
            else {
                writer=new PrintWriter(new FileOutputStream(fileName,true));
                writer.println(fileFormat());
            }
            writer.close();


        } catch (IOException e) {
            File file=new File(fileName);

            e.printStackTrace();
        }
    }

    public int maxColourValue(){
        int m =1;
        for(Galamsey n: events){
            if (n.getColourValue()>=m){ m=n.getColourValue();}
        }
        return m;
    }
    public int avgColourValue(){
        int a=0;
        for(Galamsey n: events){
            a=a+(n.getColourValue()/events.size() );
        }
        return a;
    }
    public ArrayList<Galamsey> colValGreaterThan(int v){
        ArrayList<Galamsey> cop=new ArrayList<Galamsey>(events);
        cop.removeIf(n -> n.getColourValue()<v);
        return cop;
    }


    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Observatory)) return false;
        return ((Observatory)obj).getName().equals(name);
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        String ans;
        ans="Observatory name: " + name + "\n"+
                "Country: " + country + "\n"+
                "Start year: " + startYear + "\n"+
                "Area(km): " + area + "\n"+
                "Number of events: "+ events.size()+ "\n"+
                "Events: " +"\n"+ events;

        return ans;
    }


    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the {@code hashCode} method
     * must consistently return the same integer, provided no information
     * used in {@code equals} comparisons on the object is modified.
     * This integer need not remain consistent from one execution of an
     * application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     * method, then calling the {@code hashCode} method on each of
     * the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     * according to the {@link Object#equals(Object)}
     * method, then calling the {@code hashCode} method on each of the
     * two objects must produce distinct integer results.  However, the
     * programmer should be aware that producing distinct integer results
     * for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java&trade; programming language.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
