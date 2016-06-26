import java.io.*;

public class TaskReader
{
	private FileReader fr;
	private BufferedReader br;
	private static String name;
	private static String content;
	
	public static void main(String[] args)
	{
		TaskReader tr = new TaskReader();
		tr.aufgabe1();
		System.out.println(name);
		System.out.println(content);
	}
	
	public TaskReader()
	{
		content = "";
	}
	
	public void aufgabe1()
	{
		read("Aufgabe1.txt");
	}
	
	public void aufgabe2()
	{
		
	}
	
	public void aufgabe3()
	{
		
	}
	
	private void read(String a)
	{
		try
		{
			fr = new FileReader(a);
			br = new BufferedReader(fr);
			String temp = br.readLine();
			name = temp;
			temp = br.readLine();
			while(!temp.equals("<ende>"))
			{
				content += temp + "\n";
				temp = br.readLine();
			}
		}catch (IOException e)
		{
			e.printStackTrace();
		}		
	}

	public String getName()
	{
		return name;
	}

	public String getContent()
	{
		return content;
	}
}
