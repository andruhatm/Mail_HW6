package dto;

public final class Organization {
	private int id;
	private String name;
	private String inn;
	private String checking_acc;

	public Organization(int id, String name, String inn, String checking_acc) {
		this.id = id;
		this.name = name;
		this.inn = inn;
		this.checking_acc = checking_acc;
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

	public String getInn() {
		return inn;
	}

	private void setInn(String inn) {
		this.inn = inn;
	}

	public String getChecking_acc() {
		return checking_acc;
	}

	private void setChecking_acc(String checking_acc) {
		this.checking_acc = checking_acc;
	}

	@Override
	public String toString() {
		return "Organization{" +
						"id=" + id +
						", name='" + name + '\'' +
						", inn='" + inn + '\'' +
						", checking_acc='" + checking_acc + '\'' +
						'}';
	}
}
