package a8.exceptions;

import org.springframework.dao.DataAccessException;

public class DummyDataAccessException extends DataAccessException{

	private static final long serialVersionUID = -5034375333189030148L;

	public DummyDataAccessException(String msg) {
		super(msg);
	}
}
