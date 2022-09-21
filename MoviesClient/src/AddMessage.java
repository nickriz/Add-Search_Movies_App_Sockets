
public class AddMessage extends Message {

    private Movie movie;

    public AddMessage(String message, Movie movie) {
        super(message);
        this.movie = movie;
    }

}
