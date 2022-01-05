package ie.gmit.sw;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

	ServerSocket listener;
	Socket incomingConnection;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	// SharedExample sharedObject;
	ClubList list;

	int portNumber;

	// server
	public server(int port) {

		try {
			// step 1.....
			list = new ClubList();
			listener = new ServerSocket(port, 10);
			portNumber = port;

		} catch (IOException e) {
			System.out.println("Socket is not open");
			e.printStackTrace();
		}
	}

	public void serverWorkMethod() {

		System.out.println("Set up the server socket on port: " + portNumber);

		while (true) {
			try {
				incomingConnection = listener.accept();
				System.out.println("Connection recieved");
				serverThread serverT = new serverThread(incomingConnection, list);
				serverT.start();
			}

			catch (IOException e) {
				System.out.println("Socket is not open");
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {

		int port = 10000;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}

		server myServer = new server(port);
		myServer.serverWorkMethod();
	}

}
