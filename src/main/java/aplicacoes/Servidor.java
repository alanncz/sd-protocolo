package aplicacoes;

import java.io.IOException;
import java.nio.ByteBuffer;
import protocolo.Menssagem;
import protocolo.Protocolo;
import protocolo.ProtocoloService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alann
 */
public class Servidor {

    public static void main(String[] args) throws IOException {
        
        ProtocoloService protocolo = new Protocolo();

        Menssagem requisicao = protocolo.getRequest();

        int operacao = requisicao.getOperacao();
        Menssagem resposta = null;

        if (operacao == 1) {
            resposta = new Menssagem(0, soma(requisicao));

        }

        protocolo.sendReply(requisicao.getRemetente(), resposta);

    }

    public static byte[] soma(Menssagem requisicao) {
        
        ByteBuffer bff = ByteBuffer.wrap(requisicao.getArgs());

        int valor1 = bff.getInt();
        int valor2 = bff.getInt();

        int soma = valor1 + valor2;

        String resposta = "A soma dos numeros é = " + Integer.toString(soma);
        
        return resposta.getBytes();

    }

}
