package searcher;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import searchable.Searchable;
import searchable.State;

/** DFS **/
public class DFS<T> extends CommonSearcher<T> implements Searcher<T>
{
	@Override
	public Solution search(Searchable<T> s) 
	{
		List<State<T>> visited = new LinkedList<>(); //Set<State<T>> visited = new HashSet<>();
		Stack<State<T>> stack = new Stack<>();
		
		State<T> state = s.getInitializeState();
		stack.push(state);
		
		while (!stack.isEmpty()) 
		{
			State<T> currState = stack.pop();
			
			if (currState.equals(s.getGoalState()))
				return backTrace(currState);
			
			if (!visited.contains(currState))
			{
				visited.add(currState);
				
				List<State<T>> map = s.getAllPosibleStates(currState);	
				
				for (State<T> s2 : map) 
				{
					if(!visited.contains(s2))
					{
						stack.push(s2);
						s2.setCameFrom(currState);
					}
				}
			}
		}
		
		return null;
	}

	@Override
	public int getNumberOfNodesEvaluated() 
	{
		return this.evaluatedNodes;
	}
}
