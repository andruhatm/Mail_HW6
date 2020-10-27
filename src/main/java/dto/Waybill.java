package dto;

import java.sql.Date;

public class Waybill {
	private int id;
	private Date date;
	private int organization_id;

	public Waybill(int id, Date date, int organization_id) {
		this.id = id;
		this.date = date;
		this.organization_id = organization_id;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	private void setDate(Date date) {
		this.date = date;
	}

	public int getOrganization_id() {
		return organization_id;
	}

	private void setOrganization_id(int organization_id) {
		this.organization_id = organization_id;
	}

	@Override
	public String toString() {
		return "Waybill{" +
						"id=" + id +
						", date=" + date +
						", organization_id=" + organization_id +
						'}';
	}
}
