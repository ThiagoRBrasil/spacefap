package model;

import java.awt.event.KeyEvent;
import jplay.Keyboard;
import jplay.Scene;
import jplay.Sprite;
import jplay.URL;
import jplay.Window;

public class Nave extends Sprite {

    ControleJogo tiros;
    protected int life = 2;

    /**
     * Cria um personagem do Jogo, informando a posição na janela que ele irá
     * ser inicializado.
     *
     * @param x posição HORIZONTAL que o personagem estará.
     * @param y posição VERTICAL que o personagem estará.
     */
    public Nave(int x, int y, ControleJogo cj) {
        super(URL.sprite("nave.png"), 1);//Sprite do personagem, Quantos Sprites estão contidos na imagem
        this.x = x;//posição HORIZONTAL do personagem
        this.y = y;//posição VERTICAL do personagem
        tiros = cj;
    }

    /**
     * Movimenta o personagem no cenário.
     */
    public void mover() {
        moveY(0.7);
    }

    public void atirar(Window janela, Scene cena, Keyboard teclado) {
        if (teclado.keyDown(KeyEvent.VK_SPACE)) {
            tiros.adicionaTiro(x, y, cena);
        }
        tiros.runTiro();
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

}
