package be.afelio.software_academy.pco.jdbc.exercises.dvdrental_with_statistics;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.DvdRentalJdbcRepositoryWithStatistics;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.RentalStatistic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class _11_TestFindRentalStatisticByFilmTitleAndRentalYear {

	@Test
	void testWithExistingFilmTitleWithRentalsInGivenYear() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		RentalStatistic expected = new RentalStatistic("Graceland Dynamite", 6);
		RentalStatistic actual = repository.findRentalStatisticByFilmTitleAndRentalYear("Graceland Dynamite", 2005);

		assertEquals(expected, actual);
	}

	@Test
	void testWithExistingFilmTitleWithoutRentalsInGivenYear() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		RentalStatistic expected = new RentalStatistic("Graceland Dynamite", 0);
		RentalStatistic actual = repository.findRentalStatisticByFilmTitleAndRentalYear("Graceland Dynamite", 2006);

		assertEquals(expected, actual);
	}

	@Test
	void testWithNonExistingFilmTitle() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		assertNull(repository.findRentalStatisticByFilmTitleAndRentalYear("xxxx", 2005));
	}

	@Test
	void testWithEmptyFilmTitle() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		assertNull(repository.findRentalStatisticByFilmTitleAndRentalYear("", 2005));
	}

	@Test
	void testWithNullFilmTitle() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		assertNull(repository.findRentalStatisticByFilmTitleAndRentalYear(null, 2005));
	}
}
