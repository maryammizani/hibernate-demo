package com.demo.hibernate.oneToOneUniApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Instructor;
import com.demo.hibernate.entity.InstructorDetail;
import com.demo.hibernate.entity.Student;

public class CreateOneToOneUniDemo {

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
			// Create the Objects
			Instructor lInstructor = new Instructor("Paul", "wall", "paul@demo.com");
			InstructorDetail lInstructorDetail = new InstructorDetail("SomeUrl", "hobby");		
						
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
		finally {
			lFactory.close();
		}
	}
}
