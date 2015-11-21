package ml.vandenheuvel.TI1216.source.server;

/**
 * This class starts a new server on a certain port. This port is the first
 * command at the command line. There is no way to stop the server just yet.
 * 
 * @author Bram van den Heuvel
 *
 */

public class ServerApplicatie {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Give the portnumber as the only argument.");
			return;
		}
		Server server = new Server(Integer.parseInt(args[0]));
		server.start();
	}
}
