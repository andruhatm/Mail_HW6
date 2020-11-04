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

	@After
	public void closeConnection() throws SQLException {
		statement.close();
		connection.close();
	}
}
