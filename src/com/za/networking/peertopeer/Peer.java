package com.za.networking.peertopeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Peer {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputSreamReader(System.in));
		System.out.println("> enter username & port # for this peer:");
		String[] setupValues = bufferedReader.readLine().split("")
        ServerThread serverThread = new ServerThread(setupValues[1]);
		serverThread.start();
		new Peer().updateListenToPeers(bufferedReader, setupValues[0],serverThread);
	}
	public void updateListenToPeers(BufferedReader bufferedReader, String username, ServerThread serverThread) throws Exception {
		System.out.println("> enter (space separated) hostname:port#");
		System.out.println(" peers to receive messages from (s to skip)");
		String input = bufferedReader.readLine();
		String[] inputValues = input.split(" ");
	}
}
