/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author herquiloidehele
 */
public class CartaoVoda extends SIM{
    
    private String pais;
    private String loja;

    public CartaoVoda(String pais, String loja, String proprietario, String numero, double saldo) {
        super(proprietario, numero, saldo);
        this.pais = pais;
        this.loja = loja;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }
    
    
    
    
    @Override
    public boolean recarregar(double valor) {
        if(valor > 0){
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
