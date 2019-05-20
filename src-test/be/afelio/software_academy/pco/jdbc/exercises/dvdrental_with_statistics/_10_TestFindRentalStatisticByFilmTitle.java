package be.afelio.software_academy.pco.jdbc.exercises.dvdrental_with_statistics;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.DvdRentalJdbcRepositoryWithStatistics;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.RentalStatistic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class _10_TestFindRentalStatisticByFilmTitle {

	@Test
	void testWithExistingFilmTitleWithRentals() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		RentalStatistic expected = new RentalStatistic("Opus Ice", 11);
		RentalStatistic actual = repository.findRentalStatisticByFilmTitle("Opus Ice");

		assertEquals(expected, actual);
	}

	@Test
	void testWithExistingFilmTitleWithoutRentals() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		RentalStatistic expected = new RentalStatistic("Butch Panther", 0);
		RentalStatistic actual = repository.findRentalStatisticByFilmTitle("Butch Panther");

		assertEquals(expected, actual);
	}

	@Test
	void testWithNonExistingFilmTitle() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		assertNull(repository.findRentalStatisticByFilmTitle("xxxx"));
	}

	@Test
	void testWithEmptyFilmTitle() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		assertNull(repository.findRentalStatisticByFilmTitle(""));
	}

	@Test
	void testWithNullFilmTitle() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		assertNull(repository.findRentalStatisticByFilmTitle(null));
	}
}
