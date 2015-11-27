package ml.vandenheuvel.TI1216.source.api.http;

import java.util.ArrayList;
import java.util.Date;

/**
 * Header represents the header of a HTTP request.
 * 
 * @author Arnoud van der Leer
 */
public class Header {
	/**
	 * The HTTP request-line.
	 */
	private HeaderLine headerLine;

	/**
	 * The fields of the header.
	 */
	private ArrayList<HeaderField> fields = new ArrayList<HeaderField>();
	 
	/**
	 * Class constructor.
	 */
	public Header() {
		this(new ResponseLine("HTTP/1.1", "200", "OK"));
	}
	
	/**
	 * Class constructor.
	 * 
	 * @param headerLine the HTTP header line
	 */
	public Header(HeaderLine headerLine) {
		this.headerLine = headerLine;
		this.addField(new HeaderField("Connection", "close"));
		this.addField(new HeaderField("Content-Type", "text/json"));
	}

	/**
	 * Class constructor.
	 * 
	 * @param headerLine the HTTP request-line
	 * @param headers the fields of the header
	 */
	public Header(RequestLine headerLine, ArrayList<HeaderField> fields) {
		this.headerLine = headerLine;
		this.fields = fields;
	}

	/**
	 * Gets the header fields.
	 * 
	 * @return the header fields
	 */
	public HeaderField[] getFields(){
		HeaderField[] fields = new HeaderField[this.fields.size()];
		this.fields.toArray(fields);
		return fields;
	}

	/**
	 * Gets a field with a certain name.
	 * 
	 * @param name the name of the field to get
	 * @return if found, the field with the specified name, otherwise null
	 */
	public HeaderField getField(String name){
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
	public HeaderLine getHeaderLine(){
		return this.headerLine;
	}

	/**
	 * Adds a field to the header fields.
	 * 
	 * @param field the field to add
	 */
	public void addField(HeaderField field){
		HeaderField existing = this.getField(field.getName());
		if (existing == null) {
			this.fields.add(field);
		} else {
			existing.setValue(field.getValue());
		}
	}

	/**
	 * Changes the header line.
	 * 
	 * @param headerLine the header line
	 */
	public void setHeaderLine(HeaderLine headerLine){
		this.headerLine = headerLine;
	}

	/**
	 * Merges this header with another one.
	 * 
	 * Replaces the requestLine, adds/overwrites all headers.
	 * 
	 * @param header the header to merge with
	 */
	public void merge(Header header){
		if (header.getHeaderLine() != null) {
			this.headerLine = header.getHeaderLine();
		}
		HeaderField[] fields = header.getFields();
		for (int i = 0; i < fields.length; i++) {
			this.addField(fields[i]);
		}
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
