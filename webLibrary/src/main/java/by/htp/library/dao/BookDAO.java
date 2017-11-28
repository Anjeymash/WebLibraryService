package by.htp.library.dao;

import java.util.ArrayList;

import by.htp.library.bean.Book;
import by.htp.library.dao.exception.DAOException;
/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public interface BookDAO {
        void deleteBook(long id) throws DAOException;
        Book bookById(Long id)throws DAOException;
		ArrayList<Book> listBook(String genre) throws DAOException;
		ArrayList<Book> search(String searchParam, String criteria)throws DAOException;
		Long saveBook(Book book)throws DAOException;
		Long updateBook(Book book)throws DAOException;
		void bookIn(Long id)throws DAOException;
		

}