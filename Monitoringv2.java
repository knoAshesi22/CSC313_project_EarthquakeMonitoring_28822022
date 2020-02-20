package GUIv2;


import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Monitoringv2 {

    private ArrayList<Observatory> observatories;
    private HashSet<String> setNames;

    static final String DB_URL = "jdbc:mysql://localhost/";

    private String USER = "root";
    private String PASS = "admin";
    private String DBNAME="monitorDB";
    Connection con = null;
    Statement stmt = null;

    public Monitoringv2() {
        this("root","admin","monitorDBv1");
    }

    public Monitoringv2(String USER, String PASS, String DBNAME) {
        this.USER = USER;
        this.PASS = PASS;
        this.DBNAME = DBNAME;
        this.observatories=new ArrayList<Observatory>();

        connectDB();
        createTables();
        loadObservatories();
        setNames();


    }

    public void setNames(){
        setNames=new HashSet<String>();
        Boolean exists=false;
        for (Observatory o:getObservatories()) {
            setNames.add(o.getName());
        }
    }

    public boolean checkName(String name){
        if(setNames.contains(name)){
            return true;
        }
        else return false;
    }

    public ArrayList<Observatory> getObservatories(){
        return this.observatories;
    }

    public int getNumO(){
        return this.observatories.size();
    }

    public void connectDB(){
        try{
            con = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql="Create schema if not exists "+DBNAME;
            stmt = con.createStatement();
            stmt.execute(sql);
            con = DriverManager.getConnection(DB_URL+DBNAME,USER,PASS);
            stmt = con.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void createTables(){

        try{

//            String sql = "CREATE TABLE if not exists OBSERVATORY ( " +
//                    " OID INTEGER not NULL AUTO_INCREMENT, " +
//                    " ONAME VARCHAR(255) UNIQUE, " +
//                    " COUNTRY VARCHAR(255), " +
//                    " STARTYEAR YEAR , " +
//                    "G_AREA DECIMAL(24,4),"+
//                    " PRIMARY KEY ( OID )" +
//                    ");";

            String sql = "CREATE TABLE if not exists OBSERVATORY ( " +
                    " ONAME VARCHAR(255) not NULL UNIQUE, " +
                    " COUNTRY VARCHAR(255), " +
                    " STARTYEAR YEAR , " +
                    "G_AREA DECIMAL(24,4),"+
                    " PRIMARY KEY ( ONAME )" +
                    ");";

            stmt.execute(sql);

            sql = "CREATE TABLE if not exists GALAMSEY ( " +
                    "GID INTEGER not NULL AUTO_INCREMENT, " +
                    " COLOURS VARCHAR(255), " +
                    " LATITUDE DECIMAL(6,2), " +
                    " LONGITUDE DECIMAL(6,2), " +
                    " GYEAR YEAR," +
                    "ONAME  VARCHAR(255)," +
                    "PRIMARY KEY ( GID ),"+
                    "CONSTRAINT gfk FOREIGN KEY(ONAME) REFERENCES observatory(ONAME)"+
                    ");";

            stmt.execute(sql);


        }
        catch (Exception e){
            System.out.println(e);
        }

    }

//    public void insertGalamsey(Galamsey g){
//        insertGalamsey(g.getVegColour(),g.getPosition().getLatitude(),g.getPosition().getLongitude(),g.getYear(), OID);
//    }

    public void insertGalamsey(String vegColour, double lat, double lon, String year, String Oname){
        String psql1="INSERT INTO GALAMSEY(COLOURS,LATITUDE,LONGITUDE,GYEAR,ONAME) VALUES(?,?,?,?,?);";
        try {
            PreparedStatement  ps=con.prepareStatement(psql1);

            ps.setString(1,vegColour);
            ps.setDouble(2,lat);
            ps.setDouble(3,lon);
            ps.setObject(4,year);
            ps.setString(5,Oname);
            ps.execute();

        }
        catch (Exception e){
            System.out.println(e);
        }


    }

    public void insertObservatory(Observatory o){
        insertObservatory(o.getName(),o.getCountry(),o.getStartYear().toString(),o.getArea());
        for(Galamsey g:o.getEvents()){
            try{
                insertGalamsey(g.getVegColour(),g.getPosition().getLatitude(),g.getPosition().getLatitude(),g.getYear().toString(),o.getName());
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }


    public void insertObservatory(String name, String country, String startYear, double area){
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

    public Observatory selectObservatory(String name){
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
                Observatory o=new Observatory(name,country,Year.parse(year),area);
                ArrayList<Galamsey> galamseys=selectGalamsey(tname);
                o.setEvents(galamseys);

                return o;
            }
        }
        catch (Exception e){
            System.out.println(e);

        }
        return null;

    }

    public ArrayList<Galamsey> selectGalamsey(String oname){
        String psql1="SELECT * FROM GALAMSEY WHERE ONAME=?;";
        ArrayList<Galamsey> data = new ArrayList<Galamsey>();

        try{
            PreparedStatement  ps=con.prepareStatement(psql1);
            ps.setString(1,oname);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String Cveg = rs.getString("COLOURS");
                double latitude = rs.getDouble("LATITUDE");
                double longitude = rs.getDouble("LONGITUDE");
                String year = rs.getString("GYEAR").split("-")[0];

                Galamsey g1 = new Galamsey(Cveg,latitude,longitude, Year.parse(year));
                data.add(g1);

            }
        }
        catch (Exception e){
            System.out.println(e);

        }
        return data;

    }

    public void loadObservatories(){
        String psql1="SELECT * FROM OBSERVATORY;";

        try {
            PreparedStatement  ps=con.prepareStatement(psql1);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String tname = rs.getString("ONAME");
                String country = rs.getString("COUNTRY");
                String year = rs.getString("STARTYEAR").split("-")[0];
                double area = rs.getDouble("G_AREA");
                Observatory o=new Observatory(tname,country,Year.parse(year),area);
                ArrayList<Galamsey> galamseys=selectGalamsey(tname);
                o.setEvents(galamseys);
                observatories.add(o);


//                System.out.println(o);
            }
        }
        catch (Exception e){
            System.out.println(e);

        }

    }



    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public String getDBNAME() {
        return DBNAME;
    }

    public void setDBNAME(String DBNAME) {
        this.DBNAME = DBNAME;
    }


}
