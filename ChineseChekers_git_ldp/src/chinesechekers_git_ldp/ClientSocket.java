/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinesechekers_git_ldp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author ritap
 */
public class ClientSocket {

    String serverName;
    int serverPort;

    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    boolean ligado;

    /**
     *Construtor da classe onde se vao inicializar as variaveis da mesma.
     * 
     * @param serverName
     * @param serverPort
     */
    public ClientSocket(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.s = null;
        this.dis = null;
        this.dos = null;
        this.ligado = false;
    }

    /**
     *Método que permitirá enviar uam menssagem para o servidor.
     * @param mensagem
     * @throws IOException
     */
    public void enviaMensagem(String mensagem) throws IOException {
        if (ligado) {
            dos.writeUTF(mensagem);
        }
    }

    /**
     *Método que permitirá receber e ler uma menssagem do servidor.
     * @return
     * @throws IOException
     */
    public String recebeMensagem() throws IOException {
        String mensagem = null;
        if (ligado) {
            mensagem = dis.readUTF();
        }
        return mensagem;
    }

    /**
     *O método liga permite fazer a ligação de um cliente com o servidor.
     * @throws IOException
     */
    public void liga() throws IOException {
        if (!ligado) {
            InetAddress ip = InetAddress.getByName(serverName);
            s = new Socket(ip, serverPort);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        }
        ligado = true;
    }

    /**
     *O método desliga permite desligar o cliente do servidor.
     * @throws IOException
     */
    public void desliga() throws IOException {
        if (ligado) {
            s.close();
            dis = null;
            dos = null;
        }
        ligado = false;
    }
}

