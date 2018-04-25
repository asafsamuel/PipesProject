package test;

public class MainTrain3 {

	public static void main(String[] args) 
	{
		/*
		// test Best first Search (20 points)
		byte[][] mazeData={
				{1,1,1,1,1},	
				{2,0,0,0,1},	
				{1,1,1,0,1},	
				{1,0,0,0,1},	
				{1,0,1,0,1},	
				{1,3,1,1,1},	
		};
		
		Maze m=new Maze(mazeData);		
		List<String> actions = TestSetter.solveMaze(m);
		
		// the following is the solution for the maze above:
		//List<String> answer = Arrays.asList("RIGHT","RIGHT","RIGHT","DOWN","DOWN","LEFT","LEFT","DOWN","DOWN");
		//actions=answer;

		final Grid p=m.getEntrance();
		actions.forEach(s->{
			if(s.equals("UP")) p.row--;
			if(s.equals("DOWN")) p.row++;
			if(s.equals("RIGHT")) p.col++;
			if(s.equals("LEFT")) p.col--;
		});
		
		if(!p.equals(m.getExit()))
			System.out.println("the Maze is not solved (-20)");
		
		
		System.out.println("done");
		*/
	}

}
