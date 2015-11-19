/**
 * 
 */
package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.data.DatabaseCommunicator;
import ml.vandenheuvel.TI1216.source.data.Faculty;

/**
 * @author Arnoud van der Leer
 *
 */
public class DatabaseCommunicatorTest {
	private DatabaseCommunicator create(){
		return new DatabaseCommunicator("85.151.128.10", "TI1216");
	}

	@Test
	public void testConstructor() {
		DatabaseCommunicator communicator = create();
		Faculty[] faculties = communicator.getFaculties();
		for (int i = 0; i < faculties.length; i++) {
			System.out.println("<Faculty(Some ID here, " + faculties[i].getName() + ")>");
		}
	}

}
