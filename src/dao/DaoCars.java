package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import entity.Cars;

public class DaoCars implements DaoInterface<Cars> {
	private DB db;

	public DaoCars(DB db) {
		this.db = db;
	}

	@Override
	public void insert(Cars ob) throws SQLException {
		
			PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO " + ob.getClass().getSimpleName() + " VALUES(?,?,?,?,?,?);");
			ps.setInt(1, ob.getId_car());
			ps.setString(2, ob.getMarka());
			ps.setString(3, ob.getModel());
			ps.setString(4, ob.getNomer());
			ps.setDouble(5, ob.getPrice());
			ps.setString(6, ob.getStatus());

			ps.execute();
	
	}

	@Override
	public void update(Cars ob) throws SQLException {
		
			PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName() + ""
					+ " SET marka=?, model=?, nomer=?, price=?, status=? WHERE id_car=" + ob.getId_car());

			ps.setString(1, ob.getMarka());
			ps.setString(2, ob.getModel());
			ps.setString(3, ob.getNomer());
			ps.setDouble(4, ob.getPrice());
			ps.setString(5, ob.getStatus());

			ps.execute();
	
	}

	@Override
	public void delete(Cars ob) throws SQLException {
		PreparedStatement ps = db.getCn().prepareStatement(
					"DELETE FROM " + ob.getClass().getSimpleName() + "WHERE id_car=" + ob.getId_car());
		
	}

	public void statusBlok(Cars ob) throws SQLException {
	
			PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName() + ""
					+ " SET status='blok' WHERE id_car=" + ob.getId_car());
			
			ps.execute();
	
	}
	
	public void statusNotBlok(Cars ob) throws SQLException {
		
			PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName() + ""
					+ " SET status='not blok' WHERE id_car=" + ob.getId_car());
			
			ps.execute();
		
	}
	

}
