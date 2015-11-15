package model;

import jplay.Sprite;
import jplay.URL;

public class Tiro extends Sprite {

    protected static final int VELOCIDADE_TIRO = 2;
    protected boolean movendo = false;

    public Tiro(double x, double y) {
        super(URL.sprite("tiro.png"), 1);
        this.x = x + 30;
        this.y = y + 29;
    }

    public void mover() {
        this.x += VELOCIDADE_TIRO;
        movendo = true;

        if (movendo) {
            update();
            movendo = false;
        }
    }

}
