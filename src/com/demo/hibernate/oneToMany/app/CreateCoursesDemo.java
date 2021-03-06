package com.demo.hibernate.oneToMany.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.oneToMany.entity.Course;
import com.demo.hibernate.oneToMany.entity.Instructor;
import com.demo.hibernate.oneToMany.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		
		// Create Session Factory
		SessionFactory lFactory = new Configuration()
				.configure("hibernate.oneToManyCfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
						
		// Create a Session
		Session lSession = lFactory.getCurrentSession();
		try {									
			// Start a transaction
			lSession.beginTransaction();
			
			// Get Instructor from DB
			int lId = 1;
			Instructor lInstructor = lSession.get(Instructor.class, lId);
			
			// Create Some Courses
			Course lCourse1 = new Course("Guitar");
			Course lCourse2 = new Course("Pinball");
			
			// Add Courses to the Instructor
			lInstructor.add(lCourse1);
			lInstructor.add(lCourse2);

			//Save the Courses
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
