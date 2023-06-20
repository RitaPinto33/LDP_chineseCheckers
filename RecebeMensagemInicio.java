/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChineseCheckers;

import javafx.concurrent.Task;

/**
 * <p>RecebeMensagemInicio<p>
 * Classe que é utilizada para receber uma mensagem inicial enquanto o socket estiver ligado
 * 
 * @author João Miranda & Leonardo Andrade & Miguel Cruzeiro
 */
public class RecebeMensagemInicio extends Task {

    private ClientSocket cs;

    /**
     *Construtor da classe
     * @param cs
     */
    public RecebeMensagemInicio(ClientSocket cs) {
        this.cs = cs;
    }

     /**
     * Este método é utilizado para receber uma mensagem por parte do Client Socket
     * @return 
     * @throws java.lang.Exception 
     */
    @Override
    protected String call() throws Exception {
        System.out.println("Recebe Mensagem inicial: ");
        // enquanto o socket estiver ligado e a Task não for cancelada
        if (cs.ligado && !isCancelled()) {
            // recebe uma mensagem e notifica 
            String mensagem = cs.recebeMensagem();
            updateMessage(mensagem);
            System.out.println("Mensagem inicial recebida: " + mensagem);
        }
        System.out.println("Termina receção de mensagens");

        return null;
    }

}
