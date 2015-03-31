package com.epi.movilizer.training.olivier;

import com.ibm.as400.access.AS400JDBCDriver;
import com.movilizer.util.config.IJdbcSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Peter.Grigoriev@movilizer.com
 */
public class JdbcUtils {
    public static Connection getConnection(IJdbcSettings jdbcSettings) throws SQLException {
        DriverManager.registerDriver(new AS400JDBCDriver());
        return DriverManager.getConnection(jdbcSettings.getUrl(), jdbcSettings.getUser(), jdbcSettings.getPassword());
    }

}
