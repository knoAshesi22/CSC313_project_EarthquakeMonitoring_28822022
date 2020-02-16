import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBConnection {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/myFirstDB";

    static final String USER = "root";
    static final String PASS = "dannie777";
    static Connection con = null;
    static Statement stmt = null;

    public static void main(String[] args) {
        connectDB();
        createTables();
        List<Galamsey> gs= getGalamsey(2);
        for (Galamsey g: gs) {
            System.out.println(g);
        }

    }

    public static void connectDB(){
        try{
            con = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = con.createStatement();

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void createTables(){

        try{

            String sql = "CREATE TABLE if not exists OBSERVATORY ( " +
                    " OID INTEGER not NULL AUTO_INCREMENT, " +
                    " ONAME VARCHAR(255) UNIQUE, " +
                    " COUNTRY VARCHAR(255), " +
                    " STARTYEAR YEAR , " +
                    "G_AREA DECIMAL(24,4),"+
                    " PRIMARY KEY ( OID )" +
                    ");";

            stmt.execute(sql);

            sql = "CREATE TABLE if not exists GALAMSEY ( " +
                    "GID INTEGER not NULL AUTO_INCREMENT, " +
                    " COLOURS VARCHAR(255), " +
                    " LATITUDE DECIMAL(6,2), " +
                    " LONGITUDE DECIMAL(6,2), " +
                    " GYEAR YEAR," +
                    "OID  INTEGER," +
                    "PRIMARY KEY ( GID ),"+
                    "CONSTRAINT gfk FOREIGN KEY(OID) REFERENCES observatory(OID)"+
                    ");";

            stmt.execute(sql);


        }
        catch (Exception e){
            System.out.println(e);
        }

    }


    public static void insertGalamsey(String vegColour, double lat, double lon, String year, int OID){
        String psql1="INSERT INTO GALAMSEY(COLOURS,LATITUDE,LONGITUDE,GYEAR,OID) VALUES(?,?,?,?,?);";
        try {
            PreparedStatement  ps=con.prepareStatement(psql1);

            ps.setString(1,vegColour);
            ps.setDouble(2,lat);
            ps.setDouble(3,lon);
            ps.setObject(4,year);
            ps.setInt(5,OID);
            ps.execute();

        }
        catch (Exception e){
            System.out.println(e);
        }


    }

    public static void insertObservatory(String name, String country, String startYear, double area){
        String psql1="INSERT INTO OBSERVATORY(ONAME,COUNTRY,STARTYEAR,G_AREA) VALUES(?,?,?,?);";
        try {
            PreparedStatement  ps=con.prepareStatement(psql1);

            ps.setString(1,name);
            ps.setString(2,country);
            ps.setString(3,startYear);
            ps.setDouble(4,area);
            ps.execute();

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static Observatory getObservatory(String name){
        String psql1="SELECT * FROM OBSERVATORY WHERE ONAME=?;";

        try {
            PreparedStatement  ps=con.prepareStatement(psql1);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();

            List<Object> data = new ArrayList<Object>();
            while (rs.next()) {
                String tname = rs.getString("ONAME");
                String country = rs.getString("COUNTRY");
                String year = rs.getString("STARTYEAR").split("-")[0];
                double area = rs.getDouble("G_AREA");
                System.out.println((tname + country + year + area));
                Observatory o=new Observatory(name,country,Year.parse(year),area);
                System.out.println(o);
                return o;
            }
        }
        catch (Exception e){
            System.out.println(e);

        }
        return null;

    }

    public static List<Galamsey> getGalamsey(int OID){
        String psql1="SELECT * FROM GALAMSEY WHERE OID=?;";
        List<Galamsey> data = new ArrayList<Galamsey>();

        try{
            PreparedStatement  ps=con.prepareStatement(psql1);
            ps.setInt(1,OID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String Cveg = rs.getString("COLOURS");
                double latitude = rs.getDouble("LATITUDE");
                double longitude = rs.getDouble("LONGITUDE");
                String year = rs.getString("GYEAR").split("-")[0];
                int OiD = rs.getInt("OID");

//                System.out.println((Cveg + latitude + longitude + year + OiD));
                Galamsey g1 = new Galamsey(Cveg,latitude,longitude, Year.parse(year));
                data.add(g1);

            }
        }
        catch (Exception e){
            System.out.println(e);

        }
        return data;

    }


}
