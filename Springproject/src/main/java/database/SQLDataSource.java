package database;

import config.Config;
import com.zaxxer.hikari.HikariDataSource;
import java.beans.PropertyVetoException;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;


public final class SQLDataSource implements Closeable {
    private static final SQLDataSource INSTANCE = new SQLDataSource();

    private HikariDataSource dataSource;

    public static SQLDataSource getInstance() {
        return INSTANCE;
    }

    private SQLDataSource(){
        configureSQLServer(Config.getJdbcUrl(), Config.getSQLUsername(), Config.getSQLPassword());
    }

    public void configureSQLServer(String jdbcUrl, String username, String password) {
        dataSource = new HikariDataSource();

        dataSource.setDriverClassName( "org.postgresql.Driver" );
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(16);
    }

    public Connection getSQLConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void close() {
        dataSource.close();
    }
}
