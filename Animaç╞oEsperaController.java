/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChineseCheckers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * <p>AnimaçãoEsperaController<p>
 * Controlador da pag de animação de espera (enquanto ambos os jogadores não se
 * concetam).
 * 
 * FXML Controller class
 *
 * @author João Miranda & Leonardo Andrade & Miguel Cruzeiro
 */
public class AnimaçãoEsperaController implements Initializable {

    @FXML
    private Circle Circle2;
    @FXML
    private Circle Circle1;
    @FXML
    private Circle Circle3;
    @FXML
    private Button Sair;

    private DadosJogo dj;
    private RecebeMensagemInicio recebe;
    private Thread th;

    /**
     *Neste método vamos fazer um "Set" dos dados do jogo, ou seja, vamos atualizar
     * os dados de ambos os jogadores tendo em conta os inputs realizados anteriormente,
     * e vamos fazer com que os dados de ambos os jogadores comuniquem, através de mensagens.
     * Assim sendo através de um Listener vamos conseguir efetuar a receção dos dados do nosso
     * adversário.(Este envio de menssagens permite que cada jogador consiga localmente atualizar
     * os dados de jogador1 e jogador2)
     * 
     * @param dj
     * @throws IOException
     */
    public void setDadosJogo(DadosJogo dj) throws IOException {
        this.dj = dj;
        //liga o socket e cria uma Task para receber mensagem
        dj.cs.liga();
        //enviar os nossos dados ao adversário
        dj.cs.enviaMensagem("jogador:" + dj.jogador1.nome + ":" + dj.jogador1.cor);
        recebe = new RecebeMensagemInicio(dj.cs);
        recebe.messageProperty().addListener((obs, oldMsg, newMsg) -> {
            System.out.println("Espera Jogador: " + newMsg);
            StringTokenizer st = new StringTokenizer(newMsg, ":");//separar a menssagem por :
            String token = st.nextToken();
            if (token.equals("jogador")) {
                System.out.println("Comando jogador");
                //atualizar os dados de cor e nome do jogador2
                dj.jogador2.nome = st.nextToken();
                dj.jogador2.cor = st.nextToken();

                try {
                    dj.cs.enviaMensagem("jogador:" + dj.jogador1.nome + ":" + dj.jogador1.cor);
                    IniciaJogo();
                } catch (IOException ex) {
                    System.out.println("Erro no envio da mensagem.");
                }

            }

        });
        th = new Thread(recebe);
        th.start();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Animação das bolinhas
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(javafx.util.Duration.millis(1000));
        translateTransition.setNode(Circle1);
        translateTransition.setByY(50);
        translateTransition.setCycleCount(100000);
        translateTransition.setAutoReverse(true);
        translateTransition.play();

        TranslateTransition translateTransition2 = new TranslateTransition();
        translateTransition2.setDuration(javafx.util.Duration.millis(1000));
        translateTransition2.setNode(Circle3);
        translateTransition2.setByY(50);
        translateTransition2.setCycleCount(100000);
        translateTransition2.setAutoReverse(true);
        translateTransition2.play();

        TranslateTransition translateTransition3 = new TranslateTransition();
        translateTransition3.setDuration(javafx.util.Duration.millis(1000));
        translateTransition3.setNode(Circle2);
        translateTransition3.setByY(50);
        translateTransition3.setCycleCount(100000);
        translateTransition3.setAutoReverse(true);
        translateTransition3.play();
    }

     /**
     * Permite aceder a pag dao menu inicial
     */    
    @FXML
    private void RespondeSair(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController mc = loader.getController();

        // cancela a tarefa de receber e desliga o socket
        recebe.cancel();
        dj.cs.enviaMensagem("logout");
        dj.cs.desliga();
        mc.setDadosJogo(dj); //os dados do jogo devem acompanhar cada jogador

        Stage window = (Stage) Sair.getScene().getWindow();
        window.setScene(new Scene(root));
    }

     /**
     * Permite aceder a pag do tabuleiro de jogo
     */      
    private void IniciaJogo() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController c = loader.getController();
        c.setDadosJogo(dj); //os dados do jogo devem acompanhar cada jogador
        Stage window = (Stage) Sair.getScene().getWindow(); //Não há botão
        window.setScene(new Scene(root));
    }

}
