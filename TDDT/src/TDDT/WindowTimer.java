package TDDT;

import java.util.Timer;
import java.util.TimerTask;

public class WindowTimer {
	
	private int max;
	Timer timer = new Timer();
	//zählt die Sekunden
	int secondsPassed = 0;
	//timer wird gestartet
	TimerTask task = new TimerTask() {
		public void run() {
			if(secondsPassed == max)
			{
				Window.abgelaufen();
				task.cancel();
			}
			Window.setAnzeige(secondsPassed);
			secondsPassed++;

		}
	};
	//Sekunden zähler
	public WindowTimer(int max) {
		this.max = max;
		timer.scheduleAtFixedRate(task, 1000 , 1000);
	}
	
	public void beenden()
	{
		task.cancel();
	}
}
