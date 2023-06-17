/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author ritap
 */
public class Server {
      static Vector<ClientHandler> jogadores = new Vector<>();
    static int i = 0; // para atribuir um id a cada jogador
    final static int ServerPort = 1234;

    /**
     *O método main desta classe permitirá adiconar clientes á lista ativa do
     *servidor á medida que os mesmos se conectam, através de um ciclo while.
     *É criado um ClientHandler para cada cliente que se conectar, contendo um DataInput
     *e Output Stream. 
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException { // inicia o server
        System.out.println("Servidor aceita conexões.");
        ServerSocket ss = new ServerSocket(ServerPort); 

        Socket s;
        while (true) { // enquanto espera por jogador
            s = ss.accept();
            System.out.println("Novo client recebido : " + s);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            ClientHandler mtch = new ClientHandler(s, "client " + i, dis, dos); //lida com as  ligações dos jogadores
            Thread t = new Thread(mtch);

            System.out.println("Adiciona cliente " + i + " à lista ativa.");
            jogadores.add(mtch);
            t.start();

            i++;
        }
    }

    
    /**
     * A classe ClientHandler será criada para cada cliente que se ligar
     * e associa ao mesmo um nome, um DataInput,um dataOutput, um Socket
     * e um boolean para verificar a ligação ao servidor do mesmo.
     *
     * 
     */
    private static class ClientHandler implements Runnable {

        private String name;
        final DataInputStream dis;
        final DataOutputStream dos;
        Socket s;
        boolean ligado;

        private ClientHandler(Socket s, String name,
                DataInputStream dis, DataOutputStream dos) {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
            this.name = name;
            this.ligado = true;
        }

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    
}
