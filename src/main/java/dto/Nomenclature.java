package dto;

public class Nomenclature {
	private int id;
	private String name;
	private String serial_number;

	public Nomenclature(int id, String name, String serial_number) {
		this.id = id;
		this.name = name;
		this.serial_number = serial_number;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getSerial_number() {
		return serial_number;
	}

	private void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	@Override
	public String toString() {
		return "Nomenclature{" +
						"id=" + id +
						", name='" + name + '\'' +
						", serial_number='" + serial_number + '\'' +
						'}';
	}
}
