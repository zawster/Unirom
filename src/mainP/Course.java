package mainP;

import java.util.ArrayList;
import java.util.Iterator;

public class Course {
	ArrayList<Student> students;
	ArrayList<Teacher> lecturers;

	public Integer id,CHrs,semester;
	public String CName,ATeacher,type,CCode,preReq;

	public Course(){
		students=new ArrayList<Student>();
		lecturers=new ArrayList<Teacher>();
	}
	public Course( Integer id, String CName,String CCode, Integer CHrs, String ATeachers, String preReq, String type, Integer semester) {

		this.id = id;
		this.CName = CName;
		this.CHrs = CHrs;
		this.ATeacher = ATeachers;
		this.preReq = preReq;
		this.type = type;
		this.semester = semester;
		this.CCode=CCode;
	}


	public void setCName(String CName) {
		this.CName = CName;
	}


	public void addStudents(Student student){

		students.add(student);
	}
	public void assignteacher(Teacher lecturer){

		lecturers.add(lecturer);
	}
	public int getNStudents(){
		int co=students.size();
		return co;
	}
	public void getTeacher(){

		System.out.println("TEACHERS:");
		Iterator<Teacher> itr = lecturers.iterator();
		while (itr.hasNext()){
			System.out.println(itr.next().getname());
		}
	}
	public void courseinfo(){
		System.out.println(CName);
		System.out.println(students.size()); 
		getTeacher();
	}


	public void displayStudents(){

		System.out.println("STUDENTS:");
		Iterator<Student> itr = students.iterator();
		while (itr.hasNext()){
			System.out.println(itr.next().getname());
		}
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Teacher> getLecturers() {
		return lecturers;
	}

	public void setLecturers(ArrayList<Teacher> lecturers) {
		this.lecturers = lecturers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCHrs() {
		return CHrs;
	}

	public void setCHrs(Integer CHrs) {
		this.CHrs = CHrs;
	}

	public String getPreReq() {
		return preReq;
	}

	public void setPreReq(String preReq) {
		this.preReq = preReq;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public String getCName() {
		return CName;
	}

	public String getATeacher() {
		return ATeacher;
	}

	public void setATeacher(String aTeacher) {
		this.ATeacher = aTeacher;
	}

	public String getCCode() {
		return CCode;
	}

	public void setCCode(String cCode) {
		this.CCode = cCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
