package TDDT;

import java.util.Timer;
import java.util.TimerTask;

public class Tracking {

	Timer timer = new Timer();
	int secondsPassed = 0;

	TimerTask task = new TimerTask() {
		public void run() {
			secondsPassed++;
		}
	};
	
	public Tracking() {
		timer.scheduleAtFixedRate(task, 1000 , 1000);
	}
	
	public void beenden(){
		task.cancel();
	}
	
	public int getSecondPassed(){
		return secondsPassed;
	}
	

}
