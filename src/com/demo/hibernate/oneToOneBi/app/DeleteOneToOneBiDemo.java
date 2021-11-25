package com.demo.hibernate.oneToOneBi.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.oneToOneBi.entity.Instructor;
import com.demo.hibernate.oneToOneBi.entity.InstructorDetail;


public class DeleteOneToOneBiDemo {

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
			int lId = 3;
			InstructorDetail lInstructorDetail = lSession.get(InstructorDetail.class, lId);
			System.out.println("InstructorDetail: " + lInstructorDetail);
			
			// print the associated Instructor
			System.out.println("The associated Instructor: " + lInstructorDetail.getInstructor());
						
			// Delete the instructor detail
			System.out.println("Delete Instructor detail: " + lInstructorDetail);
			
			// First set the lInstructor to null, and then delete the lInstructorDetail
			lInstructorDetail.getInstructor().setInstructorDetail(null);
			lSession.delete(lInstructorDetail);  // This will also delete the Instructor	
			
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
