
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        // TODO code application logic here

        Socket sock = new Socket("localhost", 5555);
        System.out.println("Sending message to the Server...");
        System.out.println("Connecting to " + sock.getInetAddress() + "at the port " + sock.getPort());
        ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
        ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());

        outstream.writeObject(new Message("BEGIN"));
        outstream.flush();

        Message message = (Message) instream.readObject();
        if (message.getMessage().equals("LISTENING")) {
            int option; 
            System.out.println("Give me option: ");

            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            if (option == 1) {

                System.out.println("Give me the title: ");
                String title = sc.nextLine();
                
                System.out.println("\nGive me the director: ");
                String director = sc.nextLine();
                
                System.out.println("\nGive me the genre: ");
                String genre = sc.nextLine();
                
                System.out.println("\nGive me the duration: ");
                String duration = sc.nextLine();
                
                System.out.println("\nGive me the description: ");
                String description = sc.nextLine();
                        
                outstream.writeObject(new AddMessage("ADD", new Movie(title, director, genre, duration, description)));
                outstream.flush();
            } else if (option == 2) {
                System.out.println("Do you to search by Title or by Directorq=?|Insert '1' for by Title and '2' for Director:");
                int search_way = sc.nextInt();
                if (search_way == 1) {
                    System.out.println("Insert Tilte:");
                    String title = sc.nextLine();
                    outstream.writeObject(new SearchMessage("SEARCH", "BY TITLE", title));
                    outstream.flush();

                    Message message_Trespond = (Message) instream.readObject();

                } else if (search_way == 2) {
                    System.out.println("Insert Director:");
                    String director = sc.nextLine();
                    outstream.writeObject(new SearchMessage("SEARCH", "BY DIRECTOR", director));
                    outstream.flush();

                }

                Object message_respond = (Object) instream.readObject();

                if (message_respond instanceof SM_TitleRespond) {
                    SM_TitleRespond message_respondT = (SM_TitleRespond) message_respond;
                    Movie movie_found = message_respondT.getMovie_found();
                    System.out.println("Movie found:" + movie_found);

                } else if (message_respond instanceof SM_DirectorRespond) {
                    SM_DirectorRespond message_respondD = (SM_DirectorRespond) message_respond;
                    ArrayList<Movie> movies_found = message_respondD.getMovies_found();

                    System.out.println("Movies found:");
                    for (int i = 0; i < movies_found.size(); i++) {
                        System.out.println("Movie " + i + ":\n" + movies_found.get(i));
                    }

                } else if (message_respond instanceof Message) {
                    Message message_respondM = (Message) message_respond;

                    System.out.println("Error!\n Server message: '" + message_respondM.getMessage() + "'.");
                }

                instream.close();
                outstream.close();
                sock.close();

            }

        } else {
            System.out.println("Handshake Error!\n Closing...");
            instream.close();
            outstream.close();
            sock.close();
        }

    }

}
