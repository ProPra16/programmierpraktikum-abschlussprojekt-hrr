
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
		private static TextArea editorL = new TextArea();
		private static TextArea editorLR = new TextArea();
		private static int aufgabenNummer;
		private int testNummer;
		private boolean test;
		private boolean refactor;
	    private boolean code;
		
		@Override
		public void start(Stage primaryStage) {
			Button katalog = new Button("Wähle Katalog ");
			Button aufgabe = new Button("Wähle Aufgabe");
			Button phase   = new Button();
			Button nPhase  = new Button("Nächste Phase");
			Button lPhase  = new Button("Letzte Phase    ");
			lPhase.setDisable(true); 						//Nur in der Codephase ausführbar
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
				//Wenn Aufgabe gewählt , schreibe Test
				
				if(reader.content())
				{
					code = true; 		//Sprungvariable Nächste Phase Codebearbeiten
					AufgabenWindow aw = new AufgabenWindow();
					Stage temp = new Stage();
					aw.start(temp);
					phase.setText("Test schreiben");
					testNummer = aufgabenNummer + 1;
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
				if(code)
				{
					Aufgabe a = reader.getAufgabe(testNummer);		//Liest die Test-Klase ein
					System.out.println(a.getName());
					Aufgabe b = reader.getAufgabe(aufgabenNummer);	//Liest die Aufgaben-Klasse ein
					System.out.println(b.getName());
					System.out.println(b.getContent());
					Tester test = new Tester();
					String inhalt = editorL.getText();
					if(test.CompileClass(a.getName(), inhalt, true, b.getName(), b.getContent(), false) || test.testTesten(a.getName(), inhalt, true, b.getName(), b.getContent(), false))
					{
						//speichert Test und lädt Code in den editorL
						lPhase.setDisable(false);
						SaveLoad saveload = new SaveLoad();
						saveload.speichern(classTestName, editorL);
						a.setText(editorL.getText());
						reader.setAufgabe(a, testNummer);
						saveload.laden(className, editorL);
						
						phase.setText("Code schreiben");
						phase.setStyle("-fx-border-color: black; -fx-background-color: lightgreen;");
						code = false;
						refactor = true; //Leitet Refactoring ein
					}else
					{
						System.out.println("Fehler");
					}
				}else if(test) {
					//Lädt Test in den editorL
					lPhase.setDisable(true);
					SaveLoad saveload = new SaveLoad();
					saveload.speichern(className, editorL);
					Aufgabe b = reader.getAufgabe(aufgabenNummer);
					b.setText(editorL.getText());
					reader.setAufgabe(b, aufgabenNummer);
					
					saveload.laden(classTestName, editorL);
					
					phase.setText("Test schreiben");
					phase.setStyle("-fx-border-color: black; -fx-background-color: red;");
					test = false;
					code = true;
				}else if(refactor) {
					//Refactoring Phase
					lPhase.setDisable(true);
					phase.setText("REFACTORING");
					phase.setStyle("-fx-border-color: white; -fx-background-color: black;");
					refactor = false;
					test = true;
				}
			}); 
			
			//Button um letzte Phase aufrufen zu können
			lPhase.setOnAction(event -> {
				SaveLoad saveload = new SaveLoad();
				saveload.laden(classTestName, editorL);
				
				phase.setText("Test schreiben");
				phase.setStyle("-fx-border-color: black; -fx-background-color: red;");
				code = true;
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
			
			Scene scene = new Scene(borderpane, 1200, 500);
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
}

