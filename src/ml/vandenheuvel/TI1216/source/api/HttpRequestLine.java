package ml.vandenheuvel.TI1216.source.api;

/**
 * HttpRequestLine represents the request-line of an HTTP request.
 * 
 * @author Arnoud van der Leer
 */
public class HttpRequestLine implements HttpHeaderLine {
	// TODO: add getters/setters
	/**
	 * The request method.
	 */
	private String method;
	
	/**
	 * The request uri.
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
	public HttpRequestLine(String line) {
		String[] parts = line.split(" ");
		this.method = parts[0].trim().toUpperCase();
		this.uri = parts[1].trim();
		this.version = parts[2].trim();
	}
	
	/**
	 * Class constructor, takes the data as params.
	 * 
	 * @param method  the request method
	 * @param uri     the request uri
	 * @param version the HTTP version
	 */
	public HttpRequestLine(String method, String uri, String version) {
		this.method = method;
		this.uri = uri;
		this.version = version;
	}

	/**
	 * Generates the request-line in string format.
	 * 
	 * @return the request-line in string format
	 */
	public String toString(){
		return this.method + " " + this.uri + " " + this.version + " \r\n";
	}
}
