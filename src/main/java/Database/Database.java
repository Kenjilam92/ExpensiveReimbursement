package Database;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import Models.*;
import java.util.*;


public class Database {
	private static Database database = new Database();
	
	public List<User> users;
	public List<Employee> employees;
	public List<Manager> managers;
	public List<Mail> mails;
	public List<Request> requests;
	
	private SessionFactory sessionFactory;
	private Database(){
		final Configuration config = new Configuration().configure();
		final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(builder.build());
		refreshData();
	}
	
	private Database refreshData() {
		try {
			users = getAllUsers();
			employees = getAllEmployees();
			managers = getAllManagers();
			requests = getAllRequests();
			mails = getAllMails();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public static Database getDatabase ()  {
		return database;
	}
	
	
	///Show All elements
	
	private List<User> getAllUsers() throws Exception {
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
	
	private List<Mail> getAllMails(){
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
	
	private List<Request> getAllRequests(){
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
	
	private List<Employee> getAllEmployees() throws Exception {
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
	
	private List<Manager> getAllManagers() throws Exception {
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
		return refreshData();
	}
	public Database add (Employee x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	public Database add (Manager x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	public Database add (Mail x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	public Database add (Request x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	
	/// Update
	public Database update (User x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	
	public Database update (Employee x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	public Database update (Manager x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	public Database update (Mail x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	public Database update (Request x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(x);
		tx.commit();
		session.close();
		return refreshData();
	}
		
	/// Delete
	public Database delete (User x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	
	public Database delete (Manager x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	
	public Database delete (Employee x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	
	public Database delete (Mail x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	
	public Database delete (Request x) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(x);
		tx.commit();
		session.close();
		return refreshData();
	}
	
	
}
