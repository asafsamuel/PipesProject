package pipesAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import searchable.Action;
import searchable.Searchable;
import searchable.State;

public class PipesSearchable implements Searchable<PipesGameBoard> 
{
	// Local Variables
	State<PipesGameBoard> initState;
	State<PipesGameBoard> goalState;
	HashMap<Character,Character> rotatePipe;
	
	// C'tor
	public PipesSearchable(PipesGameBoard board) 
	{
		this.initState = new PipeState(board);
		this.goalState = null;
		rotatePipe = new HashMap<>();
		
		initHash();
	}
	
	// initialize hash-map
	void initHash()
	{
		rotatePipe.put('-', '|');
		rotatePipe.put('|', '-');
		rotatePipe.put('L', 'F');
		rotatePipe.put('F', '7');
		rotatePipe.put('7', 'J');
		rotatePipe.put('J', 'L');
		// L -> F -> 7 -> J -> L ....
	}

	@Override
	public State<PipesGameBoard> getInitializeState() 
	{
		return this.initState;
	}

	@Override
	public State<PipesGameBoard> getGoalState() 
	{
		return this.goalState;	// return null;
	}

	@Override
	public List<State<PipesGameBoard>> getAllPosibleStates(State<PipesGameBoard> s) 
	{
		List<State<PipesGameBoard>> list = new LinkedList<State<PipesGameBoard>>();
		int i=0;
		int j=0;
		
		for(i=0; i<s.getState().getBoard().length; i++)
		{
			for(j=0; j<s.getState().getBoard()[i].length; j++)
			{
				Character rot = rotatePipe.get(s.getState().getBoard()[i][j]);
				
				if(rot != null)	// is pipe!
				{
					char[][] newBoard = new char[s.getState().getBoard().length][s.getState().getBoard()[0].length];
					for(int k=0; k<s.getState().getBoard().length; k++)
					{
						for(int l=0; l<s.getState().getBoard()[0].length; l++)
							newBoard[k][l] = s.getState().getBoard()[k][l];
					}
					newBoard[i][j] = rot;
					
					Action a = new PipeAction(i, j, 1);
					PipesGameBoard newPipeGame = new PipesGameBoard(newBoard);
					
					PipeState newState = new PipeState(newPipeGame);
					newState.setAction(a);
					newState.setCameFrom(s);
					newState.setCost(s.getCost()+1);
					
					list.add(newState);
				}
			}
		}
		
		return list;
	}
}
