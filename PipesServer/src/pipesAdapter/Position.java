package pipesAdapter;

public class Position 
{
	// Local Variables
	int x;
	int y;
	
	// C'tor
	public Position(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}

	// Getters and Setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object arg0) 
	{
		Position other = (Position)arg0;
		return ((this.x == other.x) && (this.y == other.y));
	}
}
