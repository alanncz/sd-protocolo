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
public class Protocolo implements ProtocoloService {

    private static final int PORT_REQUEST = 4342;
    private static final int PORT_RESPONSE = 4545;

    public Protocolo() {
    }

    @Override
    public Menssagem doOperation(String destino, Menssagem args) {

        try {
            String remetente = InetAddress.getLocalHost().getHostAddress();
            args.setRemetente(remetente);

            RemoteRef rr = new RemoteRef(destino, PORT_REQUEST);

            InetAddress addr = InetAddress.getByName(rr.getDestino());
            int port = rr.getPorta();

            byte[] msg = Convert.convertEnvio(args);

            DatagramPacket pkg = new DatagramPacket(msg, msg.length, addr, port);

            DatagramSocket ds = new DatagramSocket(PORT_RESPONSE);
            ds.send(pkg);

            DatagramPacket resposta = new DatagramPacket(new byte[1024], 1024);
            ds.receive(resposta);
            
            return Convert.convertResposta(resposta.getData());

        } catch (IOException ex) {
        }
        return null;
    }

    @Override
    public Menssagem getRequest() {

        try {
            DatagramSocket ds = new DatagramSocket(PORT_REQUEST);

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

    @Override
    public void sendReply(String destino, Menssagem resposta) {

        try {

            String remetente = InetAddress.getLocalHost().getHostAddress();
            resposta.setRemetente(remetente);

            RemoteRef rr = new RemoteRef(destino, PORT_RESPONSE);

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
