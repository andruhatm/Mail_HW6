package ru.mail.flyway;

import db.tables.Nomenclature;
import db.tables.Organization;
import db.tables.Waybill;
import db.tables.WaybillItems;
import org.jetbrains.annotations.NotNull;
import org.jooq.*;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;

import static db.tables.Nomenclature.NOMENCLATURE;
import static db.tables.Organization.ORGANIZATION;
import static db.tables.Waybill.WAYBILL;
import static db.tables.WaybillItems.WAYBILL_ITEMS;
import static org.jooq.impl.DSL.*;

public final class Reports {

	final Connection connection;

	final DSLContext context;

	public Reports(@NotNull Connection connection,@NotNull DSLContext context) {
		this.connection = connection;
		this.context = context;
	}

	public void	report1(){

		Result<Record2<String,BigDecimal>> result = context.select(
							ORGANIZATION.NAME.as("organization"),
							sum(WAYBILL_ITEMS.QUANTITY).as("sumQ"))
						.from(WAYBILL_ITEMS)
						.join(WAYBILL).on(WAYBILL_ITEMS.WAYBILL_ID.eq(WAYBILL.ID))
						.join(ORGANIZATION).on(WAYBILL.ORGANIZATION_ID.eq(ORGANIZATION.ID))
						.groupBy(WAYBILL.ID,
										 ORGANIZATION.NAME)
						.orderBy(sum(WAYBILL_ITEMS.QUANTITY).desc())
						.limit(10)
						.fetch();
		for(Record2<String,BigDecimal> r: result){
			String company = (String) r.getValue("organization");
			BigDecimal quantity = (BigDecimal) r.get("sumQ");
			System.out.println("name: "+company+" - count: "+quantity);
		}
	}

	public void report2(@NotNull int higherThan){

		Result<Record2<String,BigDecimal>> records = context.select(
							ORGANIZATION.NAME.as("organization"),
							sum(WAYBILL_ITEMS.PRICE).as("sumP"))
						.from(WAYBILL_ITEMS)
						.join(WAYBILL).on(WAYBILL_ITEMS.WAYBILL_ID.eq(WAYBILL.ID))
						.join(ORGANIZATION).on(WAYBILL.ORGANIZATION_ID.eq(ORGANIZATION.ID))
						.groupBy(WAYBILL.ID,
										ORGANIZATION.NAME)
						.having(sum(WAYBILL_ITEMS.PRICE).gt(BigDecimal.valueOf(higherThan)))
						.orderBy(sum(WAYBILL_ITEMS.PRICE).desc())
						.fetch();
		for(Record2<String,BigDecimal> r: records){
			String company = (String) r.getValue("organization");
			BigDecimal quantity = (BigDecimal) r.get("sumP");
			System.out.println("name: "+company+" - sum: "+quantity);
		}
	}

	public void report3(@NotNull String date1,@NotNull String date2){

		Result<Record3<Date, BigDecimal, BigDecimal>> records = context.select(
						WAYBILL.DATE.as("date"),
						sum(WAYBILL_ITEMS.PRICE).as("sumP"),
						sum(WAYBILL_ITEMS.QUANTITY).as("sumQ")
						)
						.from(WAYBILL_ITEMS)
						.join(WAYBILL).on(WAYBILL_ITEMS.WAYBILL_ID.eq(WAYBILL.ID))
						.groupBy(WAYBILL.DATE)
						.having(WAYBILL.DATE.between(Date.valueOf(date1)).and(Date.valueOf(date2)))
						.orderBy(WAYBILL.DATE.desc())
						.fetch();
		BigDecimal summ = BigDecimal.valueOf(0);
		BigDecimal count = BigDecimal.valueOf(0);
		for(Record3<Date,BigDecimal,BigDecimal> r: records){
			BigDecimal summa = (BigDecimal) r.get("sumP");
			summ=summ.add(summa);
			count=count.add((BigDecimal) r.get("sumQ"));
			System.out.println("date: "+r.get("date")+" - summ: "+r.get("sumP")+" - count: "+r.get("sumQ"));
		}
		System.out.println("Total: "+summ+"P Count: "+count);

	}

	public void report4(@NotNull String date1,@NotNull String date2){

		Result<Record4<Integer, String, Integer,String>> records = context.select(
						ORGANIZATION.ID,
						ORGANIZATION.NAME.as("companyName"),
						NOMENCLATURE.NOMENCLATURE_ID,
						NOMENCLATURE.NAME
		)
						.from(ORGANIZATION)
						.join(WAYBILL).on(ORGANIZATION.ID.eq(WAYBILL.ORGANIZATION_ID))
						.join(WAYBILL_ITEMS).on(WAYBILL.ID.eq(WAYBILL_ITEMS.WAYBILL_ID))
						.join(NOMENCLATURE).on(WAYBILL_ITEMS.NOMENCLATURE.eq(NOMENCLATURE.NOMENCLATURE_ID))
						.where(WAYBILL.DATE.between(Date.valueOf(date1)).and(Date.valueOf(date2)))
						.union(select(ORGANIZATION.ID,ORGANIZATION.NAME,val(null,Integer.class),val(null,String.class))
						.from(ORGANIZATION)
						.join(WAYBILL).on(ORGANIZATION.ID.eq(WAYBILL.ORGANIZATION_ID))
						.where(WAYBILL.DATE.le(Date.valueOf(date1)).or(WAYBILL.DATE.gt(Date.valueOf(date2)))))
						.orderBy(ORGANIZATION.ID.asc())
						.fetch();

		for(Record4<Integer, String, Integer,String> r: records){
			System.out.println("company_id: "+r.get(0)+" - company_name: "+r.get(1)+" - product_id: "+r.get(2)+" - name: "+r.get(3));
		}
	}

	public void report5(@NotNull String date1,@NotNull String date2){

		Result<Record3<Integer,Double,Integer>> records = context.select(
						WAYBILL_ITEMS.WAYBILL_ID,
						WAYBILL_ITEMS.PRICE.as("sumP"),
						WAYBILL_ITEMS.QUANTITY.as("sumQ")
		)
						.from(WAYBILL_ITEMS)
						.join(WAYBILL).on(WAYBILL_ITEMS.WAYBILL_ID.eq(WAYBILL.ID))
						.where(WAYBILL.DATE.between(Date.valueOf(date1)).and(Date.valueOf(date2)))
						.fetch();
		double summ = 0;
		int count = 0;
		for(Record3<Integer,Double,Integer> r: records){
			Double summa = (Double) r.get("sumP");
			summ+=summa;
			count +=  (Integer) r.get("sumQ");
			System.out.println("waybill_id: "+r.get(0)+" - price: "+r.get(1)+" - quantity: "+r.get(2));
		}
		String formattedRes = new DecimalFormat("#0.00").format(summ/count);
		System.out.println("Av price: "+formattedRes+"P");

	}

}
