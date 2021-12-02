package com.demo.hibernate.oneToManyUni.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.oneToManyUni.entity.Course;
import com.demo.hibernate.oneToManyUni.entity.Instructor;
import com.demo.hibernate.oneToManyUni.entity.InstructorDetail;
import com.demo.hibernate.oneToManyUni.entity.Review;

public class CreateCourseAndReviewsDemo {

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
			
			// Create a Course
			Course lCourse = new Course("Guitar");
			
			// Add some reviews
			lCourse.adReviews(new Review("Great Course"));
			lCourse.adReviews(new Review("cool Course"));
			lCourse.adReviews(new Review("dumb Course"));
			
			// Save course ... and leverage the cascade all
			System.out.println("Saving the course: " + lCourse);
			System.out.println("Reviews: " + lCourse.getReviews());
			lSession.save(lCourse);
			
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
