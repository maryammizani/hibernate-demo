package com.demo.hibernate.oneToOneBi.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.oneToOneBi.entity.Instructor;
import com.demo.hibernate.oneToOneBi.entity.InstructorDetail;


public class GetOneToOneBiDemo {

	public static void main(String[] args) {
		
		// Create Session Factory
		SessionFactory lFactory = new Configuration()
				.configure("hibernate.oneToOneUniCfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
						
		// Create a Session
		Session lSession = lFactory.getCurrentSession();
		try {									
			
			// Start a transaction
			lSession.beginTransaction();
			
			// get and print Instructor detail object
			int lId = 22;
			InstructorDetail lInstructorDetail = lSession.get(InstructorDetail.class, lId);
			System.out.println("Found InstructorDetail: " + lInstructorDetail);
			
			// print the associated Instructor
			System.out.println("The associated Instructor: " + lInstructorDetail.getInstructor());
						
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
