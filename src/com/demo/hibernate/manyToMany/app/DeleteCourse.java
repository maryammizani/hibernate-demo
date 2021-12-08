package com.demo.hibernate.manyToMany.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.manyToMany.entity.Course;
import com.demo.hibernate.manyToMany.entity.Instructor;
import com.demo.hibernate.manyToMany.entity.InstructorDetail;
import com.demo.hibernate.manyToMany.entity.Review;
import com.demo.hibernate.manyToMany.entity.Student;

public class DeleteCourse {

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
			
			// Get the course from DB
			int lCourseId = 11;
			Course lCourse = lSession.get(Course.class,  lCourseId);
			System.out.println("\nDeleting Course: " + lCourse);
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
