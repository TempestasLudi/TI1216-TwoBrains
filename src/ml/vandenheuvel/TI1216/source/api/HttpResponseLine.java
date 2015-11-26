package ml.vandenheuvel.TI1216.source.api;

/**
 * HttpResponseLine represents the response-line of an HTTP response.
 * 
 * @author Arnoud van der Leer
 */
public class HttpResponseLine implements HttpHeaderLine {
	// TODO: add getters/setters
	/**
	 * The HTTP version.
	 */
	private String version;
	
	/**
	 * The response code.
	 */
	private String code;
	
	/**
	 * The response status.
	 */
	private String status;
	
	/**
	 * Class constructor, reads the data from a line from a HTTP response header.
	 * 
	 * @param line the line from the HTTP response header
	 */
	public HttpResponseLine(String line) {
		String[] parts = line.split(" ");
		this.version = parts[0].trim().toUpperCase();
		this.code = parts[1].trim();
		this.status = parts[2].trim();
	}
	
	/**
	 * Class constructor, takes the data as params.
	 * 
	 * @param version the HTTP version
	 * @param code    the response code
	 * @param status the response status
	 */
	public HttpResponseLine(String version, String code, String status) {
		this.version = version;
		this.code = code;
		this.status = status;
	}

	/**
	 * Generates the response-line in string format.
	 * 
	 * @return the response-line in string format
	 */
	public String toString(){
		return this.version + " " + this.code + " " + this.status + " \r\n";
	}
}
