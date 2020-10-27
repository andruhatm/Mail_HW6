package dao;

import java.sql.*;

public class Reports {

	final Connection connection;

	public Reports(Connection connection) {
		this.connection = connection;
	}

	public void	report1(){
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery(
							"SELECT O.name,\n" +
											"       sum(quantity) as sumQ\n" +
											"from \"Waybill_items\"\n" +
											"JOIN \"Waybill\" on \"Waybill_items\".waybill_id = \"Waybill\".id\n" +
											"JOIN \"Organization\" O on \"Waybill\".organization_id = O.id\n" +
											"group by waybill_id,\n" +
											"         o.name\n" +
											"order by sumQ desc;")
			) {
				while (rs.next()){
					System.out.println("name: "+rs.getString(1)+" - count: "+rs.getInt(2));
				}
			}
		}catch (SQLException exception){
			exception.getMessage();
		}
	}

	public void report2(int higherThan){
		try(Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery(
							"SELECT O.name,\n" +
									    "   sum(price) as sumP\n" +
									"from \"Waybill_items\"\n" +
									"         JOIN \"Waybill\" on \"Waybill_items\".waybill_id = \"Waybill\".id\n" +
									"         JOIN \"Organization\" O on \"Waybill\".organization_id = O.id\n" +
									"group by waybill_id,\n" +
									"         o.name\n" +
									"having sum(price) > 150::money\n" +
									"order by sumP desc;\n")
			) {
				while (rs.next()){
					System.out.println("name: "+rs.getString(1)+" - summ: "+rs.getString(2));
				}
			}
		}catch (SQLException exception){
			exception.getMessage();
		}
	}

	public void report3(String date1,String date2){
		try(PreparedStatement statement = connection.prepareStatement(
						"SELECT W.date,\n" +
						"       sum(price) as sumP,\n" +
						"       sum(quantity) as sumQ\n" +
						"from \"Waybill_items\"\n" +
						"JOIN \"Waybill\" W on \"Waybill_items\".waybill_id = W.id\n" +
						"group by date\n" +
						"having W.date between ? and ? \n" +
						"order by date desc;")
		){
			statement.setDate(1,Date.valueOf(date1));
			statement.setDate(2,Date.valueOf(date2));
			try(ResultSet rs = statement.executeQuery()) {
				double summ = 0,count = 0;
				while (rs.next()){
					String summa = rs.getString(2)
									.replace("$","")
									.replace(",","");
					summ+= Double.parseDouble(summa);
					count+= rs.getInt(3);
					System.out.println("date: "+rs.getDate(1)+" - summ: "+rs.getString(2)+" - count: "+rs.getInt(3));
				}
				System.out.println("Total: "+summ+" Count: "+count);
			}

		}catch (SQLException exception){
			exception.getMessage();
		}
	}

	public void report4(String date1,String date2){
		try(PreparedStatement statement = connection.prepareStatement(
						"SELECT \"Organization\".name as company,\n" +
										"       I.quantity,\n" +
										"       I.price,\n" +
										"       N.name\n" +
										"FROM \"Organization\"\n" +
										"LEFT JOIN \"Waybill\" W on W.organization_id = \"Organization\".id\n" +
										"LEFT JOIN \"Waybill_items\" I on W.id = I.waybill_id\n" +
										"LEFT JOIN \"Nomenclature\" N on I.nomenclature = N.nomenclature_id \n" +
										"\n" +
										"where W.date between ? and ?")
		){
			statement.setDate(1,Date.valueOf(date1));
			statement.setDate(2,Date.valueOf(date2));
			try(ResultSet rs = statement.executeQuery()) {
				double summ = 0,count = 0;
				while (rs.next()){
					System.out.println("company: "+rs.getString(1)+" - count: "+rs.getInt(2)+" - price: "+rs.getString(3)+" - name: "+rs.getString(4));
					String summa = rs.getString(3)
									.replace("$","")
									.replace(",","");
					count+= rs.getInt(2);
					summ+= Double.parseDouble(summa);
				}
				System.out.println("Av price: "+summ/count+"$");
			}

		}catch (SQLException exception){
			exception.getMessage();
		}
	}
}
