package searchable;

public class State<T> implements Comparable<State<T>>
{
	T state;
	State<T> cameFrom;
	double cost;
	Action action;
	
	// C'tor
	public State(T state) 
	{
		this.state = state;
		cameFrom = null;
		cost = 0;
		action = null;
	}
	
	// getters and setter
	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	// compare with other states
	@Override
	public String toString() 
	{
		return state.toString();
	}
	@Override
	public boolean equals(Object arg0) 
	{
		@SuppressWarnings("unchecked")
		State<T> other = (State<T>)arg0;
		
		if(this.state.equals(other.state))
			return true;
		
		return false;
	}
	@Override
	public int hashCode() 
	{
		return this.toString().hashCode();
	}

	@Override
	public int compareTo(State<T> o) 
	{
		return (int) (this.cost-o.cost);
	}
}
