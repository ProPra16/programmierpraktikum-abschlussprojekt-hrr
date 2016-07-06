package TDDT;

import vk.core.api.*;

public class Tester
{
	public Tester()
	{
	}
	
	public boolean CompileClass(String name, String content, boolean test)
	{
		CompilationUnit testClass = new CompilationUnit(name,content,test);
		JavaStringCompiler compiler = CompilerFactory.getCompiler(testClass);
		compiler.compileAndRunTests();
		CompilerResult result = compiler.getCompilerResult();
		return result.hasCompileErrors();
	}
	
	public boolean testTesten(String name, String content, boolean test)
	{
		if(CompileClass(name, content, test))
		{
			CompilationUnit testClass = new CompilationUnit(name,content,test);
			JavaStringCompiler compiler = CompilerFactory.getCompiler(testClass);
			compiler.compileAndRunTests();
			TestResult result = compiler.getTestResult();
			if(result.getNumberOfFailedTests() == 1)
			{
				return true;
			}
		}
		return false;
	}
}
