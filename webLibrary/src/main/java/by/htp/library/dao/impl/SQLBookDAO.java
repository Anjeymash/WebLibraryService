package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;

public class SQLBookDAO implements BookDAO {

	private final static String SEL_BY_ID = "SELECT * FROM book WHERE (b_id=?)";
	private final static String SEL_BY_GENRE = "SELECT * FROM book WHERE (b_genre=?) AND (b_status=1)";
	private final static String SEL_BY_TITLE = "SELECT * FROM book WHERE (b_title LIKE ?)AND (b_status=1)";
	private final static String SEL_BY_AUTHOR = "SELECT * FROM book WHERE (b_author LIKE ?)AND (b_status=1)";
	private final static String SEL_BY_CONTEXT = "SELECT * FROM book WHERE (b_context LIKE ?)AND (b_status=1)";
	private static final String INSERT_BOOK = "INSERT INTO book (b_title, b_author, b_genre, b_year, b_quantity, b_status, b_context) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ID = "select last_insert_id() as last_id from book";
	private static final String UPDATE_BOOK = "UPDATE book SET b_title=?,b_author=?, b_genre=?, b_year=?, b_quantity=?, b_status=?, b_context=? WHERE b_id = ?";
	private static final String DEL_BOOK = "UPDATE book SET b_status = 0 WHERE b_id=?";
	private static final String SET_QUANT = "UPDATE book SET b_quantity =? WHERE b_id=?";

	// CRITERIA
	private static final String TITLE_CRIT = "title";
	private static final String AUTHOR_CRIT = "author";
	private static final String CONTEXT_CRIT = "context";

	private ConnectionPool conPool = ConnectionPool.getInstance();

	@Override
	public void deleteBook(long id) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(DEL_BOOK);
			ps.setLong(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("SQL error", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		}

		finally {
			conPool.closeConnection(con, ps);
		}
	}

	@Override
	public Book bookById(Long id) throws DAOException {
		Book book = null;
		Statement st = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = conPool.takeConnection();
			st = con.createStatement();
			PreparedStatement stmt = con.prepareStatement(SEL_BY_ID);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				book = new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7), rs.getString(8));
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		} catch (SQLException e) {
			throw new DAOException("SQL error", e);
		} finally {
			conPool.closeConnection(con, st, rs);
		}
		return book;
	}

	@Override
	public ArrayList<Book> listBook(String genre) throws DAOException {
		return src(genre, SEL_BY_GENRE);
	}

	@Override
	public ArrayList<Book> search(String searchParam, String criteria) throws DAOException {
		String expr = null;
		if (criteria.equals(TITLE_CRIT))
			expr = SEL_BY_TITLE;
		if (criteria.equals(AUTHOR_CRIT))
			expr = SEL_BY_AUTHOR;
		if (criteria.equals(CONTEXT_CRIT))
			expr = SEL_BY_CONTEXT;
		return src('%' + searchParam + '%', expr);

	}

	public ArrayList<Book> src(String searchParam, String expr) throws DAOException {
		ArrayList<Book> foundbooks = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			con = conPool.takeConnection();
			st = con.createStatement();
			ps = con.prepareStatement(expr);
			ps.setString(1, searchParam);
			rs = ps.executeQuery();
			while (rs.next()) {
				foundbooks.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		} catch (SQLException e) {
			throw new DAOException("SQL error", e);

		} finally {
			conPool.closeConnection(con, st, rs);
		}

		return foundbooks;

	}

	@Override
	public Long saveBook(Book book) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		Long id = 0L;

		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(INSERT_BOOK);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getGenre());
			ps.setString(4, book.getYear());
			ps.setInt(5, book.getQuantity());
			ps.setString(6, book.getStatus());
			ps.setString(7, book.getContext());
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
	public Long updateBook(Book book) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(UPDATE_BOOK);
			ps.setLong(8, book.getId());
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getGenre());
			ps.setString(4, book.getYear());
			ps.setInt(5, book.getQuantity());
			ps.setString(6, book.getStatus());
			ps.setString(7, book.getContext());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQL error", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		}

		finally {
			conPool.closeConnection(con, ps);
			System.out.println(book.toString());
		}
		return book.getId();
	}

	@Override
	public void bookIn(Long id) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		Book book;

		try {
			con = conPool.takeConnection();
			book = bookById(id);
			ps = con.prepareStatement(SET_QUANT);
			ps.setInt(1, book.getQuantity() - 1);
			ps.setLong(2, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("SQL error", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		} finally {
			conPool.closeConnection(con, ps);
		}

	}

}