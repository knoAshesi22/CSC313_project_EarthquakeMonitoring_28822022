import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static GUI.Guit1.test;

public class Guit extends Application {

    Stage mainStage;
    Scene scene1, scene2;
    Button btn1, btn2, btn3, btn4;
    Label lab1, lab2;

    @Override
    public void start(Stage stage) {
        mainStage = stage;

        mainStage.setTitle("Galamsey Activity Monitoring Software");
        lab1 = new Label("Hello, Welcome");
        //lab1.setText();

        btn1 = new Button("View observatory data");
        btn1.setOnAction(e-> {mainStage.setScene(scene2); mainStage.show();});
        btn2 = new Button("Add new observatory data");
//        btn2.setOnAction(e -> test());

        btn2.setOnAction(e-> Guit1.createNewObsrv());
        //Add items to pane

        Menu FileMenu = new Menu("File");
        MenuItem fm1=new MenuItem("Add new observatory");

        //fm1.setOnAction();
        MenuItem fm2=new MenuItem("Save");
        MenuItem fm3=new MenuItem("Exit");
        FileMenu.getItems().addAll(fm1,fm2,fm3);

        Menu EditMenu=new Menu("Edit");
        MenuItem em1=new MenuItem("Edit Observatory information");
        MenuItem em2=new MenuItem("View observatories");
        MenuItem em3=new MenuItem("Delete observatory");
        EditMenu.getItems().addAll(em1,em2,em3);

        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(FileMenu,EditMenu);

        VBox pane1 = new VBox(30);
        VBox temppane = new VBox(20);
        pane1.setStyle("-fx-background-color: BEIGE;");
        temppane.setAlignment(Pos.CENTER);
        temppane.getChildren().addAll(lab1,btn2,btn1);
        pane1.getChildren().addAll(menubar,temppane);
        scene1 = new Scene(pane1, 640, 480);
        scene1.setFill(Color.rgb(220,100,100,0.55));

        //Scene 2
        btn3 = new Button("Edit data ");
        btn4 = new Button("Back to home page");
        Text text1 = new Text("Name");
        Text text2 = new Text("Country");
        Text text3 = new Text("Start year");
        Text text4 = new Text("Area");
        Text text5 = new Text("Number of events");
        Label lab1 = new Label("View observatory data");

        btn4.setOnAction(e-> {mainStage.setScene(scene1); mainStage.show();});

        text1.setStyle("-fx-font: normal bold 12px 'serif' ");
        text2.setStyle("-fx-font: normal bold 12px 'serif' ");
        text3.setStyle("-fx-font: normal bold 12px 'serif' ");
        text4.setStyle("-fx-font: normal bold 12px 'serif' ");
        text5.setStyle("-fx-font: normal bold 12px 'serif' ");
        lab1.setStyle("-fx-font: normal bold 15px 'serif' ");


        GridPane pane2 = new GridPane();
        pane2.setMinSize(600, 600);
        pane2.setPadding(new Insets(5, 10, 10, 10));
        pane2.setVgap(30);
        pane2.setHgap(15);
        pane2.setAlignment(Pos.TOP_CENTER);

        pane2.add(lab1,0,0);
        pane2.add(btn3,3,1);
        pane2.add(btn4,4,1);
        pane2.add(text1,0,2);
        pane2.add(text2,1,2);
        pane2.add(text3,2,2);
        pane2.add(text4,3,2);
        pane2.add(text5,4,2);
        pane2.setStyle("-fx-background-color: BEIGE;");
        scene2 = new Scene(pane2, 640, 600);

        mainStage.setScene(scene1);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
