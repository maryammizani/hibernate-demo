package com.demo.hibernate.oneToMany.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.oneToMany.entity.Course;
import com.demo.hibernate.oneToMany.entity.Instructor;
import com.demo.hibernate.oneToMany.entity.InstructorDetail;

public class CreateOneToManyDemo {

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
			
			// Create the Objects
			Instructor lInstructor = new Instructor("Susan", "public", "susan@demo.com");
			InstructorDetail lInstructorDetail = new InstructorDetail("SomeUrl2", "hobby2");		
						
			// Associate the objects
			lInstructor.setInstructorDetail(lInstructorDetail);
			
			// Start a transaction
			lSession.beginTransaction();
			
			// Save the Instructor (This will also save the InstructorDetails because of Cascade.ALL)
			System.out.println("Saving the Instructor: " + lInstructor);
			lSession.save(lInstructor);
			
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
