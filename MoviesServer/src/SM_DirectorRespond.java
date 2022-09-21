
import java.util.ArrayList;


public class SM_DirectorRespond extends Message {

    private ArrayList<Movie> movies_found;

    public SM_DirectorRespond(String message, ArrayList<Movie> movies_found) {
        super(message);
        this.movies_found = movies_found;
    }

    public ArrayList<Movie> getMovies_found() {
        return movies_found;
    }

}
