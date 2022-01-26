/*
 * 18/10/2021
 * Zaia Nicola
 * 5°CIA
 */
/*
telnet 192.168.80.13 7979
*/
package server.tcp.demo;

import java.net.*;
import java.io.*;

public class MultiThreadedServer {
	

	public static ServerSocket ws;
	
	public static void main(String[] args) {
		boolean run = true;
		
		try{
			ws = new ServerSocket(7979);
		while(run){
			Socket s = ws.accept();
			Worker w = new Worker(s);
			new Thread(w).start();
		}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}  // fine main

}
