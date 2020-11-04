package ru.mail.flyway;

import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.*;

public final class Main {

	public static void main(String[] args) {
		Flyway flyway = Flyway.configure()
						.dataSource("jdbc:postgresql://localhost:5432/mail-lab5","postgres","кщще")
						.locations("db/migration")
						.load();
		//flyway.clean();
		flyway.migrate();

		try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mail-lab5","postgres","кщще")){

			DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

			Reports reports = new Reports(connection, context);
			//10 поставщиков по количеству поставленного товара
			//reports.report1();

			//с суммой поставленного товара выше указанного количества
			//reports.report2(150);

			//количество и сумму полученного товара в указанном периоде,посчитать итоги за период
			//reports.report3( "2020-10-02","2020-10-09");

			//Рассчитать среднюю цену полученного товара за период
			//reports.report4("2020-10-02","2020-10-09");

			//Рассчитать среднюю цену полученного товара за период
			reports.report5("2020-10-02","2020-10-09");

		}catch (SQLException e){
			e.getMessage();
		}

	}

}
