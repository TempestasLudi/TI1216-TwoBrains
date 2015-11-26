package ml.vandenheuvel.TI1216.source.api;

import java.util.ArrayList;

/**
 * HttpHeader represents the header of a HTTP request.
 * 
 * @author Arnoud van der Leer
 */
public class HttpHeader{
	/**
	 * The HTTP request-line.
	 */
	private HttpHeaderLine headerLine;
	
	/**
	 * The fields of the header.
	 */
	private ArrayList<HttpHeaderField> fields = new ArrayList<HttpHeaderField>();
	
	/**
	 * Class constructor.
	 * 
	 * @param headerLine the HTTP header line
	 */
	public HttpHeader(HttpHeaderLine headerLine) {
		this.headerLine = headerLine;
	}
	
	/**
	 * Class constructor.
	 * 
	 * @param headerLine the HTTP request-line
	 * @param headers the fields of the header
	 */
	public HttpHeader(HttpRequestLine headerLine, ArrayList<HttpHeaderField> fields) {
		this.headerLine = headerLine;
		this.fields = fields;
	}
	
	/**
	 * Adds a field to the header fields.
	 * 
	 * @param field the field to add
	 */
	public void addField(HttpHeaderField field) {
		this.fields.add(field);
	}
	
	/**
	 * Gets a field with a certain name.
	 * 
	 * @param name the name of the field to get
	 * @return     if found, the field with the specified name, otherwise null
	 */
	public HttpHeaderField getField(String name){
		for (int i = 0; i < this.fields.size(); i++) {
			if (this.fields.get(i).getName().equals(name)) {
				return this.fields.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Gets the header line.
	 * 
	 * @return the header line
	 */
	public HttpHeaderLine getHeaderLine(){
		return this.headerLine;
	}
	
	/**
	 * Changes the header line.
	 * 
	 * @param headerLine the header line
	 */
	public void setHeaderLine(HttpHeaderLine headerLine){
		this.headerLine = headerLine;
	}
	
	/**
	 * Generates a HTTP header string.
	 * 
	 * @return the HTTP header string
	 */
	public String toString(){
		String result = this.headerLine.toString();
		for (int i = 0; i < this.fields.size(); i++) {
			result += this.fields.get(i).toString();
		}
		return result;
	}

}
