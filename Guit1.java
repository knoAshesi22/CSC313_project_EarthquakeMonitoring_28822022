import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Guit1 extends Application {
   @Override
   public void start(Stage stage) {
      //creating label email
      Text text1 = new Text("Name");
      Text text2 = new Text("Country");
      Text text3 = new Text("Start year");
      Text text4 = new Text("Area");

      //Creating Text Filed for email
      TextField textField1 = new TextField();
      TextField textField2 = new TextField();
      TextField textField3 = new TextField();
      TextField textField4 = new TextField();

      //Creating Buttons
      Button button1 = new Button("Save");
      Button button2 = new Button("Clear");

      //Creating a Grid Pane
      GridPane gridPane = new GridPane();

      //Setting size for the pane
      gridPane.setMinSize(400, 600);

      //Setting the padding
      gridPane.setPadding(new Insets(10, 10, 10, 10));

      //Setting the vertical and horizontal gaps between the columns
      gridPane.setVgap(5);
      gridPane.setHgap(5);

      //Setting the Grid alignment
      gridPane.setAlignment(Pos.CENTER);

      //Arranging all the nodes in the grid
      gridPane.add(text1, 0, 0);
      gridPane.add(textField1, 1, 0);
      gridPane.add(text2, 0, 1);
      gridPane.add(textField2, 1, 1);
      gridPane.add(text3, 0, 2);
      gridPane.add(textField3, 1, 2);
      gridPane.add(text4, 0, 3);
      gridPane.add(textField4, 1, 3);
      gridPane.add(button1, 0, 4);
      gridPane.add(button2, 1, 4);

      //Styling nodes
      button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
      button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

      text1.setStyle("-fx-font: normal bold 20px 'serif' ");
      text2.setStyle("-fx-font: normal bold 20px 'serif' ");
      gridPane.setStyle("-fx-background-color: BEIGE;");

      //Creating a scene object
   Scene scene = new Scene(gridPane);

   //Setting title to the Stage
   stage.setTitle("Create new observatory");

   //Adding scene to the stage
   stage.setScene(scene);

   //Displaying the contents of the stage
   stage.show();
}
  public static void main(String args[]){
     launch(args);
  }
}
