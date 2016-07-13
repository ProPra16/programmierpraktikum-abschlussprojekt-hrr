package TDDT;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Log {
	
	String[] log = new String[3];
	
	public Log(){
		log[0] = "Dauer von Test ";
		log[1] = "Dauer der Implementierung ";
		log[2] = "Dauer des Refactorings ";
	}
	
	public void Speichern(){
		
	}
	
	public void erstelleLog(ArrayList log2){
		
		System.out.println("Listeninhalt in Methode: " + log2.get(0));
		BufferedWriter writer = null; 
		
		try {			
			writer = new BufferedWriter( new FileWriter("Log.txt"));	
			
			int counter = 0;
			int counter2 = 0;
			int zeit = 0;
			
			int x = 3;			
			if(log2.size() == 1) x = 1;
			if(log2.size() == 2) x = 2;				
			
			while(counter < log2.size()){		
				for(int i = 0; i < x; i++){
					
					if(counter>0){
						zeit = (int) log2.get(counter) - (int) log2.get(counter-1);
					} else {
						zeit = (int) log2.get(counter);
					}
					writer.write(log2.get(counter)+". Sekunde: "+ log[i] + (counter2+1) + ": " + zeit + " Sekunden");	//log2.get(counter)
					writer.write("\n");
					writer.flush();
					counter++;
					if(log[2] == log[i]) counter2++;
					if(counter == log2.size()) break;
				}							
				if(counter == log2.size()) break;
			}
			
			writer.close();
		} catch	(IOException e) {
			System.out.println(e.getMessage()+"\n");
		}		
		//Fehlerlog();
	}	
	/*public void Fehlerlog(){
		
	}*/
}
