package ml.vandenheuvel.TI1216.source.data;

/**
 * Objects of this instance are pairs of a course and a grade
 * @author 
 *
 */
public class Grade 
{
	
	private Course course;
	private int grade;
	
	/**
	 * creates a new Grade
	 * @param course the Course to which the Grade belongs
	 * @param grade an integer that represents the Grade
	 */
	public Grade(Course course, int grade)
	{
		this.course = course;
		this.grade = grade;
	}
	
	/**
	 * gets the Course to which the Grade belongs
	 * @return the Course to which the Grade belongs
	 */
	public Course getCourse()
	{
		return this.course;
	}
	
	/**
	 * gets the integer that represents the Grade
	 * @return the integer that represents the Grade
	 */
	public int getGrade()
	{
		return this.grade;
	}
	
	/**
	 * sets the Course to which the Grade belongs
	 * @param course the new Course of the Grade
	 */
	public void setCourse(Course course)
	{
		this.course = course;
	}
	
	/**
	 * sets the integer that represents the Grade
	 * @param grade the new integer of the Grade
	 */
	public void setGrade(int grade)
	{
		this.grade = grade;
	}
	
	/**
	 * checks whether two Grades are equal to each other
	 * @param other the Object to which the Grade is compared
	 * @return true if the Grades are equal, otherwise false
	 */
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof Grade)
		{
			Grade that = (Grade) other;
			return this.course.equals(that.course) && this.grade == that.grade;
		}
		return false;
	}
}