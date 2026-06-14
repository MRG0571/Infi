import db.DatabaseManager;
import Model.Ausleihe;
import Model.Kunde;
import Model.Medium;
import service.AusleiheService;
import service.KundenService;
import service.MediumService;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Kunde eingeloggterKunde = null;

    public static void main(String[] args) throws Exception {
        DatabaseManager db = new DatabaseManager();
        KundenService kundenService = new KundenService(db.getKundenDao());
        MediumService mediumService = new MediumService(db.getMedienDao());
        AusleiheService ausleiheService = new AusleiheService(db.getAusleihenDao(), mediumService);

        mediumService.beispieldatenEinfuegen();

        System.out.println("=== Mediathek-Verwaltung ===");

        boolean running = true;
        while (running) {
            if (eingeloggterKunde == null) {
                System.out.println("\n1) Registrieren");
                System.out.println("2) Einloggen");
                System.out.println("0) Beenden");
                System.out.print("Wahl: ");
                int wahl = Integer.parseInt(scanner.nextLine());

                switch (wahl) {
                    case 1 -> registrieren(kundenService);
                    case 2 -> einloggen(kundenService);
                    case 0 -> running = false;
                    default -> System.out.println("Ungueltige Eingabe.");
                }
            } else {
                System.out.println("\n--- Eingeloggt als " + eingeloggterKunde.getName() + " ---");
                System.out.println("1) Alle Medien anzeigen");
                System.out.println("2) Medien filtern");
                System.out.println("3) Medium ausleihen");
                System.out.println("4) Meine Ausleihen");
                System.out.println("5) Ausloggen");
                System.out.println("0) Beenden");
                System.out.print("Wahl: ");
                int wahl = Integer.parseInt(scanner.nextLine());

                switch (wahl) {
                    case 1 -> alleAnzeigen(mediumService);
                    case 2 -> filtern(mediumService);
                    case 3 -> ausleihen(ausleiheService, mediumService);
                    case 4 -> meineAusleihen(ausleiheService);
                    case 5 -> { eingeloggterKunde = null; System.out.println("Ausgeloggt."); }
                    case 0 -> running = false;
                    default -> System.out.println("Ungueltige Eingabe.");
                }
            }
        }

        db.close();
        System.out.println("Auf Wiedersehen!");
    }

    static void registrieren(KundenService ks) throws Exception {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("E-Mail: ");
        String email = scanner.nextLine();
        System.out.print("Passwort: ");
        String passwort = scanner.nextLine();
        ks.registrieren(name, email, passwort);
    }

    static void einloggen(KundenService ks) throws Exception {
        System.out.print("E-Mail: ");
        String email = scanner.nextLine();
        System.out.print("Passwort: ");
        String passwort = scanner.nextLine();
        eingeloggterKunde = ks.einloggen(email, passwort);
    }

    static void alleAnzeigen(MediumService ms) throws Exception {
        List<Medium> medien = ms.alleAnzeigen();
        System.out.println("\nID | Titel | Autor | Genre | Typ | Status");
        System.out.println("------------------------------------------------------------");
        for (Medium m : medien) {
            System.out.println(m);
        }
    }

    static void filtern(MediumService ms) throws Exception {
        System.out.print("Titel (leer = alle): ");
        String titel = scanner.nextLine();
        System.out.print("Autor (leer = alle): ");
        String autor = scanner.nextLine();
        System.out.print("Genre (leer = alle): ");
        String genre = scanner.nextLine();
        System.out.print("Nur verfuegbare? (j/n/leer): ");
        String v = scanner.nextLine();
        Boolean nurVerfuegbar = v.equals("j") ? true : v.equals("n") ? false : null;

        List<Medium> medien = ms.filtern(
                titel.isEmpty() ? null : titel,
                autor.isEmpty() ? null : autor,
                genre.isEmpty() ? null : genre,
                nurVerfuegbar);

        System.out.println("\nErgebnisse:");
        System.out.println("------------------------------------------------------------");
        if (medien.isEmpty()) {
            System.out.println("Keine Ergebnisse.");
        } else {
            for (Medium m : medien) {
                System.out.println(m);
            }
        }
    }

    static void ausleihen(AusleiheService as, MediumService ms) throws Exception {
        alleAnzeigen(ms);
        System.out.print("Medium-ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ausleihdauer in Tagen: ");
        int tage = Integer.parseInt(scanner.nextLine());
        as.ausleihen(eingeloggterKunde, id, tage);
    }

    static void meineAusleihen(AusleiheService as) throws Exception {
        List<Ausleihe> ausleihen = as.meineAusleihen(eingeloggterKunde);
        System.out.println("\nMeine aktiven Ausleihen:");
        System.out.println("------------------------------------------------------------");
        if (ausleihen.isEmpty()) {
            System.out.println("Keine aktiven Ausleihen.");
        } else {
            for (Ausleihe a : ausleihen) {
                System.out.println(a.getMedium().getTitel() + " | Ablauf: "
                        + String.format("%tF", a.getAblaufDatum()));
            }
        }
    }
}