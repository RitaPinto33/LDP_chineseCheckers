/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChineseCheckers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *<p>ChineseCheckers<p>
 * A classe ChineseCheckers permitirá fazer a conecção dos clientes ao
 * servidor, assim como inicializar o jogo.
 * 
 * 
 */
public class ChineseCheckers extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //A primeira página do jogo será a de Menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        scene.getStylesheets().add(ChineseCheckers.class.getResource("style.css").toExternalForm());

        DadosJogo dj = new DadosJogo();
        MenuController mc = loader.getController();
        mc.setDadosJogo(dj);

        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
