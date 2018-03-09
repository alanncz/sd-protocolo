/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author alann
 */
public class Protocolo {

    private int portaRequisicao; // porta usada para enviar requisicao 
    private int portaResposta;   // porta usada para receber respostas

    public Protocolo(int portaRequisicao, int portaResposta) {

        this.portaRequisicao = portaRequisicao;
        this.portaResposta = portaResposta;
    }

    public int getPortaRequisicao() {
        return portaRequisicao;
    }

    public void setPortaRequisicao(int portaRequisicao) {
        this.portaRequisicao = portaRequisicao;
    }

    public int getPortaResposta() {
        return portaResposta;
    }

    public void setPortaResposta(int portaResposta) {
        this.portaResposta = portaResposta;
    }

    /*
    Metodo usado pelo cliente para realizar uma requisicao do lado do servidor 
    recebendo como parametro o destino e a menssagem a ser enviada.
    */
    public Menssagem doOperation(String destino, Menssagem args) {

        try {

            String remetente = InetAddress.getLocalHost().getHostAddress();
            args.setRemetente(remetente);

            RemoteRef rr = new RemoteRef(destino, portaRequisicao);

            InetAddress addr = InetAddress.getByName(rr.getDestino());
            int port = rr.getPorta();

            byte[] msg = Convert.convertEnvio(args);

            DatagramPacket pkg = new DatagramPacket(msg, msg.length, addr, port);

            DatagramSocket ds = new DatagramSocket(portaResposta);
            ds.send(pkg);

            DatagramPacket resposta = new DatagramPacket(new byte[1024], 1024);
            ds.receive(resposta);

            return Convert.convertResposta(resposta.getData());

        } catch (IOException ex) {
        }
        return null;
    }

    /*
    metodo utilizado pelo servidor para aguardar e receber requisições.
    */
    public Menssagem getRequest() {

        try {
            DatagramSocket ds = new DatagramSocket(portaRequisicao);

            byte[] msg = new byte[1024];

            DatagramPacket pkg = new DatagramPacket(msg, msg.length);

            ds.receive(pkg);

            msg = pkg.getData();

            Menssagem requisicao = (Menssagem) Convert.convertResposta(msg);

            System.out.println(requisicao.toString());

            System.out.println("Menssagem recebida de " + pkg.getAddress());

            return requisicao;
        } catch (IOException ex) {
        }
        return null;
    }

    /*
    metodo utilizado pelo servidor para apos ter tratado um requisicao, enviar
    uma resposta para o seu devido cliente.
    */
    public void sendReply(String destino, Menssagem resposta) {

        try {

            String remetente = InetAddress.getLocalHost().getHostAddress();
            resposta.setRemetente(remetente);

            RemoteRef rr = new RemoteRef(destino, portaResposta);

            InetAddress addr = InetAddress.getByName(rr.getDestino());
            int port = rr.getPorta();

            byte[] msg = Convert.convertEnvio(resposta);

            DatagramPacket pkg = new DatagramPacket(msg, msg.length, addr, port);

            DatagramSocket ds = new DatagramSocket();
            ds.send(pkg);

        } catch (IOException ex) {
        }

    }

}
