package ml.vandenheuvel.TI1216.source.data;

import java.util.ArrayList;
import java.util.List;

public class Container {

	private List<Program> programs = new ArrayList<Program>();
	private List<Faculty> faculties = new ArrayList<Faculty>();
	
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
	
	public Faculty getFaculty(String id) {
		for (int i = 0; i < this.faculties.size(); i++) {
			if (this.faculties.get(i).getID().equals(id)) {
				return this.faculties.get(i);
			}
		}
		return null;
	}
	
}
