package org.smartsupply.api.database;

import liquibase.FileOpener;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.InvalidChangeDefinitionException;
import liquibase.exception.SetupException;
import liquibase.exception.UnsupportedChangeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MigrateUrlUserTypesChange implements CustomTaskChange {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(Database database) throws CustomChangeException, UnsupportedChangeException {

        try {
            String sqlSelect = "SELECT url_id, string_agg((user_type):: varchar, ',') as user_types\n" +
                    " FROM url_usertype GROUP BY url_id;";

            PreparedStatement pst = database.getConnection().prepareStatement(sqlSelect);
            pst.executeQuery();
            ResultSet rs = pst.getResultSet();

            Map<String, String> map = new HashMap<>();
            while ((rs != null) && (rs.next())) {
                String userTypes = rs.getString("user_types");
                String urlId = rs.getString("url_id");
                map.put(urlId, userTypes);
            }

            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                String sql = "update url set user_types_str = ? where id = ?";
                PreparedStatement pstResult = database.getConnection().prepareStatement(sql);

                Map.Entry pair = (Map.Entry) it.next();
                pstResult.setString(1, pair.getValue().toString());
                pstResult.setString(2, pair.getKey().toString());
                pstResult.executeUpdate();
            }

        } catch (Exception e) {
            log.error("Zibz", e);
        }
    }

    @Override
    public String getConfirmationMessage() {
        return null;
    }

    @Override
    public void setUp() throws SetupException {

    }

    @Override
    public void setFileOpener(FileOpener fileOpener) {

    }

    @Override
    public void validate(Database database) throws InvalidChangeDefinitionException {

    }
}
