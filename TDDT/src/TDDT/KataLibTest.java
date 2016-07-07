package TDDT;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import vk.core.api.*;
import vk.core.internal.InternalCompiler;
import vk.core.internal.InternalResult;
import vk.core.api.*; 

public class KataLibTest {
	static String name; 
	String content;
	boolean isTest;
	
	public KataLibTest() {
	//	JFileChooser fileChooser = new JFileChooser();
	//	fileChooser.setDialogTitle("Wählen Sie einen Katalog");
	//	fileChooser.showOpenDialog(null);
	 //fileChooser.getName(fileChooser.getSelectedFile());
		String s = "";
		name = "BarTest";
		File file = new File(name+".java");
		
		try(Scanner sc = new Scanner(file);){ 
			while(sc.hasNextLine()){
				s += sc.nextLine();
				s += "\n";
			}					
		} catch (FileNotFoundException e) {			
			System.out.println("FileNotFoundException: " + "RIP" + "\n");
		}
		String b = "";
		name = "Bar";
		File file2 = new File(name+".java");
		
		try(Scanner sc = new Scanner(file2);){ 
			while(sc.hasNextLine()){
				b += sc.nextLine();
				b += "\n";
			}					
		} catch (FileNotFoundException e) {			
			System.out.println("FileNotFoundException: " + "RIP" + "\n");
		}
		CompilationUnit un = new CompilationUnit("Bar","public class Bar { \n"
			+"public static int fourtyTwo() {\n"
			+"	  return 41 + 1;				\n"
			+"	 }\n"
			+"	}\n",false);
		CompilationUnit unit = new CompilationUnit("BarTest",s,true);
		JavaStringCompiler compiler = CompilerFactory.getCompiler( un);
		compiler.compileAndRunTests();
		CompilerResult compilerResult = compiler.getCompilerResult();
	}
}
