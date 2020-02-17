import java.time.Year;
import java.util.Calendar;
import java.util.Objects;

public class Galamsey {

    private enum COLOURS{GREEN,YELLOW,BROWN,NA};
    private COLOURS vegColour;
    private int colourValue;
    private Position position;
    private Calendar yearv2;
    private Year year;

    public Galamsey(String vegColour, Position position, Year year) {
        this.vegColour = genColourEnum(vegColour);
        this.position = position;
        this.year = year;
        this.colourValue=genColourValue(this.vegColour);
    }

    public Galamsey(String vegColour, double lat, double lon, Year year) {
        this.vegColour = genColourEnum(vegColour);
        this.position = new Position(lat,lon);
        this.year = year;
        this.colourValue=genColourValue(this.vegColour);
    }

    public static COLOURS[] getPossibleColours(){
        return COLOURS.values();
    }


    public COLOURS getVegColour() {
        return vegColour;
    }

    public COLOURS genColourEnum(String vegColour){
        try{
            this.vegColour= COLOURS.valueOf(vegColour.toUpperCase());
        }
        catch(Exception e) {
            this.vegColour= COLOURS.NA;
        }

        return this.vegColour;
    }

    public int genColourValue(COLOURS vegColour){
        switch (vegColour){
            case GREEN:
                colourValue=1;
                break;
            case YELLOW:
                colourValue=2;
                break;
            case BROWN:
                colourValue=3;
                break;
            default:
                colourValue=0;
        }
        return colourValue;
    }

    public void setVegColour(COLOURS vegColour) {
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
