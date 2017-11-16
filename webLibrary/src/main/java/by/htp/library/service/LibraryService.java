package by.htp.library.service;

import java.util.ArrayList;

import by.htp.library.bean.Book;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.exception.ServiceExceptionValid;

public interface LibraryService {
void delBook(long bookID) throws ServiceException;
ArrayList<Book> search(String searchparam, String criteria) throws ServiceException;
Book bookById(Long id)throws ServiceException;
ArrayList<Book> listBook(String genre)throws ServiceException;
Long saveBook(Book book)throws ServiceException, ServiceExceptionValid;
Book bookIn(Long id)throws ServiceException;


}
