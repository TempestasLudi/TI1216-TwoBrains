package ml.vandenheuvel.TI1216.source.api.http;

/**
 * Body represents an HTTP message body.
 * 
 * @author Arnoud van der Leer
 */
public class Body {

	/**
	 * The content of the body.
	 */
	private String content;

	/**
	 * Class constructor.
	 * 
	 * @param content the content of the body
	 */
	public Body(String content) {
		this.content = content;
	}

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent(){
		return this.content;
	}

	/**
	 * Changes the content.
	 * 
	 * @param content the content
	 */
	public void setContent(String content){
		this.content = content;
	}

	/**
	 * Represents the body by a string.
	 * 
	 * @return a string representation of the body
	 */
	public String toString(){
		return this.content;
	}

}
