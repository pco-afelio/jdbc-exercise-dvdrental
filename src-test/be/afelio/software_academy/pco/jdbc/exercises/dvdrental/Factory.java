package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

public class Factory {

    static String databaseUrl = "jdbc:postgresql://localhost:5432/xxxxx";
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

    public static DvdRentalJdbcRepository createDvdRentalJdbcRepository() {
        return null;
    }
}
