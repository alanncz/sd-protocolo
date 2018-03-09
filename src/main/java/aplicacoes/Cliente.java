/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacoes;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import protocolo.Menssagem;
import protocolo.Protocolo;

/**
 *
 * @author alann
 */
public class Cliente {

    public static void main(String[] args) throws SocketException, IOException {

        Protocolo protocolo = new Protocolo(4040,5050);
        
        int valor1 = 5;
        int valor2 = 25;
        
        int tamanho = Integer.BYTES * 2;
        
        ByteBuffer bff = ByteBuffer.allocate(tamanho);
        
        bff.putInt(valor1);
        bff.putInt(valor2);

        Menssagem requisicao = new Menssagem(2, bff.array());

        String destino = "localhost";
        
        Menssagem resposta = protocolo.doOperation(destino, requisicao);
        
        String texto = new String(resposta.getArgs());
        
        System.out.println(texto);

    }

}
