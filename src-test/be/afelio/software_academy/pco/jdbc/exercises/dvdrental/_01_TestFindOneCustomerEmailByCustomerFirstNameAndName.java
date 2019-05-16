package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class _01_TestFindOneCustomerEmailByCustomerFirstNameAndName {

	@Test
	void testExistingCustomer() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String expected = "jared.ely@sakilacustomer.org";
		String actual = repository.findOneCustomerEmailByCustomerFirstNameAndName("Jared", "Ely");

		assertEquals(expected, actual);
	}

	@Test
	void testNonExistingCustomer() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String actual = repository.findOneCustomerEmailByCustomerFirstNameAndName("xxx", "yyy");

		assertNull(actual);
	}

	@Test
	void testNonExistingCustomerFirstNameNull() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String actual = repository.findOneCustomerEmailByCustomerFirstNameAndName(null, "Ely");

		assertNull(actual);
	}

	@Test
	void testNonExistingCustomerNameNull() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String actual = repository.findOneCustomerEmailByCustomerFirstNameAndName("Jared", null);

		assertNull(actual);
	}
}
