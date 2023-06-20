package ChineseCheckers;

import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 * <p>
 * RecebeMensagens<p>
 * Classe que é utilizada para receber mensagens enquanto o socket estiver
 * ligado
 *
 * 
 */
public class RecebeMensagens extends Task {

    private ClientSocket cs;

    /**
     * Construtor da classe
     *
     * @param cs
     */
    public RecebeMensagens(ClientSocket cs) {
        this.cs = cs;
    }

    /**
     * Este método é utilizado para receber mensagens por parte do Client Socket
     * @return 
     * @throws java.lang.Exception
     */
    @Override
    protected String call() throws Exception {
        System.out.println("Recebe Mensagens: ");
        // enquanto o socket estiver ligado e a Task não for cancelada
        while (cs.ligado && !isCancelled()) {
            // recebe uma mensagem e notifica 
            String mensagem = cs.recebeMensagem();
            Platform.runLater(() -> updateMessage(mensagem));
            System.out.println("Mensagem recebida: " + mensagem);
        }
        System.out.println("Termina receção de mensagens");

        return null;
    }

}
