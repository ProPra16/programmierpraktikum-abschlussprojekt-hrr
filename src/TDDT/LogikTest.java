package TDDT;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogikTest
{		
	private Aufgabe Klasse = new Aufgabe();
	private Aufgabe testKlasse = new Aufgabe();
	private Tester task = new Tester();
	
	@Test
	public void klassenCompilieren()
	{
		{
			Klasse.setName("Klasse1");
			Klasse.setText("public class Klasse1 { \n" 
						+ "public static int ausgeben() { \n"
						+ " return 4; \n"
						+ " } \n"
						+ "}");
			testKlasse.setName("Klasse1Test");
			testKlasse.setText("import static org.junit.Assert.*; \n"
						+ "import org.junit.Test; \n"
						+ "public class Klasse1Test { \n"
						+ "@Test \n"
						+ "public void testAusgeben() { \n"
						+ " assertEquals(4,Klasse1.ausgeben()); \n"
						+ " } \n"
						+ "}");
			assertFalse(task.CompileClass(testKlasse.getName(), testKlasse.getContent(), true, Klasse.getName(), Klasse.getContent(), false));
		}
	}
	
	@Test
	public void klassenCompilierenNicht()
	{
		Klasse.setName("Klasse1");
		Klasse.setText("public class Klasse1 { \n" 
					+ "public static int ausgeben() { \n"
					+ " return 4; \n"
					+ " } \n");
		testKlasse.setName("Klasse1Test");
		testKlasse.setText("import static org.junit.Assert.*; \n"
					+ "import org.junit.Test; \n"
					+ "public class Klasse1Test { \n"
					+ "@Test \n"
					+ "public void testAusgeben() { \n"
					+ " assertEquals(4,Klasse1.ausgeben()); \n"
					+ " } \n"
					+ "}");
		assertTrue(task.CompileClass(testKlasse.getName(), testKlasse.getContent(), true, Klasse.getName(), Klasse.getContent(), false));
	}
	
	@Test
	public void einFehlschlagenderTest()
	{
		Klasse.setName("Klasse1");
		Klasse.setText("public class Klasse1 { \n" 
					+ "public static int ausgeben() { \n"
					+ " return 4; \n"
					+ " }"
					+ "}");
		testKlasse.setName("Klasse1Test");
		testKlasse.setText("import static org.junit.Assert.*; \n"
					+ "import org.junit.Test; \n"
					+ "public class Klasse1Test { \n"
					+ "@Test \n"
					+ "public void testAusgeben() { \n"
					+ " assertEquals(5,Klasse1.ausgeben()); \n"
					+ " } \n"
					+ "}");
		assertTrue(task.testTesten(testKlasse.getName(), testKlasse.getContent(), true, Klasse.getName(), Klasse.getContent(), false));
	}
	
	@Test
	public void mehrereFehlschlagendeTests()
	{
		Klasse.setName("Klasse1");
		Klasse.setText("public class Klasse1 { \n" 
					+ "public static int ausgeben() { \n"
					+ " return 4; \n"
					+ " }"
					+ "public static int eingeben() { \n"
					+ " return 5; \n"
					+ " }"
					+ "}");
		testKlasse.setName("Klasse1Test");
		testKlasse.setText("import static org.junit.Assert.*; \n"
					+ "import org.junit.Test; \n"
					+ "public class Klasse1Test { \n"
					+ "@Test \n"
					+ "public void testAusgeben() { \n"
					+ " assertEquals(5,Klasse1.ausgeben()); \n"
					+ " } \n"
					+ "@Test \n"
					+ "public void testEingeben() { \n"
					+ " assertEquals(6,Klasse1.eingeben()); \n"
					+ " } \n"
					+ "}");
		assertFalse(task.testTesten(testKlasse.getName(), testKlasse.getContent(), true, Klasse.getName(), Klasse.getContent(), false));
	}
	
	@Test
	public void keinFehlschlagenderTest()
	{
		Klasse.setName("Klasse1");
		Klasse.setText("public class Klasse1 { \n" 
					+ "public static int ausgeben() { \n"
					+ " return 4; \n"
					+ " }"
					+ "public static int eingeben() { \n"
					+ " return 5; \n"
					+ " }"
					+ "}");
		testKlasse.setName("Klasse1Test");
		testKlasse.setText("import static org.junit.Assert.*; \n"
					+ "import org.junit.Test; \n"
					+ "public class Klasse1Test { \n"
					+ "@Test \n"
					+ "public void testAusgeben() { \n"
					+ " assertEquals(4,Klasse1.ausgeben()); \n"
					+ " } \n"
					+ "@Test \n"
					+ "public void testEingeben() { \n"
					+ " assertEquals(5,Klasse1.eingeben()); \n"
					+ " } \n"
					+ "}");
		assertTrue(task.funktTesten(testKlasse.getName(), testKlasse.getContent(), true, Klasse.getName(), Klasse.getContent(), false));
	}
	
	@Test
	public void einFehlschlagenderTestFunktTesten()
	{
		Klasse.setName("Klasse1");
		Klasse.setText("public class Klasse1 { \n" 
					+ "public static int ausgeben() { \n"
					+ " return 4; \n"
					+ " }"
					+ "public static int eingeben() { \n"
					+ " return 5; \n"
					+ " }"
					+ "}");
		testKlasse.setName("Klasse1Test");
		testKlasse.setText("import static org.junit.Assert.*; \n"
					+ "import org.junit.Test; \n"
					+ "public class Klasse1Test { \n"
					+ "@Test \n"
					+ "public void testAusgeben() { \n"
					+ " assertEquals(5,Klasse1.ausgeben()); \n"
					+ " } \n"
					+ "@Test \n"
					+ "public void testEingeben() { \n"
					+ " assertEquals(5,Klasse1.eingeben()); \n"
					+ " } \n"
					+ "}");
		assertFalse(task.funktTesten(testKlasse.getName(), testKlasse.getContent(), true, Klasse.getName(), Klasse.getContent(), false));
	}
}
