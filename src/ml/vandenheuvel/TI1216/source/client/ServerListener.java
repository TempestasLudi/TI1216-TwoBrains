package ml.vandenheuvel.TI1216.source.client;

import java.io.ObjectInputStream;

import ml.vandenheuvel.TI1216.source.data.ChatMessage;
import ml.vandenheuvel.TI1216.source.data.Match;

public class ServerListener implements Runnable {

	private ObjectInputStream inputStream;
	private boolean running;
	
	ServerListener(ObjectInputStream inputStream){
		this.inputStream = inputStream;
		running = true;
	}
	
	public void run() {
		while(running){
			try{
				Object receivedObject = this.inputStream.readObject();
				if(receivedObject instanceof ChatMessage){
					//actions
				}
				if(receivedObject instanceof Match){
					//actions
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
