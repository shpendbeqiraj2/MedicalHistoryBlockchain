package com.za.networking.peertopeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PearThread extends Thread{
	private BufferedReader bufferedReader;
	public PeerThread(Socket socket) throws IOException{
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
}
