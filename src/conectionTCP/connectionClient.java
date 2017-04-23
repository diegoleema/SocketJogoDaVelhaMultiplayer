/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conectionTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author almir
 */
public class connectionClient {

    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        Socket aSocket = null;
        try {
            aSocket = new Socket("localhost", 7696);
            System.out.println("Jogador 1 inicializado....");
                DataInputStream in =
                        new DataInputStream(aSocket.getInputStream());
                DataOutputStream out =
                        new DataOutputStream(aSocket.getOutputStream());

            //chamada para criar o tabuleiro quando os 2 estão conectados
            Jogador jogador1 = new Humano(1);
            Jogador jogador2 = new Humano(2);
            Tabuleiro tabuleiro = new Tabuleiro();
            Jogo jogo = new Jogo();
            int rodada = 1;
            int linha1;
            int coluna1;
            int linha2;
            int coluna2;
            int [] tentativa1;
            int [] tentativa2;

            while(true) {

                do {
                    //JOGADOR 1
                    do {
                        System.out.print("Linha: ");
                        linha1 = input.nextInt();

                        if (linha1 > 3 || linha1 < 1)
                            System.out.println("Linha inválida. É 1, 2 ou 3");

                    } while (linha1 > 3 || linha1 < 1);

                    do {
                        System.out.print("Coluna: ");
                        coluna1 = input.nextInt();

                        if (coluna1 > 3 || coluna1 < 1)
                            System.out.println("Coluna inválida. É 1, 2 ou 3");

                    } while (coluna1 > 3 || coluna1 < 1);

                    tentativa1 = new int[]{linha1, coluna1};

                    tentativa1[0]--;
                    tentativa1[1]--;

                    if (!jogador1.checaTentativa(tentativa1, tabuleiro)) {
                        System.out.println("Esse local já foi marcado. Tente outro.");
                    }
                } while (!jogador1.checaTentativa(tentativa1, tabuleiro));

                tabuleiro.setPosicao(tentativa1, 1);

                //verifica se alguem ganhou
                if(jogo.ganhou(tabuleiro) == 0 ){
                    System.out.println("----------------------");
                    System.out.println("\nRodada "+rodada);

                    if(tabuleiro.tabuleiroCompleto()){
                        System.out.println("Tabuleiro Completo. Jogo empatado");
                        break;
                    }
                    rodada++;
                } else{
                    if(jogo.ganhou(tabuleiro) == -1 )
                        System.out.println("Jogador 1 ganhou!");
                    else
                        System.out.println("Jogador 2 ganhou!");
                    break;
                }

                System.out.println();
                System.out.println("Aguardando Jogador 2.... ");

                //JOGADOR 2
                do{
                    out.writeInt(linha1);
                    out.writeInt(coluna1);


                    linha2 = in.readInt();
                    coluna2 = in.readInt();

                    tentativa2 = new int[]{linha2,coluna2};

                    tentativa2[0]--;
                    tentativa2[1]--;

                    if (!jogador1.checaTentativa(tentativa2, tabuleiro)) {
                        System.out.println("Esse local já foi marcado. Tente outro.");
                    }
                } while (!jogador2.checaTentativa(tentativa2, tabuleiro));
                tabuleiro.setPosicao(tentativa2, 2);

                //verifica se alguem ganhou
                if(jogo.ganhou(tabuleiro) == 0 ){
                    System.out.println("----------------------");
                    System.out.println("\nRodada "+rodada);

                    if(tabuleiro.tabuleiroCompleto()){
                        System.out.println("Tabuleiro Completo. Jogo empatado");
                        break;
                    }
                    rodada++;
                } else{
                    if(jogo.ganhou(tabuleiro) == -1 )
                        System.out.println("Jogador 1 ganhou!");
                    else
                        System.out.println("Jogador 2 ganhou!");
                    break;
                }

            }
        }
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
        finally {
            try {
                if (aSocket != null) aSocket.close();
            }
            catch (IOException ioe){
                System.err.println("Error fechando o socket.");
            }
        }
    }
}