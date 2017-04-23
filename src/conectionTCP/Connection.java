/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conectionTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author almir
 */
public class Connection extends Thread {
    private DataInputStream input;
    private DataOutputStream output;
    private Socket s;
    private Integer mensagem1;
    private Integer mensagem2;
    private Scanner entrada = new Scanner(System.in);
    int linha;
    int coluna;

    public Connection(Socket s){
        this.s = s;
        try {
            System.out.println("Aceitou conexao....."+s.getInetAddress().getHostName());
            input = new DataInputStream(s.getInputStream());
            output = new DataOutputStream(s.getOutputStream());
            this.start();
        }
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }

    public void run(){
        try {
            while(true) {
                mensagem1 = input.readInt();
                mensagem2 = input.readInt();

                do{
                    System.out.print("Linha: ");
                    linha = entrada.nextInt();

                    if( linha > 3 || linha < 1)
                        System.out.println("Linha inválida. É 1, 2 ou 3");

                }while( linha > 3 || linha < 1);

                do{
                    System.out.print("Coluna: ");
                    coluna = entrada.nextInt();

                    if(coluna > 3 || coluna < 1)
                        System.out.println("Coluna inválida. É 1, 2 ou 3");

                }while(coluna > 3 || coluna < 1);

                int [] tentativa2 = {linha,coluna};

                output.writeInt(linha);
                output.writeInt(coluna);
                System.out.println("Aguardando Jogador 1....");
            }

        }
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
        finally {
            try {
                s.close();
            }
            catch (IOException ioe){
                System.err.println("Error fechando o socket.");
            }
        }
    }
}