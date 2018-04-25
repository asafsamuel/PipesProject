package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TestServer {
	
	static char[][] level1={
			{'s','-','7'},	
			{' ',' ','|'},	
			{'L',' ','g'},	
	};
	
	static char[][] level1a={
			{'s','-','7'},	
			{' ',' ','|'},	
			{' ',' ','g'},	
	};
	
	static char[][] level2={
			{'g','J','7'},	
			{'|',' ','|'},	
			{'L','-','s'},	
	};
	static char[][] level2a={
			{'g',' ',' '},	
			{'|',' ',' '},	
			{'L','-','s'},	
	};
	
	static char[][] level3={
			{' ','g','7'},	
			{'|','L','7'},	
			{'L','-','s'},	
	};
	static char[][] level3a={
			{' ','g',' '},	
			{' ','L','7'},	
			{' ',' ','s'},	
	};
	
	static char[][] level4={
			{'s','7','7'},	
			{'-','|','7'},	
			{'-','g',' '},	
	};
	
	static char[][] level4a={
			{'s','7',' '},	
			{' ','|',' '},	
			{' ','g',' '},	
	};
	
	public static void randomize(char[][] level){
		for(int i=0;i<level.length;i++)
			for(int j=0;j<level[i].length;j++){
				level[i][j]=turn(level[i][j]);
				if(Math.random()>0.3)
					level[i][j]=turn(level[i][j]);
				if(Math.random()>0.4)
					level[i][j]=turn(level[i][j]);
				if(Math.random()>0.5)
					level[i][j]=turn(level[i][j]);				
			}
	}

	private static char turn(char c) {
		char r=' ';
		switch(c){
		case '-': r='|';break;
		case '|': r='-';break;
		case 'L': r='F';break;
		case 'F': r='7';break;
		case '7': r='J';break;
		case 'J': r='L';break;
		case 's': r='s';break;
		case 'g': r='g';break;
		}		
		return r;
	}
	
	public static void runClient(int port){
		Socket s=null;
		PrintWriter out=null;
		BufferedReader in=null;
		try{
			s=new Socket("127.0.0.1",port);
			s.setSoTimeout(3000);
			out=new PrintWriter(s.getOutputStream());
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out.println("s|g");
			out.println("done");
			out.flush();
			String line=in.readLine();
			if(line==null || !line.equals("0,1,1"))
				System.out.println("Your Server did not reply at all or replied a correct solution (-30)");
			line=in.readLine();
			if(line==null || !line.equals("done"))
				System.out.println("Your Server does not work according to the right protocol (-10)");
		}catch(SocketTimeoutException e){
			System.out.println("Your Server takes over 3 seconds to answer (-40)");
		}catch(IOException e){
			System.out.println("Your Server ran into some IOException (-40)");
		}finally{
			try {
				in.close();
				out.close();
				s.close();
			} catch (IOException e) {
				System.out.println("Your Server ran into some IOException (-40)");
			}
		}
		
	}

	public static void runClient(int port,int index){
		char[][] level=null;
		char[][] levela=null;
		int points=0;
		switch(index){
		case 0:level=level1.clone();levela=level1a.clone();points=3*4;break;
		case 1:level=level2.clone();levela=level2a.clone();points=3*4;break;
		case 2:level=level3.clone();levela=level3a.clone();points=2*4;break;
		case 3:level=level4.clone();levela=level4a.clone();points=2*4;break;
		}
		randomize(level);
		
		Socket s=null;
		PrintWriter out=null;
		BufferedReader in=null;
		try{
			s=new Socket("127.0.0.1",port);
			s.setSoTimeout(3000);
			out=new PrintWriter(s.getOutputStream());
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			for(int i=0;i<level.length;i++){
				String send=new String(level[i]);
				out.println(send);
			}	
			out.println("done");
			out.flush();
			String line;
			while(!(line=in.readLine()).equals("done")){
				String sp[]=line.split(",");
				int row=Integer.parseInt(sp[0]);
				int col=Integer.parseInt(sp[1]);
				int times=Integer.parseInt(sp[2]);
				for(int i=0;i<times;i++)
					level[row][col]=turn(level[row][col]);
			}
			
			for(int i=0;i<levela.length;i++)
				for(int j=0;j<levela[i].length;j++)
					if(levela[i][j]!=' ' && levela[i][j]!='s' && levela[i][j]!='g'){
						if(levela[i][j]!=level[i][j])
							System.out.println("your solution for random level "+(index+1)+" containes a wrong piece of pipe (-4)");
					}
			
		}catch(SocketTimeoutException e){
			System.out.println("for test "+(index+1)+" Your Server takes over 3 seconds to connect (-"+points+")");
		}catch(IOException e){
			System.out.println("for test "+(index+1)+"Your Server ran into some IOException (-"+points+")");
		}finally{
			try {
				in.close();
				out.close();
				s.close();
			} catch (IOException e) {
				System.out.println("for test "+(index+1)+"Your Server ran into some IOException (-"+points+")");
			}
		}
	}
}
