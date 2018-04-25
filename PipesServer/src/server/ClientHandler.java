package server;

import java.net.Socket;

public interface ClientHandler 
{
	void start(Socket client);
	void stop();
}
