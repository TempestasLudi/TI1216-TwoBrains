package ml.vandenheuvel.TI1216.source.api.http;

/**
 * HeaderLine represents the request-line or response-line of a HTTP header.
 * 
 * @author Arnoud van der Leer
 */
public interface HeaderLine {
	/**
	 * Generates the header line in string format.
	 * 
	 * @return the header line in string format
	 */
	@Override
	public String toString();
}
