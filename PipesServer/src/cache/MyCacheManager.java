package cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import searcher.Solution;

public class MyCacheManager implements CacheManager<char[][]>
{
	// Local Variable
	HashMap<char[][], Solution> map;	// O(1)
	
	// C'tor
	public MyCacheManager() 
	{
		map = new HashMap<>();
	}

	@Override
	public Solution loadSolution(char[][] level) 
	{
		Solution sol =  map.get(level);	// if exists in HashMap
		
		// TODO: add if needed (read from file)
		/*
		if(sol != null)
		{
			try 
			{
				StringBuilder sb = new StringBuilder();
				for(int i=0; i<level.length; i++)
				{
					for(int j=0; j<level[i].length; j++)
						sb.append(level[i][j]);
				}
				
				File f = new File(sb.toString());
				if(f.exists())	// if solution exits as file
				{
					FileInputStream file = new FileInputStream(f);
					ObjectInputStream in = new ObjectInputStream(file);
					
					Solution s = (Solution) in.readObject();
					map.put(level, s);
					
					in.close();
					file.close();
					return s;
				}
				
				return null;
			} 
			
			catch (IOException | ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}*/
		
		return sol;
	}

	@Override
	public void saveSolution(char[][] level, Solution s) 
	{
		map.put(level, s);
		
		// TODO: add if needed (write to file)
		// Save as file
		/*
		try 
		{
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<level.length; i++)
			{
				for(int j=0; j<level[i].length; j++)
					sb.append(level[i][j]);
			}
			
			ObjectOutputStream out = new  ObjectOutputStream(new FileOutputStream(sb.toString()));
			out.writeObject(s);
			
			out.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}*/
		
	}
}
