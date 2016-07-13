package TDDT;
import java.io.*;

public class TaskReader
{
	private FileReader fr;
	private BufferedReader br;
	private Aufgabe[] aufgaben;
	
	public TaskReader()
	{
		aufgaben = new Aufgabe[6];
	}	
	
	public void read(File a)
	{
		int i = 0;
		String inhalt = "";
		try
		{
			fr = new FileReader(a);
			br = new BufferedReader(fr);
			String temp = br.readLine();
			while(!temp.equals("<katalogEnde>"))
			{
				if(temp.equals("<klasse>") || temp.equals("<test>"))
				{
					temp = br.readLine();
					Aufgabe auf = new Aufgabe();
					auf.setName(temp);
					temp = br.readLine();
					while(!temp.equals("<klasseEnde>") && !temp.equals("<testEnde>"))
					{
						inhalt += temp + "\n";
						temp = br.readLine();
					}
					auf.setText(inhalt);
					auf.createDatei();
					aufgaben[i] = auf;
					i++;
					inhalt = "";
				}
				temp = br.readLine();	
			}
		}catch (IOException e)
		{
			e.printStackTrace();
		}		
	}
	
	public Aufgabe getAufgabe(int pos)
	{
		return aufgaben[pos];
	}
	
	public void setAufgabe(Aufgabe a, int pos)
	{
		aufgaben[pos] = a;
	}
	public boolean content()
	{
		if(aufgaben[0] != null)
		{
			return true;
		}
		return false;
	}
}
