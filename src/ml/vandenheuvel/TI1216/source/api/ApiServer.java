package ml.vandenheuvel.TI1216.source.api;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ApiServer is a simple HTTP server to handle API requests.
 * 
 * @author Arnoud van der Leer
 */
public class ApiServer {
	
	/**
	 * Runner method; starts the serverSocket and assigns threads.
	 * 
	 * @param args command params
	 */
	public static void main(String[] args){
		try {
			ServerSocket server = new ServerSocket(80);
			boolean run = true;
			while (run) {
				Socket socket = server.accept();
				new Thread(new ApiThread(socket)).start();
			}
			server.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
