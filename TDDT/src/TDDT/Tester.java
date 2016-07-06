package TDDT;

import vk.core.api.*;

public class Tester
{
	public Tester()
	{
	}
	
	public boolean CompileDas(String name, String content, boolean test)  //Methode testet, ob Klasse compilierbar ist
	{
		CompilationUnit testClass = new CompilationUnit(name,content,test);
		JavaStringCompiler compiler = CompilerFactory.getCompiler(testClass);
		compiler.compileAndRunTests();
		CompilerResult result = compiler.getCompilerResult();
		System.out.println("Ergebnis: " +result.hasCompileErrors());
		return result.hasCompileErrors(); //  false = compilierbar
	}
	
	public boolean testTesten(String Tname, String Tcontent, boolean Ttest, String Cname, String Ccontent, boolean Ctest)
	{
		if(!CompileDas(Tname, Tcontent, Ttest) && !CompileDas(Cname, Ccontent, Ctest))
		{
			CompilationUnit testClass = new CompilationUnit(Tname,Tcontent,Ttest);
			CompilationUnit klasse = new CompilationUnit(Cname,Ccontent,Ctest);
			JavaStringCompiler compiler = CompilerFactory.getCompiler(testClass, klasse);
			compiler.compileAndRunTests();
			TestResult result = compiler.getTestResult();
			if(result.getNumberOfFailedTests() == 1)
			{
				return true; // Es gibt genau einen fehlschlagenden Test
			}
		}
		return false; // Es ist entweder nicht kompilierbar oder es gibt mehr als einen fehlschlagenden Test
	}
}
