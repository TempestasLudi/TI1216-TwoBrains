package ml.vandenheuvel.TI1216.data;

public class Grade 
{
	private Course course;
	private int grade;
	
	public Grade(Course course, int grade)
	{
		this.course = course;
		this.grade = grade;
	}
	
	public Course getCourse()
	{
		return this.course;
	}
	
	public int getGrade()
	{
		return this.grade;
	}
	
	public void setCourse(Course course)
	{
		this.course = course;
	}
	
	public void setGrade(int grade)
	{
		this.grade = grade;
	}
	
	public boolean equals(Object other)
	{
		if(other instanceof Grade)
		{
			Grade that = (Grade) other;
			return ((this.course.equals(that.course)) && (this.grade == that.grade));
		}
		return false;
	}
}