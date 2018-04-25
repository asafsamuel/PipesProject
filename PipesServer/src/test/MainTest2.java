package test;

import java.util.Random;

public class MainTest2 {

	public static void main(String[] args) {
		
		// execution test (40 points)
		Random r=new Random();
		int port=6000+r.nextInt(1000);
		TestSetter.runServer(port);
		try{
			TestServer.runClient(port,0);
			TestServer.runClient(port,1);
			TestServer.runClient(port,2);
			TestServer.runClient(port,3);
		}finally{
			TestSetter.stopServer();
		}
		
		System.out.println("done");
	}

}
