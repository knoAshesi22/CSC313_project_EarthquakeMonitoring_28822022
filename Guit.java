import javafx.stage.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;




public class Guit extends Application {

  Stage mainStage;
  Scene scene1, scene2;

@Override
  public void start(Stage stage) {
      mainStage = stage;

      mainStage.setTitle("Galmsey Activity Monitoring Software");
      Label lab1 = new Label();
      lab1.setText("Hello, Welcome ");


      // Button btn1 = new Button("View observatory data");
      // btn1.setOnAction(e-> mainStage.setScene(scene2));
      // Button btn2 = new Button("Add new observatory data");
      // btn2.setOnAction(e->
      //  );
      //Add items to panez

      Menu FileMenu = new Menu("File");
      MenuItem m1=new MenuItem("Add new observatory");
      MenuItem m2=new MenuItem("Save");
      MenuItem m3=new MenuItem("Exit");

      Menu EditMenu=new Menu("Edit");
      MenuItem em1=new MenuItem("Edit Observatory information");
      MenuItem em2=new MenuItem("View observatories");
      MenuItem em3=new MenuItem("Delete observatory");

      EditMenu.getItems().addAll(em1,em2,em3);
      FileMenu.getItems().addAll(m1,m2,m3);

      MenuBar menubar = new MenuBar();
      menubar.getMenus().addAll(FileMenu,EditMenu);

      StackPane pane1 = new StackPane();
      Scene scene1 = new Scene(pane1, 640, 480);
      scene1.setFill(Color.RED);
      pane1.getChildren().addAll(lab1,menubar);


      // StackPane pane2 = new StackPane();
      // Scene scene2 = new Scene(pane2, 640, 480);
      // pane2.getChildren().addAll(lab2,lab3,lab4,lab5,lab6,lab7);

      mainStage.setScene(scene1);
      mainStage.show();
  }


  public static void main(String[] args) {
      launch(args);
  }

}
