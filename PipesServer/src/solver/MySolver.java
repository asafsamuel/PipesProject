package solver;

import pipesAdapter.PipeAction;
import pipesAdapter.PipesGameBoard;
import pipesAdapter.PipesSearchable;
import searcher.BFS;
import searcher.BestFirstSearch;
import searcher.DFS;
import searcher.Searcher;
import searcher.Solution;

public class MySolver implements Solver<char[][]>
{
	// Local Variable
	Searcher<PipesGameBoard> searcher;
	
	@Override
	public Solution solve(char[][] level) 
	{
		PipesGameBoard board = new PipesGameBoard(level);
		PipesSearchable searchable = new PipesSearchable(board);
		

		searcher = new BestFirstSearch<>();
		//searcher = new BFS<>();
		//searcher = new DFS<>();
		
		Solution s = new Solution();
		s.getActions().clear();
		
		s = searcher.search(searchable);
		fixSolution(s);
		
		return s;
	}

	// fix solution - decrease number of action (x,y,1) + (x,y,1) = (x,y,2)
	private void fixSolution(Solution s) 
	{
		int size = s.getActions().size();
		
		for(int i=0; i<size-1; i++)
		{
			PipeAction action = (PipeAction) s.getActions().get(i);
			
			for(int j=i+1; j<size-1; j++)
			{
				PipeAction other = (PipeAction) s.getActions().get(j);
					
				if(other.getX() == action.getX() && other.getY() == action.getY())
				{
					action.setTimes(action.getTimes()+other.getTimes());
					s.getActions().remove(j);
					j--;
					size--;
				}
			}
		}
	}
}
