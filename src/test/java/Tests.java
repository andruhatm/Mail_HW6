import dao.NomenclatureDAO;
import dao.OrganizationDAO;
import dao.WaybillDAO;
import dao.Waybill_itemDAO;
import dto.Nomenclature;
import dto.Organization;
import dto.Waybill;
import dto.Waybill_item;
import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;
import org.junit.*;

import java.sql.*;

public class Tests {
	@SuppressWarnings("NullableProblems")
	@NotNull
	private Connection connection;
	@SuppressWarnings("NullableProblems")
	@NotNull
	private Statement statement;

	@NotNull
	private static final String URL = "jdbc:postgresql://localhost:5432/mail-lab5";
	@NotNull
	private static final String USERNAME = "postgres";
	@NotNull
	private static final String PASSWORD = "кщще";

	@BeforeClass
	public static void flywaySet() {
		final Flyway flyway = Flyway
						.configure()
						.dataSource(URL, USERNAME, PASSWORD)
						.load();
		flyway.clean();
		flyway.migrate();
	}

	@Before
	public void setConnection() throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		statement = connection.createStatement();
	}

	@Test
	public void nomenclatureInsert() {
		NomenclatureDAO nomenclatureDAO = new NomenclatureDAO(connection);
		Nomenclature obj = new Nomenclature(100,"New nomenclature","456768663");
		Assert.assertTrue(nomenclatureDAO.save(obj));
	}

	@Test
	public void nomenclatureUpdate(){
		NomenclatureDAO nomenclatureDAO = new NomenclatureDAO(connection);
		Nomenclature obj = new Nomenclature(1,"obj","12323442");
		Assert.assertTrue(nomenclatureDAO.update(obj));
	}

	@Test
	public void nomenclatureDelete(){
		NomenclatureDAO nomenclatureDAO = new NomenclatureDAO(connection);
		Waybill_itemDAO waybill_itemDAO = new Waybill_itemDAO(connection);
		waybill_itemDAO.delete(1);
		Assert.assertTrue(nomenclatureDAO.delete(1));
	}

	@Test
	public void organizationInsert() {
		OrganizationDAO organizationsDAO = new OrganizationDAO(connection);
		Organization obj = new Organization(100,"New Organization","123456","123123");
		Assert.assertTrue(organizationsDAO.save(obj));
	}

	@Test
	public void organizationUpdate() {
		OrganizationDAO organizationsDAO = new OrganizationDAO(connection);
		Organization obj = new Organization(1,"New Organization","123456","123123");
		Assert.assertTrue(organizationsDAO.update(obj));
	}

	@Test
	public void organizationDelete() {
		WaybillDAO waybillsDAO = new WaybillDAO(connection);
		OrganizationDAO organizationsDAO = new OrganizationDAO(connection);
		Waybill_itemDAO positionsDAO = new Waybill_itemDAO(connection);
		positionsDAO.delete(8);
		waybillsDAO.delete(8);
		Assert.assertTrue(organizationsDAO.delete(8));
	}

	@Test
	public void insertWaybillItems() {
		Waybill_itemDAO positionsDAO = new Waybill_itemDAO(connection);
		NomenclatureDAO nomenclatureDAO = new NomenclatureDAO(connection);
		Nomenclature nomenclature = new Nomenclature(30,"New Nomenclature","123456");
		Waybill_item position = new Waybill_item(1,1, 500, 2,20);
		nomenclatureDAO.save(nomenclature);
		Assert.assertTrue(positionsDAO.save(position));
	}

	@Test
	public void updateWaybillItems() {
		Waybill_itemDAO positionsDAO = new Waybill_itemDAO(connection);
		Waybill_item position = new Waybill_item(2,2,200,300,2);
		Assert.assertTrue(positionsDAO.update(position));
	}

	@Test
	public void deleteWaybillItems() {
		Waybill_itemDAO waybill_itemDAO = new Waybill_itemDAO(connection);
		Assert.assertTrue(waybill_itemDAO.delete(3));
	}

	@Test
	public void insertWaybill() {
		WaybillDAO waybillsDAO = new WaybillDAO(connection);
		OrganizationDAO organizationsDAO = new OrganizationDAO(connection);
		Organization organization = new Organization(1,"new Organization", "552","324242");
		Waybill waybill = new Waybill(50, java.sql.Date.valueOf("2020-10-25"), 1);
		organizationsDAO.save(organization);
		Assert.assertTrue(waybillsDAO.save(waybill));
	}

	@Test
	public void updateWaybill() {
		WaybillDAO waybillsDAO = new WaybillDAO(connection);
		Waybill waybills = new Waybill(13, Date.valueOf("2020-10-25"),1);
		Assert.assertTrue(waybillsDAO.update(waybills));
	}

	@Test
	public void deleteWaybill() {
		WaybillDAO waybillsDAO = new WaybillDAO(connection);
		Waybill_itemDAO waybill_itemDAO = new Waybill_itemDAO(connection);
		waybill_itemDAO.delete(10);
		Assert.assertTrue(waybillsDAO.delete(10));
	}

	@After
	public void closeConnection() throws SQLException {
		statement.close();
		connection.close();
	}
}
