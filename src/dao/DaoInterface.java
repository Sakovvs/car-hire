package dao;

import java.sql.SQLException;

public interface DaoInterface<T> {
	public abstract void insert(T ob) throws SQLException;

	public abstract void update(T ob)throws SQLException;

	public abstract void delete(T ob)throws SQLException;

}
