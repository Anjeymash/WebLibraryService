package by.htp.library.dao;

import by.htp.library.bean.User;
import by.htp.library.dao.exception.DAOException;

/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public interface UserDAO {
	User signIn(String login, String password) throws DAOException;
	User fetchById(Long id) throws DAOException;
	Long updateUser(User user) throws DAOException;
	Long saveNewUser(User user) throws DAOException;
}
