package model;

import java.util.Random;
import jplay.URL;

public class InimigoFraco extends Inimigo {

    private double VELOCIDADE = 0.3;

    /**
     * Cria um personagem do Jogo, informando a posição na janela que ele irá
     * ser inicializado.
     */
    public InimigoFraco() {
        super(URL.sprite("inimigo1.png"), 1);//Sprite do personagem, Quantos Sprites estão contidos na imagem
        this.x = 840;//posição HORIZONTAL do personagem
        this.y = new Random().nextInt(540);//posição VERTICAL do personagem
        this.movendo = true;
        this.pontuacao = 3;
        this.life = 0;
    }

    /**
     * Movimenta o personagem no cenário.
     */
    @Override
    public void run() {
        this.x -= getVelocidade();
    }

    private double getVelocidade() {
        return this.VELOCIDADE + super.VELOCIDADE;
    }

}
