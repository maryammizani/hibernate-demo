package com.demo.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// Create Session Factory
		SessionFactory lFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
						
		try {		
			int lStudentId = 1;
		
			// Get a new Session
			Session lSession = lFactory.getCurrentSession();
			lSession.beginTransaction();
			
			// Retrieve the student 
			System.out.println("\nGetting Student with id: " + lStudentId);
			Student lStudent = lSession.get(Student.class, lStudentId);	
			System.out.println("Updating Student ... ");
			lStudent.setFirstName("Scooby");
			
			// Commit transaction
			lSession.getTransaction().commit();
			
			// Get a new Session
			lSession = lFactory.getCurrentSession();
			lSession.beginTransaction();
			
			// Update the email of all the students
			System.out.println("\nUpdate the email of all the students");
			lSession.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			// Commit transaction
			lSession.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			lFactory.close();
		}
	}
}
