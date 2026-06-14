package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "medien")
public class Medium {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String titel;

    @DatabaseField
    private String autor;

    @DatabaseField
    private String genre;

    @DatabaseField(canBeNull = false)
    private String typ; // "EBOOK" oder "FILM"

    @DatabaseField(canBeNull = false)
    private boolean verfuegbar;

    public Medium() {}

    public Medium(String titel, String autor, String genre, String typ) {
        this.titel = titel;
        this.autor = autor;
        this.genre = genre;
        this.typ = typ;
        this.verfuegbar = true;
    }

    public int getId() { return id; }
    public String getTitel() { return titel; }
    public String getAutor() { return autor; }
    public String getGenre() { return genre; }
    public String getTyp() { return typ; }
    public boolean isVerfuegbar() { return verfuegbar; }
    public void setVerfuegbar(boolean verfuegbar) { this.verfuegbar = verfuegbar; }

    @Override
    public String toString() {
        return id + " | " + titel + " | " + autor + " | " + genre + " | " + typ
                + " | " + (verfuegbar ? "verfuegbar" : "ausgeliehen");
    }
}