package com.demo.hibernate.eagerVsLazy.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.eagerVsLazy.entity.Course;
import com.demo.hibernate.eagerVsLazy.entity.Instructor;
import com.demo.hibernate.eagerVsLazy.entity.InstructorDetail;

public class EagerLazyDemo {

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
			 System.out.println("Instructor: " + lInstructor);  // This will print all the Course info of this instructor as well
			 System.out.println("-------- Get Courses Now ----------");
			 
			// get courses for the Instructor
			System.out.println("Courses: " + lInstructor.getCourses());
			
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
