package by.htp.library.service.impl;

import java.util.ArrayList;

import by.htp.library.bean.Book;
import by.htp.library.bean.Rent;
import by.htp.library.dao.RentDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
import by.htp.library.service.RentService;
import by.htp.library.service.exception.ServiceException;

public class RentServiceImpl implements RentService  {

	@Override
	public Long saveRent(Rent rent) throws ServiceException {
		
		
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			RentDAO rentDAO = daoObjectFactory.getRentDAO();
			return rentDAO.saveRent(rent);
		} catch (DAOException e) {
			throw new ServiceException("DAOException in saveRent ", e);
		}
	
	}

	@Override
	public Rent RentById(Long userId, Long bookId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> listUsersBooks(Long UserId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Rent> listRent(Long UserId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
