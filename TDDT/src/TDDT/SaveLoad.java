package TDDT;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SaveLoad extends Application{	
	
	TextArea text;
	String datei;		//Variabel die benutzt wird um 
	
	public static void main(String[] args){		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("TDDT");
		
		GridPane root = new GridPane();
		root.setPrefSize(1000, 750);
		
		text = new TextArea();						//Textfeld um etwas zu schreiben
		text.setPrefSize(900, 650);
		root.getChildren().add(text);
		
		
		HBox topMenu = new HBox();					//HBox für die Oberen Buttons
		
		Button save = new Button("Speichern");
		save.setOnAction(event -> speichern());		//Die Methode "speichern" wird aufgerufen		
		Button load = new Button("Laden");
		load.setOnAction(event -> laden());			//Die Methode "laden" wird aufgerufen
		
		topMenu.getChildren().addAll(save, load);		
		
		BorderPane haupt = new BorderPane();			//Komplette Interface
		haupt.setTop(topMenu);

		haupt.setCenter(root);							//Program Mitte		
		
				
		Scene scene = new Scene(haupt);		
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public void speichern(){							//Inhalt des Textfelds in eine Datei speichern
		BufferedWriter writer = null; 
		
		try {
			writer = new BufferedWriter (new FileWriter("Test.java"));
			writer.write(text.getText());
			writer.flush();
			writer.close();
		} catch (IOException e){
			System.out.println("RIP");
		}		
	}
	
	public void laden(){								//Aus einer Datei in das Textfeld laden
		String s = "";
		File file = new File ("Test.java");
		
		try(Scanner sc = new Scanner(file);){ 
			while(sc.hasNextLine()){
				s += sc.nextLine();
				s += "\n";
			}
			text.setText(s);					
		} catch (FileNotFoundException e) {			
			System.out.println("FileNotFoundException: " + "RIP" + "\n");
		}
	}
	
}
