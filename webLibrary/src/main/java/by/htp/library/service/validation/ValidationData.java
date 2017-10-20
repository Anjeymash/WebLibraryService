package by.htp.library.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.library.bean.Book;
import by.htp.library.bean.User;
import by.htp.library.service.exception.ServiceException;

public class ValidationData {

	private static final Pattern P_EMAIL = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
	private static final Pattern P_TELNUMBER = Pattern.compile("^(\\d{9})$");
	private static final Pattern P_LOCATION = Pattern.compile("^[A-z0-9-]+$");
	private static final Pattern P_LOGIN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
	private static final Pattern P_PASSWORD = Pattern.compile("^[a-z0-9-]+$");
	private static final Pattern P_NAME = Pattern.compile("^[a-zA-Z]{1,20}$");
	private static final Pattern P_YEAR = Pattern.compile("^(\\d{4})$");
	private static final Pattern P_TITLE = Pattern.compile("^[A-z0-9-]+$");
	private static final Pattern P_AUTHOR = Pattern.compile("^[A-z0-9-]+$");
	private static final Pattern P_GENRE = Pattern.compile("(scifi|novels|children|adventures)");
	private static final Pattern P_STATUS = Pattern.compile("0|1");

	public static boolean validUser(User user) throws ServiceException {
		try {
			if (validLogin(user.getLogin()) && validName(user.getName()) && validName(user.getSurname())
					&& validPassword(user.getPassword()) && validLocation(user.getLocation()) && validTel(user.getTel())
					&& validEmail(user.geteMail())) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException e) {
			throw new ServiceException("Incorrect input data");
		}
	}

	public static boolean validBook(Book book) throws ServiceException {
		try {
			if (validString(book.getTitle()) && validString(book.getAuthor()) && validYear(book.getYear())
					&& validGenre(book.getGenre()) && validQuantity(book.getQuantity()) && validStatus(book.getStatus())
					&& validString(book.getContext())) {
				return true;
			} else
				return false;

		} catch (NullPointerException e) {
			throw new ServiceException("Incorrect input data");
		}
	}

	public static boolean validTitle(String title) {
		Matcher m = P_TITLE.matcher(title);
		return m.matches() && validString(title);
	}

	public static boolean validAuthor(String author) {
		Matcher m = P_AUTHOR.matcher(author);
		return m.matches() && validString(author);
	}

	public static boolean validYear(String year) {
		Matcher m = P_YEAR.matcher(year);
		return m.matches() && validString(year);
	}

	public static boolean validQuantity(int quantity) {
		if ((quantity < 0) || (quantity > 99999)) {
			return false;
		}
		return true;
	}

	public static boolean validStatus(String status) {
		Matcher m = P_STATUS.matcher(status);
		return m.matches() && validString(status);
	}

	public static boolean validGenre(String genre) {
		Matcher m = P_GENRE.matcher(genre);
		return m.matches() && validString(genre);
	}

	public static boolean validContext(String context) {
		return validString(context);
	}

	public static boolean validLogin(String login) {
		Matcher m = P_LOGIN.matcher(login);
		return m.matches() && validString(login);
	}

	public static boolean validName(String name) {
		Matcher m = P_NAME.matcher(name);
		return m.matches() && validString(name);
	}

	public static boolean validPassword(String password) {
		Matcher m = P_PASSWORD.matcher(password);
		return m.matches() && validString(password);
	}

	public static boolean validEmail(String email) {
		Matcher m = P_EMAIL.matcher(email);
		return m.matches() && validString(email);
	}

	public static boolean validTel(String tel) {
		Matcher m = P_TELNUMBER.matcher(tel);
		return m.matches() && validString(tel);
	}

	public static boolean validLocation(String location) {
		Matcher m = P_LOCATION.matcher(location);
		return m.matches() && validString(location);
	}

	private static boolean validString(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
