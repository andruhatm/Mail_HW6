package dao;

import dto.Waybill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaybillDAO implements DAO<Waybill>{

	final Connection connection;

	public WaybillDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Waybill> getAll() {
		List<Waybill> result = new ArrayList<>();
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery("SELECT * FROM \"Waybill\"")) {
				while (rs.next()){
					result.add(new Waybill(rs.getInt(1),rs.getDate(2),rs.getInt(3)));
				}
			}
		}catch (SQLException exception){
			exception.getMessage();
		}

		return result;
	}

	@Override
	public Waybill get(int id) {
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery("SELECT * FROM \"Waybill\" where id = " + id)) {
				while (rs.next()){
					return new Waybill(rs.getInt(1),rs.getDate(2),rs.getInt(3));
				}
			}
		}catch (SQLException exception){
			exception.getMessage();
		}

		throw new IllegalStateException("No records founded");
	}

	@Override
	public void save(Waybill entity) {
		try(PreparedStatement ps = connection.prepareStatement("INSERT INTO \"Waybill\"(id,date,organization_id) VALUES (?,?,?)")){
			ps.setInt(1,entity.getId());
			ps.setDate(2,entity.getDate());
			ps.setInt(3,entity.getOrganization_id());
			ps.executeUpdate();
		}catch (SQLException e){
			e.getMessage();
		}
	}

	@Override
	public void update(Waybill entity) {
		try(PreparedStatement ps = connection.prepareStatement("UPDATE \"Waybill\" SET date = ? ,organization_id = ? WHERE id = ?)")){
			ps.setDate(1,entity.getDate());
			ps.setInt(2,entity.getOrganization_id());
			ps.setInt(3,entity.getId());
			ps.executeUpdate();
		}catch (SQLException e){
			e.getMessage();
		}
	}

	@Override
	public void delete(Waybill entity) {
		try(PreparedStatement ps = connection.prepareStatement("DELETE FROM \"Waybill\" WHERE id = ?)")){
			ps.setInt(1,entity.getId());
			if(ps.executeUpdate() == 0){
				throw new IllegalStateException("Record with id: "+entity.getId()+" was not found");
			}
		}catch (SQLException e){
			e.getMessage();
		}
	}
}
