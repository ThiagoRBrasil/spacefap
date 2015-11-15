package model;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

public class Main {

    /**
     * Inicializará o Jogo
     * <p>
     * Primeira Tela - Tela de Menu
     *
     * @param args
     */
    public static void main(String[] args) {

        Window janela = new Window(800, 600);//Cria a janela do jogo com suas dimensoes especificas
        GameImage background = new GameImage(URL.sprite("menu.jpg"));//Imagem de fundo para a janela do jogo

        Keyboard teclado = janela.getKeyboard();//Listener para saber qual tecla ira ser apertada no teclado

        util_Banco.HibernateUtil.getSessionFactory().openSession();
        
        while (true) {
            background.draw();
            janela.update();

            if (teclado.keyDown(Keyboard.ENTER_KEY)) {//tecla ENTER
                new Cenario(janela);//Inicializará o novo Cenario (cenario do jogo)
            }
            if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {//tecla ESC
                janela.exit();//Encerra o jogo
                util_Banco.HibernateUtil.getSessionFactory().close();
            }
        }
    }
}
