package dao;

import dto.Nomenclature;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NomenclatureDAO implements DAO<Nomenclature>{

	final Connection connection;

	public NomenclatureDAO(@NotNull Connection connection) {
		this.connection = connection;
	}

	@NotNull
	@Override
	public List<Nomenclature> getAll() {
		List<Nomenclature> result = new ArrayList<>();
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery("SELECT * FROM \"Nomenclature\"")) {
				while (rs.next()){
					result.add(new Nomenclature(rs.getInt(1),rs.getString(2),rs.getString(3)));
				}
			}
		}catch (SQLException exception){
			exception.getMessage();
		}

		return result;
	}

	@NotNull
	@Override
	public Nomenclature get(int id) {
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery("SELECT * FROM \"Nomenclature\" where nomenclature_id = " + id)) {
				while (rs.next()){
					return new Nomenclature(rs.getInt(1),rs.getString(2),rs.getString(3));
				}
			}
		}catch (SQLException exception){
			exception.getMessage();
		}

		throw new IllegalStateException("No records founded");
	}

	@Override
	public boolean save(@NotNull Nomenclature entity) {
		try(PreparedStatement ps = connection.prepareStatement("INSERT INTO \"Nomenclature\"(nomenclature_id,name,serial_number) VALUES (?,?,?)")){
			ps.setInt(1,entity.getId());
			ps.setString(2,entity.getName());
			ps.setString(3,entity.getSerial_number());
			ps.executeUpdate();
		}catch (SQLException e){
			e.getMessage();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(@NotNull Nomenclature entity) {
		try(PreparedStatement ps = connection.prepareStatement("UPDATE \"Nomenclature\" SET name = ? ,serial_number = ?, WHERE nomenclature_id = ?)")){
			ps.setString(1,entity.getName());
			ps.setString(2,entity.getSerial_number());
			ps.setInt(3,entity.getId());
			ps.executeUpdate();
		}catch (SQLException e){
			e.getMessage();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(int id) {
		try(PreparedStatement ps = connection.prepareStatement("DELETE FROM \"Nomenclature\" WHERE nomenclature_id = ?)")){
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
