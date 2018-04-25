package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import cache.CacheManager;
import searchable.Action;
import searcher.Solution;
import solver.Solver;

public class MyClientHandler implements ClientHandler
{
	// Local Variable
	BufferedReader in;
	PrintWriter out;
	CacheManager<char[][]> cacheManager;
	Solver<char[][]> solver;
	
	// C'tor
	public MyClientHandler(CacheManager<char[][]> cache, Solver<char[][]> solver)
	{
		this.cacheManager = cache;
		this.solver = solver;
	}
	
	@Override
	public void start(Socket client) 
	{
		try
		{
			this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			this.out = new PrintWriter(client.getOutputStream());
			
			// -------- From protocol ------------
			String line = in.readLine();
			LinkedList<String> levelStrings = new LinkedList<>();
			
			// Get level from client
			while(!line.equals("done"))
			{
				levelStrings.add(line);
				line = in.readLine();
			}
			
			// convert Array of Strings to char[][]
			char[][] clientLevel = new char[levelStrings.size()][levelStrings.get(0).length()];
			
			for(int i=0; i<levelStrings.size(); i++)
				for(int j=0; j<levelStrings.get(i).length(); j++)
					clientLevel[i][j] = levelStrings.get(i).charAt(j);
			
			// Search solution in cache
			Solution sol = cacheManager.loadSolution(clientLevel);
			if(sol == null)
			{
				// Solve solution
				sol = solver.solve(clientLevel);
				
				// Save solution in cache
				cacheManager.saveSolution(clientLevel, sol);
			}
			
			// write solution to client
			for(Action a : sol.getActions())
				out.println(a.getAction());
			
			out.println("done");
			out.flush();
			
			stop();
		} 
		
		catch (IOException e) 
		{
			System.out.println("Error! cannot get client's input and output stream!");
			e.printStackTrace();
		}		
	}

	@Override
	public void stop() 
	{
		try 
		{
			in.close();
			out.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
