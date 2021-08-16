package com.demo.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// Create Session Factory
		SessionFactory lFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
						
		// Create a Session
		Session lSession = lFactory.getCurrentSession();
		try {
			// Use the session object to save the Java Object
			
			// Create a student Object
			System.out.println("Creating new student object");
			Student lStudent = new Student("Paul", "wall", "paul@demo.com");
			
			// Start a transaction
			lSession.beginTransaction();
			
			// Save the student Object
			System.out.println("Saving the student");
			lSession.save(lStudent);
			
			// Commit transaction
			lSession.getTransaction().commit();
			System.out.println("Done");
		}
		finally {
			lFactory.close();
		}

	}

}
