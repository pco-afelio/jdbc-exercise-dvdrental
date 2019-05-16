package be.afelio.software_academy.pco.jdbc.exercises.dvdrental;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class _02_TestFindAllFilmTitlesByCategoryNameOrderByFilmTitle {

	@Test
	void testExistingCategory() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<String> expected = Arrays.asList(titles);
		List<String> actual = repository.findAllFilmTitlesByCategoryNameOrderByFilmTitle("Family");

		assertEquals(expected, actual);
	}

	@Test
	void testNonExistingCategory() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<String> actual = repository.findAllFilmTitlesByCategoryNameOrderByFilmTitle("xxx");

		assertNotNull(actual);
		assertEquals(0, actual.size());
	}

	@Test
	void testNullCategory() {
		DvdRentalJdbcRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<String> actual = repository.findAllFilmTitlesByCategoryNameOrderByFilmTitle(null);

		assertNotNull(actual);
		assertEquals(0, actual.size());
	}

	String[] titles = new String[] {
			"African Egg", "Apache Divine", "Atlantis Cause", "Baked Cleopatra", "Bang Kwai",
			"Bedazzled Married", "Bilko Anonymous", "Blanket Beverly", "Blood Argonauts", "Blues Instinct",
			"Braveheart Human", "Chasing Fight", "Chisum Behavior", "Chocolat Harry", "Confused Candles",
			"Conversation Downhill", "Date Speed", "Dinosaur Secretary", "Dumbo Lust", "Earring Instinct",
			"Effect Gladiator", "Feud Frogmen", "Finding Anaconda", "Gables Metropolis", "Gandhi Kwai",
			"Gladiator Westward", "Grease Youth", "Half Outfield", "Hocus Frida", "Homicide Peach", "House Dynamite",
			"Hunting Musketeers", "Indian Love", "Jason Trap", "Jedi Beneath", "Killer Innocent", "King Evolution",
			"Lolita World", "Louisiana Harry", "Maguire Apache", "Manchurian Curtain", "Movie Shakespeare",
			"Music Boondock", "Natural Stock", "Network Peak", "Odds Boogie", "Opposite Necklace", "Pilot Hoosiers",
			"Pittsburgh Hunchback", "President Bang", "Prix Undefeated", "Rage Games", "Range Moonwalker",
			"Remember Diary", "Resurrection Silverado", "Robbery Bright", "Rush Goodfellas", "Secrets Paradise",
			"Sensibility Rear", "Siege Madre", "Slums Duck", "Soup Wisdom", "Spartacus Cheaper", "Spinal Rocky",
			"Splash Gump", "Sunset Racer", "Super Wyoming", "Virtual Spoilers", "Willow Tracy"
	};

}
