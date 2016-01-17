package ml.vandenheuvel.ti1216.http;

/**
 * RequestLine represents the request-line of an HTTP request.
 */
public class RequestLine implements HeaderLine {
	/**
	 * The request method.
	 */
	private String method;

	/**
	 * The request URI.
	 */
	private String uri;

	/**
	 * The HTTP version.
	 */
	private String version;

	/**
	 * Class constructor, reads the data from a line from a HTTP request header.
	 * 
	 * @param line the line from the HTTP request header
	 */
	public RequestLine(String line) {
		String[] parts = line.split(" ");
		this.method = parts[0].trim().toUpperCase();
		this.uri = parts[1].trim();
		this.version = parts[2].trim();
	}

	/**
	 * Class constructor, takes the data as params.
	 * 
	 * @param method the request method
	 * @param uri the request URI
	 * @param version the HTTP version
	 */
	public RequestLine(String method, String uri, String version) {
		this.method = method;
		this.uri = uri;
		this.version = version;
	}

	/**
	 * Gets the request method.
	 * 
	 * @return the request method
	 */
	public String getMethod() {
		return this.method;
	}

	/**
	 * Gets the request URI.
	 * 
	 * @return the request URI
	 */
	public String getUri() {
		return this.uri;
	}

	/**
	 * Gets the HTTP version.
	 * 
	 * @return the HTTP version
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * Changes the request method.
	 * 
	 * @return the request method
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Changes the request URI.
	 * 
	 * @param uri the request URI
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	/**
	 * Changes the HTTP version.
	 * 
	 * @param version the HTTP version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Generates the request-line in string format.
	 * 
	 * @return the request-line in string format
	 */
	@Override
	public String toString(){
		return this.method + " " + this.uri + " " + this.version + "\r\n";
	}
	
	
	/**
	 * Compares two objects to see if they are equal
	 * @param other Object to compare this instance to
	 * @return true if equal false if not
	 */
	@Override
	public boolean equals(Object other) {
		if(other instanceof RequestLine){
			RequestLine that = (RequestLine) other;
			return this.getMethod().equals(that.getMethod()) && 
					this.getUri().equals(that.getUri()) && 
					this.getVersion().equals(that.getVersion());
		}
		
		return false;
	}
}
