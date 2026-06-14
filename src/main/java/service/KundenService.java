package service;

import com.j256.ormlite.dao.Dao;
import Model.Kunde;

import java.sql.SQLException;
import java.util.List;

public class KundenService {

    private Dao<Kunde, Integer> dao;

    public KundenService(Dao<Kunde, Integer> dao) {
        this.dao = dao;
    }

    public boolean registrieren(String name, String email, String passwort) throws SQLException {
        // Pruefen ob E-Mail schon existiert
        List<Kunde> vorhanden = dao.queryForEq("email", email);
        if (!vorhanden.isEmpty()) {
            System.out.println("Fehler: E-Mail bereits vergeben.");
            return false;
        }
        dao.create(new Kunde(name, email, passwort));
        System.out.println("Registrierung erfolgreich!");
        return true;
    }

    public Kunde einloggen(String email, String passwort) throws SQLException {
        List<Kunde> ergebnis = dao.queryForEq("email", email);
        if (ergebnis.isEmpty()) {
            System.out.println("Fehler: E-Mail nicht gefunden.");
            return null;
        }
        Kunde kunde = ergebnis.get(0);
        if (!kunde.getPasswort().equals(passwort)) {
            System.out.println("Fehler: Falsches Passwort.");
            return null;
        }
        System.out.println("Login erfolgreich! Willkommen, " + kunde.getName());
        return kunde;
    }
}