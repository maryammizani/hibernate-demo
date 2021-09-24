package com.demo.hibernate.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// Create Session Factory
		SessionFactory lFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
						
		// Create a Session
		Session lSession = lFactory.getCurrentSession();
		try {			
			// Start a transaction
			lSession.beginTransaction();
			
			// Query Student
			List<Student> lStudents = lSession.createQuery("from Student").getResultList();
			displayStudents(lStudents);
			
			// Query Students where lastName='Doe'
			lStudents = lSession.createQuery("from Student s where s.lastName='Doe'").getResultList();
			System.out.print("\n\nStudents who have last name of Doe\n");
			displayStudents(lStudents);
			
			// Query Students where lastName='Doe' or firstName='Mary'
			lStudents = lSession.createQuery("from Student s where"
				+ " s.lastName='Doe' OR s.firstName='Mary'").getResultList();
			System.out.print("\n\nStudents who have last name of Doe or First name of Mary\n");
			displayStudents(lStudents);
									
			// Query Students where email LIKE '%demo.com'
			lStudents = lSession.createQuery("from Student s where"
				+ " s.email LIKE '%demo.com'").getResultList();
			System.out.print("\n\nStudents who their emails ends with '%demo.com'\n");
			displayStudents(lStudents);
						
			// Commit transaction
			lSession.getTransaction().commit();
			System.out.println("Done");
		}
		finally {
			lFactory.close();
		}
	}

	private static void displayStudents(List<Student> iStudents) {
		for(Student lStudent: iStudents) {
			System.out.println(lStudent);
		}
	}
}
