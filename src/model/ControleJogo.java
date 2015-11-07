package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import jplay.Scene;
import jplay.Sound;
import jplay.URL;

/**
 *
 * @author Thiago
 */
public class ControleJogo {

    private final static LinkedList<Inimigo> inimigos = new LinkedList<>();
    private final static LinkedList<Tiro> tiros = new LinkedList<>();
    private int pontuacao;
    private Random rdm = new Random();
    boolean jogando = true;

    public boolean isJogando() {
        return jogando;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void adicionaInimigo(Scene cena) {
        int a = rdm.nextInt(100);

        if (a == 10) {
            a = rdm.nextInt(3);

            if (a == 2) {
                inimigos.add(new InimigoForte());
            } else if (a < 2) {
                inimigos.add(new InimigoFraco());
            }

            for (int i = 0; i < inimigos.size(); i++) {
                cena.addOverlay(inimigos.get(i));
            }
        }

        //somDisparoInimigo();
    }

    public void adicionaTiro(double x, double y, Scene cena) {
        Tiro tiro = new Tiro(x, y);
        tiros.addFirst(tiro);
        cena.addOverlay(tiro);
        //somDisparoTiro();
    }

    public void runInimigo() {
//        for (int i = 0; i < inimigos.size(); i++) {
//            Inimigo inimigo = inimigos.removeFirst();
//            inimigo.run();
//            inimigos.addLast(inimigo);
//        }
        for (Inimigo inimigo : inimigos) {
            inimigo.run();
        }
    }

    public void runTiro() {
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.removeFirst();
            tiro.mover();
            tiros.addLast(tiro);
        }
    }

    public void colisaoTiroInimigo(Scene scena) {

        LinkedList<Tiro> colidedShot = new LinkedList<>();
        LinkedList<Inimigo> colidedEnemy = new LinkedList<>();

        for (Tiro tiro : tiros) {
            for (Inimigo inimigo : inimigos) {
                if (tiro.collided(inimigo)) {
                    if (inimigo.getLife() > 0) {
                        tiro.x = 1000;
                        inimigo.setLife(inimigo.getLife() - 1);
                    } else {
                        inimigo.x = -70;
                        tiro.x = 1000;
                        pontuacao += inimigo.getPontuacao();
                        scena.removeOverlay(tiro);
                        scena.removeOverlay(inimigo);
                        colidedShot.add(tiro);
                        colidedEnemy.add(inimigo);
                    }
                    System.out.println(pontuacao);
                }
            }
        }
        for (Inimigo enemy : colidedEnemy) {
            inimigos.remove(enemy);
        }
        for (Tiro shot : colidedShot) {
            inimigos.remove(shot);
        }

    }

    public void colisaoNaveInimigo(Nave nave) {
        for (Inimigo inimigo : inimigos) {
            if (nave.collided(inimigo)) {
                if (nave.getLife() > 0) {
                    inimigo.x = -70;
                    nave.setLife(nave.getLife() - 1);
                } else {
                    jogando = false;
                    System.out.println("GAME OVER, BURRO");
                }
                System.out.println(nave.getLife());
            }
        }
    }

    private void somDisparoInimigo() {
        new Sound(URL.audio("flecha.wav")).play();
    }

    private void somDisparoTiro() {
        new Sound(URL.audio("flecha.wav")).play();
    }

}
