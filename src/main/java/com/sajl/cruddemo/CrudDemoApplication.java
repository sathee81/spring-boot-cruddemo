package com.sajl.cruddemo;

import com.sajl.cruddemo.dao.StudentDAO;
import com.sajl.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(CrudDemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner ( StudentDAO theStudentDAO )
	{
		return runner -> {
			//doTest( theStudentDAO );
		};
	}

	private void doTest( StudentDAO theStudentDAO )
	{
		createStudent( theStudentDAO );
		createMultipleStudents( theStudentDAO );
		retrieveStudent( theStudentDAO );
		retrieveAllStudents( theStudentDAO );
		retrieveAllLastName( theStudentDAO );
		updateStudent( theStudentDAO );
		deleteStudent( theStudentDAO );
		deleteAllStudent( theStudentDAO );
	}

	private void deleteAllStudent(StudentDAO theStudentDAO)
	{
		theStudentDAO.deleteAll();
	}

	private void deleteStudent(StudentDAO theStudentDAO)
	{
		System.out.println("Create new Student");
		Student student = new Student("Temp1", "LastTemp1" , "sathee.archu@gmail.com");
		System.out.println("Save Student Object " + student );
		theStudentDAO.save( student );
		System.out.println("Saved Student ID : " + student.getId() );
		theStudentDAO.delete( student.getId() );

	}

	private void updateStudent(StudentDAO theStudentDAO)
	{
		Student theStudent = theStudentDAO.findById( 1 );
		theStudent.setEmail( "sathee.archu@gmail.com" );
		theStudentDAO.update( theStudent );
		theStudent = theStudentDAO.findById( 1 );
		System.out.println( "Updated Student : " + theStudent );
	}

	private void retrieveAllLastName(StudentDAO theStudentDAO)
	{
		System.out.println( "Retrieve All Students");
		List<Student> theStudentList = theStudentDAO.findByLastname( "Satheesh" );
		for( Student theStudent : theStudentList )
		{
			System.out.println( theStudent.toString() );
		}
	}

	private void retrieveAllStudents(StudentDAO theStudentDAO)
	{
		System.out.println( "Retrieve All Students");
		List<Student> theStudentList = theStudentDAO.findAll();
		for( Student theStudent : theStudentList )
		{
			System.out.println( theStudent.toString() );
		}
	}

	private void retrieveStudent(StudentDAO theStudentDAO)
	{
		Student theStudent = theStudentDAO.findById( 1 );
		System.out.println( theStudent.toString());

		theStudent = theStudentDAO.findById( 2 );
		System.out.println( theStudent.toString());

		theStudent = theStudentDAO.findById( 3 );
		System.out.println( theStudent.toString());

		theStudent = theStudentDAO.findById( 4 );
		System.out.println( theStudent.toString());

		theStudent = theStudentDAO.findById( 5 );
		System.out.println( theStudent ==null ? "Object is NULL " : theStudent.toString());

	}

	private void createMultipleStudents(StudentDAO theStudentDAO)
	{
		System.out.println("Creating 3 New Students");
		Student student1 = new Student("Archana", "Satheesh" , "archana@gmail.com");
		Student student2 = new Student("Joshna", "Satheesh" , "joshna@gmail.com");
		Student student3 = new Student("Lishanth", "Satheesh" , "lishanth@gmail.com");
		theStudentDAO.save( student1 );
		theStudentDAO.save( student2 );
		theStudentDAO.save( student3 );

	}

	private void createStudent(StudentDAO theStudentDAO)
	{
		System.out.println("Create new Student");
		Student student = new Student("Satheesh", "Venkatachalam" , "sathee.archu@gmail.com");
		System.out.println("Save Student Object " + student );
		theStudentDAO.save( student );
		System.out.println("Saved Student ID : " + student.getId() );
	}
}
