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
  
  Scene scene1, scene2;

@Override
  public void start(Stage stage) {
      Stage mainStage = stage;

      mainStage.setTitle("Galamsey Activity Monitoring Software");
      Label lab1 = new Label();
      lab1.setText("Hello, Welcome ");


      Button btn1 = new Button("View observatory data");
      btn1.setOnAction(e-> {mainStage.setScene(scene2);mainStage.show();});
      Button btn2 = new Button("Add new observatory data");
      btn2.setOnAction(e->Guit1.createNewObsrv());
      //Add items to panez

      Menu FileMenu = new Menu("File");
      MenuItem m1=new MenuItem("Add new observatory");
      //m1.setOnAction();
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

      VBox pane1 = new VBox(30);
      pane1.getChildren().addAll(menubar,btn2,btn1,lab1);
      Scene scene1 = new Scene(pane1, 640, 480);
      scene1.setFill(Color.rgb(220,100,100,0.55));

      Button btn3 = new Button("View more ");
      Label lab2= new Label();
      lab2.setText("View observatory data");
      StackPane pane2 = new StackPane();
      pane2.getChildren().addAll(lab2,btn3);
      Scene scene2 = new Scene(pane2, 640, 480);

      mainStage.setScene(scene1);
      mainStage.show();
  }


  public static void main(String[] args) {
      launch(args);
  }

}
