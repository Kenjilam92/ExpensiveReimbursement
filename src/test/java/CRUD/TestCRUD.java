package CRUD;
import ServicesImpl.*;
import tools.*;
import Models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*; 

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCRUD{
	private UserServicesImpl userSv = UserServicesImpl.getInstance();
	private EmployeeServicesImpl empSv = EmployeeServicesImpl.getInstance();
	private ManagerServicesImpl manSv = ManagerServicesImpl.getInstance();
	
//	@BeforeAll
//	void greeting() {
//		System.out.println("Begin testing! ");
//	}
//	
//	@BeforeEach
//	void border() {
//		System.out.println("*******************");
//	}
	
	@Test
	@Order(1)
	void registerUser () {
		User u = new User("john", "john@gmail.com", "1234");
		assertEquals(userSv.register(u),true);
	}
	
	@Test
	@Order(2)
	void uniqueUserName () {
		User u = new User("joh", "john@gmail.com", "1234");
		assertEquals(userSv.register(u),false);
	}
	
	@Test
	@Order(3)
	void uniqueEmail () {
		User u = new User("john", "joh@gmail.com", "1234");
		assertEquals(userSv.register(u),false);
	}
	
	@Test
	@Order(4)
	void loginUser() {
		User u = userSv.login("john", "1234");
		assertEquals(u.getEmail(),"john@gmail.com");
	}
	
	@Test
	@Order(5)
	void updateUser () {
		User select = userSv.login("john", "1234");
//
		select.setFirstName("John");
		select.setLastName("Doe");
//		System.out.println(select);
		assertEquals(userSv.updateProfile(select),true);
	}
	
	@Test
	@Order(6)
	void deleteUser () {
		User select = userSv.login("john", "1234");
		assertEquals(userSv.removeAccount(select),true);
	}
	@Test
	@Order(7)
	void deleteUserTest () {
		User select = userSv.login("joh", "1234");
		assertEquals(userSv.removeAccount(select),false);
	}
	@Test
	@Order(8)
	void deleteUserTest2 () {
		User select = userSv.login("joh@gmail.com", "1234");
		assertEquals(userSv.removeAccount(select),false);
	}
}
