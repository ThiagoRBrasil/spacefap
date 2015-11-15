package model;

import java.awt.Color;
import java.awt.Font;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario {

    private final Window janela;
    private final Scene cena;
    private final Nave nave;
    private ControleJogo cj;
    private Font f = new Font("arial", Font.BOLD, 29);//Font HUD

    /**
     * Nesta classe será a tela do jogo onde terá o cenário e seus personagens.
     * <p>
     * O Menu Principal e o Cenário do Jogo utilizam de uma mesma janela que
     * precisa ser atualizada para mudança de Cenário.
     *
     * @param janela sera a janela anterior que sera "atualizada", "pintada",
     * para a nova cena.
     */
    public Cenario(Window janela) {
        this.janela = janela;
        cena = new Scene();
        cena.loadFromFile(URL.scenario("space.scn"));//Background da Fase do Jogo
        cj = new ControleJogo();
        nave = new Nave(10, 300, cj);//Criando Personagem

        run();
    }

    /**
     * Executa um "loop" que será para criar "todo o decorrer" de uma partida.
     * <p>
     * O programa encerrara se apertar a tecla ESC (27).
     */
    private void run() {
        while (cj.isJogando()) {
            nave.atirar(janela, cena, this.janela.getKeyboard());
            cena.draw();
            nave.draw();
            nave.mover();
            this.janela.drawText("Life: " + (nave.getLife() + 1), 20, 36, Color.ORANGE, this.f);//Life do jogo
            this.janela.drawText(String.valueOf(cj.getPontuacao()), 713, 36, Color.ORANGE, this.f);//Score do jogo
            cj.adicionaInimigo(cena); //Criando Inimigos
            cj.runInimigo();
            cj.colisaoTiroInimigo(cena);
            cj.colisaoNaveInimigo(nave);
            cj.velocidadeInimigos(new Inimigo());
            cj.verificaObjCena(cena);
            janela.update();
            if (this.janela.getKeyboard().keyDown(27)) {//tecla ESC
                janela.exit();//Encerra o jogo
            }
        }
    }
}
