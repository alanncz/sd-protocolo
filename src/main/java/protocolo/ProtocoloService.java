/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolo;

/**
 *
 * @author alann
 */
public interface ProtocoloService {
    
    public Menssagem doOperation(String destino, Menssagem args);
    
    public Menssagem getRequest();
    
    public void sendReply(String destino, Menssagem resposta);
    
}
