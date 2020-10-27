package dto;

public class Waybill_item {
	private int id;
	private int waybill_id;
	private int price;
	private int quantity;
	private int nomenclature_id;

	public Waybill_item(int id, int waybill_id, int price, int quantity, int nomenclature_id) {
		this.id = id;
		this.waybill_id = waybill_id;
		this.price = price;
		this.quantity = quantity;
		this.nomenclature_id = nomenclature_id;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public int getWaybill_id() {
		return waybill_id;
	}

	private void setWaybill_id(int waybill_id) {
		this.waybill_id = waybill_id;
	}

	public int getPrice() {
		return price;
	}

	private void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getNomenclature_id() {
		return nomenclature_id;
	}

	private void setNomenclature_id(int nomenclature_id) {
		this.nomenclature_id = nomenclature_id;
	}

	@Override
	public String toString() {
		return "Waybill_item{" +
						"id=" + id +
						", waybill_id=" + waybill_id +
						", price=" + price +
						", quantity=" + quantity +
						", nomenclature_id=" + nomenclature_id +
						'}';
	}
}
