package TDDT;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

public class Window extends Application {
	
	private static TaskReader reader = new TaskReader();
	private static String className;
	private static String classTestName;
	private static TextArea editor = new TextArea();
	private boolean test;

	@Override
	public void start(Stage primaryStage) {
		Button katalog = new Button("Wähle Katalog ");
		Button aufgabe = new Button("Wähle Aufgabe");
		Button phase   = new Button();
		Button nPhase  = new Button("Nächste Phase");
		Button lPhase  = new Button("Letzte Phase    ");
		lPhase.setDisable(true);
		phase.setMinSize(50, 50);
		phase.setText("Katalog auswählen");
		phase.setStyle("-fx-border-color: black; -fx-background-color: red;");
		
		katalog.setOnAction( event -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Wählen Sie einen Katalog");
			fileChooser.showOpenDialog(null);
			reader.read(fileChooser.getSelectedFile());
			katalog.setDisable(true);
			phase.setText("Aufgabe auswählen");
		});
		
		aufgabe.setOnAction(event -> {
			if(reader.content())
			{
				test = true;
				AufgabenWindow aw = new AufgabenWindow();
				Stage temp = new Stage();
				aw.start(temp);
				phase.setText("Test schreiben");
				aufgabe.setDisable(true);
			}else
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Keine Aufgaben vorhaneden!");
				alert.setContentText("Wählen sie zuerst einen Katalog aus, bevor sie Aufgaben auswählen");
				alert.showAndWait();
			}
			
		});
		
		nPhase.setOnAction(event -> {
			//Wenn Test funktionieren:
			if(test)
			{
				SaveLoad saveload = new SaveLoad();
				saveload.speichern(classTestName, editor);
				
				//Danach:
				saveload.laden(className, editor);
				
				phase.setText("Code schreiben");
				phase.setStyle("-fx-border-color: black; -fx-background-color: lightgreen;");
				test = false;
			}else
			{
				SaveLoad saveload = new SaveLoad();
				saveload.speichern(className, editor);
				
				//Danach:
				saveload.laden(classTestName, editor);
				
				phase.setText("Test");
				phase.setStyle("-fx-border-color: black; -fx-background-color: red;");
				test = true;
			}
		}); 
		
		lPhase.setOnAction(event -> {
			//Phase zurück
				
		});
		
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
	
	public static TaskReader getReader()
	{
		return reader;
	}
	
	public static void aufgabenEinlesen(SaveLoad s)
	{
		s.laden(classTestName, editor);
	}
	
	
	public static void setClassName(String n)
	{
		className = n;
	}
	
	public static void setClassTestName(String n)
	{
		classTestName = n;
	}
}
