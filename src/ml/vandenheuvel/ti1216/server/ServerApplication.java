package ml.vandenheuvel.ti1216.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ApiServer is a simple HTTP server to handle API requests.
 */
public class ServerApplication {
	
	private ServerApplication(){
		
	}
	
	/**
	 * Runner method; starts the serverSocket and assigns threads.
	 * 
	 * Arguments can for example be: 80, 85.151.128.10/192.168.1.111, TI1216, TI1216, 3t.uGmL365j2f7B
	 * 
	 * @param args portnumber databasehost databasename databaseusername databasepassword
	 */
	public static void main(String[] args){
		try {
			ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
			Processor processor = new Processor(args[1], args[2], args[3], args[4]);
			boolean run = true;
			while (run) {
				Socket socket = server.accept();
				new Thread(new ClientCommunicator(socket, processor)).start();
			}
			server.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
