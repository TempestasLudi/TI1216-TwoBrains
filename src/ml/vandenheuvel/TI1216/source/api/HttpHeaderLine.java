package ml.vandenheuvel.TI1216.source.api;

/**
 * HttpHeaderLine represents the request-line or response-line of a HTTP header.
 * 
 * @author Arnoud van der Leer
 */
public interface HttpHeaderLine {
	/**
	 * Generates the header line in string format.
	 * 
	 * @return the header line in string format
	 */
	public String toString();
}
