package be.afelio.software_academy.pco.jdbc.exercises.dvdrental_with_statistics;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.DvdRentalJdbcRepositoryWithStatistics;

public class FactoryWithStatistics {

    static String databaseUrl = "jdbc:postgresql://localhost:5432/dvdrental";
    static String databaseUser = "postgres";
    static String databasePassword = "postgres";

    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    public static String getDatabaseUser() {
        return databaseUser;
    }

    public static String getDatabasePassword() {
        return databasePassword;
    }

    public static DvdRentalJdbcRepositoryWithStatistics createDvdRentalJdbcRepositoryWithStatistics() {
        return null;
    }
}
