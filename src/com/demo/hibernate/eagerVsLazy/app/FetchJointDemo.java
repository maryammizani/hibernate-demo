package com.demo.hibernate.eagerVsLazy.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.demo.hibernate.eagerVsLazy.entity.Course;
import com.demo.hibernate.eagerVsLazy.entity.Instructor;
import com.demo.hibernate.eagerVsLazy.entity.InstructorDetail;

public class FetchJointDemo {

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
			// This query will load instructor and courses all at once
			Query<Instructor> lQuery = lSession.createQuery("select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id=:lInstructorId",
					Instructor.class);
			
			// set parameter on query
			lQuery.setParameter("lInstructorId", lId);
			
			// Execute the query and get Instructor
			Instructor lInstructor = lQuery.getSingleResult();
			 System.out.println("Instructor: " + lInstructor);  
			 
			// Commit transaction
			lSession.getTransaction().commit();
			lSession.close();
						
			// get courses for the Instructor
			System.out.println("Courses: " + lInstructor.getCourses());						
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
