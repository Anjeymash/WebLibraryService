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

public class SQLUserDAO implements UserDAO {

	private static final String UPDATE_USER = "UPDATE user SET u_login=?,u_password=?, u_name=?, u_surname=?, u_location=?, u_tel=?, u_status=?, u_email=? WHERE u_id = ?";
	private static final String INSERT_USER = "INSERT INTO user (u_login, u_password, u_name, u_surname, u_location, u_tel, u_status, u_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_USER = "select * from user where u_login = ? and u_password = ?";
	private static final String SELECT_BYID = "select * from user where u_id = ?";
	private static final String SELECT_ID = "select last_insert_id() as last_id from user";

	private ConnectionPool conPool = ConnectionPool.getInstance();

	@Override
	public User signIn(String login, String password) throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = conPool.takeConnection();
			st = con.createStatement();
			PreparedStatement stmt = con.prepareStatement(SELECT_USER);
			stmt.setString(1, login);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			while (rs.next())
				user = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));

		} catch (SQLException e) {
			throw new DAOException("SQL error", e);

		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		} finally {
			conPool.closeConnection(con, st, rs);
		}
		return user;
	}

	
	@Override
	public User fetchById(Long id) throws DAOException {
		Statement st = null;
		Connection con = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = conPool.takeConnection();
			st = con.createStatement();
			PreparedStatement stmt = con.prepareStatement(SELECT_BYID);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("SQL error", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		} finally {
			conPool.closeConnection(con, st, rs);
		}
		return user;
	}

	@Override
	public Long updateUser(User user) throws DAOException {

		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(UPDATE_USER);
			ps.setLong(9, user.getId());
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getSurname());
			ps.setString(5, user.getLocation());
			ps.setString(6, user.getTel());
			ps.setString(7, user.getRole());
			ps.setString(8, user.geteMail());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQL error", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		} finally {
			conPool.closeConnection(con, ps);
			System.out.println(user.getName());
		}
		return user.getId();
	}

	@Override
	public Long saveNewUser(User user) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		Long id = 0L;
		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(INSERT_USER);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getSurname());
			ps.setString(5, user.getLocation());
			ps.setInt(6, Integer.parseInt(user.getTel()));
			ps.setString(7, user.getRole());
			ps.setString(8, user.geteMail());
			ps.executeUpdate();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_ID);
			while (rs.next())
				id = rs.getLong("last_id");

		} catch (SQLException e) {
			throw new DAOException("SQL error", e);

		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		}

		finally {
			conPool.closeConnection(con, ps, rs);

		}
		return id;
	}

}
