package TDDT;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWindow {	
	
	public AlertWindow(){
		
	}
	
	public static void display(){
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);  //Man muss das neu geöffnete Fenster zuerst bearbeiten.
		window.setTitle("Aufgaben im Katalog");
		window.setMinWidth(250);
		
		Label label = new Label("Wählen sie zuerst einen Katalog aus, bevor sie Aufgaben auswählen");
		Button close = new Button("OK");
		close.setOnAction(event -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, close);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();		
		
	}
	
}
