/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChineseCheckers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * <p>DefunirJogadorController<p>
 * Controlador da pag de menu inicial.
 * 
 * FXML Controller class
 *
 * @author João Miranda & Leonardo Andrade & Miguel Cruzeiro
 */
public class DefinirJogadorController implements Initializable {

    @FXML
    private CheckBox CheckboxVermelho;
    @FXML
    private CheckBox CheckboxLaranja;
    @FXML
    private CheckBox CheckboxAmarelo;
    @FXML
    private CheckBox CheckboxVerde;
    @FXML
    private Button BotaoComecar;
    @FXML
    private Button BotaoRetroceder;
    @FXML
    private TextField labelNome;
    private DadosJogo dj;

    /**
     *Setter dos Dados do Jogo, permite passar os atributos do jogo para o ecrã.
     * @param dj
     */
    public void setDadosJogo(DadosJogo dj) {
        this.dj = dj;

        labelNome.setText(dj.jogador1.nome);

        //Seleciona a checkbox correta e desseleciona as outras
        if (dj.jogador1.cor == DadosJogo.Vermelho) {
            CheckboxVermelho.setSelected(true);
            CheckboxLaranja.setSelected(false);
            CheckboxAmarelo.setSelected(false);
            CheckboxVerde.setSelected(false);

        } else if (dj.jogador1.cor == DadosJogo.Amarelo) {
            CheckboxVermelho.setSelected(false);
            CheckboxLaranja.setSelected(false);
            CheckboxAmarelo.setSelected(true);
            CheckboxVerde.setSelected(false);

        } else if (dj.jogador1.cor == DadosJogo.Laranja) {
            CheckboxVermelho.setSelected(false);
            CheckboxLaranja.setSelected(true);
            CheckboxAmarelo.setSelected(false);
            CheckboxVerde.setSelected(false);

        } else if (dj.jogador1.cor == DadosJogo.Verde) {
            CheckboxVermelho.setSelected(false);
            CheckboxLaranja.setSelected(false);
            CheckboxAmarelo.setSelected(false);
            CheckboxVerde.setSelected(true);
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
     /**
     * Permite aceder a pag de Animação Espera
     */
    @FXML
    private void RespondeComeçar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AnimaçãoEspera.fxml"));
        Parent root = loader.load();
        AnimaçãoEsperaController ae = loader.getController();
        atualizaJogador();//atualiza a cor do jogador e o seu nome
        ae.setDadosJogo(dj);//os dados do jogo devem acompanhar cada jogador

        Stage window = (Stage) BotaoComecar.getScene().getWindow();
        window.setScene(new Scene(root));

    }

     /**
     * Permite aceder a pag de menu inicial
     */
    @FXML
    private void RespondeRetroceder(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController mc = loader.getController();
        atualizaJogador();//atualiza a cor do jogador e o seu nome
        mc.setDadosJogo(dj);//os dados do jogo devem acompanhar cada jogador

        Stage window = (Stage) BotaoRetroceder.getScene().getWindow();
        window.setScene(new Scene(root));
    }

     /**
     * Permite atualizar a label tendo em conta o nome do jogador
     */
    @FXML
    private void setNome(ActionEvent event) {
        dj.jogador1.nome = labelNome.getText();
    }

     /**
     * Permite atualizar o nome e a cor do jogador tendo em conta os inputs
     */
    private void atualizaJogador() {
        dj.jogador1.nome = labelNome.getText();
        if (CheckboxVermelho.isSelected()) {
            dj.jogador1.cor = DadosJogo.Vermelho;
        } else if (CheckboxAmarelo.isSelected()) {
            dj.jogador1.cor = DadosJogo.Amarelo;
        } else if (CheckboxLaranja.isSelected()) {
            dj.jogador1.cor = DadosJogo.Laranja;
        } else if (CheckboxVerde.isSelected()) {
            dj.jogador1.cor = DadosJogo.Verde;

        }
    }

     /**
     * Permite atualizar a cor tendo em conta a checkbox e desseleciona as 
     * restantes
     */
    @FXML
    private void setVermelho(ActionEvent event) {
        CheckboxVermelho.setSelected(true);
        CheckboxLaranja.setSelected(false);
        CheckboxAmarelo.setSelected(false);
        CheckboxVerde.setSelected(false);

        dj.jogador1.cor = DadosJogo.Vermelho;
    }

     /**
     * Permite atualizar a cor tendo em conta a checkbox e desseleciona as 
     * restantes
     */
    @FXML
    private void setLaranja(ActionEvent event) {
        CheckboxVermelho.setSelected(false);
        CheckboxLaranja.setSelected(true);
        CheckboxAmarelo.setSelected(false);
        CheckboxVerde.setSelected(false);

        dj.jogador1.cor = DadosJogo.Laranja;
    }

    /**
     * Permite atualizar a cor tendo em conta a checkbox e desseleciona as 
     * restantes
     */
    @FXML
    private void setAmarelo(ActionEvent event) {
        CheckboxVermelho.setSelected(false);
        CheckboxLaranja.setSelected(false);
        CheckboxAmarelo.setSelected(true);
        CheckboxVerde.setSelected(false);

        dj.jogador1.cor = DadosJogo.Amarelo;
    }

    
    /**
     * Permite atualizar a cor tendo em conta a checkbox e desseleciona as 
     * restantes
     */
    @FXML
    private void setVerde(ActionEvent event) {
        CheckboxVermelho.setSelected(false);
        CheckboxLaranja.setSelected(false);
        CheckboxAmarelo.setSelected(false);
        CheckboxVerde.setSelected(true);

        dj.jogador1.cor = DadosJogo.Verde;
    }

}
