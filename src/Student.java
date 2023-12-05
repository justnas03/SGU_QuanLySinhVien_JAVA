
public class Student {
	private static int  age;
	private static String name, address;
	private static double gpa;
	
	public Student() {
		
	}
	
	public Student(String name, int age, String address, double gpa) {
		
		this.name = name;
		this.age = age;
		this.address = address;
		this.gpa = gpa;
	}
	
	@Override
	public String toString() {
		return "Student [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public static int getAge() {
		return age;
	}
	
	public static void setAge(int age) {
		Student.age = age;
	}
	
	public static String getName() {
		return name;
	}
	
	public static void setName(String name) {
		Student.name = name;
	}
	
	public static String getAddress() {
		return address;
	}
	
	public static void setAddress(String address) {
		Student.address = address;
	}
	
	public static double getGpa() {
		return gpa;
	}
	public static void setGpa(double gpa) {
		Student.gpa = gpa;
	}
	
	
	
}
