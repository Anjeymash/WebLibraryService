package by.htp.library.service;

import by.htp.library.bean.User;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.exception.ServiceExceptionValid;

public interface ClientService {
User singIn(String login, String password) throws ServiceException, ServiceExceptionValid;
User fetchById(Long id) throws ServiceException;
Long saveUser(User user)throws ServiceException, ServiceExceptionValid;
}
