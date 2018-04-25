package pipesAdapter;

public class PipesGameBoard
{
	// Local Variables
	char[][] board;
	Position start;
	Position end;
	
	// C'tor
	public PipesGameBoard(char[][] level) 
	{
		this.board = new char[level.length][level[0].length];
		
		for(int i=0; i<level.length; i++)
		{
			for(int j=0; j<level[0].length; j++)
			{
				this.board[i][j] = level[i][j];
				
				if(board[i][j] == 's')
					start = new Position(i, j);
				
				else if(board[i][j] == 'g')
					end = new Position(i, j);
			}
		}
	}

	// Getters and Setters
	public char[][] getBoard() {
		return board;
	}
	public void setBoard(char[][] board) {
		this.board = board;
	}
	public Position getStart() {
		return start;
	}

	public void setStart(Position start) {
		this.start = start;
	}
	public Position getEnd() {
		return end;
	}
	public void setEnd(Position end) {
		this.end = end;
	}
	
	@Override
	public boolean equals(Object arg0) 
	{
		PipesGameBoard other = (PipesGameBoard)arg0;
		
		if(this.board.length != other.board.length)
			return false;
		
		if(this.board[0].length != other.board[0].length)
			return false;
		
		for(int i=0; i<this.board.length; i++)
		{
			for(int j=0; j<this.board[i].length; j++)
			{
				if(this.board[i][j] != other.board[i][j])
					return false;
			}
		}
		
		return true;
	}
}
