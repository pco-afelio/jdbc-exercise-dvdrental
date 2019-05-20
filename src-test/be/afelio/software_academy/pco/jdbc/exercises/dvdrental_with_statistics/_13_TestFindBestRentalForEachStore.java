package be.afelio.software_academy.pco.jdbc.exercises.dvdrental_with_statistics;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.DvdRentalJdbcRepositoryWithStatistics;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.RentalStatistic;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class _13_TestFindBestRentalForEachStore {

	@Test
	void test() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		Map<Integer, RentalStatistic> expected = new HashMap<>();
		expected.put(1, new RentalStatistic("Love Suicides", 20));
		expected.put(2, new RentalStatistic("Idols Snatchers", 20));

		Map<Integer, RentalStatistic> actual = repository.findBestRentalForEachStore();

		assertEquals(expected, actual);
	}
}
