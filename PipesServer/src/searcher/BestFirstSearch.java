 package searcher;

import java.util.HashSet;
import java.util.List;

import searchable.Searchable;
import searchable.State;

/**	Best First Search **/
public class BestFirstSearch<T> extends CommonSearcher<T>  implements Searcher<T>
{
	@Override
	public Solution search(Searchable<T> s)
	{
		openList.add(s.getInitializeState());
		HashSet<State<T>> closeSet = new HashSet<>();

		while(!openList.isEmpty())
		{
			State<T> currState = popOpenList();	// dequeue

			if(currState.equals(s.getGoalState()))
				return backTrace(currState);

			List<State<T>> successors = s.getAllPosibleStates(currState);
			
			for (State<T> state : successors) 
			{
				if (!closeSet.contains(state) && !openList.contains(state))
				{
					state.setCameFrom(currState);
					state.setCost(currState.getCost()+1);
					openList.add(state);
				}
				
				else if(state.getCost() > currState.getCost()+1)
				{
					if(!openList.contains(state))
						openList.add(state);

					else
					{
						openList.remove(state);
						state.setCost(currState.getCost()+1);
						openList.add(state);
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