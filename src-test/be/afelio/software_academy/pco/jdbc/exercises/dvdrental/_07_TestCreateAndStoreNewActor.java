package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.exceptions.DuplicatedActorException;
import be.afelio.software_academy.pco.jdbc.utils.DvdRentalTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class _07_TestCreateAndStoreNewActor {

	DvdRentalTestUtils dvdRentalTestUtils;

	@BeforeEach
	void init() {
		dvdRentalTestUtils = new DvdRentalTestUtils(Factory.getDatabaseUrl(), Factory.getDatabaseUser(),
				Factory.getDatabasePassword());
	}

	@Test
	void testNotDuplicatedActor() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		try {
			repository.createAndStoreNewActor("Betty", "Boop");
			assertNotNull(dvdRentalTestUtils.findOneActorIdByFirstnameAndName("Betty", "Boop"));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteActorByFirstnameAndName("Betty", "Boop");
		}
	}

	@Test
	void testDuplicatedActor() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		assertThrows(DuplicatedActorException.class, () -> { repository.createAndStoreNewActor("Penelope", "Guiness"); });
	}

	@Test
	void testBlankFirstname() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		try {
			repository.createAndStoreNewActor("", "Boop");
			assertNull(dvdRentalTestUtils.findOneActorIdByFirstnameAndName("", "Boop"));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteActorByFirstnameAndName("", "Boop");
		}
	}

	@Test
	void testBlankName() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		try {
			repository.createAndStoreNewActor("Betty", "");
			assertNull(dvdRentalTestUtils.findOneActorIdByFirstnameAndName("Betty", ""));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteActorByFirstnameAndName("Betty", "");
		}
	}

	@Test
	void testNullFirstname() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		try {
			repository.createAndStoreNewActor(null, "Boop");
			assertNull(dvdRentalTestUtils.findOneActorIdByFirstnameAndName(null, "Boop"));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteActorByFirstnameAndName(null, "Boop");
		}
	}

	@Test
	void testNullName() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		try {
			repository.createAndStoreNewActor("Betty", null);
			assertNull(dvdRentalTestUtils.findOneActorIdByFirstnameAndName("Betty", null));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteActorByFirstnameAndName("Betty", null);
		}
	}
}
