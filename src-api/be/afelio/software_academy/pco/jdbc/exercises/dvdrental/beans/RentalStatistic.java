package be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans;

import java.util.Objects;

public class RentalStatistic {

    protected String filmTitle;
    protected int rentalCount;

    public RentalStatistic() {}

    public RentalStatistic(String filmTitle, int rentalCount) {
        this.filmTitle = filmTitle;
        this.rentalCount = rentalCount;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public int getRentalCount() {
        return rentalCount;
    }

    public void setRentalCount(int rentalCount) {
        this.rentalCount = rentalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalStatistic that = (RentalStatistic) o;
        return rentalCount == that.rentalCount &&
                Objects.equals(filmTitle, that.filmTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmTitle, rentalCount);
    }

    @Override
    public String toString() {
        return "RentalStatistic{" +
                "filmTitle='" + filmTitle + '\'' +
                ", rentalCount=" + rentalCount +
                '}';
    }
}
