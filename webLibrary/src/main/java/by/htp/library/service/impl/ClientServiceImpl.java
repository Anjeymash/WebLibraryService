package by.htp.library.service.impl;

import by.htp.library.bean.User;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
import by.htp.library.service.ClientService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.validation.ValidationData;
/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public class ClientServiceImpl implements ClientService {
	private static final int newUserId = 0;
	/**
	 * The method returns the user-object by the pair login-password
	 */
	@Override
	public User singIn(String login, String password) throws ServiceException {
		if (!ValidationData.validLogin(login) || !ValidationData.validPassword(password)) {
			throw new ServiceException("Incorrect input login or password");
		}

		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoObjectFactory.getUserDAO();
		try {
			return userDAO.signIn(login, password);
		} catch (DAOException e) {
			throw new ServiceException("DAOException in singIn ", e);
		}
	}
	/**
	 * The method returns the user by id
	 */
	@Override
	public User fetchById(Long id) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoObjectFactory.getUserDAO();
		try {
			return userDAO.fetchById(id);
		} catch (DAOException e) {
			throw new ServiceException("DAOException in fetchById ", e);
		}
	}
	/**
	 * The method returns the id of the updated user
	 */
	@Override
	public Long saveUser(User user) throws ServiceException {
		if (!ValidationData.validUser(user)) {
			throw new ServiceException("Incorrect input data");
		}
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoObjectFactory.getUserDAO();
		try {
			if (user.getId() == newUserId) {
				return userDAO.saveNewUser(user);
			} else {
				return userDAO.updateUser(user);
			}
		} catch (DAOException e) {
			throw new ServiceException("DAOException in saveUser ", e);
		}
	}
}
