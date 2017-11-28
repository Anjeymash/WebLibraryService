package by.htp.library.dao.impl;

import java.sql.Connection;
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

/**
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class SQLRentDAO implements RentDAO {
	private final static String SEL_BY_ID = "SELECT * FROM rent WHERE (r_id=?)";
	private static final String INSERT_RENT = "INSERT INTO rent (u_id, b_id, r_start, r_end, r_status) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_ID = "select last_insert_id() as last_id from rent";
	private static final int INDEX_ONE = 1;
	private static final int INDEX_TWO = 2;
	private static final int INDEX_THREE = 3;
	private static final int INDEX_FOUR = 4;
	private static final int INDEX_FIVE = 5;
	private static final int INDEX_SIX = 6;
	private static final String LAST_ID = "last_id";

	private ConnectionPool conPool = ConnectionPool.getInstance();

	/**
	 * The method saves the rent-object
	 */
	@Override
	public Long saveRent(Rent rent) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		Long id = 0L;

		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);// start transaction
			try {
				ps = con.prepareStatement(INSERT_RENT);
				ps.setLong(INDEX_ONE, rent.getUserId());
				ps.setLong(INDEX_TWO, rent.getBookId());
				ps.setDate(INDEX_THREE, convertUtilToSql(rent.getStart()));
				ps.setDate(INDEX_FOUR, convertUtilToSql(rent.getEnd()));
				ps.setInt(INDEX_FIVE, rent.getStatus());
				ps.executeUpdate();
				st = con.createStatement();
				rs = st.executeQuery(SELECT_ID);
				while (rs.next()) {
					id = rs.getLong(LAST_ID);
				}
			} catch (SQLException e) {
				con.rollback();
			} finally {
				con.setAutoCommit(true);// close transaction
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("SQL connection error", e);
		} finally {
			conPool.closeConnection(con, ps, rs);
		}
		return id;
	}

	/**
	 * The method returns the rent-object by id
	 */
	@Override
	public Rent RentById(Long rentId) throws DAOException {

		Rent rent = null;
		Statement st = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);// start transaction
			try {
				st = con.createStatement();
				PreparedStatement stmt = con.prepareStatement(SEL_BY_ID);
				stmt.setLong(INDEX_ONE, rentId);
				rs = stmt.executeQuery();
				while (rs.next()) {
					rent = new Rent(rs.getLong(INDEX_ONE), rs.getLong(INDEX_TWO), rs.getLong(INDEX_THREE),
							rs.getDate(INDEX_FOUR), rs.getDate(INDEX_FIVE), rs.getInt(INDEX_SIX));
				}
				con.commit();
			} catch (SQLException e) {
				con.rollback();
			} finally {
				con.setAutoCommit(true);// close transaction
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("SQL error", e);

		} finally {
			conPool.closeConnection(con, st, rs);
		}
		return rent;
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
