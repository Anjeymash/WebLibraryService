package by.htp.library.bean;

import java.io.Serializable;

/**
 * Entity class serves for pagination of search results
 * 
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class Pagination implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int limit;
	private int records;
	private double lim;
	private final static int LINES_LIMIT = 5;
	private final static int START_PAGE_N = 0;
	private final static int FIRST_PAGE_N = 1;

	public Pagination(int currentPage, int records) {
		this.currentPage = currentPage;
		this.records = records;
	}

	public int getOffset() {
		if (currentPage == START_PAGE_N)
			return FIRST_PAGE_N;
		return (currentPage - 1) * LINES_LIMIT;
	}

	public int getNextPage() {
		return currentPage++;
	}

	public int getLimit() {
		lim = (double) records / LINES_LIMIT;
		this.limit = (int) Math.ceil(lim);
		return limit;
	}

	public static int getLinesLimit() {
		return LINES_LIMIT;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPage;
		result = prime * result + records;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagination other = (Pagination) obj;
		if (currentPage != other.currentPage)
			return false;
		if (records != other.records)
			return false;
		return true;
	}
}