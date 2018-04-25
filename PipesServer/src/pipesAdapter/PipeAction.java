package pipesAdapter;

import searchable.Action;

@SuppressWarnings("serial")
public class PipeAction extends Action
{
	// Local Variables
	int x;
	int y;
	int times;
	
	// C'tors
	public PipeAction() 
	{
		this.x = 0;
		this.y = 0;
		this.times = 0;
		this.setAction(""+x+","+y+","+times);
	}
	public PipeAction(int x, int y, int times) 
	{
		super();
		this.x = x;
		this.y = y;
		this.times = times;
		this.setAction(""+x+","+y+","+times);
	}

	// Getters and Setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.setAction(""+x+","+y+","+times);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.setAction(""+x+","+y+","+times);
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
		this.setAction(""+x+","+y+","+times);
	}
	
	@Override
	public String getAction() 
	{
		String s = ""+x+","+y+","+times;
		return s;
	}
}
