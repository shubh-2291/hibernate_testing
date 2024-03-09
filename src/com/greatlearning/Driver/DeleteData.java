package com.greatlearning.Driver;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.greatlearning.entity.Address;
import com.greatlearning.entity.Student;


public class DeleteData {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.addAnnotatedClass(Address.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			int theStudentId=1;
			
			// start transaction
			session.beginTransaction();

			Student tempStudent = session.get(Student.class,theStudentId);
			
			if(tempStudent!=null) {
				System.out.println("Deleting : "+ tempStudent);
				
				//Note!! : it will also delete TeacherDetails data 
			    //         as we have provided CascadeType.ALL
				session.delete(tempStudent);
			}
			
			

			// commit transaction
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}
}