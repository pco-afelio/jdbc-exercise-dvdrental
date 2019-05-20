package be.afelio.software_academy.pco.jdbc.exercises.dvdrental.impl;

import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.DvdRentalJdbcRepository;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Actor;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Film;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.beans.Language;
import be.afelio.software_academy.pco.jdbc.exercises.dvdrental.exceptions.DuplicatedActorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DvdRentalJdbcRepositoryImpl implements DvdRentalJdbcRepository {

    protected final String url;
    protected final String user;
    protected final String password;

    public DvdRentalJdbcRepositoryImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public String findOneCustomerEmailByCustomerFirstNameAndName(String firstName, String name) {
        String email = null;

        if (firstName != null && !firstName.isBlank() && name != null && !name.isBlank()) {
            String sql = "select email from customer where first_name = ? and last_name = ?";
            try (
                Connection c = getNewConnection();
                PreparedStatement st = c.prepareStatement(sql)
            ) {
                st.setString(1, firstName);
                st.setString(2, name);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        email = rs.getString(1);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return email;
    }

    @Override
    public List<String> findAllFilmTitlesByCategoryNameOrderByFilmTitle(String name) {
        List<String> list = null;

        if (name != null && !name.isBlank()) {
            String sql =
                    "select f.title " +
                    "from category c join film_category fc on fc.category_id = c.category_id " +
                    "                join film f on fc.film_id = f.film_id " +
                    "where c.name = ? order by f.title";
            try (
                Connection connection = getNewConnection();
                PreparedStatement query = connection.prepareStatement(sql)
            ) {
                query.setString(1, name);
                try (ResultSet rs = query.executeQuery()) {
                    list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(rs.getString(1));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            list = Collections.emptyList();
        }

        return list;
    }

    @Override
    public List<Actor> findAllActorsByActorFirstNameIgnoreCase(String firstName) {
        List<Actor> list;

        if (firstName != null && !firstName.isBlank()) {
            String sql =
                    "select actor_id, first_name, last_name " +
                    "from actor " +
                    "where lower(first_name) = lower(?)";
            try (
                Connection connection = getNewConnection();
                PreparedStatement query = connection.prepareStatement(sql)
            ) {
                query.setString(1, firstName);
                try (ResultSet rs = query.executeQuery()) {
                    list = new ArrayList<>();
                    while (rs.next()) {
                        Actor a = new Actor();
                        a.setId(rs.getInt(1));
                        a.setFirstName(rs.getString(2));
                        a.setLastName(rs.getString(3));
                        list.add(a);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            list = Collections.emptyList();
        }

        return list;
    }

    @Override
    public Film findOneFilmByTitle(String title) {
        Film film = null;

        if (title != null && !title.isBlank()) {
            try (
                Connection connection = getNewConnection();
            ) {
                film = findOneFilmByTitle(connection, title);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return film;
    }

    @Override
    public boolean updateStaffPasswordByEmail(String email, String newPassword) {
        boolean updated = false;

        if (email != null && !email.isBlank() && newPassword != null && !newPassword.isBlank()) {
            try (
                Connection connection = getNewConnection()
            ) {
                if (findOneEmailByPassword(connection, newPassword) == null) {
                    connection.setAutoCommit(true);
                    String sql = "update staff set password = ? where email = ?";
                    try (
                        PreparedStatement query = connection.prepareStatement(sql)
                    ) {
                        query.setString(1, newPassword);
                        query.setString(2, email);
                        query.executeUpdate();
                        updated = query.getUpdateCount() > 0;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return updated;
    }

    @Override
    public boolean deleteCategoryByName(String name) {
        boolean deleted = false;

        if (name != null && !name.isBlank()) {

            String sqlDeleteFilmCategory = "delete from film_category where category_id = (select category_id from category where name = ?)";
            String sqlDeleteCategory = "delete from category where name = ?";

            try (
                Connection connection = getNewConnection();
                PreparedStatement deleteFilmCategoryStatement = connection.prepareStatement(sqlDeleteFilmCategory);
                PreparedStatement deleteCategoryStatement = connection.prepareStatement(sqlDeleteCategory)
            ) {
                connection.setAutoCommit(false);
                deleteFilmCategoryStatement.setString(1, name);
                deleteFilmCategoryStatement.executeUpdate();
                deleteCategoryStatement.setString(1, name);
                deleteCategoryStatement.executeUpdate();
                connection.commit();
                deleted = deleteCategoryStatement.getUpdateCount() > 0;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return deleted;
    }

    @Override
    public void createAndStoreNewActor(String firstname, String lastname) throws DuplicatedActorException {
        if (firstname != null && !firstname.isBlank() && lastname != null && ! lastname.isBlank()) {
            try (
                Connection connection = getNewConnection()
            ) {
                connection.setAutoCommit(true);
                Actor duplicate = findOneActorByFirstnameAndName(connection, firstname, lastname);
                if (duplicate != null) {
                    throw new DuplicatedActorException();
                }
                storeActorAndReturnGeneratedId(connection, firstname, lastname);
            } catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Film createAndStoreNewFilm(String title, String description, int releaseYear, String languageName,
                                      short length, List<Integer> actorIds) {
        Film film = null;

        if (title != null && !title.isBlank() && description != null && !description.isBlank()
            && (languageName == null || !languageName.isBlank())
            && releaseYear > 1900 && length > 0) {
            try (Connection connection = getNewConnection()) {
                connection.setAutoCommit(false);
                try {
                    if (findOneFilmByTitle(connection, title) == null) {
                        boolean doInsert = true;
                        if (actorIds != null) {
                            for (int index = 0; doInsert && index < actorIds.size(); index++) {
                                Integer actorId = actorIds.get(index);
                                if (actorId == null || findOneActorById(connection, actorId) == null) {
                                    doInsert = false;
                                }
                            }
                        }
                        if (doInsert) {
                            Integer languageId = null;
                            if (languageName != null) {
                                Language language = findOneLanguageByName(connection, languageName);
                                if (language == null) {
                                    languageId = storeLanguageAndReturnGeneratedId(connection, languageName);
                                } else {
                                    languageId = language.getId();
                                }
                            }
                            int filmId = storeFilmAndReturnGeneratedId(connection, title, description, releaseYear,
                                    languageId, length);
                            if (actorIds != null && !actorIds.isEmpty()) {
                                storeFilmActorLinks(connection, filmId, actorIds);
                            }
                            connection.commit();
                            film = findOneFilmByTitle(connection, title);
                        }
                    }
                } catch(SQLException e) {
                    if (connection != null && !connection.isClosed()) {
                        connection.rollback();
                        throw e;
                    }
                }
            } catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return film;
    }

    protected Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    protected String findOneEmailByPassword(Connection connection, String password)
        throws SQLException {
        String email = null;

        String sql = "select email from staff where password = ?";
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, password);
            try (
                ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (resultSet.next()) {
                    email = resultSet.getString(1);
                }
            }
        }

        return email;
    }

    protected Language findOneLanguageByName(Connection connection, String name) throws SQLException {
        Language language = null;
        try (
                PreparedStatement statement = connection.prepareStatement("select language_id, name from language where name = ?")
        ) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    language = new Language(id, name);
                }
            }
        }
        return language;
    }

    protected Actor findOneActorById(Connection connection, Integer id) throws SQLException {
        Actor actor = null;
        try (
                PreparedStatement query = connection.prepareStatement("select * from actor where actor_id = ?")
        ) {
            query.setInt(1, id);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    actor = createActorFromResultSet(rs);
                }
            }
        }
        return actor;
    }

    protected Actor findOneActorByFirstnameAndName(Connection connection, String firstname, String name) throws SQLException {
        Actor actor = null;
        try (
            PreparedStatement query = connection.prepareStatement("select * from actor where first_name = ? and last_name = ?")
        ) {
            query.setString(1, firstname);
            query.setString(2, name);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    actor = createActorFromResultSet(rs);
                }
            }
        }
        return actor;
    }

    protected Film findOneFilmByTitle(Connection connection, String title) throws SQLException {
        Film film = null;

        String sql =
                "select f.film_id, f.title, f.description, f.release_year, f.length, " +
                        "       l.language_id, trim(l.name) as name, " +
                        "       a.actor_id, a.first_name, a.last_name " +
                        "from film f left join language l on f.language_id = l.language_id " +
                        "            left join film_actor fa on f.film_id = fa.film_id " +
                        "            left join actor a on fa.actor_id = a.actor_id " +
                        "where f.title = ?";
        try (
            PreparedStatement query = connection.prepareStatement(sql)
        ) {
            query.setString(1, title);
            try (ResultSet rs = query.executeQuery()) {
                film = createFilmFromResultSet(rs);
            }
        }

        return film;
    }

    protected Integer storeActorAndReturnGeneratedId(Connection connection, String firstname, String name) throws SQLException {
        Integer id = null;

        try (
            PreparedStatement statement = connection.prepareStatement(
                    "insert into actor(first_name, last_name) values(?, ?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, firstname);
            statement.setString(2, name);
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        }
        return id;
    }

    protected Integer storeLanguageAndReturnGeneratedId(Connection connection, String name) throws SQLException {
        Integer id = null;
        try (
            PreparedStatement statement = connection.prepareStatement(
                    "insert into language(name) values(?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, name);
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        }
        return id;
    }

    protected Integer storeFilmAndReturnGeneratedId(Connection connection, String title, String description,
                                        int releaseYear, Integer languageId, short length) throws SQLException {
        Integer id = null;
        String sql = "insert into film(title, description, release_year, language_id, length) " +
                " values(?, ?, ?, ?, ?)";
        try (
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setInt(3, releaseYear);
            if (languageId != null) {
                statement.setInt(4, languageId);
            } else {
                statement.setNull(4, java.sql.Types.INTEGER);
            }
            statement.setShort(5, length);
            statement.executeUpdate();
            try (
                ResultSet rs = statement.getGeneratedKeys()
            ) {
                rs.next();
                id = rs.getInt(1);
            }
        }
        return id;
    }

    protected void storeFilmActorLinks(Connection connection, int filmId, List<Integer> actorIds) throws SQLException {
        try (
            PreparedStatement statement = connection.prepareStatement(
                "insert into film_actor(film_id, actor_id) values(?, ?)")
        ) {
            statement.setInt(1, filmId);
            for(Integer actorId: actorIds) {
                statement.setInt(2, actorId);
                statement.executeUpdate();
            }
        }
    }

    protected Actor createActorFromResultSet(ResultSet rs) throws SQLException {
        Actor actor = new Actor();
        actor.setId(rs.getInt("actor_id"));
        actor.setFirstName(rs.getString("first_name"));
        actor.setLastName(rs.getString("last_name"));
        return actor;
    }

    protected Film createFilmFromResultSet(ResultSet rs) throws SQLException {
        Film film = null;

        if (rs.next()) {
            film = new Film();
            film.setId(rs.getInt("film_id"));
            film.setTitle(rs.getString("title"));
            film.setDescription(rs.getString("description"));
            film.setYear(rs.getInt("release_year"));
            film.setLength(rs.getInt("length"));

            int language_id = rs.getInt("language_id");
            if (!rs.wasNull()) {
                Language language = new Language();
                language.setId(language_id);
                language.setName(rs.getString("name"));
                film.setLanguage(language);
            }

            rs.getInt("actor_id");
            if (!rs.wasNull()) {
                film.setActors(new ArrayList<>());

                Actor actor = createActorFromResultSet(rs);
                film.getActors().add(actor);

                while (rs.next()) {
                    actor = createActorFromResultSet(rs);
                    film.getActors().add(actor);
                }
            } else {
                film.setActors(Collections.emptyList());
            }
        }

        return film;
    }


}
