package ml.vandenheuvel.TI1216.source.data;

/**
 * Class "ChatMessage".
 * 
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft
 *         2015-2016.
 */

public class ChatMessage {

	/**
	 * Class-instances/variables.
	 */
	String sender;
	String message;
	String receiver;

	// BEGIN CONSTRUCTORS

	/**
	 * Default Constructor
	 */
	public ChatMessage() {
	}

	/**
	 * General constructor.
	 * 
	 * @param sender	The username of the person who sends the message
	 * @param message	The actual content of the message
	 * @param receiver	The username of the person who should receive the message
	 */
	public ChatMessage(String sender, String message, String receiver) {
		this.sender = sender;
		this.message = message;
		this.receiver = receiver;
	}

}
