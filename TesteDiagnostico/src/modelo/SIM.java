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
public  abstract class SIM implements Serializable, Comparable<SIM>{
    
    
    private int id;
    private String proprietario;
    private String numero;
    private double saldo;

    public SIM(int id, String proprietario, String numero) {
        this.proprietario = proprietario;
        this.numero = numero;
        this.setSaldo(20);
        this.id = id;
        
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }


    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    
    public abstract boolean recarregar(double valor);
    
    public static boolean tranferir (SIM origem, SIM destino, double valor){
        if(origem.getClass().getName().equals(destino.getClass().getName())){
            origem.setSaldo(origem.getSaldo()-valor);
            destino.setSaldo(destino.getSaldo()+valor);
            return true;
        }
        return false;
    }
    
    
    @Override
    public String toString() {
        return "\nid: " + id + "    \nProprietario: " + this.getProprietario() + "    \nSaldo: " + this.getSaldo() +"\n\n";
    }

    @Override
    public int compareTo(SIM outro) {
        if(this.getSaldo() == outro.getSaldo())
            return 1;
        if(this.getSaldo() > outro.getSaldo())
            return 1;
        return -1;     
    }

    
    
    
    
}
