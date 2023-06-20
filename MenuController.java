/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChineseCheckers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * <p>MenuController<p>
 * Controlador da pag de menu inicial.
 * 
 * FXML Controller class
 *
 */
public class MenuController implements Initializable {

    @FXML
    private Button BotaoRegras;
    @FXML
    private Button BotaoJogar;
    private DadosJogo dj;

    /**
     *Setter dos Dados do Jogo, permite passar os atributos do jogo para o ecrã.
     * @param dj
     */
    public void setDadosJogo(DadosJogo dj) {
        this.dj = dj;
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
     * Permite fechar o jogo ao carregar no botão de sair
     */
    @FXML
    private void RespondeSair(ActionEvent event) {
        Platform.exit();
    }

     /**
     * Permite aceder a pag das Regras
     */
    @FXML
    private void RespondeRegras(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Regras.fxml"));
        Parent root = loader.load();
        RegrasController dc = loader.getController();
        dc.setDadosJogo(dj); //os dados do jogo devem acompanhar cada jogador

        Stage window = (Stage) BotaoRegras.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * Permite aceder a pag de definir jogador
     */
    @FXML
    private void RespondeJogar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DefinirJogador.fxml"));
        Parent root = loader.load();
        DefinirJogadorController dc = loader.getController();
        dc.setDadosJogo(dj);//os dados do jogo devem acompanhar cada jogador

        Stage window = (Stage) BotaoJogar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
