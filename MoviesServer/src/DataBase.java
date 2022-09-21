
import java.util.ArrayList;
import java.util.HashSet;


public class DataBase {

    private HashSet<Movie> movies_set;
    private HashSet<String> directors_set;

    public DataBase() {
        this.movies_set = null;
        this.directors_set = null;
    }

    public HashSet<Movie> getMovies_set() {
        return movies_set;
    }

    public HashSet<String> getDirectors_set() {
        return directors_set;
    }

    //Functions
    public void addMovie(Movie movie) {
        movies_set.add(movie);
        directors_set.add(movie.getDirector());
    }

    public Movie searchMovieByTitle(String title) {

        for (Movie m : movies_set) {
            if (m.getTitle().equals(title)) {
                return m;
            }

        }
        return null;
    }

    public ArrayList<Movie> searchMovieByDirector(String director) {

        ArrayList<Movie> movies_found = new ArrayList();
        for (Movie m : movies_set) {
            if (m.getDirector().equals(director)) {
                movies_found.add(m);
            }

        }
        return movies_found;
    }
}
