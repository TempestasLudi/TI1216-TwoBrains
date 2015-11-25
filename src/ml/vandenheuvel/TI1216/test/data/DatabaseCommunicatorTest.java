/**
 * 
 */
package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sun.org.apache.xpath.internal.functions.Function;

import ml.vandenheuvel.TI1216.source.data.DatabaseCommunicator;
import ml.vandenheuvel.TI1216.source.data.Faculty;
import ml.vandenheuvel.TI1216.source.data.Program;

/**
 * @author Arnoud van der Leer
 *
 */
public class DatabaseCommunicatorTest{
	private DatabaseCommunicator create(){
//		return new DatabaseCommunicator("192.168.1.111", "TI1216");
		return new DatabaseCommunicator("85.151.128.10", "TI1216");
	}
	
	@Test
	public void testConstructor(){
		DatabaseCommunicator communicator = create();
		Faculty[] faculties = communicator.getFaculties();
		for (int i = 0; i < faculties.length; i++) {
			System.out.println("<Faculty(Some ID here, " + faculties[i].getName() + ")>");
		}
		Faculty thing = new Faculty("LR", "Lucht- en ruimtevaart", new ArrayList<Program>());
		communicator.save(thing);
		thing = new Faculty("3Me", "3-thing-stuff", new ArrayList<Program>());
		communicator.save(thing);
	}

}
