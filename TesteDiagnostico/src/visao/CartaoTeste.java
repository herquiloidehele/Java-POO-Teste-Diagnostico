/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.Controle;
import java.util.ArrayList;
import java.util.Collections;
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
        
        return Integer.parseInt(JOptionPane.showInputDialog("Selecione o id do Proprietario: \n"+sims));
    }
   
    
    public static void main(String[] args) {
        
        int opcao ;
        Controle controle = new Controle("SIM.txt");

        
        do{
            opcao = menuPrincipal();
            
            switch(opcao){
                case 1: {
                        String proprietario = JOptionPane.showInputDialog(null, "Proprietario: ");
                        String pais = JOptionPane.showInputDialog(null, "Localizacao da loja: ");
                        CartaoVoda voda = new CartaoVoda(controle.gerarId(), pais, proprietario, "847005571");
                        controle.salvar(voda);
                }break;
                
                case 2: {
                        String proprietario = JOptionPane.showInputDialog(null, "Proprietario: ");
                        String localizacao = JOptionPane.showInputDialog(null, "Localizacao: ");
                        String loja = JOptionPane.showInputDialog(null, "Loja: ");
                        CartaoMcel mcel = new CartaoMcel(controle.gerarId(), localizacao, loja, proprietario, "847005571");
                        controle.salvar(mcel);
                }break;
                
                case 3: {
                        SIM sim = controle.pesquisar(mostarSIM(controle));
                        double saldo = Double.parseDouble(JOptionPane.showInputDialog(null, "Introduza o valor da recarga"));
                        if(sim.recarregar(saldo)){
                            controle.actualizar(sim);
                            JOptionPane.showMessageDialog(null, "Recarga Efectuada com Sucesso");
                        }else
                            JOptionPane.showMessageDialog(null, "Erro ao Recarregar");
                }break;
                
                case 4: {
                        SIM origem = (SIM) JOptionPane.showInputDialog(null, "Selecione o cartao Origem", "Origem", JOptionPane.QUESTION_MESSAGE, null, controle.listarTodos().toArray(), 0);
                        SIM destino = (SIM) JOptionPane.showInputDialog(null, "Selecione o cartao Destino", "Destino", JOptionPane.QUESTION_MESSAGE, null, controle.listarTodos().toArray(), 0);  
                        double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Introduza o valor"));
                        if(SIM.tranferir(origem, destino, valor)){
                            controle.actualizar(origem);
                            controle.actualizar(destino);
                          JOptionPane.showMessageDialog(null, "Transferencia efectuada com sucesso");
                        }else
                            JOptionPane.showMessageDialog(null, "Tranferencia nao efectuada \n" + origem.toString() + destino.toString());   
                } break;

                case 5: {
                        String a = "";
                        ArrayList<SIM> cartoes = controle.listarTodos();
                        Collections.sort(cartoes);
                        for (SIM sim : cartoes)
                            a += sim.toString();
                        JOptionPane.showMessageDialog(null, a);
                } break;
                
                
                case 0 : return;     
            }
        }while(true);
    
    }
 
    
    
}
