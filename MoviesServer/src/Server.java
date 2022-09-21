
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here

        DataBase data_base = new DataBase();

        try {
            ServerSocket serversock = new ServerSocket(5555);

            while (true) {
                
                Socket connetcion = serversock.accept();
                ObjectInputStream instream = new ObjectInputStream(connetcion.getInputStream());
                ObjectOutputStream outstream = new ObjectOutputStream(connetcion.getOutputStream());
                System.out.println("Client trying to connect...");

                Object message = (Object) instream.readObject();
                if (message instanceof Message) {
                    Message messageB = (Message) message;
                    if (messageB.getMessage().equals("BEGIN")) {
                        outstream.writeObject(new Message("LISTENING"));
                        outstream.flush();
                        System.out.println("Client Connected!");

                        Object messageA = (Object) instream.readObject();

                        if (messageA instanceof AddMessage) {
                            AddMessage messageADD = (AddMessage) messageA;
                            data_base.addMovie(messageADD.getMovie());
                            outstream.writeObject(new Message("OK"));
                            outstream.flush();
                        } else if (messageA instanceof SearchMessage) {
                            SearchMessage messageS = (SearchMessage) messageA;
                            if (messageS.getSearch_way().equals("TITLE")) {
                                Movie movie_found = data_base.searchMovieByTitle(messageS.getSearch_element());
                                outstream.writeObject(new SM_TitleRespond("OK", movie_found));
                                outstream.flush();

                            } else if (messageS.getSearch_way().equals("DIRECTOR")) {
                                ArrayList<Movie> movies_found = data_base.searchMovieByDirector(messageS.getSearch_element());
                                outstream.writeObject(new SM_DirectorRespond("OK", movies_found));
                                outstream.flush();
                            }

                        }

                    } else {
                        System.out.println("Wrong Handshake Message!");
                        instream.close();
                        outstream.close();
                        serversock.close();
                    }

                }
                instream.close();
                outstream.close();
                serversock.close();

            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
