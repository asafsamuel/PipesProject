package searcher;

import java.util.LinkedList;
import java.util.PriorityQueue;

import searchable.Action;
import searchable.State;

public abstract class CommonSearcher<T> implements Searcher<T>
{
	PriorityQueue<State<T>> openList;
	int evaluatedNodes;
	
	// C'tor
	public CommonSearcher() 
	{
		openList = new PriorityQueue<State<T>>();
		this.evaluatedNodes = 0;
	}
	
	// pop State from queue
	public State<T> popOpenList()
	{
		evaluatedNodes++;
		return openList.poll();
	}
	
	/** Go backTrace and returns all steps as a solution **/
	public Solution backTrace(State<T> goalState)
	{
		LinkedList<Action> actions = new LinkedList<Action>();

		State<T> currState = goalState;
		while (currState.getCameFrom() != null)
		{
			actions.addFirst(currState.getAction());
			currState = currState.getCameFrom();
		}

		Solution sol = new Solution();
		sol.setActions(actions);
		return sol;
	}

}
