package TDDT;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AufgabenWindow extends Application
{
	private String name;
	private String testName;

	@Override
	public void start(Stage primaryStage)
	{
		Button aufgabe1 = new Button();
		Button aufgabe2 = new Button();
		Button aufgabe3 = new Button();
		TaskReader tr = Window.getReader();
		Aufgabe temp = tr.getAufgabe(0);
		aufgabe1.setText(temp.getName());
		temp = tr.getAufgabe(2);
		aufgabe2.setText(temp.getName());
		temp = tr.getAufgabe(4);
		aufgabe3.setText(temp.getName());
		
		aufgabe1.setOnAction( event -> {
			Aufgabe laden = tr.getAufgabe(0);
			Window.setAufgabenNummer(0);
			name = laden.getName();
			testName = name + "Test.java";
			name += ".java";
			namenSetzen();
			SaveLoad sl = new SaveLoad();
			Window.aufgabenEinlesen(sl);
			primaryStage.close();
		});
		
		aufgabe2.setOnAction( event -> {
			Aufgabe laden = tr.getAufgabe(2);
			Window.setAufgabenNummer(2);
			name = laden.getName();
			testName = name + "Test.java";
			name += ".java";
			System.out.print(name);
			System.out.print(testName);
			namenSetzen();
			SaveLoad sl = new SaveLoad();
			Window.aufgabenEinlesen(sl);
			primaryStage.close();
		});
		
		aufgabe3.setOnAction( event -> {
			Aufgabe laden = tr.getAufgabe(4);
			Window.setAufgabenNummer(4);
			name = laden.getName();
			testName = name + "Test.java";
			name += ".java";
			namenSetzen();
			SaveLoad sl = new SaveLoad();
			Window.aufgabenEinlesen(sl);
			primaryStage.close();
		});
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.add(aufgabe1, 0, 0);
		gp.add(aufgabe2, 1, 0);
		gp.add(aufgabe3, 2, 0);
		
		Scene scene = new Scene(gp, 350, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Aufgabe auswählen");
		primaryStage.show();
	}
	
	public void namenSetzen()
	{
		Window.setClassName(name);
		Window.setClassTestName(testName);
	}
}
