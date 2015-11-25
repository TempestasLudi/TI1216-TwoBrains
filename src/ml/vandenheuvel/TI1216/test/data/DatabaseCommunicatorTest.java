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
		return new DatabaseCommunicator("85.151.128.10", "TI1216");
	}

}
