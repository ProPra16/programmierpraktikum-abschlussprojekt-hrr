import java.io.*;

public class Aufgabe
{
	private String name;
	private String content;
	private BufferedWriter bw;
	private FileWriter fw;
	
	public Aufgabe()
	{
	}

	public void setName(String n)
	{
		name = n;
	}
	
	public void setText(String c)
	{
		content = c;
	}
	
	public void createDatei()
	{
		try
		{
			fw = new FileWriter(name + ".java");
			bw = new BufferedWriter(fw);
			String[] s = content.split("\n");
			for(int i=0;i<s.length;i++)
			{
				bw.write(s[i]);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
