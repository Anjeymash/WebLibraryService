package by.htp.library.dao.impl;

import java.util.Date;
import javax.servlet.ServletException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import by.htp.library.bean.Rent;
import by.htp.library.dao.RentDAO;
import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
/**
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class TestRentDAO {
	private DAOFactory factory = DAOFactory.getInstance();
	private RentDAO rentDAO = factory.getRentDAO();
	@SuppressWarnings("deprecation")
	private static final Rent DEFAULT_RENT = new Rent(0L, 1L, 1L, new Date(2001, 01, 01), new Date(2000, 01, 01), 0);
	
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
	public void testRentById() throws DAOException {
		Rent rent = rentDAO.RentById(2L);
		Assert.assertEquals(2L, rent.getRentId());
	}
	@Test
	public void testSaveRent() throws DAOException {
		Long id = rentDAO.saveRent(DEFAULT_RENT);
		Rent rent = rentDAO.RentById(id);
		Assert.assertEquals(DEFAULT_RENT.getUserId(), rent.getUserId());
		Assert.assertEquals(DEFAULT_RENT.getBookId(), rent.getBookId());
		Assert.assertEquals(DEFAULT_RENT.getStart(), rent.getStart());
		Assert.assertEquals(DEFAULT_RENT.getEnd(), rent.getEnd());
		Assert.assertEquals(DEFAULT_RENT.getStatus(), rent.getStatus());
	}
	
	
	
}
