package dao;

import dto.Organization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDAO implements DAO<Organization> {

	final Connection connection;

	public OrganizationDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Organization> getAll() {
		List<Organization> result = new ArrayList<>();
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery("SELECT * FROM \"Organization\"")) {
				while (rs.next()){
					int Id = rs.getInt(1);
					String name = rs.getString(2);
					String inn = rs.getString(3);
					String checking_acc = rs.getString(4);
					result.add(new Organization(Id,name,inn,checking_acc));
				}
			}
		}catch (SQLException exception){
			exception.getMessage();
		}

		return result;
	}

	@Override
	public Organization get(int id) {
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery("SELECT * FROM \"Organization\" where id = " + id)) {
				while (rs.next()){
					int Id = rs.getInt(1);
					String name = rs.getString(2);
					String inn = rs.getString(3);
					String checking_acc = rs.getString(4);

					return new Organization(Id,name,inn,checking_acc);
				}
			}
		}catch (SQLException exception){
			exception.getMessage();
		}

		throw new IllegalStateException("No records founded");
	}

	@Override
	public void save(Organization entity) {
		try(PreparedStatement ps = connection.prepareStatement("INSERT INTO \"Organization\"(id,name,inn,checking_acc) VALUES (?,?,?,?)")){
			ps.setInt(1,entity.getId());
			ps.setString(2,entity.getName());
			ps.setString(3,entity.getInn());
			ps.setString(4,entity.getChecking_acc());
			ps.executeUpdate();
		}catch (SQLException e){
			e.getMessage();
		}
	}

	@Override
	public void update(Organization entity) {
		try(PreparedStatement ps = connection.prepareStatement("UPDATE \"Organization\" SET name = ? ,inn = ?,checking_acc = ? WHERE id = ?)")){
			ps.setString(1,entity.getName());
			ps.setString(2,entity.getInn());
			ps.setString(3,entity.getChecking_acc());
			ps.setInt(4,entity.getId());
			ps.executeUpdate();
		}catch (SQLException e){
			e.getMessage();
		}
	}

	@Override
	public void delete(Organization entity) {
		try(PreparedStatement ps = connection.prepareStatement("DELETE FROM \"Organization\" WHERE id = ?)")){
			ps.setInt(1,entity.getId());
			if(ps.executeUpdate() == 0){
				throw new IllegalStateException("Record with id: "+entity.getId()+" was not found");
			}
		}catch (SQLException e){
			e.getMessage();
		}
	}
}
