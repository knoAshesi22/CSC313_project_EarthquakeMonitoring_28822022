package GUIv2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Year;
import java.util.ArrayList;

public class GUIv2 extends Application {

    Stage mainStage;

    Scene mainScene;
    Button viewButton, addButton;

    Scene viewScene;
    Button go, backHomeView;

    Label WelcomeLabel, lab2;

    Scene addPage;
    Button backHomeAdd, SaveAdd, FinishAdd, CancelAdd, addGalamseyButton;
    BorderPane mainPaneAdd;
    Monitoringv2 monitor=new Monitoringv2();
    Observatory nObs=new Observatory();


    @Override
    public void start(Stage stage) throws Exception {
        genMainPage(stage);
    }

    public void genMainPage(Stage stage){
        mainStage = stage;

        mainStage.setTitle("Galamsey Activity Monitoring Software");
        WelcomeLabel = new Label("Hello, Welcome");

        viewButton = new Button("View observatory data");
        viewButton.setOnAction(e -> genViewPage());
        addButton = new Button("Add new observatory data");


        addButton.setOnAction(e-> genAddPage());

        //Add items to pane

        Menu FileMenu = new Menu("File");
        MenuItem fm1=new MenuItem("Add new observatory");

        VBox pane1 = new VBox(30);
        VBox temppane = new VBox(20);
        pane1.setStyle("-fx-background-color: BEIGE;");
        temppane.setAlignment(Pos.CENTER);
        temppane.getChildren().addAll(WelcomeLabel, addButton, viewButton);
        pane1.getChildren().addAll(/*menubar,*/temppane);
        mainScene = new Scene(pane1, 640, 480);
        mainScene.setFill(Color.rgb(220,100,100,0.55));

        mainStage.setScene(mainScene);
        mainStage.show();

    }

    public void genViewPage(){
        GridPane pane2=new GridPane();
        monitor.loadObservatories();
        ArrayList<Observatory> observatories=monitor.getObservatories();


        go = new Button("Go ");
        backHomeView = new Button("Back to home page");
        Text text1 = new Text("Name");
        Text text2 = new Text("Country");
        Text text3 = new Text("Start year");
        Text text4 = new Text("Area");
        Text text5 = new Text("Number of events");
        Text text6= new Text("Smallest Colour Value ");
        Text text7= new Text("Largest Colour Value ");
        Text text8 = new Text("Average Colour Value ");
        Text text9 = new Text("No Events with Col Value > n");
        Label lab1 = new Label("View observatory data");

        TextField largerThan =  new TextField();
        Label large = new Label("Type n here");
        Label warn1 = new Label();
        Tooltip largerThanLab = new Tooltip("Value colour values are greater than ");
        Tooltip.install(large,largerThanLab);

        ArrayList<Text[]> nValues=new ArrayList<Text[]>();

        int a=0;
        for (Observatory ob: observatories) {
            Text[] set={
                    new Text(ob.getName()),
                    new Text(ob.getCountry()),
                    new Text(ob.getStartYear().toString()),
                    new Text(String.valueOf(ob.getArea())),
                    new Text(String.valueOf(ob.getEvents().size())),
                    new Text(String.valueOf(ob.getSmallest())),
                    new Text(String.valueOf(ob.getLargest())),
                    new Text(String.valueOf(ob.getAverageColor())),
                    new Text("")
            };

            nValues.add(set);
            pane2.add(nValues.get(a)[0], 0 , 5+a);
            pane2.add(nValues.get(a)[1],1, 5+a);
            pane2.add(nValues.get(a)[2],2,5+a);
            pane2.add(nValues.get(a)[3],3,5+a);
            pane2.add(nValues.get(a)[4],4,5+a);
            pane2.add(nValues.get(a)[5], 5,5+a);
            pane2.add(nValues.get(a)[6],6,5+a);
            pane2.add(nValues.get(a)[7],7,5+a);
            pane2.add(nValues.get(a)[8],8,5+a);

            a++;

        }

        backHomeView.setOnAction(e-> {mainStage.setScene(mainScene); mainStage.show();});

        go.setOnAction(e->{
            int i =5;
            int num=0;
            try{
                num=Integer.parseInt(largerThan.getText());
            }
            catch(NumberFormatException exp){
                warn1.setText("Invalid integer provided");
                return;
            }
            for (Observatory ob:observatories) {
                int s = ob.colValGreaterThan(num).size();
                nValues.get(i-5)[8].setText(String.valueOf(s));
                i++;
            }
        });


        text1.setStyle("-fx-font: normal bold 12px 'serif' ");
        text2.setStyle("-fx-font: normal bold 12px 'serif' ");
        text3.setStyle("-fx-font: normal bold 12px 'serif' ");
        text4.setStyle("-fx-font: normal bold 12px 'serif' ");
        text5.setStyle("-fx-font: normal bold 12px 'serif' ");
        lab1.setStyle("-fx-font: normal bold 15px 'serif' ");

        pane2.setMinSize(600, 600);
        pane2.setPadding(new Insets(5, 10, 10, 10));
        pane2.setVgap(30);
        pane2.setHgap(15);
        pane2.setAlignment(Pos.TOP_CENTER);

        pane2.add(lab1,0,0);
        pane2.add(warn1, 7,3);
        pane2.add(large, 6,2);
        pane2.add(largerThan, 7,2);
        pane2.add(go,8,2);
        pane2.add(backHomeView,8,1);
        pane2.add(text1,0,4);
        pane2.add(text2,1,4);
        pane2.add(text3,2,4);
        pane2.add(text4,3,4);
        pane2.add(text5,4,4);
        pane2.add(text6,5,4);
        pane2.add(text7,6,4);
        pane2.add(text8,7,4);
        pane2.add(text9,8,4);


        pane2.setStyle("-fx-background-color: BEIGE;");
        viewScene = new Scene(pane2, 640, 600);
        mainStage.setScene(viewScene);

    }


    public void genAddPage() {

        mainPaneAdd = new BorderPane();
        GridPane form = new GridPane();
        VBox rightpane = new VBox(15);
        mainPaneAdd.setLeft(form);
        mainPaneAdd.setCenter(rightpane);

        Label l4 = new Label("Enter Observatory details");
        Text oNameText = new Text("Name");
        Text countryText = new Text("Country");
        Text oYearText = new Text("Start year");
        Text oAreaText = new Text("Area");
        TextField oNameField = new TextField();
        TextField countryField = new TextField();
        TextField oYearField = new TextField();
        TextField oAreaField = new TextField();
        CheckBox tcheck=new CheckBox();

        Label l1 = new Label("Enter event Details");
        Text latText = new Text("Latitude");
        Text longText = new Text("Longitude");
        Text gYearText = new Text("Start year");
        Text colorText = new Text("Vegetation colour");
        TextField latField = new TextField();
        TextField longField = new TextField();
        TextField gYearField = new TextField();
        TextField colorField = new TextField();

        Label l3 = new Label("Observatory name:");
        Label l2 = new Label("Events");
        Text t1 = new Text("Start year");
        Text t2 = new Text("Coordinates");
        Text t3 = new Text("Vegetation colour");

         SaveAdd = new Button("Save");
         FinishAdd = new Button("Finish");
         addGalamseyButton= new Button("Add new galamsey event");
         CancelAdd = new Button("Cancel");
         backHomeAdd = new Button("Go back to home page");
         backHomeAdd.setOnAction(e->genMainPage(mainStage));
//        btn3.setOnAction(e->nWin.close());

        rightpane.setMinSize(400,400);
        form.setMinSize(400, 400);
        form.setPadding(new Insets(10, 10, 10, 10));
        form.setVgap(5);
        form.setHgap(5);
        form.setAlignment(Pos.CENTER);
        form.setStyle("-fx-background-color: BEIGE;");

        t1.setStyle("-fx-font: normal bold 20px 'serif' ");
        t2.setStyle("-fx-font: normal bold 20px 'serif' ");
        t3.setStyle("-fx-font: normal bold 20px 'serif' ");
        latText.setStyle("-fx-font: normal bold 20px 'serif' ");
        longText.setStyle("-fx-font: normal bold 20px 'serif' ");
        gYearText.setStyle("-fx-font: normal bold 20px 'serif' ");
        colorText.setStyle("-fx-font: normal bold 20px 'serif' ");
        oNameText.setStyle("-fx-font: normal bold 20px 'serif' ");
        countryText.setStyle("-fx-font: normal bold 20px 'serif' ");
        oYearText.setStyle("-fx-font: normal bold 20px 'serif' ");
        oAreaText.setStyle("-fx-font: normal bold 20px 'serif' ");

        l1.setStyle("-fx-font: normal bold 20px 'serif' ");
        l2.setStyle("-fx-font: normal bold 20px 'serif' ");
        l3.setStyle("-fx-font: normal bold 20px 'serif' ");

        form.setStyle("-fx-background-color: BEIGE;");
        rightpane.setStyle("-fx-background-color: BEIGE;");

        SaveAdd.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        addGalamseyButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        FinishAdd.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        CancelAdd.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        Label latWarning=new Label("");
        Label lonWarning=new Label("");
        Label gYearWarning=new Label("");
        Label oNameWarning=new Label("");
        Label oYearWarning=new Label("");
        Label oAreaWarning=new Label("");
        Label buffer=new Label("");

        addGalamseyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                boolean readyToAdd=true;

                //Validation of Galamsey data
                if(!latField.getText().matches(("(-)?\\d+(.\\d+)?"))||Math.abs(Double.parseDouble(latField.getText()))>90){
                    latWarning.setText("Invalid input entered. Please, enter a decimal between -90 and 90.");
                    readyToAdd=false;
                }
                else{
                    latWarning.setText("");
                }
                if(!longField.getText().matches(("(-)?\\d+(.\\d+)?"))||Math.abs(Double.parseDouble(longField.getText()))>180){
                    lonWarning.setText("Invalid input entered. Please, enter a decimal between -180 and 180.");
                    readyToAdd=false;
                }
                else{
                    lonWarning.setText("");
                }
                Text cord = new Text("("+latField.getText()+", "+longField.getText()+")" );

                if(gYearField.getText().length()!=4 &&!gYearField.getText().matches("\\d\\d\\d\\d")){
                    gYearWarning.setText("Invalid input entered. Please, enter a 4-digit number.");
                    readyToAdd=false;
                }
                else{
                    gYearWarning.setText("");
                }

                Text s = new Text(gYearField.getText());

                Text col = new Text(colorField.getText());


                if(readyToAdd==true){
                    if(nObs==null){
                        nObs=new Observatory();
                    }
                    Galamsey ng=new Galamsey(col.getText(),Double.parseDouble(latField.getText()),Double.parseDouble(longField.getText()),Year.parse(s.getText()));
                    nObs.addEvent(ng);
                    HBox row = new HBox(90);
                    row.getChildren().addAll(s,cord,col);
                    rightpane.getChildren().addAll(row);
                }

            }
        });


        SaveAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                boolean readyToSave=true;
                boolean readyToAdd=true;

                // Validation of observatory data
                if(monitor.checkName(oNameField.getText())){
                    oNameWarning.setText("An observatory with this name already exists. Please, enter another name.");
                    readyToSave=false;
                }
                else if(oNameField.getText().length()==0){
                    oNameWarning.setText("Observatory must have a name. Please, enter name.");
                    readyToSave=false;
                }
                else{
                    oNameWarning.setText("");

                }
                String oName=oNameField.getText();

                if(oYearField.getText().length()!=4 &&!oYearField.getText().matches(("\\d\\d\\d\\d"))){
                    oYearWarning.setText("Invalid input entered. Please, enter a 4-digit number.");
                    readyToSave=false;
                }
                else {
                    oYearWarning.setText("");
                }
                if(!oAreaField.getText().matches("(\\d+)(.\\d+)?")){
                    oAreaWarning.setText("Invalid input entered. Please, enter a number.");
                    readyToSave=false;
                }
                else{
                    oAreaWarning.setText("");
                }



                //Validation of Galamsey data
                if(!latField.getText().matches(("(-)?\\d+(.\\d+)?"))||Math.abs(Double.parseDouble(latField.getText()))>90){
                    latWarning.setText("Invalid input entered. Please, enter a decimal between -90 and 90.");
                    readyToSave=false;
                    readyToAdd=false;
                }
                else{
                    latWarning.setText("");
                }
                if(!longField.getText().matches(("(-)?\\d+(.\\d+)?"))||Math.abs(Double.parseDouble(longField.getText()))>180){
                    lonWarning.setText("Invalid input entered. Please, enter a decimal between -180 and 180.");
                    readyToSave=false;
                    readyToAdd=false;
                }
                else{
                    lonWarning.setText("");
                }
                Text cord = new Text("("+latField.getText()+", "+longField.getText()+")" );

                if(gYearField.getText().length()!=4 &&!gYearField.getText().matches("\\d\\d\\d\\d")){
                    gYearWarning.setText("Invalid input entered. Please, enter a 4-digit number.");
                    readyToSave=false;
                    readyToAdd=false;
                }
                else{
                    gYearWarning.setText("");
                }

                Text s = new Text(gYearField.getText());

                Text col = new Text(colorField.getText());


                if(readyToAdd==true){
                    Galamsey ng=new Galamsey(col.getText(),Double.parseDouble(latField.getText()),Double.parseDouble(longField.getText()),Year.parse(s.getText()));
                    nObs.addEvent(ng);
                    HBox row = new HBox(90);
                    row.getChildren().addAll(s,cord,col);
                    rightpane.getChildren().addAll(row);
                }

                if(readyToSave==true){
                    nObs.setArea(Double.parseDouble(oAreaField.getText()));
                    nObs.setCountry(countryField.getText());
                    nObs.setName(oNameField.getText());
                    nObs.setStartYear(Year.parse(oYearField.getText()));
                    monitor.insertObservatory(nObs);
                    System.out.println(nObs);
                    nObs=new Observatory();
                }

            }
        });



        form.add(l4,0,0);

        form.add(oNameText,0, 1);
        form.add(oNameField, 1,1);
        form.add(oNameWarning,1,2);
//        form.add(oNameField,1,2);

        form.add(countryText,0,3);
        form.add(countryField,1 , 3);
        form.add(new Label(""),1,4);

        form.add(oYearText,0,5);
        form.add(oYearField,1,5);
        form.add(oYearWarning,1,6);

        form.add(oAreaText,0,7);
        form.add(oAreaField,1,7);
        form.add(oAreaWarning,1,8);


        form.add(l1,0,9);

        form.add(latText,0,11);
        form.add(latField,1,11);
        form.add(latWarning,1,12);

        form.add(longText,0,13);
        form.add(longField,1,13);
        form.add(lonWarning,1,14);

        form.add(gYearText,0,15);
        form.add(gYearField,1,15);
        form.add(gYearWarning,1,16);

        form.add(colorText,0,17);
        form.add(colorField,1,17);
        form.add(new Label(""),1,18);
//        form.add(tcheck,2,10);
        form.add(SaveAdd,0,19);
        form.add(addGalamseyButton,1,19);


        //Row for columns
        HBox row1= new HBox(10);
        row1.setAlignment(Pos.CENTER);
        row1.getChildren().add(l2);

        HBox row2= new HBox(5);
        row2.getChildren().addAll(t1,t2,t3);

        HBox row3= new HBox(15);
        row3.setAlignment(Pos.TOP_RIGHT);
        row3.getChildren().add(backHomeAdd);

        rightpane.getChildren().addAll(row3,row1,row2);

        //Row for buttons
        HBox row4= new HBox(5);
        row4.setAlignment(Pos.CENTER);
        row4.getChildren().addAll(FinishAdd, CancelAdd);

        mainPaneAdd.setBottom(row4);



        addPage = new Scene(mainPaneAdd,1000,800);
        mainStage.setTitle("Create new observatory");
        mainStage.setScene(addPage);


    }


}
