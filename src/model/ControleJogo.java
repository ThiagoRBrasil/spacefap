package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import jplay.Scene;
import jplay.Sound;
import jplay.URL;

public class ControleJogo {

    private final static LinkedList<Inimigo> inimigos = new LinkedList<>();
    private final static LinkedList<Tiro> tiros = new LinkedList<>();
    private final Random rdm = new Random();
    private int pontuacao;
    private int RANDOM = 1000;
    private int NVL_DIFICULDADE = 50;
    private int NVL_INIMIGO = 200;
    boolean jogando = true;

    public boolean isJogando() {
        return jogando;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void velocidadeInimigos(Inimigo inimigo) {
        if (pontuacao >= NVL_INIMIGO && NVL_INIMIGO <= 4000) {
            inimigo.setVelocidade();
            NVL_INIMIGO += 200;
        }
    }

    public void verificaObjCena(Scene cena) {//remove TIRO/INIMIGO da cena quando sair da janela

        LinkedList<Tiro> shot = new LinkedList<>();
        LinkedList<Inimigo> enemy = new LinkedList<>();

        for (Tiro t : this.tiros) {
            if (t.getX() > 850) {
                cena.removeOverlay(t);
                shot.add(t);
            }
        }
        for (Tiro t : shot) {
            this.tiros.remove(t);
        }
        for (Inimigo i : this.inimigos) {
            if (i.getX() < -80) {
                cena.removeOverlay(i);
                enemy.add(i);
            }
        }
        for (Inimigo i : enemy) {
            this.inimigos.remove(i);
        }
    }

    public void adicionaInimigo(Scene cena) {
        int a = rdm.nextInt(RANDOM);

        if (a == 10) {
            a = rdm.nextInt(3);

            if (a == 2 && this.pontuacao > 100) {
                inimigos.add(new InimigoForte());
            } else if (a < 2) {
                inimigos.add(new InimigoFraco());
            }

            for (int i = 0; i < inimigos.size(); i++) {
                cena.addOverlay(inimigos.get(i));
            }
            if (pontuacao >= NVL_DIFICULDADE && RANDOM > 50) {
                RANDOM -= 50;
                NVL_DIFICULDADE += 50;
            }
        }
    }

    public void adicionaTiro(double x, double y, Scene cena) {
        Tiro tiro = new Tiro(x, y);
        tiros.addFirst(tiro);
        cena.addOverlay(tiro);
    }

    public void runInimigo() {
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
                        scena.removeOverlay(tiro);//remove o tiro da cena do jogo
                        scena.removeOverlay(inimigo);//remove o inimigo da cena do jogo
                        colidedShot.add(tiro);//add tiro atingido em um LinkedList temporario
                        colidedEnemy.add(inimigo);//add inimigo atingido em um LinkedList temporario
                    }
                }
            }
        }
        for (Inimigo enemy : colidedEnemy) {//Retira inimigos atingidor do LinkedList
            inimigos.remove(enemy);
        }
        for (Tiro shot : colidedShot) {//Retira tiros atingidor do LinkedList
            tiros.remove(shot);
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
                    this.tiros.clear();//apaga todos os tiros do jogo
                    this.inimigos.clear();//apaga todos os inimigos do jogo

                    ranking.RankingDAO rankingDAO = new ranking.RankingDAO();
                    if (rankingDAO.listar(pontuacao).isEmpty()) {//verifica pontuacao ja adicionada

                        ranking.Ranking ranking = new ranking.Ranking();

                        ranking.setPontuacao(this.pontuacao);
                        rankingDAO.salvar(ranking);//salca nova pontuacao ao banco

                    }
                }
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
