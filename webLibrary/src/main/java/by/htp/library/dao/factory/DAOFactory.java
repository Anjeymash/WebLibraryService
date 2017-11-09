package by.htp.library.dao.factory;

import by.htp.library.dao.BookDAO;
import by.htp.library.dao.RentDAO;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.impl.SQLBookDAO;
import by.htp.library.dao.impl.SQLRentDAO;
import by.htp.library.dao.impl.SQLUserDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private final BookDAO sqlBookImpl = new SQLBookDAO();
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	private final RentDAO sqlRentImpl = new SQLRentDAO();

	public static DAOFactory getInstance() {
		return instance;
	}

	public BookDAO getBookDAO() {
		return sqlBookImpl;
	}

	public UserDAO getUserDAO() {
		return sqlUserImpl;
	}

	public RentDAO getRentDAO() {
		return sqlRentImpl;
	}
}
