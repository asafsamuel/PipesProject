package searcher;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import searchable.Action;

@SuppressWarnings("serial")
public class Solution implements Serializable
{
	List<Action> actions;
	
	// C'tor
	public Solution() 
	{
		actions = new LinkedList<Action>();
	}
	
	// Getters and Setters
	public List<Action> getActions()
	{
		return actions;
	}
	public void setActions(List<Action> actions)
	{
		this.actions = actions;
	}

	// Override function
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for (Action a : actions)
			sb.append(a.getAction()).append("\n");

		return sb.toString();
	}
}
