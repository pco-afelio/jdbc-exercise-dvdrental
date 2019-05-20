package be.afelio.software_academy.pco.jdbc.exercises.dvdrental_with_statistics;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.DvdRentalJdbcRepository;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.DvdRentalJdbcRepositoryWithStatistics;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.Factory;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class _09_TestFactoryWithStaticstics {

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
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);
	}
}
