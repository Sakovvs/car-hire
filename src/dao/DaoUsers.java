package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import entity.Users;

public class DaoUsers implements DaoInterface<Users> {
	private DB db;

	public DaoUsers(DB db) {
		this.db = db;
	}

	@Override
	public void insert(Users ob) throws SQLException {
	
			PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO " + ob.getClass().getSimpleName() + " VALUES(?,?,?,?,?);");
			
			ps.setInt(1, ob.getId_user());
			ps.setString(2, ob.getLogin());
			ps.setString(3, ob.getPassword());
			ps.setInt(4, ob.getRol());
			ps.setString(5, ob.getStatus());

			ps.execute();
	
	}

	@Override
	public void update(Users ob) throws SQLException {
	
			PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName() + ""
					+ " SET login=?, password=?, rol=?, status=? WHERE id_user=" + ob.getId_user());

			ps.setString(1, ob.getLogin());
			ps.setString(2, ob.getPassword());
			ps.setInt(3, ob.getRol());
			ps.setString(4, ob.getStatus());

			ps.execute();
		
	}

	@Override
	public void delete(Users ob) throws SQLException {
		PreparedStatement ps;
	
			ps = db.getCn()
					.prepareStatement("DELETE FROM " + ob.getClass().getSimpleName() + " WHERE id_user=" + ob.getId_user());
			ps.execute();

		

	}

	public void statusBlok(Users ob) throws SQLException {
	
			PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName() + ""
					+ " SET status='blok' WHERE id_user=" + ob.getId_user());

			ps.execute();
		
	}

	public void statusNotBlok(Users ob) throws SQLException {
		
			PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName() + ""
					+ " SET status='not blok' WHERE id_user=" + ob.getId_user());

			ps.execute();
		
	}

	public void rolAdmin(Users ob) throws SQLException {
		
			PreparedStatement ps = db.getCn().prepareStatement(
					"UPDATE " + ob.getClass().getSimpleName() + "" + " SET rol=1 WHERE id_user=" + ob.getId_user());

			ps.execute();
		
	}

	public void rolUser(Users ob) throws SQLException {
		
			PreparedStatement ps = db.getCn().prepareStatement(
					"UPDATE " + ob.getClass().getSimpleName() + "" + " SET rol=2 WHERE id_user=" + ob.getId_user());

			ps.execute();
		
	}

}
