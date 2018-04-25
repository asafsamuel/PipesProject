package searcher;

import searchable.Searchable;
import searchable.State;

import java.util.List;
import java.util.Random;

/** Hill Climbing Searcher **/
public class HillClimbingSearch<T> extends CommonSearcher<T> implements Searcher<T>
{
	// Variables
    long timeToRun;
    StateGrader<T> grader;

    // C'tor
    public HillClimbingSearch(StateGrader<T> grader, long timeToRun) 
    {
        this.grader = grader;
        this.timeToRun = timeToRun;
    }

    @Override
    public Solution search(Searchable<T> searchable) 
    {
        State<T> currState = searchable.getInitializeState();
        long time0 = System.currentTimeMillis();

        //Loop until the goal state is achieved or no more operators can be applied on the current state:
        while (System.currentTimeMillis() - time0 < timeToRun) 
        {
            List<State<T>> neighbors = searchable.getAllPosibleStates(currState);

            if (Math.random() < 0.7) 
            { 
                int grade = 0;
                
                for (State<T> state : neighbors) 
                {
                	int g = grader.grade(state);
                	
                	if (g > grade) 
                	{
                        grade = g;
                        state.setCameFrom(currState);
                        state.setCost(currState.getCost()+1);
    					currState = state;
                	}
				}
            } 
            
            else 
            	currState = neighbors.get(new Random().nextInt(neighbors.size()));
        }

        return backTrace(currState);
    }

	@Override
	public int getNumberOfNodesEvaluated() 
	{
		return this.evaluatedNodes;
	}
}

