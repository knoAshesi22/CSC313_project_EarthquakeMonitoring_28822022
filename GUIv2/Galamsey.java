package GUIv2;

//Fields: vegetation colour, colour value, position (latitude and longitude) and year of the event.
//[6 points] Methods: Accessor (2), Mutator (2), Inherited/Auxiliary (1), Constructor (1)

import java.time.Year;
import java.util.Calendar;
import java.util.Objects;

public class Galamsey {

    private String vegColour;
    private int colourValue;
    private Position position;
    private Calendar yearv2;
    private Year year;

    public Galamsey(String vegColour, Position position, Year year) {
        this.vegColour = parseColor(vegColour);
        this.position = position;
        this.year = year;
        this.colourValue=genColourValue(this.vegColour);
    }

    public Galamsey(String vegColour, double lat, double lon, Year year) {
        this.vegColour = parseColor(vegColour);
        this.position = new Position(lat,lon);
        this.year = year;
        this.colourValue=genColourValue(this.vegColour);
    }



    public String getVegColour() {
        return vegColour;
    }

    public String parseColor(String vegColour){

        vegColour=vegColour.toUpperCase();
        if(vegColour.equals("GREEN")||vegColour.equals("YELLOW")||vegColour.equals("BROWN")){
            return vegColour;
        }
        else {
            return "NA";
        }
    }

    public int genColourValue(String vegColour){
        switch (vegColour.toUpperCase()){
            case "GREEN":
                colourValue=1;
                break;
            case "YELLOW":
                colourValue=2;
                break;
            case "BROWN":
                colourValue=3;
                break;
            default:
                colourValue=0;
        }
        return colourValue;
    }

    public void setVegColour(String vegColour) {
        this.vegColour = vegColour;
    }

    public int getColourValue() {
        return colourValue;
    }

    public void setColourValue(int colourValue) {
        this.colourValue = colourValue;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    @Override
    public String toString() {
        String ans;
        ans="Galamsey{" +
                "vegColour=" + vegColour +
                ", colourValue=" + colourValue +
                ", position=" + position +
                ", year=" + year +
                '}';
        return ans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Galamsey)) return false;
        Galamsey galamsey = (Galamsey) o;
        return colourValue == galamsey.colourValue &&
                vegColour == galamsey.vegColour &&
                Objects.equals(position, galamsey.position) &&
                Objects.equals(year, galamsey.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vegColour, colourValue, position, year);
    }
}
