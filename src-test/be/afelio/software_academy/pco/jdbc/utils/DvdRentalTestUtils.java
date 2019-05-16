package be.afelio.software_academy.pco.jdbc.utils;

public class DvdRentalTestUtils extends JdbcTestUtils {

    public DvdRentalTestUtils(String url, String user, String password) {
        super(url, user, password);
    }

    public String findOneStaffPasswordByEmail(String email) {
        return queryForObject("select password from staff where email = ?", String.class, email);
    }

    public void updateStaffPasswordForEmail(String email, String password) {
        update("update staff set password = ? where email = ?", password, email);
    }

    public Integer findCategoryByName(String categoryName) {
        return queryForObject("select category_id from category where name = ?", Integer.class, categoryName);
    }

    public void createCategoryForFilm(String categoryName, String filmTitle) {
        Integer categoryId = queryForObject("select category_id from category where name = ?", Integer.class, categoryName);
        if (categoryId != null) {
            throw new RuntimeException("duplicated category name");
        }
        Integer filmId = queryForObject("select film_id from film where title = ?", Integer.class, filmTitle);
        if (filmId == null) {
            throw new RuntimeException("film not found");
        }
        update("insert into category(name) values(?)", categoryName);
        categoryId = queryForObject("select category_id from category where name = ?", Integer.class, categoryName);
        if (categoryId == null) {
            throw new RuntimeException("category could not be inserted");
        }
        update("insert into film_category(film_id, category_id) values(?, ?)", filmId, categoryId);
    }

    public void deleteCategory(String categoryName) {
        update("delete from film_category where category_id = (select category_id from category where name = ?)", categoryName);
        update("delete from category where name = ?", categoryName);
    }

    public Integer findOneActorIdByFirstnameAndName(String firstname, String name) {
        return queryForObject("select actor_id from actor where first_name = ? and last_name = ?", Integer.class, firstname, name);
    }

    public void deleteActorByFirstnameAndName(String firstname, String name) {
        update("delete from film_actor where actor_id = (select actor_id from actor where first_name = ? and last_name = ?)", firstname, name);
        update("delete from actor where first_name = ? and last_name = ?", firstname, name);
    }

    public void deleteFilmByTitle(String title) {
        update("delete from film_actor where film_id = (select film_id from film where title = ?)", title);
        update("delete from film where title = ?", title);
    }

    public void deleteLanguageByName(String name) {
        update("delete from language where name = ?", name);
    }
}
