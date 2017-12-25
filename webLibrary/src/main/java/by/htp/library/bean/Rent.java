package by.htp.library.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity class serves to store rent-objects with properties
 * 
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class Rent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long rentId;
	private long bookId;
	private long userId;
	private Date start;
	private Date end;
	private int status;

	public Rent() {
		super();
	}

	/**
	 * Entity class-constructor for rent-objects initializing
	 */
	public Rent(long rentId, long bookId, long userId, Date start, Date end, int status) {
		super();
		this.rentId = rentId;
		this.bookId = bookId;
		this.userId = userId;
		this.start = start;
		this.end = end;
		this.status = status;
	}

	public long getRentId() {
		return rentId;
	}

	public void setRentId(long rentId) {
		this.rentId = rentId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bookId ^ (bookId >>> 32));
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + (int) (rentId ^ (rentId >>> 32));
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + status;
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		Rent other = (Rent) obj;
		if (bookId != other.bookId)
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (rentId != other.rentId)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (status != other.status)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rent [rentId=" + rentId + ", bookId=" + bookId + ", userId=" + userId + ", start=" + start + ", end="
				+ end + ", status=" + status + "]";
	}

}
