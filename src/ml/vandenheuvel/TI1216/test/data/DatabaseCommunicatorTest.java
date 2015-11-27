/**
 * 
 */
package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.data.DatabaseCommunicator;
import ml.vandenheuvel.TI1216.source.data.Faculty;
import ml.vandenheuvel.TI1216.source.data.Program;

/**
 * @author Arnoud van der Leer
 *
 */
public class DatabaseCommunicatorTest {
	private DatabaseCommunicator create(){
//		return new DatabaseCommunicator("192.168.1.111", "TI1216");
		return new DatabaseCommunicator("85.151.128.10", "TI1216-test");
	}
	
	@Test
	public void testConstructor(){
 		DatabaseCommunicator communicator = create();
 		
 		System.out.println("Fetch 1:");
 		Faculty[] faculties = communicator.getFaculties();
 		for (int i = 0; i < faculties.length; i++) {
 			System.out.println("<Faculty(Some ID here, " + faculties[i].getName() + ")>");
 		}
 		
		Faculty lr = new Faculty("LR", "Lucht- en ruimtevaart", new ArrayList<Program>());
		communicator.save(lr);
		Faculty me = new Faculty("3Me", "3-thing-stuff", new ArrayList<Program>());
		communicator.save(me);
		
 		System.out.println("Fetch 2:");
 		faculties = communicator.getFaculties();
 		for (int i = 0; i < faculties.length; i++) {
 			System.out.println("<Faculty(Some ID here, " + faculties[i].getName() + ")>");
 		}
 		
		communicator.delete(lr);
		communicator.delete(me);
 	}
}
