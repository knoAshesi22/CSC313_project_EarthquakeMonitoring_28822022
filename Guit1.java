import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Guit1 {
   public static void createNewObsrv() {
      Stage nwWin = new Stage();
      nwWin.initModality(Modality.APPLICATION_MODAL);
      nwWin.setMinWidth(300);
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

   Scene scene = new Scene(gridPane);
   nwWin.setTitle("Create new observatory");
   nwWin.setScene(scene);

   //Displaying the contents of the stage
   nwWin.showAndWait();
}

}