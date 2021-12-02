package com.demo.hibernate.oneToManyUni.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.oneToManyUni.entity.Course;
import com.demo.hibernate.oneToManyUni.entity.Instructor;
import com.demo.hibernate.oneToManyUni.entity.InstructorDetail;
import com.demo.hibernate.oneToManyUni.entity.Review;

public class GetCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		// Create Session Factory
		SessionFactory lFactory = new Configuration()
				.configure("hibernate.oneToManyUniCfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
						
		// Create a Session
		Session lSession = lFactory.getCurrentSession();
		try {									
			// Start a transaction
			lSession.beginTransaction();
			
			// Get the Course with id=10
			int lId = 10;			
			Course lCourse = lSession.get(Course.class, lId);			
			System.out.println("Course: " + lCourse);
			System.out.println("Reviews: " + lCourse.getReviews());
			
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
