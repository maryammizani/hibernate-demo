package com.demo.hibernate.manyToMany.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.manyToMany.entity.Course;
import com.demo.hibernate.manyToMany.entity.Instructor;
import com.demo.hibernate.manyToMany.entity.InstructorDetail;
import com.demo.hibernate.manyToMany.entity.Review;
import com.demo.hibernate.manyToMany.entity.Student;

public class AddCoursesForStudentDemo {

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
			
			// Get student from DB
			int lStudentId = 2;
			Student lStudent = lSession.get(Student.class,  lStudentId);
			System.out.println("\nLoaded student: " + lStudent);
			System.out.println("Course: " + lStudent.getCourses());
			
			// Create more Courses 
			Course lCourse1 = new Course("RubikCube1");
			Course lCourse2 = new Course("Atari1");
								
			// Add Student to courses
			lCourse1.addStudent(lStudent);
			lCourse2.addStudent(lStudent);
			
			// Save the courses
			System.out.println("\nSaving courses ...");
			lSession.save(lCourse1);
			lSession.save(lCourse2);

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
