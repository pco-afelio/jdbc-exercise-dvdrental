package be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans;

import java.util.List;
import java.util.Objects;

public class Film {

	protected Integer id;
	protected String title;
	protected String description;
	protected Integer year;
	protected Integer length;
	protected Language language;
	protected List<Actor> actors;

	public Film() {}

	public Film(Integer id, String title, String description, Integer year, Integer length,
				Language language, List<Actor> actors) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.year = year;
		this.length = length;
		this.language = language;
		this.actors = actors;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Film film = (Film) o;
		return Objects.equals(id, film.id) &&
				Objects.equals(title, film.title) &&
				Objects.equals(description, film.description) &&
				Objects.equals(year, film.year) &&
				Objects.equals(length, film.length) &&
				Objects.equals(language, film.language) &&
				Objects.equals(actors, film.actors);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, description, year, length, language, actors);
	}

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", length=" + length +
                ", language=" + language +
                ", actors=" + actors +
                '}';
    }
}
