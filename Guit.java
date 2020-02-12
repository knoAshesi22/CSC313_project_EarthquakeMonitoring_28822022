//import javafx.fxml.FXMLLoader;
//import javfx.scene.Parent;
//import javafx.scene.Group;
//import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Guit extends Application {
  @Override
  Stage mainStage;


  public static void loadStartPage(Stage s, Layout l, Monitoring m){
    s.setTitle("Galmsey Activity Monitoring Software");
    Label lab1 = new Label();
    lab1.setText("Hello, Welcome ");

    Button btn1 = new Button("View observatory data");
    btn1.setOnAction(e->displayOdata(s,l,m.getObservatories()));

    Button btn2 = new Button("Add new observatory data");
    btn2.setOnAction(e->editData());
    //Add items to pane
    l.getChildren().addAll(lab1,btn1,btn2);
  }
  public static void displayOdata(Stage s, Layout l, ArrayList<Observatory> os){
    //Displays individual observatory info
    for(Observatory i : os){
      Button btn = new Button(i.getName());
      btn.onAction(dispOpage(i,l));
      l.getChildren().add(btn);
    }
  }
  public static void dispOpage(Observatory o, Layout l){
    Label l1 = new Label();
    l1.setText(o.getName());
    Label l2 = new Label();
    l2.setText(o.getCountry());
    Label l3 = new Label();
    l3.setText(o.getStartYear());
    Label l4 = new Label();
    l4.setText(o.getArea());
    Label l5 = new Label();
    l5.setText(o.getEvents().toString());

    l.getChildren().addAll(l1,l2,l3,l4,l5);
  }
  public static void editData(){
    Stage newStage = new Stage();
    newStage.initModality(Modality.APPLICATION_MODAL);
    newStage.setTitle("Entering new observatory data");
    newStage.setMinWidth(300);

    Label l = new Label();
    l.setText("Enter ob data below");

    Stackpane sp= new StackPane();
    sp.getChildren().add(l);
    Scene ns= new Scene(sp,440,500);
    newStage.showAndWait();
  }

  public static void dispMenus(Stage s, Layout pane){
    Menu FileMenu = new Menu("Monitoring");
    MenuItem m1=new MenuItem("Add observatory");
    MenuItem m2=new MenuItem("Save information");
    MenuItem m3=new MenuItem("Exit");

    Menu EditMenu=new Menu("Edit");
    MenuItem em1=new MenuItem("Edit Observatory information");
    MenuItem em2=new MenuItem("View observatories");
    MenuItem em3=new MenuItem("Delete observatory");

    EditMenu.getItems().addAll(em1,em2,em3);
    FileMenu.getItems().addAll(m1,m2,m3);

    MenuBar menubar = new MenuBar();
    menubar.getMenus().addAll(FileMenu,EditMenu);
    pane.setTop(menubar);
    pane.getChildren().add(menubar);


  }


  public void start(Stage stage) {
      mainStage = stage;
      StackPane pane1 = new StackPane();
      loadStartPage(pane1);
      dispMenus(mainStage, pane1);

      Scene scene1 = new Scene(pane1, 640, 480);
      StackPane rt1 = new StackPane();
      Label l2= new Label("Select an observatory to view data");

      rt1.getChildren().addAll(l2, dispMenus());
      Scene scene2 = new Scene(rt1, 640, 480);

      stage.setScene(scene);
      stage.show();
  }


  public static void main(String[] args) {
      launch();
  }

}
