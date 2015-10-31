package model;

import com.sun.webkit.dom.RangeImpl;
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

    static LinkedList<Inimigo> inimigos = new LinkedList<>();
    static LinkedList<Tiro> tiros = new LinkedList<>();
    int pontuacao;
    Random rdm = new Random();
    boolean jogando = true;

    public boolean isJogando() {
        return jogando;
    }

    public void adicionaInimigo(Scene cena) {
        inimigos.add(new InimigoForte());
        inimigos.add(new InimigoFraco());
        
        for (int i = 0; i < inimigos.size(); i++) {
            cena.addOverlay(inimigos.get(i));
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

    public void colisaoTiroInimigo() {
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
                    }
                    System.out.println(pontuacao);
                }
            }
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
