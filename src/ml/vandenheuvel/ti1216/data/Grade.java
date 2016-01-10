package ml.vandenheuvel.ti1216.data;

import org.json.JSONObject;

/**
 * Objects of this instance are pairs of a course and a grade
 *
 */
public class Grade {

	private String courseId;
	private int grade;

	/**
	 * creates a new Grade
	 * 
	 * @param courseId
	 *            the courseId to which the Grade belongs
	 * @param grade
	 *            an integer that represents the Grade
	 */
	public Grade(String courseId, int grade) {
		this.courseId = courseId;
		this.grade = grade;
	}

	/**
	 * gets the Course to which the Grade belongs
	 * 
	 * @return the Course to which the Grade belongs
	 */
	public String getCourseId() {
		return this.courseId;
	}

	/**
	 * gets the integer that represents the Grade
	 * 
	 * @return the integer that represents the Grade
	 */
	public int getGrade() {
		return this.grade;
	}

	/**
	 * sets the Course to which the Grade belongs
	 * 
	 * @param course
	 *            the new Course of the Grade
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	 * sets the integer that represents the Grade
	 * 
	 * @param grade
	 *            the new integer of the Grade
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * Creates a JSON object out of a Grade object.
	 * 
	 * @return a JSON object out of a Grade object
	 */
	public JSONObject toJSON() {
		return new JSONObject().put("grade", Integer.toString(this.grade)).put(
				"courseId", this.courseId);
	}

	/**
	 * Creates Grade object out of a JSON object
	 * 
	 * @param json
	 *            the JSON object
	 * @return a Grade object
	 */
	public static Grade fromJSON(JSONObject jsonObject) {
		if (jsonObject != null) {
			//if(jsonObject.get("grade") instanceof String) {
				return new Grade(jsonObject.getString("courseId"),
						Integer.parseInt(jsonObject.getString("grade")));
			}
			/*else {
				return new Grade(jsonObject.getString("courseId"),
						(int)jsonObject.get("grade"));
			}
		}*/
		return null;
	}

	/**
	 * checks whether two Grades are equal to each other
	 * 
	 * @param other
	 *            the Object to which the Grade is compared
	 * @return true if the Grades are equal, otherwise false
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Grade) {
			Grade that = (Grade) other;
			return this.courseId.equals(that.courseId)
					&& this.grade == that.grade;
		}
		return false;
	}
}