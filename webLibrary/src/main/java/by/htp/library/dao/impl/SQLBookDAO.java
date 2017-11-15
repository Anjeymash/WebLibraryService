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

/**
 * @author Mashkouski Andrei
 * @version 1.0
 */
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
	private static final String LAST_ID = "last_id";
	private static final int INDEX_ONE = 1;
	private static final int INDEX_TWO = 2;
	private static final int INDEX_THREE = 3;
	private static final int INDEX_FOUR = 4;
	private static final int INDEX_FIVE = 5;
	private static final int INDEX_SIX = 6;
	private static final int INDEX_SEVEN = 7;
	private static final int INDEX_EIGHT = 8;

	// CRITERIA
	private static final String TITLE_CRIT = "title";
	private static final String AUTHOR_CRIT = "author";
	private static final String CONTEXT_CRIT = "context";

	private ConnectionPool conPool = ConnectionPool.getInstance();

	/**
	 * The method makes the book inactive
	 */
	@Override
	public void deleteBook(long id) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(DEL_BOOK);
			ps.setLong(INDEX_ONE, id);
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

	/**
	 * The method returns the book by id
	 */
	@Override
	public Book bookById(Long id) throws DAOException {
		Book book = null;
		Statement st = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);//start transaction
			try {
				st = con.createStatement();
				PreparedStatement stmt = con.prepareStatement(SEL_BY_ID);
				stmt.setLong(INDEX_ONE, id);
				rs = stmt.executeQuery();
				while (rs.next()) {
					book = new Book(rs.getLong(INDEX_ONE), rs.getString(INDEX_TWO), rs.getString(INDEX_THREE),
							rs.getString(INDEX_FOUR), rs.getString(INDEX_FIVE), rs.getInt(INDEX_SIX),
							rs.getString(INDEX_SEVEN), rs.getString(INDEX_EIGHT));
				}
				con.commit();
			} catch (SQLException e) {
				con.rollback();
			} finally {
				con.setAutoCommit(true);//close transaction
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("SQL error", e);

		} finally {
			conPool.closeConnection(con, st, rs);
		}
		return book;
	}

	/**
	 * The method returns the list of books
	 */
	@Override
	public ArrayList<Book> listBook(String genre) throws DAOException {
		return searchByExpression(genre, SEL_BY_GENRE);
	}

	/**
	 * The method returns the list of books by criteria and search-parameter
	 */
	@Override
	public ArrayList<Book> search(String searchParam, String criteria) throws DAOException {
		String expr = null;
		if (criteria.equals(TITLE_CRIT)) {
			expr = SEL_BY_TITLE;
		}
		if (criteria.equals(AUTHOR_CRIT)) {
			expr = SEL_BY_AUTHOR;
		}
		if (criteria.equals(CONTEXT_CRIT)) {
			expr = SEL_BY_CONTEXT;
		}
		return searchByExpression('%' + searchParam + '%', expr);

	}

	/**
	 * The method returns the list of books by expression
	 */
	public ArrayList<Book> searchByExpression(String searchParam, String expr) throws DAOException {
		ArrayList<Book> foundbooks = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);//start transaction
			try {
				st = con.createStatement();
				ps = con.prepareStatement(expr);
				ps.setString(INDEX_ONE, searchParam);
				rs = ps.executeQuery();

				while (rs.next()) {
					foundbooks.add(new Book(rs.getLong(INDEX_ONE), rs.getString(INDEX_TWO), rs.getString(INDEX_THREE),
							rs.getString(INDEX_FOUR), rs.getString(INDEX_FIVE), rs.getInt(INDEX_SIX),
							rs.getString(INDEX_SEVEN), rs.getString(INDEX_EIGHT)));
				}
				con.commit();
			} catch (SQLException e) {
				con.rollback();
			} finally {
				con.setAutoCommit(true);//close transaction
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("SQL  error", e);

		} finally {
			conPool.closeConnection(con, st, rs);
		}
		return foundbooks;
	}

	/**
	 * The method returns the id of the new book
	 */
	@Override
	public Long saveBook(Book book) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		Long id = 0L;

		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);//start transaction
			try {
				ps = con.prepareStatement(INSERT_BOOK);
				ps.setString(INDEX_ONE, book.getTitle());
				ps.setString(INDEX_TWO, book.getAuthor());
				ps.setString(INDEX_THREE, book.getGenre());
				ps.setString(INDEX_FOUR, book.getYear());
				ps.setInt(INDEX_FIVE, book.getQuantity());
				ps.setString(INDEX_SIX, book.getStatus());
				ps.setString(INDEX_SEVEN, book.getContext());
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
				con.setAutoCommit(true);//close transaction
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("SQL error", e);
		} finally {
			conPool.closeConnection(con, ps, rs);
		}
		return id;
	}

	/**
	 * The method returns the id of the updated book
	 */
	@Override
	public Long updateBook(Book book) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = conPool.takeConnection();
			ps = con.prepareStatement(UPDATE_BOOK);
			ps.setLong(INDEX_EIGHT, book.getId());
			ps.setString(INDEX_ONE, book.getTitle());
			ps.setString(INDEX_TWO, book.getAuthor());
			ps.setString(INDEX_THREE, book.getGenre());
			ps.setString(INDEX_FOUR, book.getYear());
			ps.setInt(INDEX_FIVE, book.getQuantity());
			ps.setString(INDEX_SIX, book.getStatus());
			ps.setString(INDEX_SEVEN, book.getContext());
			ps.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("SQL error", e);
		} finally {
			conPool.closeConnection(con, ps);
		}
		return book.getId();
	}

	/**
	 * The method reduces the amount of available books
	 */
	@Override
	public void bookIn(Long id) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		Book book;
		int newQuantity;

		try {
			con = conPool.takeConnection();
			book = bookById(id);
			ps = con.prepareStatement(SET_QUANT);
			newQuantity = book.getQuantity() - 1;
			ps.setInt(INDEX_ONE, newQuantity);
			ps.setLong(INDEX_TWO, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("SQL error", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("SQL connection error", e);
		} finally {
			conPool.closeConnection(con, ps);
		}

	}

}