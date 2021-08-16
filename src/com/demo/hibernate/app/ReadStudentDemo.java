package com.demo.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		// Create Session Factory
		SessionFactory lFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
						
		// Create a Session
		Session lSession = lFactory.getCurrentSession();
		try {		
			// Create a student Object
			System.out.println("Creating new student object");
			Student lStudent = new Student("D", "Duck", "d@demo.com");
			
			// Start a transaction
			lSession.beginTransaction();
			
			// Save the student Object
			System.out.println("Saving the student");
			System.out.println(lStudent);
			lSession.save(lStudent);
			
			// Commit transaction
			lSession.getTransaction().commit();			
			System.out.println("Saved Student. Generated id: " + lStudent.getId());
			
			// Get a new Session
			lSession = lFactory.getCurrentSession();
			lSession.beginTransaction();
			
			// Retrieve the student based in the id: primary key
			System.out.println("\nGetting Student with id: " + lStudent.getId());
			lStudent = lSession.get(Student.class, lStudent.getId());	
			System.out.println("Get Complete: " + lStudent);
			
			// Commit transaction
			lSession.getTransaction().commit();
			System.out.println("Done");
		}
		finally {
			lFactory.close();
		}
	}
}
