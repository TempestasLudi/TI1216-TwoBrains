package ml.vandenheuvel.TI1216.source.data;

import java.util.ArrayList;
import java.util.List;

public class Container {

	private List<Program> programs;
	private List<Faculty> faculties;
	
	public Container() {
		programs = new ArrayList<Program>();
		faculties = new ArrayList<Faculty>();
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
	
	public void addProgram(Program program)
	{
		if(!programs.contains(program))
		{
			programs.add(program);
		}
	}
	
	public void addFaculty(Faculty faculty)
	{
		if(!faculties.contains(faculty))
		{
			faculties.add(faculty);
		}
	}
	
}
