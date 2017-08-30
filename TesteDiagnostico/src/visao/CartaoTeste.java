/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.Controle;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.CartaoMcel;
import modelo.CartaoVoda;
import modelo.SIM;

/**
 *
 * @author herquiloidehele
 */
public class CartaoTeste {
    
    static int menuPrincipal(){
    
        return Integer.parseInt(
                JOptionPane.showInputDialog("1. Registar Cartao da Vodacom\n"
                                           +"2. Registar Cartao da Mcel\n"
                                           +"3. Recarregar Saldo de um Cartao\n"
                                           +"4. Transferir Saldo entre dois Cartoes\n"
                                           +"5. Listar Todos cartoes registados\n"
                                           +"0. Sair"));
        
        
    }
    
    
    static int mostarSIM(Controle controle){
        String sims = "";
        int cont = 1;
        ArrayList<SIM> lista = controle.listarTodos();
        for (SIM sim : lista){
            sims += sim.getId()+". " + sim.getProprietario() + "\n";
            cont++;
        }
        
        return Integer.parseInt(JOptionPane.showInputDialog(sims));
    }
   
    
    public static void main(String[] args) {
        
        int opcao ;
        Controle controle = new Controle("SIM.dat");
        ArrayList<SIM> lista = new ArrayList<>();

        do{
            opcao = menuPrincipal();
            
            switch(opcao){
                case 1: {
                        String proprietario = JOptionPane.showInputDialog(null, "Proprietario: ");
                        String pais = JOptionPane.showInputDialog(null, "Pais: ");
                        String loja = JOptionPane.showInputDialog(null, "Loja: ");
                        CartaoVoda voda = new CartaoVoda(pais, loja, proprietario, "847005571", 100);
                        controle.salvar(voda);
                }break;
                
                case 2: {
                        String proprietario = JOptionPane.showInputDialog(null, "Proprietario: ");
                        String localizacao = JOptionPane.showInputDialog(null, "Localizacao: ");
                        String loja = JOptionPane.showInputDialog(null, "Loja: ");
                        CartaoMcel mcel = new CartaoMcel(localizacao, loja, proprietario, "847005571", 100);
                        controle.salvar(mcel);
                }break;
                
                case 3: {
                        int sim = mostarSIM(controle);
                        
                        double saldo = Double.parseDouble(JOptionPane.showInputDialog(null, "Introduza o saldo"));
                }
                
            }
            
            
        }while(opcao!=0);
        
        
        
        
        
        
        
        
        
    }
 
    
    
}
