package orw.warnotte.elecribulator.CControlers;


public class Thread_Modulateurs extends Thread
{
	boolean finish=false;
	CCManager man = null;
	long sleeplen = 10;
	boolean Paused=false;
	
	public synchronized boolean isPaused() {
		return Paused;
	}

	public void setPaused(boolean paused) {
		Paused = paused;
		synchronized(this)
		{
		notify();
		}
	
	}

	public Thread_Modulateurs(CCManager m, int sleepLen) 
	{
		this.man = m;
		this.setName("Thread evolution SignGen");
		
		sleeplen = sleepLen;
		
	}
	
	public void run()
	{
		while(finish==false)
		{
			while (Paused==true)
			{
				try {
					synchronized(this)
					{
					wait();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			man.evolue(false);
			try
			{
				Thread.sleep(sleeplen);
			} 
			catch (InterruptedException e)
			{
				// DO nothing, probaly setFinished(true) was invoked so the loop will stop.
			}
		}
		//System.err.println(getName()+" has finished working");
	}

	public synchronized boolean isFinish()
	{
		return finish;
	}

	public synchronized void setFinish(boolean finish)
	{
		this.finish = finish;
		if (finish==true)
			this.interrupt();
	}

	public synchronized CCManager getMan()
	{
		return man;
	}

	public synchronized void setMan(CCManager man)
	{
		this.man = man;
	}

	public synchronized long getSleeplen()
	{
		return sleeplen;
	}

	public synchronized void setSleeplen(long sleeplen)
	{
		this.sleeplen = sleeplen;
	}
	
	
}
