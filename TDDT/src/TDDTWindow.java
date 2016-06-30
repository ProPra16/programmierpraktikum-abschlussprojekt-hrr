
import java.awt.Color;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TDDTWindow extends Application {

	@Override
	public void start(Stage primaryStage) {
		Button katalog = new Button("Wähle Katalog ");
		Button aufgabe = new Button("Wähle Aufgabe");
		Button phase   = new Button("Kompilieren");
		Button nPhase  = new Button("Nächste Phase");
		Button lPhase  = new Button("Letzte Phase    ");
		
		phase.setMinSize(50, 50);
		phase.setStyle("-fx-border-color: black; -fx-background-color: red;");
		
		GridPane left = new GridPane(); 
		left.setPadding(new Insets(10,10,10,10));
		left.setVgap(5);
		left.setHgap(5);
		
		left.add(katalog, 0, 0);
		left.add(aufgabe, 0, 1);
		
		GridPane right = new GridPane(); 
		right.setPadding(new Insets(10,10,10,10));
		right.setVgap(5);
		right.setHgap(5);
		
		right.add(nPhase, 0, 0);
		right.add(lPhase, 0, 1);
		
		GridPane center = new GridPane();
		center.setPadding(new Insets(10,10,10,10));
		center.setVgap(5);
		center.setHgap(5);
		center.setAlignment(Pos.CENTER);
		
		center.add(phase, 0, 0);
		
		BorderPane top = new BorderPane(); 
		top.setLeft(left);
		top.setRight(right);
		top.setCenter(center);
		
		TextArea editor = new TextArea();
		
		
		BorderPane borderpane = new BorderPane();
		borderpane.setTop(top);
		borderpane.setCenter(editor);
		
		Scene scene = new Scene(borderpane, 700, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TDDT");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
