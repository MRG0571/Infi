package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "ausleihen")
public class Ausleihe {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Kunde kunde;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Medium medium;

    @DatabaseField(canBeNull = false)
    private Date startDatum;

    @DatabaseField(canBeNull = false)
    private Date ablaufDatum;

    @DatabaseField(canBeNull = false)
    private boolean aktiv;

    public Ausleihe() {}

    public Ausleihe(Kunde kunde, Medium medium, Date startDatum, Date ablaufDatum) {
        this.kunde = kunde;
        this.medium = medium;
        this.startDatum = startDatum;
        this.ablaufDatum = ablaufDatum;
        this.aktiv = true;
    }

    public int getId() { return id; }
    public Kunde getKunde() { return kunde; }
    public Medium getMedium() { return medium; }
    public Date getStartDatum() { return startDatum; }
    public Date getAblaufDatum() { return ablaufDatum; }
    public boolean isAktiv() { return aktiv; }
    public void setAktiv(boolean aktiv) { this.aktiv = aktiv; }
}