package ml.vandenheuvel.ti1216.api;

public class ServerApplication {

	private ServerApplication() {
		//To override default constructor
	}

	//port, 85.151.128.10/192.168.1.111, TI1216, TI1216, 3t.uGmL365j2f7B
	
	public static void main(String[] args) {
		if(args.length != 5){
			System.out.println("Give the port number to run on and address, databasename, username and password of the database as arguments.");
			return;
		}
		Server server = new Server(Integer.parseInt(args[0]), args[1], args[2], args[3], args[4]);
		server.start();
	}

}
