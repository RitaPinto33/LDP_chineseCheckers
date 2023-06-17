/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChineseCheckers;

/**
 *
 * @author Asus
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * <p>Circulo<p>
 *Esta classe define as proriedades de cada bola no tabuleiro.
 * 
 * @author Rui Fernandes, Rita Pinto e Nuno Mansilhas
 */
public class Circulo extends Circle {

    private String cor;
    private static final int raio = 11;

    /**
     *Construtor da classe
     * @param centerX
     * @param centerY
     * @param cor
     */
    public Circulo(double centerX, double centerY, String cor) {
        super(centerX, centerY, raio, Color.web(cor));
        this.cor = cor;
    }

    /**
     *Getter da cor
     * @return
     */
    public String getCor() {
        return cor;
    }

    /**
     *Setter da cor
     * @param cor
     */
    public void setCor(String cor) {
        this.cor = cor;
        this.setFill(Color.web(cor));
    }

    /**
     *Metodo para verificar se uma casa está livre, ou seja, se contem cor branca.
     * @return
     */
    public boolean livre() {
        return cor.equals(DadosJogo.Branco);
        
    }

    /**
     *Metodo para verificar se uma casa está ocupada, ou seja, se contem a cor 
     *de um determinado jogador.
     * @param j
     * @return
     */
    public boolean ocupadaJogador(Jogador j) {
        return cor.equals(j.cor);
    }

}
