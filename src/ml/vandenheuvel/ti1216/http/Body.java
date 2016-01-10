package ml.vandenheuvel.ti1216.http;

/**
 * Body represents an HTTP message body.
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
	@Override
	public String toString(){
		return this.content;
	}
	
	/**
	 * Equals method to compare object with this instance
	 */
	@Override
	public boolean equals(Object other){
		if(other instanceof Body){
			Body that = (Body) other;
			return this.getContent().equals(that.getContent());
		}
		return false;
		
	}

}
