package ChineseCheckers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

// handler para tratar dos clicks num circulo... precisa do tabuleiro

/**
 * <p>CirculoHandler<p>
 * Classe que atribui propriedades de click para as bolas do tabuleiro
 * 
 * @author Rui Fernandes, Rita Pinto e Nuno Mansilhas....
 */ 
public class CirculoHandler implements EventHandler<MouseEvent> {

    private Tabuleiro t;
    private int i, j;

    
    /**
     *Construtor da classe
     */    
    CirculoHandler(Tabuleiro t, int i, int j) {
        this.t = t;
        this.i = i;
        this.j = j;
    }

    /**
     *Metodo de handle(Mouse event)
     */   
    @Override
    public void handle(MouseEvent event) {
        t.jogadorClicou(i, j);
    }
    
}
