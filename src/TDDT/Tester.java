package TDDT;

import java.util.ArrayList;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerFactory;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

public class Tester
{	
	
	ArrayList <String>errorListe = new ArrayList<String>();
	String errors = "";
	
	public Tester()	{
		
	}
	
	public boolean CompileClass(String Tname, String Tcontent, boolean Ttest, String Cname, String Ccontent, boolean Ctest)  //Methode testet, ob Klasse compilierbar ist
	{
		CompilationUnit testClass = new CompilationUnit(Tname,Tcontent,Ttest);					//Ob die Dateien kompilieren
		CompilationUnit klasse = new CompilationUnit(Cname,Ccontent,Ctest);
		JavaStringCompiler compiler = CompilerFactory.getCompiler(testClass, klasse);
		compiler.compileAndRunTests();
		CompilerResult result = compiler.getCompilerResult();
		System.out.println("Ergebnis: " +result.hasCompileErrors());
		
		
		return result.hasCompileErrors(); //  false = compilierbar
	}
	
	public boolean testTesten(String Tname, String Tcontent, boolean Ttest, String Cname, String Ccontent, boolean Ctest)
	{
		CompilationUnit testClass = new CompilationUnit(Tname,Tcontent,Ttest);					//Ob es einen fehlschlagenen Test gibt
		CompilationUnit klasse = new CompilationUnit(Cname,Ccontent,Ctest);
		JavaStringCompiler compiler = CompilerFactory.getCompiler(testClass, klasse);
		compiler.compileAndRunTests();
		CompilerResult fehler = compiler.getCompilerResult();
		if(!fehler.hasCompileErrors())
		{
			TestResult result = compiler.getTestResult();
			System.out.println(result.getNumberOfFailedTests());
			if(result.getNumberOfFailedTests() == 1)
			{
				return true; // Es gibt genau einen fehlschlagenden Test
			}else
			{
				return false; // Es ist entweder nicht kompilierbar oder es gibt mehr als einen fehlschlagenden Test
			}
		}
		return false;
	}
	
	public boolean funktTesten(String Tname, String Tcontent, boolean Ttest, String Cname, String Ccontent, boolean Ctest)
	{
		CompilationUnit testClass = new CompilationUnit(Tname,Tcontent,Ttest);					//Ob es keinen fehlschlagenen Test gibt
		CompilationUnit klasse = new CompilationUnit(Cname,Ccontent,Ctest);
		JavaStringCompiler compiler = CompilerFactory.getCompiler(testClass, klasse);
		compiler.compileAndRunTests();
		CompilerResult fehler = compiler.getCompilerResult();
				
		if(!fehler.hasCompileErrors())
		{
			TestResult result = compiler.getTestResult();
			System.out.println(result.getNumberOfFailedTests());
			if(result.getNumberOfFailedTests() == 0)
			{
				return true; // Es gibt keinen fehlschlagenden Test
			}else
			{
				return false; // Es ist entweder nicht kompilierbar oder es gibt mehr als einen fehlschlagenden Test
			}
		}
		errors += fehler.getCompilerErrorsForCompilationUnit(klasse).toString() + 
				"\n----------------------------------------------------------------------\n";
		return false;
	}
	
	public ArrayList getErrorList(){		//		
		
		return errorListe;
	}
	
	public String getErrorString(){
		return errors;
	}
}
