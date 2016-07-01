package TDDT;
//Startet die Bearbeitung des jeweiligen Testcodes
public class Test {
	public Test() {
		//Dem User soll in rot angezeigt werden, dass Test bearbeitet werden
		//Test soll geoeffnet werden ? Oder öffnet der User das selbst
	}
	
	//Testet ob die Test fehlschlagen
	public boolean testFails() {
		return false;
	}
	
	//Soll zurueck geben ob der Test kompilierbar ist
	public boolean compileTest() {
		return false;
	}
	
	public void changePhase() {
		if(compileTest() == true && testFails() == true) {
			Code green = new Code();
		}
	}
}
