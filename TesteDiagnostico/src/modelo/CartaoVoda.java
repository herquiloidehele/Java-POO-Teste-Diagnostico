/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author herquiloidehele
 */
public class CartaoVoda extends SIM implements Serializable{
    
    private String pais;

    public CartaoVoda(int id, String pais, String proprietario, String numero) {
        super(id, proprietario, numero);
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    
    
    @Override
    public boolean recarregar(double valor) {
         
        if(valor > 0){
            System.out.println("Recarga Voda");
            double bonus = valor*0.5;
            this.setSaldo(this.getSaldo() + valor + bonus);
            return true;
        }
        return false;
    }
    
    
    public boolean recarregar(double valor, double bonus){
       if(valor > 0){
            this.setSaldo(this.getSaldo() + valor + bonus);
            return true;
        }
        return false;
    }
    
    
    
    
    
}
