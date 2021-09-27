package com.demo.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class DeleteStudentDemo {

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
			System.out.println("Deleting Student: "+ lStudent);
			lSession.delete(lStudent);
			
			// Delete Student where id=2
			lSession.createQuery("delete from Student where id=2").executeUpdate();
			
			// Commit transaction
			lSession.getTransaction().commit();
							
			System.out.println("Done");
		}
		finally {
			lFactory.close();
		}
	}
}
