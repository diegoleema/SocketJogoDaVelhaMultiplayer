package conectionTCP;

import java.net.*;
import java.io.*;

//CONEXÃO SERVIDOR UDP
public class connectionServer {
    public static void main(String []args){
        ServerSocket sSocket = null;
        try {
            sSocket = new ServerSocket(7696);
            System.out.println("Jogador 2 inicializado....");
            while (true){
                Socket socket = sSocket.accept();
                new Connection(socket);
            }
        }
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }
}