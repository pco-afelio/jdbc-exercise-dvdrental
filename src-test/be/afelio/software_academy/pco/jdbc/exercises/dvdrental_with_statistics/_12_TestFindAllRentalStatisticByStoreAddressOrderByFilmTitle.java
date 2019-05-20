package be.afelio.software_academy.pco.jdbc.exercises.dvdrental_with_statistics;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.DvdRentalJdbcRepositoryWithStatistics;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.RentalStatistic;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class _12_TestFindAllRentalStatisticByStoreAddressOrderByFilmTitle {

	@Test
	void testWithStoreOneAddress() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		String address = "47 MySakila Drive";
		List<RentalStatistic> list = repository.findAllRentalStatisticsByStoreAddressOrderByFilmTitle(address);

		assertNotNull(list);
		assertEquals(Integer.valueOf(759), Integer.valueOf(list.size()));
		assertEquals(new RentalStatistic("Academy Dinosaur", 12), list.get(0));
		assertEquals(new RentalStatistic("Zorro Ark", 16), list.get(758));
	}

	@Test
	void testWithStoreTwoAddress() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		String address = "28 MySQL Boulevard";
		List<RentalStatistic> list = repository.findAllRentalStatisticsByStoreAddressOrderByFilmTitle(address);

		assertNotNull(list);
		assertEquals(Integer.valueOf(762), Integer.valueOf(list.size()));
		assertEquals(new RentalStatistic("Academy Dinosaur", 11), list.get(0));
		assertEquals(new RentalStatistic("Zorro Ark", 15), list.get(761));
	}

	@Test
	void testWithNonExistingStoreAddress() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		String address = "xxxxx";
		List<RentalStatistic> list = repository.findAllRentalStatisticsByStoreAddressOrderByFilmTitle(address);

		assertNotNull(list);
		assertEquals(Integer.valueOf(0), Integer.valueOf(list.size()));
	}

	@Test
	void testWithNullStoreAddress() {
		DvdRentalJdbcRepositoryWithStatistics repository
				= FactoryWithStatistics.createDvdRentalJdbcRepositoryWithStatistics();
		assertNotNull(repository);

		String address = "xxxxx";
		List<RentalStatistic> list = repository.findAllRentalStatisticsByStoreAddressOrderByFilmTitle(address);

		assertNotNull(list);
		assertEquals(Integer.valueOf(0), Integer.valueOf(list.size()));
	}
}
