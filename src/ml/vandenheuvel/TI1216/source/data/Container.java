package ml.vandenheuvel.TI1216.source.data;

import java.util.ArrayList;
import java.util.List;

public class Container {

	private List<Program> programs = new ArrayList<Program>();
	
	public Container() {
		
	}

	public Program getProgram(String id) {
		for (int i = 0; i < this.programs.size(); i++) {
			if (this.programs.get(i).getID().equals(id)) {
				return this.programs.get(i);
			}
		}
		return null;
	}
	
}
