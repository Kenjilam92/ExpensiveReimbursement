package Database;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import Models.*;
import java.util.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;




public class Database {
	private static Database database = new Database();
	
	
	private SessionFactory sessionFactory;
	private Database() {
		final Configuration config = new Configuration().configure();
		final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(builder.build());
	}
	
	public static Database getDatabase ()  {
		return database;
	}
	
	
	///Show All elements
	
	public List<User> getAllUsers() throws Exception {
		List<User> users;
		try{
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			users = session.createCriteria(User.class).list();

			
			System.out.println(users.size());
			tx.commit();
			session.close();
		}
		catch (Exception e) {
			throw e;
		}
		return users;
	}
	
	public List<Mail> getAllMails(){
		List<Mail> mails = null;
		try{
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			mails = session.createCriteria(Mail.class).list();
			tx.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();;
		}
		return mails;
	}
	
	public List<Request> getAllRequest(){
		List<Request> requests = null;
		try{
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			requests = session.createCriteria(Request.class).list();
			tx.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return requests;
	}
	
	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> requests;
		try{
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			requests = session.createCriteria(Employee.class).list();
			tx.commit();
			session.close();
		}
		catch (Exception e) {
			throw e;
		}
		return requests;
	}
	
	public List<Manager> getAllManagers() throws Exception {
		List<Manager> requests;
		try{
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			requests = session.createCriteria(Manager.class).list();
			tx.commit();
			session.close();
		}
		catch (Exception e) {
			throw e;
		}
		return requests;
	}
	//// Add
	public Database add (User x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(x);
		tx.commit();
		session.close();
		return this;
	}
	public Database add (Employee x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(x);
		tx.commit();
		session.close();
		return this;
	}
	public Database add (Manager x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(x);
		tx.commit();
		session.close();
		return this;
	}
	public Database add (Mail x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(x);
		tx.commit();
		session.close();
		return this;
	}
	public Database add (Request x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(x);
		tx.commit();
		session.close();
		return this;
	}
	
	/// Update
	public Database update (User x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(x);
		tx.commit();
		session.close();
		return this;
	}
	
	public Database update (Employee x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(x);
		tx.commit();
		session.close();
		return this;
	}
	public Database update (Manager x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(x);
		tx.commit();
		session.close();
		return this;
	}
	public Database update (Mail x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(x);
		tx.commit();
		session.close();
		return this;
	}
	public Database update (Request x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(x);
		tx.commit();
		session.close();
		return this;
	}
	
	
	
	/// Delete
	public Database delete (User x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(x);
		tx.commit();
		session.close();
		return this;
	}
	
	public Database delete (Manager x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(x);
		tx.commit();
		session.close();
		return this;
	}
	
	public Database delete (Employee x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(x);
		tx.commit();
		session.close();
		return this;
	}
	
	public Database delete (Mail x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(x);
		tx.commit();
		session.close();
		return this;
	}
	
	public Database delete (Request x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(x);
		tx.commit();
		session.close();
		return this;
	}
	
//	private static <T> List<T> loadAllData(Class<T> type, Session session) {
//	    CriteriaBuilder builder = session.getCriteriaBuilder();
//	    CriteriaQuery<T> criteria = builder.createQuery(type);
//	    criteria.from(type);
//	    List<T> data = session.createQuery(criteria).getResultList();
//	    return data;
//	 }
//	
}
