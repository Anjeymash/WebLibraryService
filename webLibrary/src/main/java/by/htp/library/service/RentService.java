package by.htp.library.service;

import java.util.ArrayList;

import by.htp.library.bean.Book;
import by.htp.library.bean.Rent;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.exception.ServiceExceptionValid;
/**
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public interface RentService {
Long saveRent(Rent rent)throws ServiceException, ServiceExceptionValid;
ArrayList<Book> listUsersBooks(Long UserId)throws ServiceException;
ArrayList<Rent> listRent(Long UserId)throws ServiceException;
Rent RentById(Long rentId) throws ServiceException;
}
