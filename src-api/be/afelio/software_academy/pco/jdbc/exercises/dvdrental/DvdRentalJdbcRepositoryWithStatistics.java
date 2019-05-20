package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.RentalStatistic;

import java.util.List;
import java.util.Map;

public interface DvdRentalJdbcRepositoryWithStatistics extends DvdRentalJdbcRepository {

    RentalStatistic findRentalStatisticByFilmTitle(String title);
    RentalStatistic findRentalStatisticByFilmTitleAndRentalYear(String title, int year);
    List<RentalStatistic> findAllRentalStatisticsByStoreAddressOrderByFilmTitle(String address);
    Map<Integer, RentalStatistic> findBestRentalForEachStore();
}
