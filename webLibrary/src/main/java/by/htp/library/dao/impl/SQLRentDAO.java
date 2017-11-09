package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.htp.library.bean.Book;
import by.htp.library.bean.Rent;
import by.htp.library.dao.RentDAO;
import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;

public class SQLRentDAO implements RentDAO {
	private static final String INSERT_RENT = "INSERT INTO rent (u_id, b_id, r_start, r_end, r_status) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_ID = "select last_insert_id() as last_id from rent";

	private ConnectionPool conPool = ConnectionPool.getInstance();

	@Override
	public Long saveRent(Rent rent) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		Long id = 0L;
		System.out.println(rent.getStart());
		System.out.println(rent.getEnd());
		System.out.println(rent.getBookId());
		System.out.println(rent.getUserId());
		System.out.println(rent.getStatus());

		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(INSERT_RENT);
			ps.setLong(1, rent.getUserId());
			ps.setLong(2, rent.getBookId());
			ps.setDate(3, convertUtilToSql(rent.getStart()));
			ps.setDate(4, convertUtilToSql(rent.getEnd()));
			ps.setInt(5, rent.getStatus());
			ps.executeUpdate();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_ID);
			 while (rs.next())
			 id = rs.getLong("last_id");

		}

		catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		} catch (SQLException e) {
			throw new DAOException("SQL error", e);
		}

		finally {
			conPool.closeConnection(con, ps, rs);

		}
		return id;
	}

	@Override
	public Rent RentById(Long userId, Long bookId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> listUsersBooks(Long UserId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Rent> listRent(Long UserId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;

	}

}
