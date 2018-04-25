package pipesAdapter;

import java.util.ArrayList;

import searchable.State;

public class PipeState extends State<PipesGameBoard>
{
	// C'tor
	public PipeState(PipesGameBoard state) 
	{
		super(state);
		for(int i=0; i<state.getBoard().length; i++)
		{
			for(int j=0; j<state.getBoard()[i].length; j++)
				this.getState().board[i][j] = state.board[i][j];
		}
		
		this.getState().setStart(state.getStart());
		this.getState().setEnd(state.getEnd());
	}

	@Override
	public boolean equals(Object arg0) 
	{		
		if(arg0 == null) // check if goalState
			return IsGoalState();
		
		else
		{
			PipeState other = (PipeState)arg0;
			
			if(this.getState().equals(other.getState()))
				return true;
		
			return false;
		}
	}

	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<this.getState().getBoard().length; i++)
		{
			for(int j=0; j<this.getState().getBoard()[0].length; j++)
				sb.append(this.getState().getBoard()[i][j]);
			
			sb.append('\n');
			
		}
		sb.append('\n');
		
		return sb.toString();
	}
	
	// Check if goalState
	private boolean IsGoalState() 
	{
		int direction = -1;	// {0-up, 1-down, 2-left, 3-right}
		
		if(this.getState().start != null && this.getState().end != null)
			return findPath(this.getState().start,this.getState().end,direction,this.getState().getBoard());
		
		return false;
	}

	private boolean findPath(Position start, Position end, int direction, char[][] state) 
	{
		if(start.equals(end))
			return true;
		
		if(direction == -1)	// go all directions
		{
			ArrayList<Integer> check = new ArrayList<>();
			check.add(0);
			check.add(1);
			check.add(2);
			check.add(3);
			
			if(start.getX() == 0)
				check.remove((Integer)0);
			
			if(start.getX() == state.length-1)
				check.remove((Integer)1);
			
			if(start.getY() == 0)
				check.remove((Integer)2);
			
			if(start.getY() == state[0].length-1)
				check.remove((Integer)3);
			
			for (Integer integer : check) 
			{
				if(findPath(start, end, integer, state))
					return true;
			}
			
			return false;	
		}
		
		else if(direction == 0)	// up
		{
			if(start.getX() == 0)
				return false;
			
			char c = state[start.getX()-1][start.getY()];
			
			if(c == '|')
				return findPath(new Position(start.getX()-1,start.getY()), end, 0, state);	// still up
			
			else if(c == 'F')
				return findPath(new Position(start.getX()-1,start.getY()), end, 3, state);	// right
			
			else if(c == '7')
				return findPath(new Position(start.getX()-1,start.getY()), end, 2, state);	// left
			
			else if(c == 'g')
				return true;
			
			else
				return false;
		}
		
		else if(direction == 1)	// down
		{
			if(start.getX() == state.length-1)
				return false;
			
			char c = state[start.getX()+1][start.getY()];
			
			if(c == '|')
				return findPath(new Position(start.getX()+1,start.getY()), end, 1, state);	// still down
			
			else if(c == 'L')
				return findPath(new Position(start.getX()+1,start.getY()), end, 3, state);	// right
			
			else if(c == 'J')
				return findPath(new Position(start.getX()+1,start.getY()), end, 2, state);	// left
			
			else if(c == 'g')
				return true;
			
			else
				return false;
		}
		
		else if(direction == 2) // left
		{
			if(start.getY() == 0)
				return false;
			
			char c = state[start.getX()][start.getY()-1];
			
			if(c == '-')
				return findPath(new Position(start.getX(),start.getY()-1), end, 2, state);	// still left
			
			else if(c == 'F')
				return findPath(new Position(start.getX(),start.getY()-1), end, 1, state);	// down
			
			else if(c == 'L')
				return findPath(new Position(start.getX(),start.getY()-1), end, 0, state);	// up
			
			else if(c == 'g')
				return true;
			
			else
				return false;
		}
		
		else if(direction == 3) // right
		{
			if(start.getY() == state[0].length-1)
				return false;
			
			char c = state[start.getX()][start.getY()+1];
			
			if(c == '-')
				return findPath(new Position(start.getX(),start.getY()+1), end, 3, state);	// still right
			
			else if(c == '7')
				return findPath(new Position(start.getX(),start.getY()+1), end, 1, state);	// down
			
			else if(c == 'J')
				return findPath(new Position(start.getX(),start.getY()+1), end, 0, state);	// up
			
			else if(c == 'g')
				return true;
			
			else
				return false;
		}
		
		else
			return false;
	}
}
