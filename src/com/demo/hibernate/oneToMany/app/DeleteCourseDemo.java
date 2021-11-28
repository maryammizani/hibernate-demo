package com.demo.hibernate.oneToMany.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.oneToMany.entity.Course;
import com.demo.hibernate.oneToMany.entity.Instructor;
import com.demo.hibernate.oneToMany.entity.InstructorDetail;

public class DeleteCourseDemo {

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
			
			// Get a Course
			int lId = 10;
			Course lCourse = lSession.get(Course.class, lId);			
			
			// Delete Course
			System.out.println("Deleting Course: " + lCourse);
			lSession.delete(lCourse);
			
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
