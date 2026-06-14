package service;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import Model.Medium;

import java.sql.SQLException;
import java.util.List;

public class MediumService {

    private Dao<Medium, Integer> dao;

    public MediumService(Dao<Medium, Integer> dao) {
        this.dao = dao;
    }

    public List<Medium> alleAnzeigen() throws SQLException {
        return dao.queryForAll();
    }

    public Medium findById(int id) throws SQLException {
        return dao.queryForId(id);
    }

    public void update(Medium medium) throws SQLException {
        dao.update(medium);
    }

    public List<Medium> filtern(String titel, String autor, String genre, Boolean nurVerfuegbar) throws SQLException {
        boolean keineFilter = (titel == null || titel.isEmpty())
                && (autor == null || autor.isEmpty())
                && (genre == null || genre.isEmpty())
                && nurVerfuegbar == null;

        if (keineFilter) return dao.queryForAll();

        QueryBuilder<Medium, Integer> qb = dao.queryBuilder();
        Where<Medium, Integer> where = qb.where();
        boolean first = true;

        if (titel != null && !titel.isEmpty()) {
            where.like("titel", "%" + titel + "%");
            first = false;
        }
        if (autor != null && !autor.isEmpty()) {
            if (!first) where.and();
            where.like("autor", "%" + autor + "%");
            first = false;
        }
        if (genre != null && !genre.isEmpty()) {
            if (!first) where.and();
            where.like("genre", "%" + genre + "%");
            first = false;
        }
        if (nurVerfuegbar != null) {
            if (!first) where.and();
            where.eq("verfuegbar", nurVerfuegbar);
        }

        return qb.query();
    }

    public void beispieldatenEinfuegen() throws SQLException {
        if (dao.countOf() > 0) return;

        // E-Books
        dao.create(new Medium("Der Herr der Ringe", "J.R.R. Tolkien", "Fantasy", "EBOOK"));
        dao.create(new Medium("Der Hobbit", "J.R.R. Tolkien", "Fantasy", "EBOOK"));
        dao.create(new Medium("Harry Potter und der Stein der Weisen", "J.K. Rowling", "Fantasy", "EBOOK"));
        dao.create(new Medium("1984", "George Orwell", "Dystopie", "EBOOK"));
        dao.create(new Medium("Farm der Tiere", "George Orwell", "Dystopie", "EBOOK"));
        dao.create(new Medium("Die Verwandlung", "Franz Kafka", "Literatur", "EBOOK"));
        dao.create(new Medium("Der Prozess", "Franz Kafka", "Literatur", "EBOOK"));
        dao.create(new Medium("Faust", "Johann Wolfgang von Goethe", "Klassik", "EBOOK"));
        dao.create(new Medium("Der kleine Prinz", "Antoine de Saint-Exupery", "Literatur", "EBOOK"));
        dao.create(new Medium("Sherlock Holmes", "Arthur Conan Doyle", "Krimi", "EBOOK"));
        dao.create(new Medium("Moby Dick", "Herman Melville", "Abenteuer", "EBOOK"));
        dao.create(new Medium("Das Parfum", "Patrick Suskind", "Thriller", "EBOOK"));
        dao.create(new Medium("Dracula", "Bram Stoker", "Horror", "EBOOK"));
        dao.create(new Medium("Frankenstein", "Mary Shelley", "Horror", "EBOOK"));
        dao.create(new Medium("Brave New World", "Aldous Huxley", "Dystopie", "EBOOK"));
        dao.create(new Medium("Der Name der Rose", "Umberto Eco", "Krimi", "EBOOK"));
        dao.create(new Medium("Anna Karenina", "Leo Tolstoi", "Literatur", "EBOOK"));
        dao.create(new Medium("Krieg und Frieden", "Leo Tolstoi", "Literatur", "EBOOK"));
        dao.create(new Medium("Die Pest", "Albert Camus", "Literatur", "EBOOK"));
        dao.create(new Medium("Der Alchimist", "Paulo Coelho", "Philosophie", "EBOOK"));
        dao.create(new Medium("Don Quijote", "Miguel de Cervantes", "Klassik", "EBOOK"));
        dao.create(new Medium("Romeo und Julia", "William Shakespeare", "Klassik", "EBOOK"));
        dao.create(new Medium("Hamlet", "William Shakespeare", "Klassik", "EBOOK"));
        dao.create(new Medium("Der Graf von Monte Christo", "Alexandre Dumas", "Abenteuer", "EBOOK"));
        dao.create(new Medium("Die drei Musketiere", "Alexandre Dumas", "Abenteuer", "EBOOK"));
        dao.create(new Medium("Stolz und Vorurteil", "Jane Austen", "Romance", "EBOOK"));
        dao.create(new Medium("Oliver Twist", "Charles Dickens", "Literatur", "EBOOK"));
        dao.create(new Medium("Die Schatzinsel", "Robert Louis Stevenson", "Abenteuer", "EBOOK"));
        dao.create(new Medium("Der grosse Gatsby", "F. Scott Fitzgerald", "Literatur", "EBOOK"));
        dao.create(new Medium("Fahrenheit 451", "Ray Bradbury", "Dystopie", "EBOOK"));
        dao.create(new Medium("20000 Meilen unter dem Meer", "Jules Verne", "Abenteuer", "EBOOK"));
        dao.create(new Medium("In 80 Tagen um die Welt", "Jules Verne", "Abenteuer", "EBOOK"));
        dao.create(new Medium("Dune", "Frank Herbert", "SciFi", "EBOOK"));
        dao.create(new Medium("Siddhartha", "Hermann Hesse", "Philosophie", "EBOOK"));
        dao.create(new Medium("Im Westen nichts Neues", "Erich Maria Remarque", "Krieg", "EBOOK"));

        // Filme
        dao.create(new Medium("Inception", "Christopher Nolan", "SciFi", "FILM"));
        dao.create(new Medium("Interstellar", "Christopher Nolan", "SciFi", "FILM"));
        dao.create(new Medium("The Dark Knight", "Christopher Nolan", "Action", "FILM"));
        dao.create(new Medium("Der Pate", "Francis Ford Coppola", "Drama", "FILM"));
        dao.create(new Medium("Apocalypse Now", "Francis Ford Coppola", "Krieg", "FILM"));
        dao.create(new Medium("Schindlers Liste", "Steven Spielberg", "Drama", "FILM"));
        dao.create(new Medium("Jurassic Park", "Steven Spielberg", "Abenteuer", "FILM"));
        dao.create(new Medium("Pulp Fiction", "Quentin Tarantino", "Thriller", "FILM"));
        dao.create(new Medium("Django Unchained", "Quentin Tarantino", "Western", "FILM"));
        dao.create(new Medium("Kill Bill", "Quentin Tarantino", "Action", "FILM"));
        dao.create(new Medium("The Matrix", "Wachowski", "SciFi", "FILM"));
        dao.create(new Medium("Forrest Gump", "Robert Zemeckis", "Drama", "FILM"));
        dao.create(new Medium("Fight Club", "David Fincher", "Thriller", "FILM"));
        dao.create(new Medium("Sieben", "David Fincher", "Thriller", "FILM"));
        dao.create(new Medium("Gladiator", "Ridley Scott", "Action", "FILM"));
        dao.create(new Medium("Alien", "Ridley Scott", "Horror", "FILM"));
        dao.create(new Medium("Das Schweigen der Laemmer", "Jonathan Demme", "Thriller", "FILM"));
        dao.create(new Medium("Titanic", "James Cameron", "Romance", "FILM"));
        dao.create(new Medium("Terminator 2", "James Cameron", "SciFi", "FILM"));
        dao.create(new Medium("The Shawshank Redemption", "Frank Darabont", "Drama", "FILM"));
        dao.create(new Medium("Leon der Profi", "Luc Besson", "Action", "FILM"));
        dao.create(new Medium("Psycho", "Alfred Hitchcock", "Horror", "FILM"));
        dao.create(new Medium("Vertigo", "Alfred Hitchcock", "Thriller", "FILM"));
        dao.create(new Medium("Full Metal Jacket", "Stanley Kubrick", "Krieg", "FILM"));
        dao.create(new Medium("2001 Odyssee im Weltraum", "Stanley Kubrick", "SciFi", "FILM"));
        dao.create(new Medium("The Shining", "Stanley Kubrick", "Horror", "FILM"));
        dao.create(new Medium("Casablanca", "Michael Curtiz", "Romance", "FILM"));
        dao.create(new Medium("Goodfellas", "Martin Scorsese", "Krimi", "FILM"));
        dao.create(new Medium("The Departed", "Martin Scorsese", "Thriller", "FILM"));
        dao.create(new Medium("Taxi Driver", "Martin Scorsese", "Drama", "FILM"));
        dao.create(new Medium("Parasite", "Bong Joon-ho", "Thriller", "FILM"));
        dao.create(new Medium("Oldboy", "Park Chan-wook", "Thriller", "FILM"));
        dao.create(new Medium("Das Leben der Anderen", "Florian Henckel", "Drama", "FILM"));
        dao.create(new Medium("Das Boot", "Wolfgang Petersen", "Krieg", "FILM"));
        dao.create(new Medium("Braveheart", "Mel Gibson", "Abenteuer", "FILM"));
        dao.create(new Medium("Die Truman Show", "Peter Weir", "Drama", "FILM"));

        System.out.println("Beispieldaten eingefuegt: " + dao.countOf() + " Medien.");
    }
}