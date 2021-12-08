package com.demo.hibernate.manyToMany.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.manyToMany.entity.Course;
import com.demo.hibernate.manyToMany.entity.Instructor;
import com.demo.hibernate.manyToMany.entity.InstructorDetail;
import com.demo.hibernate.manyToMany.entity.Review;
import com.demo.hibernate.manyToMany.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		
		// Create Session Factory
		SessionFactory lFactory = new Configuration()
				.configure("hibernate.manyToManyCfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
						
		// Create a Session
		Session lSession = lFactory.getCurrentSession();
		try {									
			// Start a transaction
			lSession.beginTransaction();
			
			// Create a Course
			Course lCourse = new Course("Guitar");
					
			// Save course
			System.out.println("Saving the course: " + lCourse);
			lSession.save(lCourse);
			
			// Create a student
			Student lStudent1 = new Student("John", "Doe", "john@abc.com");
			Student lStudent2 = new Student("Mary", "Public", "mary@abc.com");
			
			// Add student to the course
			lCourse.addStudent(lStudent1);
			lCourse.addStudent(lStudent2);
			
			// Save the students
			System.out.println("\nSaving students ... ");
			lSession.save(lStudent1);
			lSession.save(lStudent2);
			System.out.println("Saved students: " + lCourse.getStudents());
			
			// Commit transaction
			lSession.getTransaction().commit();
			System.out.println("Done");
		}
		catch(Exception exc) {				
			exc.printStackTrace();
		}
		finally {
			// handle connection leak issue
			lSession.close();
			lFactory.close();
		}
	}
}
