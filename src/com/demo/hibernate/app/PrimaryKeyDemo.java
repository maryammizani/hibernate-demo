package com.demo.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// Create Session Factory
		SessionFactory lFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
						
		// Create a Session
		Session lSession = lFactory.getCurrentSession();
		try {		
			// Create 3 student Objects
			System.out.println("Create 3 student objects");
			Student lStudent1 = new Student("J", "Doe", "j@demo.com");
			Student lStudent2 = new Student("Mary", "p", "mary@demo.com");
			Student lStudent3 = new Student("Bonita", "q", "bonita@demo.com");
			
			// Start a transaction
			lSession.beginTransaction();
			
			// Save the student Object
			System.out.println("Saving the student");
			lSession.save(lStudent1);
			lSession.save(lStudent2);
			lSession.save(lStudent3);
			
			// Commit transaction
			lSession.getTransaction().commit();
			System.out.println("Done");
		}
		finally {
			lFactory.close();
		}
	}
}
