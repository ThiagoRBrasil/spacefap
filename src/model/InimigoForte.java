/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;
import jplay.URL;

public class InimigoForte extends Inimigo{
    
     
    public InimigoForte() {
        super(URL.sprite("inimigo2.png"), 1);//Sprite do personagem, Quantos Sprites estão contidos na imagem
        this.x = 840;//posição HORIZONTAL do personagem
        this.y = new Random().nextInt(540);//posição VERTICAL do personagem
        this.VELOCIDADE = 0.17;
        this.movendo = true;
        this.pontuacao = 10;
        this.life = 2;
    }
    /**
     * Movimenta o personagem no cenário.
     */
    @Override
    public void run(){
        this.x -= VELOCIDADE;
    }
}
