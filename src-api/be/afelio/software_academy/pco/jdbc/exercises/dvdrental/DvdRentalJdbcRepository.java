package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Actor;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Film;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.exceptions.DuplicatedActorException;

import java.util.List;

public interface DvdRentalJdbcRepository {

    String findOneCustomerEmailByCustomerFirstNameAndName(String firstName, String name);
    List<String> findAllFilmTitlesByCategoryNameOrderByFilmTitle(String name);
    List<Actor> findAllActorsByActorFirstNameIgnoreCase(String firstName);
    Film findOneFilmByTitle(String title);
    boolean updateStaffPasswordByEmail(String email, String newPassword);
    boolean deleteCategoryByName(String name);
    void createAndStoreNewActor(String firstName, String lastName) throws DuplicatedActorException;
    Film createAndStoreNewFilm(String title, String description, int releaseYear, String languageName,
                               short length, List<Integer> actorIds);
}
