package test;

import pipesAdapter.PipesSearchable;
import searcher.BestFirstSearch;
import server.ClientHandler;
import server.MyClientHandler;
import server.MyServer;
import server.Server;

public class TestSetter {
	
	public static void setClasses(DesignTest dt){
		
		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(Server.class);
		// now fill in the other types according to their names
		// server's implementation
		dt.setServerClass(MyServer.class);
		// client handler interface
		dt.setClientHandlerInterface(ClientHandler.class);
		// client handler class
		dt.setClientHandlerClass(MyClientHandler.class);
		// cache manager interface
		dt.setCacheManagerInterface(cache.CacheManager.class);
		// cache manager class
		dt.setCacheManagerClass(cache.MyCacheManager.class);
		// solver interface
		dt.setSolverInterface(solver.Solver.class);
		// solver class
		dt.setSolverClass(solver.MySolver.class);
		// searchable interface
		dt.setSearchableInterface(searchable.Searchable.class);
		// searcher interface
		dt.setSearcherInterface(searcher.Searcher.class);
		// your searchable pipe game class
		dt.setPipeGameClass(PipesSearchable.class);
		// your Best First Search implementation
		dt.setBestFSClass(BestFirstSearch.class);
	}
	
	// run your server here
	static Server s;
	public static void runServer(int port)
	{
		s= new MyServer(port, new MyClientHandler(new cache.MyCacheManager(), new solver.MySolver()));
		s.start();
	}
	// stop your server here
	public static void stopServer(){
		s.stop();
	}
}
