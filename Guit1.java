import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.Year;

public class Guit1 {
    static Stage nWin;
    static Scene initScene;
    static BorderPane mainPane;
    static Monitoringv2 monitor;

    public static void createNewObsrv() {
        monitor=new Monitoringv2();
        nWin = new Stage();
        nWin.initModality(Modality.APPLICATION_MODAL);
        nWin.setMinWidth(1000);

        mainPane = new BorderPane();
        GridPane form = new GridPane();
        VBox rightpane = new VBox(5);
        mainPane.setLeft(form);
        mainPane.setCenter(rightpane);

        Label l4 = new Label("Enter Observatory details");
        Text t8 = new Text("Name");
        Text t9 = new Text("Country");
        Text t10 = new Text("Start year");
        Text t11 = new Text("Area");
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();

        Label l1 = new Label("Enter event Details");
        Text t4 = new Text("Latitude");
        Text t5 = new Text("Longitude");
        Text t6 = new Text("Start year");
        Text t7 = new Text("Vegetation colour");
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();
        TextField textField8 = new TextField();

        Label l3 = new Label("Observatory name:");
        Label l2 = new Label("Events");
        Text t1 = new Text("Start year");
        Text t2 = new Text("Coordinates");
        Text t3 = new Text("Vegetation colour");

        Button btn1 = new Button("Save");
        Button btn2 = new Button("Finish");
        Button btn3 = new Button("Cancel");
        btn3.setOnAction(e->nWin.close());

        rightpane.setMinSize(400,600);
        form.setMinSize(400, 600);
        form.setPadding(new Insets(10, 10, 10, 10));
        form.setVgap(5);
        form.setHgap(5);
        form.setAlignment(Pos.CENTER);
        form.setStyle("-fx-background-color: BEIGE;");

        t1.setStyle("-fx-font: normal bold 20px 'serif' ");
        t2.setStyle("-fx-font: normal bold 20px 'serif' ");
        t3.setStyle("-fx-font: normal bold 20px 'serif' ");
        t4.setStyle("-fx-font: normal bold 20px 'serif' ");
        t5.setStyle("-fx-font: normal bold 20px 'serif' ");
        t6.setStyle("-fx-font: normal bold 20px 'serif' ");
        t7.setStyle("-fx-font: normal bold 20px 'serif' ");
        t8.setStyle("-fx-font: normal bold 20px 'serif' ");
        t9.setStyle("-fx-font: normal bold 20px 'serif' ");
        t10.setStyle("-fx-font: normal bold 20px 'serif' ");
        t11.setStyle("-fx-font: normal bold 20px 'serif' ");

        l1.setStyle("-fx-font: normal bold 20px 'serif' ");
        l2.setStyle("-fx-font: normal bold 20px 'serif' ");
        l3.setStyle("-fx-font: normal bold 20px 'serif' ");

        form.setStyle("-fx-background-color: BEIGE;");
        rightpane.setStyle("-fx-background-color: BEIGE;");

        btn1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn3.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        form.add(l4,0,0);
        form.add(t8,0, 1);
        form.add(textField1, 1,1);
        form.add(t9,0,2);
        form.add(textField2,1 , 2);
        form.add(t10,0,3);
        form.add(textField3,1,3);
        form.add(t11,0,4);
        form.add(textField4,1,4);
        form.add(l1,0,6);
        form.add(t4,0,7);
        form.add(textField5,1,7);
        form.add(t5,0,8);
        form.add(textField6,1,8);
        form.add(t6,0,9);
        form.add(textField7,1,9);
        form.add(t7,0,10);
        form.add(textField8,1,10);
        form.add(btn1,1,12);

        //Row for columns
        HBox row1= new HBox(5);
        row1.setAlignment(Pos.CENTER);
        row1.getChildren().add(l2);

        HBox row2= new HBox(5);
        row2.getChildren().addAll(t1,t2,t3);

        rightpane.getChildren().addAll(row1,row2);

        //Row for buttons
        HBox row3= new HBox(10);
        row3.setAlignment(Pos.CENTER);
        row3.getChildren().addAll(btn2,btn3);

        mainPane.setBottom(row3);

        btn2.setOnAction(e->nWin.close());

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Text s = new Text(textField7.getText());
                Text cord = new Text("("+textField5.getText()+", "+textField6.getText()+")" );
                Text col = new Text(textField8.getText());
                HBox row = new HBox(90);
                row.getChildren().addAll(s,cord,col);
                rightpane.getChildren().addAll(row);
                nWin.show();
            }
        });


        initScene = new Scene(mainPane,1000,600);
        nWin.setTitle("Create new observatory");
        nWin.setScene(initScene);
        nWin.show();
    }

    public static void validate(Button save){

    }

    public static void test(TextField name,TextField country,TextField year,TextField area){
        if(testArea(area.getCharacters().toString())==false){
            test(name,country,year,area);
        }
        if(testYear(year.getCharacters().toString())==false){
            test(name,country,year,area);
        }
        if(testForm(name,country,year,area)==false){
            test(name,country,year,area);
        }
        String sname=name.getText();
        String scountry=country.getText();
        String syear=year.getText();
        String sarea=area.getText();

        Observatory o=new Observatory(sname,scountry, Year.parse(syear),Double.parseDouble(sarea));




    }

    public static boolean testForm(TextField name,TextField country,TextField year,TextField area){
        if(name.getCharacters().length()==0||country.getCharacters().length()==0||year.getCharacters().length()==0||area.getCharacters().length()==0){
            System.out.println("One of the fields is left empty. Please fill all fields");
            return false;
        }
        return true;

    }


    private static boolean testYear(String year){
        if(year.length()!=4 &&!year.matches(("\\d\\d\\d\\d"))){
            System.out.println("Incorrect format for Year");
            return false;
        }
        return true;
    }

    private static boolean testArea(String area){
        if(!area.matches("(\\d+)(.(\\d+))?")){
            System.out.println("Incorrect format for Area");
            return  false;
        }
        return true;
    }
}
