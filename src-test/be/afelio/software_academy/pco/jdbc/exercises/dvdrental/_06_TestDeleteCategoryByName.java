package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import be.afelio.software_academy.pco.jdbc.utils.DvdRentalTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class _06_TestDeleteCategoryByName {

	DvdRentalTestUtils dvdRentalTestUtils;

	@BeforeEach
	void init() {
		dvdRentalTestUtils = new DvdRentalTestUtils(Factory.getDatabaseUrl(), Factory.getDatabaseUser(),
				Factory.getDatabasePassword());
	}

	@Test
	void testExistingCategoryName() {
		String categoryName = "Zoology";
		String filmTitle = "Arachnophobia Rollercoaster";

		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		try {
			dvdRentalTestUtils.createCategoryForFilm(categoryName, filmTitle);
			assertTrue(repository.deleteCategoryByName("Zoology"));
			assertNull(dvdRentalTestUtils.findCategoryByName(categoryName));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteCategory(categoryName);
		}
	}

	@Test
	void testNonExistingCategoryName() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		assertFalse(repository.deleteCategoryByName("JeNeSuisPasDansLaDb"));
	}

	@Test
	void testNullCategoryName() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		assertFalse(repository.deleteCategoryByName(null));
	}
}
