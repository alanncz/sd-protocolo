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
public class RemoteRef implements Serializable {
    
    private String destino; //host de destino da requisicao 
    private int porta;      // porta do host que vai receber a requisicao 

    public RemoteRef( String destino, int porta) {
        this.destino = destino;
        this.porta = porta;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

   
    
    
}
