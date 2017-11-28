package by.htp.library.dao.impl;

import javax.servlet.ServletException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import by.htp.library.bean.User;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
/**
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class TestClientDAO {

	private DAOFactory factory = DAOFactory.getInstance();
	private UserDAO userDAO = factory.getUserDAO();
	private static final User DEFAULT_USER = new User(1L, "Anjey", "123", "Andrey", "Mash", "Minsk", "777777777",
			"admin", "a@jf.ru");
	private static final User TEST_USER = new User(0L, "TestUser", "TestUser", "TestUser", "TestUser", "TestUser",
			"111111111", "user", "test@user.ru");

	@Before
	public void init() throws ServletException, ConnectionPoolException {
		ConnectionPool conPool = ConnectionPool.getInstance();
		conPool.initPoolData();
	}
		
	@After
	public void destroy() {
		ConnectionPool conPool = ConnectionPool.getInstance();
		conPool.dispose();
	}
	@Test
	public void testSignUpDAO() throws DAOException {
		User user = userDAO.signIn("Anjey", "123");
		Assert.assertEquals(DEFAULT_USER, user);
	}
	@Test
	public void testFetchById() throws DAOException {
		User user = userDAO.fetchById(1L);
		Assert.assertEquals(1L, user.getId());
	}
	@Test
	public void testUpdateUser() throws DAOException {
		User updatedUser = DEFAULT_USER;
		updatedUser.seteMail("sss.@ss.ru");
		Long id = userDAO.updateUser(updatedUser);
		User user = userDAO.fetchById(id);
		Assert.assertEquals("sss.@ss.ru", user.geteMail());
	}
	@Test
	public void testsaveNewUser() throws DAOException {
		Long id = userDAO.saveNewUser(TEST_USER);
		User user = userDAO.fetchById(id);
		Assert.assertEquals(TEST_USER.getName(), user.getName());
	}
}
