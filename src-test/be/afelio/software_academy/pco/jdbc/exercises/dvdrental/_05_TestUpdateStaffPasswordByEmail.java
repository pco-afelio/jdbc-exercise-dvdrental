package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import be.afelio.software_academy.pco.jdbc.utils.DvdRentalTestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class _05_TestUpdateStaffPasswordByEmail {

	DvdRentalTestUtils dvdRentalTestUtils;

	@BeforeEach
	void init() {
		dvdRentalTestUtils = new DvdRentalTestUtils(Factory.getDatabaseUrl(), Factory.getDatabaseUser(),
				Factory.getDatabasePassword());
	}

	@AfterEach
	void reset() {
		dvdRentalTestUtils.updateStaffPasswordForEmail(
				"8cb2237d0679ca88db6464eac60da96345513964", "Jon.Stephens@sakilastaff.com");
	}

	@Test
	void testExistingEmail() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String expected = String.valueOf(System.currentTimeMillis());

		boolean updated = repository.updateStaffPasswordByEmail("Jon.Stephens@sakilastaff.com", expected);
		assertTrue(updated);

		String actual = dvdRentalTestUtils.findOneStaffPasswordByEmail("Jon.Stephens@sakilastaff.com");

		assertEquals(expected, actual);
	}

	@Test
	void testNonExistingEmail() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		boolean updated = repository.updateStaffPasswordByEmail("je_n_existe_pas@nulle_part.com",
				String.valueOf(System.currentTimeMillis()));
		assertFalse(updated);
	}

	@Test
	void testNullEmail() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		boolean updated = repository.updateStaffPasswordByEmail(null, String.valueOf(System.currentTimeMillis()));
		assertFalse(updated);
	}

	@Test
	void testNullPassword() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String expected = dvdRentalTestUtils.findOneStaffPasswordByEmail("Jon.Stephens@sakilastaff.com");

		boolean updated = repository.updateStaffPasswordByEmail("Jon.Stephens@sakilastaff.com", null);
		assertFalse(updated);

		String actual = dvdRentalTestUtils.findOneStaffPasswordByEmail("Jon.Stephens@sakilastaff.com");

		assertEquals(expected, actual);
	}

	@Test
	void testDuplicatePassword() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String expected = dvdRentalTestUtils.findOneStaffPasswordByEmail("Jon.Stephens@sakilastaff.com");
		String duplicate = dvdRentalTestUtils.findOneStaffPasswordByEmail("Mike.Hillyer@sakilastaff.com");

		boolean updated = repository.updateStaffPasswordByEmail("Jon.Stephens@sakilastaff.com", duplicate);
		assertFalse(updated);

		String actual = dvdRentalTestUtils.findOneStaffPasswordByEmail("Jon.Stephens@sakilastaff.com");

		assertEquals(expected, actual);
	}
}
