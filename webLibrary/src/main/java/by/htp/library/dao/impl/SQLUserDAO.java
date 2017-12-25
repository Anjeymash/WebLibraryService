package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import by.htp.library.bean.User;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;

/**
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class SQLUserDAO implements UserDAO {

	private static final String UPDATE_USER = "UPDATE user SET u_login=?,u_password=?, u_name=?, u_surname=?, u_location=?, u_tel=?, u_status=?, u_email=? WHERE u_id = ?";
	private static final String INSERT_USER = "INSERT INTO user (u_login, u_password, u_name, u_surname, u_location, u_tel, u_status, u_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_USER = "select * from user where u_login = ? and u_password = ?";
	private static final String SELECT_BYID = "select * from user where u_id = ?";
	private static final String SELECT_ID = "select last_insert_id() as last_id from user";
	private static final String CHECK_EMAIL = "SELECT u_id FROM user WHERE u_email = ?";
	private static final String CHECK_LOGIN = "SELECT u_id FROM user WHERE u_login = ?";
	private static final int INDEX_ONE = 1;
	private static final int INDEX_TWO = 2;
	private static final int INDEX_THREE = 3;
	private static final int INDEX_FOUR = 4;
	private static final int INDEX_FIVE = 5;
	private static final int INDEX_SIX = 6;
	private static final int INDEX_SEVEN = 7;
	private static final int INDEX_EIGHT = 8;
	private static final int INDEX_NINE = 9;
	private static final String LAST_ID = "last_id";

	private ConnectionPool conPool = ConnectionPool.getInstance();

	/**
	 * The method returns the user-object by the pair login-password
	 */
	@Override
	public User signIn(String login, String password) throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);// start transaction
			try {
				st = con.createStatement();
				PreparedStatement stmt = con.prepareStatement(SELECT_USER);
				stmt.setString(INDEX_ONE, login);
				stmt.setString(INDEX_TWO, password);
				rs = stmt.executeQuery();

				while (rs.next()) {
					user = new User(rs.getLong(INDEX_ONE), rs.getString(INDEX_TWO), rs.getString(INDEX_THREE),
							rs.getString(INDEX_FOUR), rs.getString(INDEX_FIVE), rs.getString(INDEX_SIX),
							rs.getString(INDEX_SEVEN), rs.getString(INDEX_EIGHT), rs.getString(INDEX_NINE));
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
		return user;
	}

	/**
	 * The method returns the user by id
	 */
	@Override
	public User fetchById(Long id) throws DAOException {
		Statement st = null;
		Connection con = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);// start transaction
			try {
				st = con.createStatement();
				PreparedStatement stmt = con.prepareStatement(SELECT_BYID);
				stmt.setLong(INDEX_ONE, id);
				rs = stmt.executeQuery();
				while (rs.next()) {
					user = new User(rs.getLong(INDEX_ONE), rs.getString(INDEX_TWO), rs.getString(INDEX_THREE),
							rs.getString(INDEX_FOUR), rs.getString(INDEX_FIVE), rs.getString(INDEX_SIX),
							rs.getString(INDEX_SEVEN), rs.getString(INDEX_EIGHT), rs.getString(INDEX_NINE));
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
		return user;
	}

	/**
	 * The method returns the id of the updated user
	 */
	@Override
	public Long updateUser(User user) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(UPDATE_USER);
			ps.setLong(INDEX_NINE, user.getId());
			ps.setString(INDEX_ONE, user.getLogin());
			ps.setString(INDEX_TWO, user.getPassword());
			ps.setString(INDEX_THREE, user.getName());
			ps.setString(INDEX_FOUR, user.getSurname());
			ps.setString(INDEX_FIVE, user.getLocation());
			ps.setString(INDEX_SIX, user.getTel());
			ps.setString(INDEX_SEVEN, user.getRole());
			ps.setString(INDEX_EIGHT, user.geteMail());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQL error", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		} finally {
			conPool.closeConnection(con, ps);
		}
		return user.getId();
	}

	/**
	 * The method returns the id of the new user
	 */
	@Override
	public Long saveNewUser(User user) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		Long id = 0L;
		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);// start transaction
			try {
				ps = con.prepareStatement(INSERT_USER);
				ps.setString(INDEX_ONE, user.getLogin());
				ps.setString(INDEX_TWO, user.getPassword());
				ps.setString(INDEX_THREE, user.getName());
				ps.setString(INDEX_FOUR, user.getSurname());
				ps.setString(INDEX_FIVE, user.getLocation());
				ps.setInt(INDEX_SIX, Integer.parseInt(user.getTel()));
				ps.setString(INDEX_SEVEN, user.getRole());
				ps.setString(INDEX_EIGHT, user.geteMail());
				ps.executeUpdate();
				st = con.createStatement();
				rs = st.executeQuery(SELECT_ID);
				while (rs.next()) {
					id = rs.getLong(LAST_ID);
				}
				con.commit();

			} catch (SQLException e) {
				con.rollback();
			} finally {
				con.setAutoCommit(true);
			} // close transaction

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("SQL connection error", e);
		} finally {
			conPool.closeConnection(con, ps, rs);
		}
		return id;
	}

	@Override
	public boolean checkEmail(String email) throws DAOException {
		boolean isValid = false;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(CHECK_EMAIL);
			ps.setString(INDEX_ONE, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				isValid = true;
			}
		} catch (SQLException e) {
			throw new DAOException("fail in checkEmail(String email)", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);

		} finally {
			conPool.closeConnection(con, ps, rs);
		}
		return isValid;

	}
	@Override
	public boolean checkLogin(String login) throws DAOException {
		boolean isValid = false;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(CHECK_LOGIN);
			ps.setString(INDEX_ONE, login);
			rs = ps.executeQuery();
			if (rs.next()) {
				isValid = true;
			}
		} catch (SQLException e) {
			throw new DAOException("fail in checkEmail(String email)", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);

		} finally {
			conPool.closeConnection(con, ps, rs);
		}
		return isValid;

	}
}
