import javafx.stage.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

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
        btn2.setOnAction(e->Guit1.createNewObsrv());
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
        pane1.getChildren().addAll(menubar,lab1,btn2,btn1);
        scene1 = new Scene(pane1, 640, 480);
        scene1.setFill(Color.rgb(220,100,100,0.55));

        btn3 = new Button("View more ");
        btn4 = new Button("Back to home page.");
        btn4.setOnAction(e-> {mainStage.setScene(scene1); mainStage.show();});

        lab2= new Label("View observatory data");
        VBox pane2 = new VBox(20);
        pane2.getChildren().addAll(lab2,btn3,btn4);
        scene2 = new Scene(pane2, 640, 480);

        mainStage.setScene(scene1);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
