package ru.mail.flyway;

import dao.*;
import dto.Nomenclature;
import dto.Organization;
import dto.Waybill;
import dto.Waybill_item;
import org.flywaydb.core.Flyway;

import java.sql.*;

public class Main {

	public static void main(String[] args) {
		Flyway flyway = Flyway.configure()
						.dataSource("jdbc:postgresql://localhost:5432/mail-lab5","postgres","кщще")
						.locations("db/migration")
						.load();
		//flyway.clean();
		flyway.migrate();

		try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mail-lab5","postgres","кщще")){

			/*
				1) Выбрать первые 10 поставщиков по количеству поставленного товара
				2) Выбрать поставщиков с суммой поставленного товара выше указанного
				количества.
				3) За каждый день в разрезе номенклатур рассчитать количество и сумму
				полученного товара в указанном периоде, посчитать итоги за период
				4) Рассчитать среднюю цену полученного товара за период
				Вывести список товаров, поставленных организациями за период.
			*/

			Reports reports = new Reports(connection);
			//10 поставщиков по количеству поставленного товара
			//reports.report1();

			//с суммой поставленного товара выше указанного количества
			//reports.report2(150);

			//количество и сумму полученного товара в указанном периоде,посчитать итоги за период
			//reports.report3( "2020-10-02","2020-10-09");

			//Рассчитать среднюю цену полученного товара за период
			reports.report4("2020-10-02","2020-10-09");

		}catch (SQLException e){
			e.getMessage();
		}

	}

}
