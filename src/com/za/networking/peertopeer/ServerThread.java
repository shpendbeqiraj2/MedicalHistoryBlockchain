package com.za.networking.peertopeer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerThread extends Thread {
	private ServerSocket serverSocket;
	private Set<ServerThreadThread> serverThreadThreads = new HashSet<ServerThreadThread>();
	public ServerThread(String portNumb) throws IOException{
			serverSocket = new ServerSocket(Integer.valueOf(portNumb));
	}
	public void run() {
		
	}
	public Set<ServerThreadThread> getServerThreadThreads(){return serverThreadThreads; }
}
