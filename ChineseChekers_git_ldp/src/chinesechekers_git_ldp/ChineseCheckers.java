/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinesechekers_git_ldp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ritap
 */
public class ChineseCheckers {

    public void start(Stage stage) throws Exception {
        //A primeira página do jogo será a de Menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        scene.getStylesheets().add(ChineseCheckers.class.getResource("style.css").toExternalForm());

       

    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
