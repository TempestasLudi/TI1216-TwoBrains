package ml.vandenheuvel.ti1216.http;

/**
 * HeaderLine represents the request-line or response-line of a HTTP header.
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
