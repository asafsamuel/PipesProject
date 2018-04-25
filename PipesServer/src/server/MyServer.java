package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer implements Server
{
	// Local Variable
	ServerSocket server;
	ClientHandler handler;
	int port;
	boolean stop;
	
	// C'tor
	public MyServer(int port, ClientHandler handlers) 
	{
		this.port = port;
		this.handler = handlers;
		stop = false;
	}
	
	// Start the server - create new server socket
	@Override
	public void start()
	{
		new Thread(() -> 
		{
			try 
			{
				server = new ServerSocket(port);
				searchForClient();
				
				server.close();
				// Server is closing (finish)!
			} 
			
			catch (IOException e) 
			{
				System.out.println("Cannot open server socket!");
				e.printStackTrace();
			}
			
		}).start();
	}

	@Override
	public void stop() 
	{
		stop = true;
	}

	// Search for clients
	@Override
	public void searchForClient() 
	{
		try 
		{
			server.setSoTimeout(1000);
			
			while(!stop)
			{
				Socket clientSocket = null;
				
				try 
				{
					clientSocket = server.accept();
					handler.start(clientSocket);
					
					clientSocket.close();
				} 
				
				catch (IOException e) 
				{
					//e.printStackTrace();
				}
			}
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
