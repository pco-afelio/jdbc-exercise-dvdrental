package be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans;

import java.util.Objects;

public class Language {

    protected Integer id;
    protected String name;

    public Language() {}

    public Language(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(id, language.id) &&
                Objects.equals(name, language.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
