package TDDT;

public class Refactor extends Code {
	public Refactor() {
		
	}
	
	public boolean differentCode() {
		//soll sicher gehen, dass Refactored wurde
		return false;
	}
	
	public void changePhase() {
		if(differentCode() == true && compileCode() == true && testWorks() == true ) {
			Test red = new Test();
		}
	}
}
