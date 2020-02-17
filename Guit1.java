import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    static GridPane maingridPane;

    public static void createNewObsrv() {
        nWin = new Stage();
        nWin.initModality(Modality.APPLICATION_MODAL);
        nWin.setMinWidth(300);
        //creating label for text fields
        Text text1 = new Text("Name");
        Text text2 = new Text("Country");
        Text text3 = new Text("Start year");
        Text text4 = new Text("Area");

        //Creating Text Filed for observatory data
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();

        //Creating Buttons
        Button button1 = new Button("Save");
        Button button2 = new Button("Next");

        //Creating a Grid Pane
        maingridPane = new GridPane();

        //Setting size for the pane
        maingridPane.setMinSize(400, 600);

        //Setting the padding
        maingridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        maingridPane.setVgap(5);
        maingridPane.setHgap(5);

        //Setting the Grid alignment
        maingridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        maingridPane.add(text1, 0, 0);
        maingridPane.add(textField1, 1, 0);
        maingridPane.add(text2, 0, 1);
        maingridPane.add(textField2, 1, 1);
        maingridPane.add(text3, 0, 2);
        maingridPane.add(textField3, 1, 2);
        maingridPane.add(text4, 0, 3);
        maingridPane.add(textField4, 1, 3);
        maingridPane.add(button1, 0, 4);
        maingridPane.add(button2, 1, 4);

        //Styling nodes
        button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        button1.setOnAction(actionEvent -> test(textField1,textField2,textField3,textField4));
        button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        button2.setOnAction(actionEvent -> createGalamsey());


        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");
        text3.setStyle("-fx-font: normal bold 20px 'serif' ");
        text4.setStyle("-fx-font: normal bold 20px 'serif' ");


        maingridPane.setStyle("-fx-background-color: BEIGE;");

        initScene = new Scene(maingridPane);
        nWin.setTitle("Create new observatory");
        nWin.setScene(initScene);

        //Displaying the contents of the stage
        nWin.showAndWait();
    }
    public static void createGalamsey(){
        //Scene B
        VBox mainpane = new VBox(5);
        HBox row1 = new HBox(5);
        HBox row2 = new HBox(15);
        HBox row3 = new HBox(5);

        Label l1 = new Label("Events");
        Text tx1 = new Text("Start year");
        Text tx2 = new Text("Cooordinates");
        Text tx3 = new Text("Vegetation colllour");
        Button b1 = new Button("Add new event");

        row1.getChildren().add(l1);
        row2.getChildren().add(b1);
        row3.getChildren().addAll(tx1,tx2,tx3);

        mainpane.getChildren().addAll(row1,row2,row3);
        mainpane.setStyle("-fx-background-color: BEIGE;");
        row1.setAlignment(Pos.CENTER);
        row2.setAlignment(Pos.CENTER_RIGHT);

        Scene newScene1 = new Scene(mainpane,500,600);


        nWin.setScene(newScene1);
        nWin.show();

        //Scene A
        VBox pane = new VBox(15);
        HBox inpane1 = new HBox(5);
        HBox inpane2 = new HBox(5);
        HBox inpane3 = new HBox(5);
        HBox inpane4 = new HBox(5);
        HBox inpane5 = new HBox(5);

        Text t1 = new Text("Latitude");
        Text t2 = new Text("Longitude");
        Text t3 = new Text("Start year");
        Text t4 = new Text("Vegetation colour");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();


        Button btn1 = new Button("Done");

        Button btn2 = new Button("Cancel");


        t1.setStyle("-fx-font: normal bold 20px 'serif' ");
        t2.setStyle("-fx-font: normal bold 20px 'serif' ");
        t3.setStyle("-fx-font: normal bold 20px 'serif' ");
        t4.setStyle("-fx-font: normal bold 20px 'serif' ");


        pane.setStyle("-fx-background-color: BEIGE;");
        btn1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        inpane1.setAlignment(Pos.CENTER);
        inpane2.setAlignment(Pos.CENTER);
        inpane3.setAlignment(Pos.CENTER);
        inpane4.setAlignment(Pos.CENTER);
        inpane5.setAlignment(Pos.CENTER);

        inpane1.getChildren().addAll(t1,textField1);
        inpane2.getChildren().addAll(t2,textField2);
        inpane3.getChildren().addAll(t3,textField3);
        inpane4.getChildren().addAll(t4,textField4);
        inpane5.getChildren().addAll(btn1,btn2);
        pane.getChildren().addAll(inpane1,inpane2,inpane3,inpane4,inpane5);
        pane.setAlignment(Pos.CENTER);
        Scene newScene = new Scene(pane,400,600);


        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HBox newRow = new HBox(5);
                Text tt1 = new Text(textField1.getText());
                Text tt2 = new Text(textField2.getText());
                Text tt3 = new Text(textField3.getText());
                Text tt4 = new Text(textField4.getText());
                newRow.getChildren().addAll(tt1,tt2,tt3,tt4);
                maingridPane.getChildren().add(newRow);
                nWin.setScene(newScene);
                nWin.show();
            }
        });



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
