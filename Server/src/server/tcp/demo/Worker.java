package server.tcp.demo;

import java.net.*;
import java.io.*;


public class Worker extends MultiThreadedServer implements Runnable {
	public Socket s;
	static boolean session = true;

	BufferedReader in = null;
	PrintWriter out = null;
	String[] data;
	
	
	public Worker(Socket s) {
		
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			out.println("Zaia Nicola");	
			System.out.println("remote host: "+s.getInetAddress());
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("Unable to get I/O stream on socket!");
		}
		
	}


	@Override
	public void run() {

		do{
			try {
				session = true;
				
				String str = in.readLine();
				System.out.println(str);
				
				if(str.contains(" pow ")){				//potenza
					data = str.split(" ");
					int a = Integer.parseInt(data[0]);
					int b = Integer.parseInt(data[2]);
					
					if (b>=0){
					double pow = 0;
					pow = Math.pow(a, b);
					out.println(pow);
					}	
					else
						out.println("not supported");
				}
				
				else
					out.println("error");				//errore
						
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Communication Error!");
			}
			
			}while(session);
		
	}

}
