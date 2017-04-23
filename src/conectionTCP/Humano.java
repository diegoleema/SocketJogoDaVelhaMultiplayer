package conectionTCP;

import java.util.Scanner;

public class Humano extends Jogador{
    public Scanner entrada = new Scanner(System.in);

    public Humano(int jogador){
        super(jogador);
        this.jogador = jogador;
    }

    @Override
    public void jogar(Tabuleiro tabuleiro){
        //Tentativa(tabuleiro);
        tabuleiro.setPosicao(tentativa, jogador);
    }

    public void Tentativa(Tabuleiro tabuleiro, int[] tentativa){
        do{
            do{
                System.out.print("Linha: ");
                tentativa[0] = entrada.nextInt();

                if( tentativa[0] > 3 ||tentativa[0] < 1)
                    System.out.println("Linha inválida. É 1, 2 ou 3");

            }while( tentativa[0] > 3 ||tentativa[0] < 1);

            do{
                System.out.print("Coluna: ");
                tentativa[1] = entrada.nextInt();

                if(tentativa[1] > 3 ||tentativa[1] < 1)
                    System.out.println("Coluna inválida. É 1, 2 ou 3");

            }while(tentativa[1] > 3 ||tentativa[1] < 1);

            tentativa[0]--;
            tentativa[1]--;

            if(!checaTentativa(tentativa, tabuleiro))
                System.out.println("Esse local já foi marcado. Tente outro.");
        }while( !checaTentativa(tentativa, tabuleiro) );
    }
}