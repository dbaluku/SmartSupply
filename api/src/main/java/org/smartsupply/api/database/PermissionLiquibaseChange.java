/**
 *
 */
package org.smartsupply.api.database;

import liquibase.FileOpener;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.InvalidChangeDefinitionException;
import liquibase.exception.SetupException;
import liquibase.exception.UnsupportedChangeException;
import org.smartsupply.api.security.PermissionAnnotation;
import org.smartsupply.api.security.PermissionConstants;
import org.smartsupply.model.enums.RecordStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionLiquibaseChange implements CustomTaskChange {

    public static final String INSERT_PERM_QUERY = "INSERT INTO permission (id, description, name, record_status) "
            + "VALUES (?, ?, ?, ?)";
    public static final String PERM_BY_ID_QUERY = "SELECT 1 FROM permission WHERE id=?";
    public static final String PERM_BY_NAME_AND_ID_QUERY = "SELECT 1 FROM permission WHERE id=? and name = ?";
    public static final String UPDATE_PERM_QUERY = "UPDATE permission set name = ?, description = ? where id = ?";
    private Logger log = LoggerFactory.getLogger(PermissionLiquibaseChange.class);

    @Override
    public void execute(Database database) throws CustomChangeException, UnsupportedChangeException {
        log.debug("Extracting fields from >> class org.smartsupply.security.PermissionConstants");
        Field[] fields = PermissionConstants.class.getFields();
        if (fields != null) {
            for (Field field : fields) {
                log.debug("Extracting annotation from field >> " + field.getName());
                PermissionAnnotation annotation = field.getAnnotation(PermissionAnnotation.class);
                if (annotation != null) {
                    try {
                        if (!idExists(database, annotation.id())) {
                            insert(database, annotation, field);
                        } else {
                            if (!namedAndIdExist(database, annotation, field)) {
                                update(database, annotation, field);
                            }
                        }
                    } catch (SQLException e) {
                        log.error(this.getClass().getName(), e);
                    } catch (IllegalArgumentException e) {
                        log.error(this.getClass().getName(), e);
                    } catch (IllegalAccessException e) {
                        log.error(this.getClass().getName(), e);
                    }
                }
            }
        }
    }

    private void update(Database database, PermissionAnnotation annotation, Field field)
            throws SQLException, IllegalAccessException {
        PreparedStatement statement = database.getConnection().prepareStatement(UPDATE_PERM_QUERY);
        statement.setString(1, (String) field.get(null));
        statement.setString(2, annotation.description());
        statement.setString(3, annotation.id());
        statement.execute();
    }


    private void insert(Database database, PermissionAnnotation annotation, Field field) throws SQLException, IllegalAccessException {
        DatabaseConnection connection = database.getConnection();
        connection.setAutoCommit(true);
        PreparedStatement statement = connection.prepareStatement(INSERT_PERM_QUERY);
        statement.setString(1, annotation.id());
        statement.setString(2, annotation.description());
        statement.setString(3, (String) field.get(null));
        statement.setInt(4, RecordStatus.ACTIVE.ordinal());
        statement.execute();
    }

    private boolean idExists(Database database, String id) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement(PERM_BY_ID_QUERY);
        statement.setString(1, id);
        return exists(statement);
    }

    private boolean namedAndIdExist(Database database, PermissionAnnotation annotation, Field field)
            throws SQLException, IllegalAccessException {
        PreparedStatement statement = database.getConnection().prepareStatement(PERM_BY_NAME_AND_ID_QUERY);
        statement.setString(1, annotation.id());
        statement.setString(2, (String) field.get(null));
        return exists(statement);
    }

    private boolean exists(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        boolean exists = resultSet != null && resultSet.next();
        return exists;
    }


    @Override
    public String getConfirmationMessage() {
        return null;
    }

    @Override
    public void setFileOpener(FileOpener arg0) {
    }

    @Override
    public void setUp() throws SetupException {
    }

    @Override
    public void validate(Database arg0) throws InvalidChangeDefinitionException {
    }
}
