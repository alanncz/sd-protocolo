/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolo;

import java.io.Serializable;

/**
 *
 * @author alann
 */

public class Menssagem implements Serializable {
    
    private String remetente; // indica quem está enviando a menssagem
    private int operacao;     // operacao a ser executada no servidor
    private byte[] args;      // argumentos das menssagem

    public Menssagem(int operacao, byte[] args) {
        this.operacao = operacao;
        this.args = args;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public int getOperacao() {
        return operacao;
    }

    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }

    public byte[] getArgs() {
        return args;
    }

    public void setArgs(byte[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Menssagem{" + "remetente=" + remetente + ", operacao=" + operacao + ", args=" + args + '}';
    }

    
    
    
    
    
}
