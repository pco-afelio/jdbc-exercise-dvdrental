package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

class _00_TestFactory {

	@Test
	void testConnectionParameters() throws Exception {
		String url = Factory.getDatabaseUrl();
		String user = Factory.getDatabaseUser();
		String password = Factory.getDatabasePassword();
		try (Connection c = DriverManager.getConnection(url, user, password)) {
			assertNotNull(c);
		}
	}

	@Test
	void testRepositoryCreation() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
	}
}
