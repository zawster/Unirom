package mainP;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Student implements person {
	public ArrayList<Course> courses;
	public static String id, Fname,Lname,username,type,gender,address,Sid;
	public static String dob;


	Student(){
		courses=new ArrayList<Course>();
	}
	Student(String ids,String fnames,String lnames,String usernames,String types,String genders,String dobs,String addresss){
		this.id=ids;
		this.Fname=fnames;
		this.Lname=lnames;
		this.username=usernames;
		this.type=types;
		this.gender=genders;
		this.dob=dobs;
		this.address=addresss;

	}
	public void displayCourses(){

		Iterator<Course> itr = courses.iterator();
		while (itr.hasNext()){
			System.out.println(itr.next().CName);
		}
		
	}
//	public static String getSid(){
//
//		return Sid;
//	}
	public static String getSid(){
		
		return id;
	}
	public static String getName(){

		return Fname;

	}
	
	public void addCourse(Course course){

		courses.add(course);
	}


	@Override
	public String getname() {
		return this.Fname;
	}

	@Override
	public String gettype() {
		return type;
	}
}
