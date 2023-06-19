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

 
}

