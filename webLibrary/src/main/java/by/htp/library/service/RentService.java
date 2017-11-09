package by.htp.library.service;

import java.util.ArrayList;

import by.htp.library.bean.Book;
import by.htp.library.bean.Rent;
import by.htp.library.service.exception.ServiceException;

public interface RentService {
Long saveRent(Rent rent)throws ServiceException;
Rent RentById(Long userId, Long bookId)throws ServiceException;
ArrayList<Book> listUsersBooks(Long UserId)throws ServiceException;
ArrayList<Rent> listRent(Long UserId)throws ServiceException;
}
