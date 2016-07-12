package TDDT;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Timer läuft im Hintergrund
public class Countdown extends Application {
	
	private int max;
	
	@Override
	public void start(Stage primaryStage)
	{
		Label auf = new Label("Geben sie die Zeit in Sekunden ein" + "\nDrücken sie speichern um fort zu setzen");
		TextField eingabe = new TextField();
		Button save = new Button("Speichern");
		save.setOnAction(event -> {
			max = Integer.parseInt(eingabe.getText());
			Window.speicher(max);
			primaryStage.close();
		});
		VBox hb = new VBox();
		hb.getChildren().addAll(auf, eingabe, save);
		Scene scene = new Scene(hb, 350, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Zeit Einstellen");
		primaryStage.show();
		
	}
}

