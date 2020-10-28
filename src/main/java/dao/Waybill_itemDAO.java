package dao;

import dto.Waybill_item;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Waybill_itemDAO implements DAO<Waybill_item> {

	final Connection connection;

	public Waybill_itemDAO(@NotNull Connection connection) {
		this.connection = connection;
	}

	@NotNull
	@Override
	public List<Waybill_item> getAll() {
		List<Waybill_item> result = new ArrayList<>();
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery("SELECT * FROM \"Waybill_items\"")) {
				while (rs.next()){
					result.add(new Waybill_item(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5)));
				}
			}
		}catch (SQLException exception){
			exception.getMessage();
		}

		return result;
	}

	@NotNull
	@Override
	public Waybill_item get(int id) {
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery("SELECT * FROM \"Waybill_items\" where item_id = " + id)) {
				while (rs.next()){
					return new Waybill_item(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
				}
			}
		}catch (SQLException exception){
			exception.getMessage();
		}

		throw new IllegalStateException("No records founded");
	}

	@Override
	public boolean save(@NotNull Waybill_item entity) {
		try(PreparedStatement ps = connection.prepareStatement("INSERT INTO \"Waybill_items\"(item_id,waybill_id,price,quantity,nomenclature) VALUES (?,?,?,?,?)")){
			System.out.println("work");
			ps.setInt(1,entity.getId());
			ps.setInt(2,entity.getWaybill_id());
			ps.setInt(3,entity.getPrice());
			ps.setInt(4,entity.getQuantity());
			ps.setInt(5,entity.getNomenclature_id());
			System.out.println("md: "+ps.getMetaData());
			ps.executeUpdate();
		}catch (SQLException e){
			e.getMessage();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(@NotNull Waybill_item entity) {
		try(PreparedStatement ps = connection.prepareStatement("UPDATE \"Waybill_items\" SET waybill_id = ? ,price = ?,quantity = ?,nomenclature = ? WHERE item_id = ?)")){
			ps.setInt(1,entity.getWaybill_id());
			ps.setDouble(2,entity.getPrice());
			ps.setInt(3,entity.getQuantity());
			ps.setInt(4,entity.getNomenclature_id());
			ps.setInt(5,entity.getId());
			ps.executeUpdate();
		}catch (SQLException e){
			e.getMessage();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(int id) {
		try(PreparedStatement ps = connection.prepareStatement("DELETE FROM \"Waybill_items\" WHERE id = ?)")){
			ps.setInt(1,id);
			if(ps.executeUpdate() == 0){
				throw new IllegalStateException("Record with id: "+id+" was not found");
			}
		}catch (SQLException e){
			e.getMessage();
			return false;
		}
		return true;
	}
}
