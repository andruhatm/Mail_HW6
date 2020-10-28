package dao;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.text.DecimalFormat;

public final class Reports {

	final Connection connection;

	public Reports(@NotNull Connection connection) {
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

	public void report2(@NotNull int higherThan){
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

	public void report3(@NotNull String date1,@NotNull String date2){
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

	public void report4(@NotNull String date1,@NotNull String date2){
		try(PreparedStatement statement = connection.prepareStatement(
						"SELECT \"Organization\".id,\n" +
										"       \"Organization\".name as company,\n" +
										"       N.nomenclature_id,\n" +
										"       N.name\n" +
										"FROM \"Organization\"\n" +
										"         JOIN \"Waybill\" W on W.organization_id = \"Organization\".id\n" +
										"         JOIN \"Waybill_items\" I on W.id = I.waybill_id\n" +
										"         JOIN \"Nomenclature\" N on I.nomenclature = N.nomenclature_id\n" +
										"\n" +
										"WHERE W.date BETWEEN '"+ date1 +"' AND '"+ date2 +"' \n" +
										"UNION SELECT \"Organization\".id,\n" +
										"       \"Organization\".name,\n" +
										"       null,\n" +
										"       null\n" +
										"FROM \"Organization\"\n" +
										"JOIN \"Waybill\" W2 on \"Organization\".id = W2.organization_id\n" +
										"WHERE date < '"+ date1 +"' OR date > '"+date2+"'\n" +
										"ORDER BY id")
		){
			try(ResultSet rs = statement.executeQuery()) {
				while (rs.next()){
					System.out.println("company_id: "+rs.getInt(1)+" - company_name: "+rs.getString(2)+" - product_id: "+rs.getInt(3)+" - name: "+rs.getString(4));
				}
			}

		}catch (SQLException exception){
			exception.getMessage();
		}
	}

	public void report5(@NotNull String date1,@NotNull String date2){
		try(PreparedStatement statement = connection.prepareStatement(
						"SELECT \"Waybill_items\".waybill_id,\n" +
										"       \"Waybill_items\".price, \n" +
										"       \"Waybill_items\".quantity\n" +
										"FROM \"Waybill_items\"\n" +
										"JOIN \"Waybill\" W on \"Waybill_items\".waybill_id = W.id\n" +
										"where W.date between ? and ?")
		){
			statement.setDate(1,Date.valueOf(date1));
			statement.setDate(2,Date.valueOf(date2));
			try(ResultSet rs = statement.executeQuery()) {
				double summ = 0,count = 0;
				while (rs.next()){
					System.out.println("waybill_id: "+rs.getInt(1)+" - price: "+rs.getString(2)+" - quantity: "+rs.getInt(3));
					String summa = rs.getString(2)
									.replace("$","")
									.replace(",","");
					count+= rs.getInt(3);
					summ+= Double.parseDouble(summa);
				}
				String formattedRes = new DecimalFormat("#0.00").format(summ/count);
				System.out.println("Av price: "+formattedRes+"$");
			}

		}catch (SQLException exception){
			exception.getMessage();
		}
	}

}
