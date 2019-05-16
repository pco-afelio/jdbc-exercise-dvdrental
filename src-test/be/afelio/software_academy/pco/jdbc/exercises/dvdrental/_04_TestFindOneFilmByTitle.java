package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Actor;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Film;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Language;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class _04_TestFindOneFilmByTitle {

	@Test
	void testExistingTitle() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		Film expected = new Film(
				98, "Bright Encounters",
				"A Fateful Yarn of a Lumberjack And a Feminist who must Conquer a Student in A Jet Boat",
				2006, 73,
				new Language(1, "English"),
				Arrays.asList(actors));
		Film actual = repository.findOneFilmByTitle("Bright Encounters");

		assertNotNull(actual);
		assertEquals(expected.getId(), expected.getId());
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getLength(), actual.getLength());
		assertEquals(expected.getYear(), actual.getYear());
		assertEquals(expected.getLanguage(), actual.getLanguage());

		List<Actor> list = actual.getActors();
		assertNotNull(list);
		list.sort(comparator);
		assertEquals(expected.getActors(), list);
	}

	@Test
	void testNonExistingTitle() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		Film actual = repository.findOneFilmByTitle("xxx");
		assertNull(actual);
	}

	@Test
	void testNullTitle() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		Film actual = repository.findOneFilmByTitle(null);
		assertNull(actual);
	}

	@Test
	void testExistingTitleWithoutActors() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		Film expected = new Film(
				257, "Drumline Cyclone",
				"A Insightful Panorama of a Monkey And a Sumo Wrestler who must Outrace a Mad Scientist in The Canadian Rockies",
				2006, 110,
				new Language(1, "English"),
				Collections.emptyList());
		Film actual = repository.findOneFilmByTitle("Drumline Cyclone");

		assertNotNull(actual);
		assertEquals(expected.getId(), expected.getId());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getLength(), actual.getLength());
		assertEquals(expected.getYear(), actual.getYear());
		assertEquals(expected.getLanguage(), actual.getLanguage());
		assertEquals(expected.getActors(), actual.getActors());
	}

	Actor[] actors = new Actor[] {
			new Actor(125, "Albert", "Nolte"),
			new Actor(111, "Cameron", "Zellweger"),
			new Actor(194, "Meryl", "Allen"),
			new Actor(187, "Renee", "Ball")
	};

	Comparator<Actor> comparator = (Actor a, Actor b) -> {
		if (a == null) return -1;
		if (b == null) return 1;
		if (a.getFirstName() == null && b.getFirstName() != null) return -1;
		if (a.getFirstName() != null && b.getFirstName() == null) return 1;
		int compare = a.getFirstName().compareTo(b.getFirstName());
		if (compare != 0) return compare;
		if (a.getLastName() == null && b.getLastName() != null) return -1;
		if (a.getLastName() != null && b.getLastName() == null) return 1;
		return a.getLastName().compareTo(b.getLastName());
	};
}
