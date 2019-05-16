package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Actor;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Film;
import be.afelio.software_academy.pco.jdbc.utils.DvdRentalTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class _08_TestCreateAndStoreNewFilm {

	DvdRentalTestUtils dvdRentalTestUtils;

	@BeforeEach
	void init() {
		dvdRentalTestUtils = new DvdRentalTestUtils(Factory.getDatabaseUrl(), Factory.getDatabaseUser(),
				Factory.getDatabasePassword());
	}

	@Test
	void testNotDuplicatedFilmWithExistingLanguageAndExistingActorsAndValidParameters() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String title = "Jdbc programming video";
		String description = "Jdbc video tutorial";
		int releaseYear = 2019;
		String languageName = "English";
		short length = 60;
		List<Actor> actors = Arrays.asList(
				new Actor[] {
					new Actor(1, "Penelope", "Guiness"),
					new Actor(2, "Nick", "Wahlberg")
				});
		List<Integer> actorIds = Arrays.asList(new Integer[] {1, 2});

		try {
			Film film = repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds);
			assertNotNull(film);
			assertEquals(title, film.getTitle());
			assertEquals(description, film.getDescription());
			assertEquals(Integer.valueOf(releaseYear), Integer.valueOf(film.getYear()));
			assertNotNull(film.getLanguage());
			assertEquals(languageName, film.getLanguage().getName());
			assertEquals(Integer.valueOf(length), Integer.valueOf(film.getLength()));
			assertNotNull(film.getActors());
			assertEquals(2, film.getActors().size());
			assertTrue(film.getActors().contains(actors.get(0)));
			assertTrue(film.getActors().contains(actors.get(1)));
			Film found = repository.findOneFilmByTitle(title);
			assertEquals(film, found);
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteFilmByTitle(title);
		}
	}

	@Test
	void testNotDuplicatedFilmWithExistingLanguageAndNotExistingActorsAndValidParameters() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String title = "Jdbc programming video";
		String description = "Jdbc video tutorial";
		int releaseYear = 2019;
		String languageName = "English";
		short length = 60;
		List<Integer> actorIds = Arrays.asList(new Integer[] {1, Integer.MAX_VALUE});

		try {
			Film film = repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds);
			assertNull(film);
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteFilmByTitle(title);
		}
	}

	@Test
	void testNotDuplicatedFilmWithNotExistingLanguageAndExistingActorsAndValidParameters() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String title = "Jdbc programming video";
		String description = "Jdbc video tutorial";
		int releaseYear = 2019;
		String languageName = "Wallon";
		short length = 60;
		List<Actor> actors = Arrays.asList(
				new Actor[] {
						new Actor(1, "Penelope", "Guiness"),
						new Actor(2, "Nick", "Wahlberg")
				});
		List<Integer> actorIds = Arrays.asList(new Integer[] {1, 2});

		try {
			Film film = repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds);
			assertNotNull(film);
			assertEquals(title, film.getTitle());
			assertEquals(description, film.getDescription());
			assertEquals(Integer.valueOf(releaseYear), Integer.valueOf(film.getYear()));
			assertNotNull(film.getLanguage());
			assertEquals(languageName, film.getLanguage().getName());
			assertEquals(Integer.valueOf(length), Integer.valueOf(film.getLength()));
			assertNotNull(film.getActors());
			assertEquals(2, film.getActors().size());
			assertTrue(film.getActors().contains(actors.get(0)));
			assertTrue(film.getActors().contains(actors.get(1)));
			Film found = repository.findOneFilmByTitle(title);
			assertEquals(film, found);
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteFilmByTitle(title);
			dvdRentalTestUtils.deleteLanguageByName(languageName);
		}
	}

	@Test
	void testNotDuplicatedFilmWithExistingLanguageAndEmptyActorsListAndValidParameters() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String title = "Jdbc programming video";
		String description = "Jdbc video tutorial";
		int releaseYear = 2019;
		String languageName = "English";
		short length = 60;
		List<Integer> actorIds = Collections.emptyList();

		try {
			Film film = repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds);
			assertNotNull(film);
			assertEquals(title, film.getTitle());
			assertEquals(description, film.getDescription());
			assertEquals(Integer.valueOf(releaseYear), Integer.valueOf(film.getYear()));
			assertNotNull(film.getLanguage());
			assertEquals(languageName, film.getLanguage().getName());
			assertEquals(Integer.valueOf(length), Integer.valueOf(film.getLength()));
			assertNotNull(film.getActors());
			assertEquals(0, film.getActors().size());
			Film found = repository.findOneFilmByTitle(title);
			assertEquals(film, found);
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteFilmByTitle(title);
		}
	}

	@Test
	void testNotDuplicatedFilmWithExistingLanguageAndNullActorsListAndValidParameters() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String title = "Jdbc programming video";
		String description = "Jdbc video tutorial";
		int releaseYear = 2019;
		String languageName = "English";
		short length = 60;
		List<Integer> actorIds = null;

		try {
			Film film = repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds);
			assertNotNull(film);
			assertEquals(title, film.getTitle());
			assertEquals(description, film.getDescription());
			assertEquals(Integer.valueOf(releaseYear), Integer.valueOf(film.getYear()));
			assertNotNull(film.getLanguage());
			assertEquals(languageName, film.getLanguage().getName());
			assertEquals(Integer.valueOf(length), Integer.valueOf(film.getLength()));
			assertNotNull(film.getActors());
			assertEquals(0, film.getActors().size());
			Film found = repository.findOneFilmByTitle(title);
			assertEquals(film, found);
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteFilmByTitle(title);
		}
	}


	@Test
	void testDuplicatedFilmWithExistingLanguageAndExistingActorsAndValidParameters() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String title = "Jdbc programming video";
		String description = "Jdbc video tutorial";
		int releaseYear = 2019;
		String languageName = "English";
		short length = 60;
		List<Actor> actors = Arrays.asList(
				new Actor[] {
						new Actor(1, "Penelope", "Guiness"),
						new Actor(2, "Nick", "Wahlberg")
				});
		List<Integer> actorIds = Arrays.asList(new Integer[] {1, 2});

		try {
			Film film = repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds);
			assertNotNull(film);
			assertEquals(title, film.getTitle());
			assertEquals(description, film.getDescription());
			assertEquals(Integer.valueOf(releaseYear), Integer.valueOf(film.getYear()));
			assertNotNull(film.getLanguage());
			assertEquals(languageName, film.getLanguage().getName());
			assertEquals(Integer.valueOf(length), Integer.valueOf(film.getLength()));
			assertNotNull(film.getActors());
			assertEquals(2, film.getActors().size());
			assertTrue(film.getActors().contains(actors.get(0)));
			assertTrue(film.getActors().contains(actors.get(1)));
			Film found = repository.findOneFilmByTitle(title);
			assertEquals(film, found);
			assertNull(repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteFilmByTitle(title);
		}
	}


	@Test
	void testInvalidTitle() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String title = null;
		String description = "Jdbc video tutorial";
		int releaseYear = 2019;
		String languageName = "English";
		short length = 60;
		List<Integer> actorIds = Arrays.asList(new Integer[] {1, 2});

		try {
			Film film = repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds);
			assertNull(film);
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteFilmByTitle(title);
		}
	}

	@Test
	void testInvalidDescription() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String title = "Jdbc programming video";
		String description = null;
		int releaseYear = 2019;
		String languageName = "English";
		short length = 60;
		List<Integer> actorIds = Arrays.asList(new Integer[] {1, 2});

		try {
			Film film = repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds);
			assertNull(film);
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteFilmByTitle(title);
		}
	}

	@Test
	void testInvalidReleaseYear() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String title = "Jdbc programming video";
		String description = "Jdbc video tutorial";
		int releaseYear = 1895;
		String languageName = "English";
		short length = 60;
		List<Integer> actorIds = Arrays.asList(new Integer[] {1, 2});

		try {
			Film film = repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds);
			assertNull(film);
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteFilmByTitle(title);
		}
	}

	@Test
	void testInvalidLength() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String title = "Jdbc programming video";
		String description = "Jdbc video tutorial";
		int releaseYear = 2019;
		String languageName = "English";
		short length = -1;
		List<Integer> actorIds = Arrays.asList(new Integer[] {1, 2});

		try {
			Film film = repository.createAndStoreNewFilm(title, description, releaseYear, languageName, length, actorIds);
			assertNull(film);
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteFilmByTitle(title);
		}
	}
}
