package lab2.controller.dao;

import com.sun.istack.internal.NotNull;
import lab2.model.exceptions.NotEnoughDataException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static lab2.controller.dao.JDBCVars.getConnection;

public abstract class AbstractDAO<T> {

    abstract Logger getLogger();

    abstract String getInsert();

    abstract String getSelectById();

    abstract String getSelectAll();

    abstract String getDelete();

    abstract String getUpdate();

    abstract String getFind();

    /**
     * Inserts object fields (all but id) into a prepared statement
     *
     * @param preparedStatement - statement to be modified
     * @param object            - object which fields will be inserted into the prepared statement
     */
    abstract void setStatement(@NotNull PreparedStatement preparedStatement, @NotNull T object)
            throws SQLException, NotEnoughDataException;

    /**
     * Inserts an ID into a prepared statement
     *
     * @param preparedStatement - statement to be modified
     * @param object            - object which id field will be inserted into the prepared statement
     */
    abstract void setUpdateStatementId(@NotNull PreparedStatement preparedStatement, @NotNull T object)
            throws SQLException;

    /**
     * Gets values from result set and sets respective fields of an object
     *
     * @param rs - set of results from executing query
     * @return returns (new!) object created with fields, taken from result set passed as a parameter
     */
    abstract T setObjectParams(@NotNull ResultSet rs) throws SQLException;

    /**
     * Takes existing object and sets it's 'id' field to id field taken from result set
     *
     * @param rs     - set of results from executing query
     * @param object - object to be modified
     */
    abstract void setObjectId(@NotNull ResultSet rs, @NotNull T object) throws SQLException;


    /**
     * Inserts object into a database
     *
     * @param object - object to be inserted into database
     *               (object's id doesn't matter and is assigned automatically)
     */
    public void insert(@NotNull T object) {
        Logger logger = getLogger();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(getInsert())) {
            logger.info("Inserting into DB");
            setStatement(ps, object);

            logger.info("Executing statement: " + ps);
            ps.executeUpdate();
        } catch (SQLException | NotEnoughDataException e) {
            logger.error(e.getMessage());
        }

        logger.info("Insert: success");
    }

    /**
     * Selects and entry from database (hotel) by its id.
     *
     * @param id - entry id
     * @return returns corresponding object from database
     * (with all fields, including 'id'); if object wasn't found, returns NULL
     */
    public T selectById(int id) {
        Logger logger = getLogger();
        T object = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(getSelectById())) {
            ps.setInt(1, id);
            logger.info("Executing statement: " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                object = setObjectParams(rs);
                setObjectId(rs, object);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        logger.info("Select by id: success");

        return object;
    }

    /**
     * Selects all entries from database and returns in an ArrayList.
     *
     * @return returns all objects from database in a list;
     * if object wasn't found, returns an empty ArrayList
     */
    public List<T> selectAll() {
        Logger logger = getLogger();
        List<T> objectList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(getSelectAll())) {
            logger.info("Executing statement: " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                T object = setObjectParams(rs);
                setObjectId(rs, object);
                objectList.add(object);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        logger.info("Select all: success");

        return objectList;
    }

    public List<T> find(@NotNull int id) {
        Logger logger = getLogger();
        List<T> objectList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(getFind())) {
            ps.setInt(1, id);
            logger.info("Executing statement: " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                T object = setObjectParams(rs);
                setObjectId(rs, object);
                objectList.add(object);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        logger.info("Select all: success");

        return objectList;
    }

    /**
     * Deletes an object by its id
     *
     * @param id - object id
     * @return returns TRUE if update was successful, FALSE if update failed
     */
    public boolean delete(int id) {
        boolean rowDeleted = false;
        Logger logger = getLogger();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(getDelete())) {
            ps.setInt(1, id);
            logger.info("Deleting DB entry: executing statement: " + ps);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        logger.info("Deleting DB entry: success");

        return rowDeleted;
    }

    /**
     * Updates an object by its id (id of the passed object)
     *
     * @param object - object to be inserted into database
     * @return returns TRUE if update was successful, FALSE if update failed
     */
    public boolean update(@NotNull T object) {
        boolean rowUpdated = false;
        Logger logger = getLogger();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(getUpdate())) {
            setStatement(ps, object);
            setUpdateStatementId(ps, object);

            logger.info("Executing statement: " + ps);
            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException | NotEnoughDataException e) {
            logger.error(e.getMessage());
        }

        logger.info("Update: success");

        return rowUpdated;
    }

    /**
     * returns id of the given object, -1 if object wasn't found
     *
     * @param object - object to be found
     * @return the id of the object in database
     */
    public int findId(@NotNull T object) {
        Logger logger = getLogger();
        int id = -1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getFind())) {

            setStatement(preparedStatement, object);
            logger.info("Executing statement: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException | NotEnoughDataException e) {
            logger.error(e.getMessage());
        }
        logger.info("Select by id: success");
        return id;
    }
}
