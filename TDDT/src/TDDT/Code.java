package TDDT;

public class Code {
	public Code() {
		//Analog zu TDDTTest
	}
	
	public void changePhaseToRed() {
		//Button zurueck zu den Tests 
		//Ersetzt den geschriebenen Code durch den in der Zwischenablage
	}
	
	public boolean compileCode() {
		return false;
	}
	
	public boolean testWorks() {
		return false;
	}
	
	public void changePhase() {
		if(compileCode() == true && testWorks() == true) {
			//Hier soll vorher der aktuelle Codestand zwischen gespeichert werden,
			//in der Zwischenablage
			//Eventuell Lohnt es sich eine Klasse zu erstellen die unsere Übungscodes
			//verwaltet ?
			Refactor refactor = new Refactor();
		}
	}
}
