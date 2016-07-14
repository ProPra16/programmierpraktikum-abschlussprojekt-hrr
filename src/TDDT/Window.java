
	package TDDT;
	
import java.util.ArrayList;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

	public class Window extends Application {
		
		private static TaskReader reader = new TaskReader();
		private static String className;
		private static String classTestName;
		private static TextArea editorL = new TextArea();
		private static TextArea editorLR = new TextArea();
		private static int aufgabenNummer;
		private static int testNummer;
		private boolean test;
		private boolean refactor;
	    private static boolean code;
	    private static int zeit;
	    private static boolean babysteps;
	    private static Button baby = new Button("Babysteps");
	    private static TextField zeitAnzeige = new TextField();
	    private static String anzeige = "";
		private static WindowTimer timer;
		private static ArrayList verlauf = new ArrayList();
		private static Tracking verlaufsZeit;
		private static Log doc;
		private static Tester testX = new Tester();
		
		@Override
		public void start(Stage primaryStage) {
			
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				if(babysteps) timer.beenden();
				System.exit(1);
				primaryStage.close();
			});
			
			Button katalog = new Button("Wähle Katalog ");
			Button aufgabe = new Button("Wähle Aufgabe");
			Button phase   = new Button();
			Button nPhase  = new Button("Nächste Phase");
			Button lPhase  = new Button("Letzte Phase    ");
			Button makeLog = new Button("Erstelle Log");
			makeLog.setDisable(true);
			
			lPhase.setDisable(true); 						//Nur in der Codephase ausführbar
			baby.setDisable(true);
			phase.setMinSize(50, 50);
			phase.setText("Katalog auswählen");
			phase.setStyle("-fx-border-color: black; -fx-background-color: red;");
			
			katalog.setOnAction( event -> {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Wählen Sie einen Katalog");
				fileChooser.showOpenDialog(null);
				reader.read(fileChooser.getSelectedFile());
				katalog.setDisable(true);
				baby.setDisable(false);
				phase.setText("Aufgabe auswählen");
			});
			
			baby.setOnAction(event -> {
				if(babysteps){
					timer.beenden();
				}
				Countdown c = new Countdown();
				Stage temp =  new Stage();
				c.start(temp);

			});
			aufgabe.setOnAction(event -> {
				//Wenn Aufgabe gewählt , schreibe Test
				
				if(reader.content())
				{
						editorLR.setDisable(true);
						editorLR.setText("");
						editorL.setDisable(false);
						code = true; 									//Sprungvariable Nächste Phase Codebearbeiten
						AufgabenWindow aw = new AufgabenWindow();
						Stage temp = new Stage();
						aw.start(temp);
						phase.setText("Test schreiben");
						phase.setStyle("-fx-border-color: black; -fx-background-color: red;");
					verlaufsZeit = new Tracking(); 		//Log
				}else
				{
					AlertWindow alert = new AlertWindow();
					alert.display("Aufgaben im Katalog", "Wählen sie zuerst einen Katalog aus, bevor sie Aufgaben auswählen");
				}
			});
			
			nPhase.setOnAction(event -> { //ROT
				Aufgabe a = new Aufgabe();
				Aufgabe b = new Aufgabe();
				makeLog.setDisable(false);
				if(code)
				{
					a = reader.getAufgabe(testNummer);		//Liest die Test-Klase ein
					b = reader.getAufgabe(aufgabenNummer);	//Liest die Aufgaben-Klasse ein
					String inhalt = editorL.getText();
					if(testX.CompileClass(a.getName(), inhalt, true, b.getName(), b.getContent(), false) || testX.testTesten(a.getName(), inhalt, true, b.getName(), b.getContent(), false))
					{
						verlauf.add(verlaufsZeit.getSecondPassed());		//Für Log
						
						
						//speichert Test und lädt Code in den editorL
						if(babysteps){
							timer.beenden();
						}
						lPhase.setDisable(false);
						SaveLoad saveload = new SaveLoad();
						saveload.speichern(classTestName, editorL);
						a.setText(editorL.getText());
						reader.setAufgabe(a, testNummer);
						saveload.laden(className, editorLR);
						editorL.setDisable(true);
						editorLR.setDisable(false);
						phase.setText("Code schreiben");
						phase.setStyle("-fx-border-color: black; -fx-background-color: lightgreen;");
						code = false;
						refactor = true; //Leitet Refactoring ein
						if(babysteps)
						{
							timer = new WindowTimer(zeit);
						}	
					}
				}else if(test) { //SCHWARZ
					a = reader.getAufgabe(testNummer);
					b = reader.getAufgabe(aufgabenNummer);
				//	Tester testX = new Tester();
					String inhalt2 = editorLR.getText(); 
					if(testX.funktTesten(a.getName(), a.getContent(), true, b.getName(), inhalt2, false))
					{
						verlauf.add(verlaufsZeit.getSecondPassed());
						//Lädt Test in den editorL
						if(babysteps){
							timer.beenden();
						}
						lPhase.setDisable(true);
						SaveLoad saveload = new SaveLoad();
						saveload.speichern(className, editorLR);
					
						b.setText(editorLR.getText());
						reader.setAufgabe(b, aufgabenNummer);
						editorL.setDisable(false);
						editorLR.setDisable(true);
						phase.setText("Test schreiben");
						phase.setStyle("-fx-border-color: black; -fx-background-color: red;");
						test = false;
						code = true;
						if(babysteps)
						{
							timer = new WindowTimer(zeit);
						}
					}else
					{
						
						AlertWindow alert = new AlertWindow();
						alert.display("CompileError", testX.getError2String());
					}
				}else if(refactor) { //GREEN
					
					//Refactoring Phase
					a = reader.getAufgabe(testNummer);
					b = reader.getAufgabe(aufgabenNummer);
			//		Tester test2 = new Tester();
					String inhalt3 = editorLR.getText();
					if(testX.funktTesten(a.getName(), a.getContent(), true, b.getName(), inhalt3, false))
					{
						verlauf.add(verlaufsZeit.getSecondPassed());
						if(babysteps){
							timer.beenden();
						}
						lPhase.setDisable(true);
						SaveLoad saveload = new SaveLoad();
						saveload.speichern(className, editorLR);
						
						b.setText(editorLR.getText());
						reader.setAufgabe(b, aufgabenNummer);
						
						phase.setText("REFACTORING");
						phase.setStyle("-fx-border-color: white; -fx-background-color: black;");
						refactor = false;
						test = true;
					}else
					{
						AlertWindow alert = new AlertWindow();
						alert.display("CompileError", testX.getError2String());
					}
				}
			}); 
			
			//Button um letzte Phase aufrufen zu können
			lPhase.setOnAction(event -> {
				if(babysteps){
					timer.beenden();
				}
				editorLR.setDisable(true);
				editorL.setDisable(false);
				phase.setText("Test schreiben");
				phase.setStyle("-fx-border-color: black; -fx-background-color: red;");
				code = true;
				refactor = false;
				if(babysteps)
				{
					timer = new WindowTimer(zeit);
				}
			});
			
			makeLog.setOnAction(event -> {
				doc = new Log();
				doc.erstelleLog(verlauf);
				AlertWindow alert = new AlertWindow();
				alert.display("Log erstellt", "  Zeitübersicht in Log.txt erstellt. \n  Fehlerübersicht in FehlerLog.txt gespeichert.");
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
			BorderPane editorPane = new BorderPane();
			borderpane.setCenter(editorPane);
			editorPane.setLeft(editorL);
			editorPane.setRight(editorLR);
			
			GridPane box = new GridPane();			
			box.setAlignment(Pos.CENTER);
			box.add(baby,0,0);
			box.setVgap(5);			
			zeitAnzeige.setMaxWidth(50);			
			box.add(zeitAnzeige, 0, 1);
			box.add(makeLog, 0, 2);
			editorPane.setCenter(box);
			
			Scene scene = new Scene(borderpane, 1200, 500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("TDDT");
			primaryStage.show();
		}
		
		public static void abgelaufen()
		{
			if(code)
			{
				SaveLoad einlesen = new SaveLoad();
				aufgabenEinlesen(einlesen);
				timer = new WindowTimer(zeit);
			}else
			{
				SaveLoad einlesen = new SaveLoad();
				testEinlesen(einlesen);
				timer = new WindowTimer(zeit);
			}
		}

		public static void main(String[] args) {
			launch(args);
		}
		
		public static TaskReader getReader()
		{
			return reader;
		}
		
		public static void testEinlesen(SaveLoad s)
		{
			s.laden(className, editorLR);
		}
		
		public static void aufgabenEinlesen(SaveLoad s)
		{
			s.laden(classTestName, editorL);
		}
		
		
		public static void setClassName(String n)
		{
			className = n;
		}
		
		public static void setClassTestName(String n)
		{
			classTestName = n;
		}
		
		public static void setAufgabenNummer(int n)
		{
			aufgabenNummer = n;
		}
		
		public static void setTestNummer(int n)
		{
			testNummer = n;
		}
		
		public static void speicher(int t)
		{
			zeit = t;
			timer = new WindowTimer(zeit);
			babysteps = true;
		}
		
		public static void setAnzeige(int s)
		{
			anzeige = String.valueOf(s);
			setZeit();
		}
		
		public static void setZeit()
		{
			zeitAnzeige.setText(anzeige);
		}
		
		public static Tester getTester(){
			return testX;
		}
		
}

