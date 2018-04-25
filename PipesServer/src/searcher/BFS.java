package searcher;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import searchable.Searchable;
import searchable.State;

/** BFS **/
public class BFS<T> extends CommonSearcher<T> implements Searcher<T>
{
	@Override
	public Solution search(Searchable<T> s) 
	{
		HashSet<searchable.State<T>> visited = new HashSet<>();
		Queue<State<T>> queue = new LinkedList<>();
		
		queue.add(s.getInitializeState());
		visited.add(s.getInitializeState());
		
		while (!queue.isEmpty()) 
		{
			State<T> currState = queue.poll();
			this.evaluatedNodes++;
			
			if (currState.equals(s.getGoalState()))
				return backTrace(currState);
			
			List<State<T>> map = s.getAllPosibleStates(currState);
			
			for (State<T> state : map) 
			{
				if(!visited.contains(state))
				{
					visited.add(state);
					state.setCameFrom(currState);
					queue.add(state);
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
