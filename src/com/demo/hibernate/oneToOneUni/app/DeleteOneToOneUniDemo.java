package com.demo.hibernate.oneToOneUni.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.oneToOneUni.entity.Instructor;
import com.demo.hibernate.oneToOneUni.entity.InstructorDetail;

public class DeleteOneToOneUniDemo {

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
			
			// get Instructor by primary key / id
			int lId = 1;
			Instructor lInstructor = lSession.get(Instructor.class, lId);
			
			// Delete the instructor
			System.out.println("Found Instructor: " + lInstructor);
			if(lInstructor != null) {
				// This will also delete InstructorDetail because of CascadeType.ALL
				lSession.delete(lInstructor);
			}
						
			// Commit transaction
			lSession.getTransaction().commit();
			System.out.println("Done");
		}
		finally {
			lFactory.close();
		}
	}
}
