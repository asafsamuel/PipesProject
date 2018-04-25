package searchable;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Action implements Serializable
{
	String action;
	
	// C'tor
	public Action() 
	{
		action = "";
	}

	// Getters and Setters
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() 
	{
		return action;
	}
}
