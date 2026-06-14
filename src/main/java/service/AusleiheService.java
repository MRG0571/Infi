package service;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import Model.Ausleihe;
import Model.Kunde;
import Model.Medium;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AusleiheService {

    private Dao<Ausleihe, Integer> dao;
    private MediumService mediumService;

    public AusleiheService(Dao<Ausleihe, Integer> dao, MediumService mediumService) {
        this.dao = dao;
        this.mediumService = mediumService;
    }

    public boolean ausleihen(Kunde kunde, int mediumId, int tage) throws SQLException {
        Medium medium = mediumService.findById(mediumId);
        if (medium == null || !medium.isVerfuegbar()) {
            System.out.println("Fehler: Medium nicht verfuegbar.");
            return false;
        }

        Date start = new Date();
        Date ablauf = new Date(start.getTime() + (long) tage * 24 * 60 * 60 * 1000);

        dao.create(new Ausleihe(kunde, medium, start, ablauf));

        // Medium auf "ausgeliehen" setzen
        medium.setVerfuegbar(false);
        mediumService.update(medium);

        System.out.println("Ausgeliehen bis: " + String.format("%tF", ablauf));
        return true;
    }

    public List<Ausleihe> meineAusleihen(Kunde kunde) throws SQLException {
        QueryBuilder<Ausleihe, Integer> qb = dao.queryBuilder();
        qb.where().eq("kunde_id", kunde.getId()).and().eq("aktiv", true);
        return qb.query();
    }
}