
public class TDDTRefactor extends TDDTCode {
	public TDDTRefactor() {
		
	}
	
	public boolean differentCode() {
		//soll sicher gehen, dass Refactored wurde
		return false;
	}
	
	public void changePhase() {
		if(differentCode() == true && compileCode() == true && testWorks() == true ) {
			TDDTTest red = new TDDTTest();
		}
	}
}
