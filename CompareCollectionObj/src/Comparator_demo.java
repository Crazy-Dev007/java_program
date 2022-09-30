import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student {
	public int sid;
	public String sname;
	public int age;
	public int marks;

	public Student() {
		super();

	}

	public Student(int sid, String sname, int age, int marks) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.age = age;
		this.marks = marks;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", age=" + age + ", marks=" + marks + "]";
	}

}

class CompareByNames implements Comparator<Student> {

	// Compare each Name with Another
	@Override
	public int compare(Student o1, Student o2) {

		return o1.getSname().compareTo(o2.getSname());
	}

}

public class Comparator_demo {

	public static void main(String[] args) {
		List<Student> l = new ArrayList<>();

		l.add(new Student(101, "c", 23, 35));
		l.add(new Student(101, "bc", 53, 35));
		l.add(new Student(101, "q", 3, 35));
		l.add(new Student(101, "mbc", 20, 35));

		System.out.println("Before sort : ");
		for (Student student : l) {
			System.out.println(student);
		}

		// By using Separate class
		// Collections.sort(l, new CompareByNames());

		// By using Lambda Expression
		Collections.sort(l, (o1, o2) -> {
			return o1.age - o2.age; // Ascending
		});

		System.out.println("After sort : ");
		for (Student student : l) {
			System.out.println(student);
		}

	}

}
