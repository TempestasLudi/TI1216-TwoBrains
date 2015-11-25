package ml.vandenheuvel.TI1216.source.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;

import ml.vandenheuvel.TI1216.source.data.ChatMessage;
import ml.vandenheuvel.TI1216.source.data.Login;
import ml.vandenheuvel.TI1216.source.data.User;

public class Client {
	private String server;
	private int portNumber;
	private User user;
	private Login login;
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	Client(String server, int portNumber) {
		this.server = server;
		this.portNumber = portNumber;
	}

	public boolean start() {
		try {
			this.socket = new Socket(this.server, this.portNumber);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		System.out.println("Connected to " + this.server + " on port "
				+ this.portNumber);
		try {
			this.inputStream = new ObjectInputStream(
					this.socket.getInputStream());
			this.outputStream = new ObjectOutputStream(
					this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Thread logIn(Login login) {
		int response = 1;
		do {
			try {
				this.outputStream.writeObject(login);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			try {
				response = this.inputStream.readByte();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// edit the login object
		} while (response != 0);
		
		ServerListener serverListener = new ServerListener(this.inputStream);
		return new Thread(serverListener);
	}
	
	public void close(Thread serverListener){
		serverListener.interrupt();
		try{
			outputStream.close();
		} catch (IOException e){
			e.getMessage();
		}
		try {
			inputStream.close();
		} catch (IOException e){
			e.getMessage();
		}
		try{
			socket.close();
		} catch (IOException e){
			e.getMessage();
		}
	}
	
	public void sendMessage(ChatMessage chatMessage){
		try{
			this.outputStream.writeObject(chatMessage);
		} catch (IOException e){
			e.getMessage();
		}
	}

}
