package by.htp.library.dao;

import java.util.ArrayList;
import by.htp.library.bean.Book;
import by.htp.library.bean.Rent;
import by.htp.library.dao.exception.DAOException;


public interface RentDAO {
	Long saveRent(Rent rent)throws DAOException;
	Rent RentById(Long userId, Long bookId)throws DAOException;
	ArrayList<Book> listUsersBooks(Long UserId)throws DAOException;
	ArrayList<Rent> listRent(Long UserId)throws DAOException;
}
