package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Actor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class _03_TestFindAllActorsByActorFirstNameIgnoreCase {

	@Test
	void testExistingFirstName() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<Actor> expected = Arrays.asList(actors);
		List<Actor> actual = repository.findAllActorsByActorFirstNameIgnoreCase("Christian");

		assertNotNull(actual);
		Collections.sort(actual, comparator);

		assertEquals(expected, actual);
	}

	@Test
	void testExistingFirstNameCaseSensitive() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<Actor> expected = Arrays.asList(actors);
		List<Actor> actual = repository.findAllActorsByActorFirstNameIgnoreCase("cHrIsTiAn");

		assertNotNull(actual);
		Collections.sort(actual, comparator);

		assertEquals(expected, actual);
	}

	@Test
	void testNonExistingFirstName() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<Actor> actual = repository.findAllActorsByActorFirstNameIgnoreCase("xxx");

		assertNotNull(actual);
		assertEquals(0, actual.size());
	}

	@Test
	void testNullFirstName() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<Actor> actual = repository.findAllActorsByActorFirstNameIgnoreCase(null);

		assertNotNull(actual);
		assertEquals(0, actual.size());
	}

	Actor[] actors = new Actor[] {
			new Actor(58, "Christian", "Akroyd"),
			new Actor(10, "Christian", "Gable"),
			new Actor(61, "Christian", "Neeson")
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
