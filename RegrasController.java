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
import javafx.stage.Stage;

/**
 * <p>RegrasController<p>
 * Controlador da pag de regras.
 * 
 * FXML Controller class
 *
 * 
 */
public class RegrasController implements Initializable {

    @FXML
    private Button Sair;

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
     * Permite aceder a pag de menu
     */
    @FXML
    private void RespondeSair(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController mc = loader.getController();
        mc.setDadosJogo(dj);//os dados do jogo devem acompanhar cada jogador

        Stage window = (Stage) Sair.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
