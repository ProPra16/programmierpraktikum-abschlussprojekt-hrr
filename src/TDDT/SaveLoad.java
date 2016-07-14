package TDDT;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.TextArea;

public class SaveLoad{	
	
	TextArea text;
	String datei;		//Variabel die benutzt wird um 
	
	public SaveLoad(){
		
	}

	public void speichern(String name, TextArea text){							//Inhalt des Textfelds in eine Datei speichern
		BufferedWriter writer = null; 
		
		try {
			writer = new BufferedWriter (new FileWriter(name));
			writer.write(text.getText());
			writer.flush();
			writer.close();
		} catch (IOException e){
			System.out.println("RIP");
		}		
	}
	
	public void laden(String name, TextArea text){								//Aus einer Datei in das Textfeld laden
		String s = "";
		File file = new File (name);
		
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
