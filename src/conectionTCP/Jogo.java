package conectionTCP;

import java.net.Socket;
import java.util.Scanner;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Socket s = null;
    private int rodada=1, vez=1;
    private Jogador jogador1;
    private Jogador jogador2;
    public Scanner entrada = new Scanner(System.in);


    public Jogo(){
        tabuleiro = new Tabuleiro();


    }

    public void iniciarJogadores(){
//        if (escolherJogador() == true){
            this.jogador1 = new Humano(1);
            this.jogador2 = new Humano(2);
//        }
    }

    public boolean escolherJogador(){
        do{
            System.out.println("Presione ENTER para começar o jogo\n");

            if(!entrada.hasNextLine())
                System.out.println("Opção inválida! Tente novamente");
        }while(!entrada.hasNextLine());

        return true;
    }

    public boolean Jogar(){
        if(ganhou(tabuleiro) == 0 ){
            System.out.println("----------------------");
            System.out.println("\nRodada "+rodada);

            if(tabuleiro.tabuleiroCompleto()){
                System.out.println("Tabuleiro Completo. Jogo empatado");
                return false;
            }
            rodada++;

            return true;
        } else{
            if(ganhou(tabuleiro) == -1 )
                System.out.println("Jogador 1 ganhou!");
            else
                System.out.println("Jogador 2 ganhou!");

            return false;
        }

    }

    public int vez(){
        if(vez%2 == 1)
            return 1;
        else
            return 2;
    }

    public int ganhou(Tabuleiro tabuleiro){
        if(tabuleiro.checaLinhas() == 1)
            return 1;
        if(tabuleiro.checaColunas() == 1)
            return 1;
        if(tabuleiro.checaDiagonais() == 1)
            return 1;

        if(tabuleiro.checaLinhas() == -1)
            return -1;
        if(tabuleiro.checaColunas() == -1)
            return -1;
        if(tabuleiro.checaDiagonais() == -1)
            return -1;

        return 0;
    }


}