/**
 * 
 */
package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.data.DatabaseCommunicator;

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
	}

}
