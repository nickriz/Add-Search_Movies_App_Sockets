
public class SM_TitleRespond extends Message {

    private Movie movie_found;

    public SM_TitleRespond(String message, Movie movie_found) {
        super(message);
        this.movie_found = movie_found;
    }

    public Movie getMovie_found() {
        return movie_found;
    }

}
