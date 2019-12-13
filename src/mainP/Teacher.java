package mainP;

import java.util.ArrayList;

public class Teacher implements person {
	ArrayList<Course> taughtCourses;
	Integer id;
	String FName,LName,type;

	public Teacher(Integer id, String FName, String LName, String type) {
		this.id = id;
		this.FName = FName;
		this.LName = LName;
		this.type = type;
	}

	public ArrayList<Course> getTaughtCourses() {
		return taughtCourses;
	}

	public void setTaughtCourses(ArrayList<Course> taughtCourses) {
		this.taughtCourses = taughtCourses;
	}

	public String getFName() {
		return FName;
	}

	public void setFName(String FName) {
		this.FName = FName;
	}

	public String getLName() {
		return LName;
	}

	public void setLName(String LName) {
		this.LName = LName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Teacher(){
		taughtCourses=new ArrayList<Course>();
	}
	
	public void dispaytcourses(){
		System.out.println(taughtCourses);

	}

	public void addCourse(Course course){

		taughtCourses.add(course);
	}


	@Override
	public String getname() {
	return FName;
	}

	@Override
	public String gettype() {
		return type;
	}
}
