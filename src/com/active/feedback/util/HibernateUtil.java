package com.active.feedback.util;

import org.hibernate.SessionFactory; 
import org.hibernate.cfg.Configuration; 
import org.hibernate.Session;


public class HibernateUtil {
	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public static final String test = "test Hibernate";
	
	/**
	  * getSession()
	  * @return Session object
	  * @throws HibernateException
	  */
	public static Session getSession(){
		Session session = null;
		try{
			session = sessionFactory.openSession();
		}catch(Exception e){
			System.out.println("sfsfsafsddddddddddddddddddddddddddddddd");
			System.out.println("Exception occurs when start session,the cause is:");
			e.printStackTrace();
		}
		return session;
	}

	
	/**
	  * closeSession
	  * @param session The Session object to be closed
	*/
	public static void closeSession(Session session){
		try{
			if(null != session)
			session.close();
		}catch(Exception e){
			System.out.println("Exception occurs when close session,the cause is:");
			e.printStackTrace();
		}
	}

}
