package ml.vandenheuvel.ti1216.server;

import ml.vandenheuvel.ti1216.data.DatabaseCommunicator;
import ml.vandenheuvel.ti1216.data.Match;
import ml.vandenheuvel.ti1216.data.User;

public class Matcher implements Runnable {

	private boolean run = true;
	private DatabaseCommunicator communicator;

	public Matcher(String databaseAddress, String databaseUsername, String databaseName, String databasePassword) {
		this.communicator = new DatabaseCommunicator(databaseAddress, databaseName, databaseUsername, databasePassword);
	}

	public void run() {
		while (this.run) {
			User[] users = this.communicator.getUsers();
			int addedCount = 0;
			for (int i = 0; i < users.length; i++) {
				Match[] matches = this.communicator.getMatches(users[i].getUsername());
				for (int j = 0; j < users.length; j++) {
					if (i == j) {
						continue;
					}
					boolean found = false;
					for (int k = 0; k < matches.length; k++) {
						if (matches[k].getMatchUsername().equals(users[j].getUsername())) {
							found = true;
							break;
						}
					}
					if (!found) {
						this.match(users[i].getUsername(), users[j].getUsername());
						addedCount++;
					}
				}
			}

			if (addedCount == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	private void match(String uname1, String uname2) {
		Match match1 = new Match(-1, uname1, uname2, false, true);
		Match match2 = new Match(-1, uname2, uname1, false, true);

		boolean approved = match1.getRating(this.communicator) > 17.0;

		match1.setApproved(approved);
		match2.setApproved(approved);
		this.communicator.save(match1);
		this.communicator.save(match2);
	}

	public void stop() {
		this.run = false;
	}

}
