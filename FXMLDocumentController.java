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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * <p>
 * FXMLDocumentController<p>
 * Controlador da pagina do tabuleiro
 *
 * @author João Miranda & Leonardo Andrade & Miguel Cruzeiro
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private Polygon Centro;
    @FXML
    private AnchorPane APane;

    @FXML
    private TextArea textAreaMensagem;
    @FXML
    private Label jogador1;
    @FXML
    private Label jogador2;
    @FXML
    private Button BotaoDesistir;

    private DadosJogo dj;
    private RecebeMensagens recebe;
    private Thread th;
    private Tabuleiro tabuleiro;
    @FXML
    private Button BotaoAcabarTurno;

    /**
     * Neste método vamos fazer um "Set" dos dados do jogo, ou seja, vamos
     * atualizar os dados de ambos os jogadores tendo em conta o decorrer do
     * jogo, e vamos fazer com que os dados de ambos os jogadores comuniquem,
     * através de mensagens. Assim sendo através de um Listener vamos conseguir
     * efetuar a receção dos dados do nosso adversário.(Este envio de menssagens
     * permite que cada jogador consiga localmente atualizar os dados de
     * jogador1 e jogador2, assim como o tabuleiro, de modo a que cada um veja a
     * mesma informação)
     *
     * @param dj
     * @throws IOException
     */
    public void setDadosJogo(DadosJogo dj) throws IOException {
        this.dj = dj;
        dj.cs.enviaMensagem("sorteio:" + dj.jogador1.sortear());
        recebe = new RecebeMensagens(dj.cs);
        recebe.messageProperty().addListener((obs, oldMsg, newMsg) -> {
            // Nova mensagem recebida:
            textAreaMensagem.appendText("\n " + newMsg);
            StringTokenizer st = new StringTokenizer(newMsg, ":");
            String token = st.nextToken();
            // o primeiro item da mensagem é o nome do comando:
            // - jogador:<nome>:<cor>   -- novo jogo
            // - sorteio:<valor>        -- sorteio de quem joga
            // - passaVez               -- o jogador passou a vez de jogar
            // - preparaJogada:<i>:<j>:<preparaJogada>:<jogadaI>:<jogadaJ>:<salto>  -- jogador começa a jogada na casa i,j
            // - anulaJogada:<i>:<j>:<preparaJogada>:<jogadaI>:<jogadaJ>:<salto>    -- jogador desistiu da jogada na casa i,j
            // - fazJogada:<i>:<j>:<preparaJogada>:<jogadaI>:<jogadaJ>:<salto>      -- jogador conclui a jogada na casa i,j
            // - vencedor               -- jogador declara-se vencedor
            // - terminar               -- jogador decide terminar o jogo

            // - sorteio:<valor>        -- sorteio de quem joga
            if (token.equals("sorteio")) {
                String valorSorteio = st.nextToken();
                dj.jogador2.sorteio = Integer.parseInt(valorSorteio);
                if (dj.jogador1.sorteio > dj.jogador2.sorteio) {
                    textAreaMensagem.appendText("\nPor sorteio começa o jogador " + dj.jogador1.nome);
                    dj.jogador1.turno = true;
                    dj.jogador2.turno = false;
                } else if (dj.jogador2.sorteio > dj.jogador1.sorteio) {
                    textAreaMensagem.appendText("\nPor sorteio começa o jogador " + dj.jogador2.nome);
                    dj.jogador1.turno = false;
                    dj.jogador2.turno = true;
                } else {
                    textAreaMensagem.appendText("\nPrimeiro sorteio deu empate. Repetir.");
                    try {
                        dj.cs.enviaMensagem("sorteio:" + dj.jogador1.sortear());
                    } catch (IOException ex) {
                        System.out.println("Erro no envio da mensagem.");
                    }
                }
            } // - passaVez -- o jogador passou a vez de jogar
            else if (token.equals("passaVez")) {
                dj.jogador1.turno = true;
                dj.jogador2.turno = false;
                textAreaMensagem.appendText("\nAdversario terminou a jogada e passou a vez.");
            } // - preparaJogada:<i>:<j>:<preparaJogada>:<jogadaI>:<jogadaJ>:<salto> 
            //-- jogador começa a jogada na casa i,j
            else if (token.equals("preparaJogada")) {
                String valor_i = st.nextToken();
                String valor_j = st.nextToken();
                String valor_preparaJogada = st.nextToken();
                String valor_JogadaI = st.nextToken();
                String valor_JogadaJ = st.nextToken();
                String valor_salto = st.nextToken();
                //Converte as coordenadas da parte de baixo do tabuleiro para a parte de cima
                int i = 8 - Integer.parseInt(valor_i);
                int j = 8 - Integer.parseInt(valor_j);
                dj.jogador2.jogadaPreparada = Boolean.parseBoolean(valor_preparaJogada);
                dj.jogador2.jogadaI = 8 - Integer.parseInt(valor_JogadaI);
                dj.jogador2.jogadaJ = 8 - Integer.parseInt(valor_JogadaJ);
                dj.jogador2.salto = Boolean.parseBoolean(valor_salto);

                tabuleiro.jogadorPreparaJogada(i, j, dj.jogador2);
                textAreaMensagem.appendText("\n" + dj.jogador2.nome + " preparou jogada");
            } // - anulaJogada:<i>:<j>:<preparaJogada>:<jogadaI>:<jogadaJ>:<salto>   
            //-- jogador desistiu da jogada na casa i,j
            else if (token.equals("anulaJogada")) {
                String valor_i = st.nextToken();
                String valor_j = st.nextToken();
                String valor_preparaJogada = st.nextToken();
                String valor_JogadaI = st.nextToken();
                String valor_JogadaJ = st.nextToken();
                String valor_salto = st.nextToken();
                //Converte as coordenadas da parte de baixo do tabuleiro para a parte de cima
                int i = 8 - Integer.parseInt(valor_i);
                int j = 8 - Integer.parseInt(valor_j);
                dj.jogador2.jogadaPreparada = Boolean.parseBoolean(valor_preparaJogada);
                dj.jogador2.jogadaI = 8 - Integer.parseInt(valor_JogadaI);
                dj.jogador2.jogadaJ = 8 - Integer.parseInt(valor_JogadaJ);
                dj.jogador2.salto = Boolean.parseBoolean(valor_salto);
                tabuleiro.jogadorAnulaJogada(i, j, dj.jogador2);
                textAreaMensagem.appendText("\n" + dj.jogador2.nome + " anulou jogada preparada");
            } // - fazJogada:<i>:<j>:<preparaJogada>:<jogadaI>:<jogadaJ>:<salto>      
            // -- jogador conclui a jogada na casa i,j
            else if (token.equals("fazJogada")) {
                String valor_i = st.nextToken();
                String valor_j = st.nextToken();
                String valor_preparaJogada = st.nextToken();
                String valor_JogadaI = st.nextToken();
                String valor_JogadaJ = st.nextToken();
                String valor_salto = st.nextToken();
                //Converte as coordenadas da parte de baixo do tabuleiro para a parte de cima
                int i = 8 - Integer.parseInt(valor_i);
                int j = 8 - Integer.parseInt(valor_j);
                dj.jogador2.jogadaPreparada = Boolean.parseBoolean(valor_preparaJogada);
                dj.jogador2.jogadaI = 8 - Integer.parseInt(valor_JogadaI);
                dj.jogador2.jogadaJ = 8 - Integer.parseInt(valor_JogadaJ);
                dj.jogador2.salto = Boolean.parseBoolean(valor_salto);
                tabuleiro.jogadorFazJogada(i, j, dj.jogador2);
                textAreaMensagem.appendText("\n" + dj.jogador2.nome + " fez jogada");
                System.out.println(dj.jogador2);
            } // - vencedor -- jogador declara-se vencedor
            else if (token.equals("vencedor")) {
                textAreaMensagem.appendText("\nAdversario declara-se vencedor.");
            } // - terminar -- jogador decide terminar o jogo
            else if (token.equals("terminar")) {
                textAreaMensagem.appendText("\nAdversario decidiu terminar o jogo.");
            } // Desconhecido
            else {
                textAreaMensagem.appendText("\nMensagem desconhecida.");
            }
        });
        th = new Thread(recebe);
        th.start();

        iniciaNovoJogo();
    }

    /**
     * Método utilizado para iniciar um novo jogo, cria um tabuleiro tendo em
     * conta as propriedades de Dados do Jogo e altera as labels dos jogadores
     */
    public void iniciaNovoJogo() {
        // coloca os nomes dos jogadores
        jogador1.setText(dj.jogador1.nome);
        jogador1.setStyle("-fx-alignment: CENTER;  -fx-background-color: " + dj.jogador1.cor + ";");
        jogador2.setText(dj.jogador2.nome);
        jogador2.setStyle("-fx-alignment: CENTER;  -fx-background-color: " + dj.jogador2.cor + ";");
        // cria o tabuleiro de jogo
        tabuleiro = new Tabuleiro(dj);
        tabuleiro.setCorJogador(dj.jogador1.cor);
        tabuleiro.setCorAdversario(dj.jogador2.cor);
        tabuleiro.desenhaTabuleiro(APane);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Permite aceder a pag dao menu inicial
     */
    @FXML
    private void respondeDesistir(ActionEvent event) throws IOException {         //AMBOS OS JOGADORES TÊM DE SAIR (SÓ ESTÁ A SAIR UM)          
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController mc = loader.getController();

        // cancela a tarefa dereceber e desliga o socket
        recebe.cancel();
        dj.cs.enviaMensagem("terminar");
        dj.cs.enviaMensagem("logout");
        dj.cs.desliga();
        mc.setDadosJogo(dj);//os dados do jogo devem acompanhar cada jogador

        Stage window = (Stage) BotaoDesistir.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * Permite passar o turno/a vez ao adversário
     */
    @FXML
    private void acabarTurno(ActionEvent event) throws IOException {
        if (dj.jogador1.turno == true) {
            dj.cs.enviaMensagem("passaVez");
            dj.jogador2.turno = true;
            dj.jogador1.turno = false;
            System.out.println("Passa a vez");
        } else {
            System.out.println("Não é a vez de jogar");
        }
    }

}
