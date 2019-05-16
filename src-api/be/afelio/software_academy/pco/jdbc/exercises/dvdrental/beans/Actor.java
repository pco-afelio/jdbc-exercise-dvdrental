package be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans;

import java.util.Objects;

public class Actor {

    protected Integer id;
    protected String firstName;
    protected String lastName;

    public Actor() {}

    public Actor(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id) &&
                Objects.equals(firstName, actor.firstName) &&
                Objects.equals(lastName, actor.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
