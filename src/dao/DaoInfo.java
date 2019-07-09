package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import entity.Info;

public class DaoInfo implements DaoInterface<Info> {
	private DB db;

	public DaoInfo(DB db) {
		this.db = db;
	}
	@Override
	public void insert(Info ob) throws SQLException {
		
			PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO " + ob.getClass().getSimpleName() + " VALUES(?,?,?,?,?,?);");

			ps.setInt(1, ob.getId_info());
			ps.setInt(2, ob.getId_user());
			ps.setString(3, ob.getSurname());
			ps.setString(4, ob.getName());
			ps.setString(5, ob.getPassport());
			ps.setString(6, ob.getTel());

			ps.execute();
		

	}

	@Override
	public void update(Info ob) throws SQLException {
		
			PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName() + ""
					+ " SET surname=?, name=?, passport=?, tel=? WHERE id_info=" + ob.getId_info());

			ps.setString(1, ob.getSurname());
			ps.setString(2, ob.getName());
			ps.setString(3, ob.getPassport());
			ps.setString(4, ob.getTel());

			ps.execute();
		
	}

	@Override
	public void delete(Info ob) throws SQLException {
		PreparedStatement ps;
		
			ps = db.getCn().prepareStatement(
					"DELETE FROM " + ob.getClass().getSimpleName() + " WHERE id_info=" + ob.getId_info());
			ps.execute();

		

	}

}
