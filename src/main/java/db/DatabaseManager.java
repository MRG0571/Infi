package db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import Model.Ausleihe;
import Model.Kunde;
import Model.Medium;

import java.sql.SQLException;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:mediathek.db";

    private ConnectionSource connectionSource;
    private Dao<Kunde, Integer> kundenDao;
    private Dao<Medium, Integer> medienDao;
    private Dao<Ausleihe, Integer> ausleihenDao;

    public DatabaseManager() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL);

        // Tabellen anlegen (nur wenn sie noch nicht existieren)
        TableUtils.createTableIfNotExists(connectionSource, Kunde.class);
        TableUtils.createTableIfNotExists(connectionSource, Medium.class);
        TableUtils.createTableIfNotExists(connectionSource, Ausleihe.class);

        // DAOs erstellen
        kundenDao = DaoManager.createDao(connectionSource, Kunde.class);
        medienDao = DaoManager.createDao(connectionSource, Medium.class);
        ausleihenDao = DaoManager.createDao(connectionSource, Ausleihe.class);
    }

    public Dao<Kunde, Integer> getKundenDao() { return kundenDao; }
    public Dao<Medium, Integer> getMedienDao() { return medienDao; }
    public Dao<Ausleihe, Integer> getAusleihenDao() { return ausleihenDao; }

    public void close() throws Exception {
        if (connectionSource != null) {
            connectionSource.close();
        }
    }
}