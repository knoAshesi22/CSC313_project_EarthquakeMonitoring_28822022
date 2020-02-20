package GUIv2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewPage extends Application {

    Scene scene2;
    Button btn3, btn4;


    @Override
    public void start(Stage stage){
        btn3 = new Button("Edit data ");
        btn4 = new Button("Back to home page");
        Text text1 = new Text("Name");
        Text text2 = new Text("Country");
        Text text3 = new Text("Start year");
        Text text4 = new Text("Area");
        Text text5 = new Text("Number of events");
        Label lab1 = new Label("View observatory data");

//        btn4.setOnAction(e-> {mainStage.setScene(mainScene); mainStage.show();});

        text1.setStyle("-fx-font: normal bold 12px 'serif' ");
        text2.setStyle("-fx-font: normal bold 12px 'serif' ");
        text3.setStyle("-fx-font: normal bold 12px 'serif' ");
        text4.setStyle("-fx-font: normal bold 12px 'serif' ");
        text5.setStyle("-fx-font: normal bold 12px 'serif' ");
        lab1.setStyle("-fx-font: normal bold 15px 'serif' ");
    }

}
