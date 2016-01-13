package ml.vandenheuvel.ti1216.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ServerApplication is a simple HTTP server to handle API requests.
 */
public class ServerApplication {

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.server");

	private ServerApplication() {

	}

	/**
	 * Runner method; starts the serverSocket and assigns threads.
	 * 
	 * Arguments can for example be: 80, 85.151.128.10/192.168.1.111, TI1216,
	 * TI1216, 3t.uGmL365j2f7B
	 * 
	 * @param args
	 *            portnumber databasehost databasename databaseusername
	 *            databasepassword loglevel
	 */
	public static void main(String[] args) {
		if (args.length < 5) {
			logger.severe(
					"At least a portnumber, databasehost, databasename, databaseusername and databasepassword need to be supplied.");
			return;
		} else if (args.length == 5) {
			logger.warning("Assuming loglevel \"WARNING\".");
			logger.setLevel(Level.WARNING);
		} else {
			switch (args[5]) {
			case "OFF":
				logger.setLevel(Level.OFF);
				break;
			case "SEVERE":
				logger.setLevel(Level.SEVERE);
				break;
			case "WARNING":
				logger.setLevel(Level.WARNING);
				break;
			case "INFO":
				logger.setLevel(Level.INFO);
				break;
			case "FINE":
				logger.setLevel(Level.FINE);
				break;
			case "FINER":
				logger.setLevel(Level.FINER);
				break;
			case "FINEST":
				logger.setLevel(Level.FINEST);
				break;
			case "ALL":
				logger.setLevel(Level.ALL);
				break;
			default:
				logger.setLevel(Level.WARNING);
			}
		}

		try {
			logger.addHandler(new FileHandler("%t/TwoBrains.log"));
			logger.info("Logging to " + System.getProperty("java.io.tmpdir") + "/TwoBrains.log" + " using loglevel "
					+ logger.getLevel().getLocalizedName());
		} catch (SecurityException e) {
			logger.log(Level.WARNING, "Could not obtain the right permissions to write the logfile in "
					+ System.getProperty("java.io.tmpdir"), e);
		} catch (IOException e) {
			logger.log(Level.WARNING, "Could not write in " + System.getProperty("java.io.tmpdir"), e);
		}

		try {
			ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
			logger.fine("Opened serversocket on port " + args[0]);
			Processor processor = new Processor(args[1], args[2], args[3], args[4]);
			logger.fine("Created new Processor instance with arguments " + args[1] + ", " + args[2] + ", " + args[3]
					+ ", " + args[4]);
			boolean run = true;
			logger.info("Accepting messages on port " + args[0]);
			while (run) {
				Socket socket = server.accept();
				logger.finest("A client opened a new connection");
				new Thread(new ClientCommunicator(socket, processor)).start();
				logger.finer("A new ClientCommunicator based thread has been started.");
			}
			server.close();
			logger.info("Serversocket closed.");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Server stopped running.", e);
		}
	}

}
