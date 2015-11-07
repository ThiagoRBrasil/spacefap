/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jplay.Sprite;
import jplay.URL;

public class Inimigo extends Sprite {

    protected int life;
    protected static double VELOCIDADE;
    protected boolean movendo;
    protected int pontuacao;

    public Inimigo(){
        super(URL.sprite("inimigo2.png"), 1);
    }
    
    public Inimigo(String filename, int frames) {
        super(filename, frames);
    }

    public void run() {

    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setVelocidade() {
        this.VELOCIDADE += 0.05;
    }

}
