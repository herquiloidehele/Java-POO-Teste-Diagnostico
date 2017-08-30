 
package controle;

import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.SIM;

/**
 * Esta classe eh responsavel por manter a iteacao direcata com
 * o ficheiro. Possui metodos para salvar, listar e pesquisar SIMs
 * 
 * @author Herquilide
 */
public class Controle {

    
    /**
     * Nome do ficheiro 
     */
    private String ficheiro;
    
    
    
    
    /**
     * Contrutor do contralador onde eh feita a abertura ou cricao do ficheiro
     * @param ficheiro 
     */
    public Controle(String ficheiro) {
        this.ficheiro = ficheiro;  
            
        if(!this.verificarFicheiro(ficheiro)){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(ficheiro);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
            
    }
    
    
    /**
     * Busca todos os registros existentes no ficheiro
     * @return lista de todos sims
     */
    public ArrayList<SIM> listarTodos(){
       
        ArrayList<SIM> sims = new ArrayList<>();
        
        try {
            FileInputStream fileInputStream  = new FileInputStream(ficheiro);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
          
             sims = (ArrayList<SIM>) objectInputStream.readObject();
            
        }catch(EOFException ex){
            return sims;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Litsar sim" + ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Litsar sim" + ex);
        }
        return sims;
    }

    
    /**
     * Salva um determinado sim no ficheiro.
     * Para tal eh necessario buscar todos os sims existentes no ficheiro
     * e voltar a salvar novamente todos.
     * @param sim
     * @return true se o sim for gravado e false caso contrario
     */
    public boolean salvar(SIM sim){
        ArrayList<SIM> sims = new ArrayList<SIM>();
        sims.addAll(this.listarTodos());
        sims.add(sim);
        try {
            
            FileOutputStream fileOutputStream = new FileOutputStream(ficheiro);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            
            objectOutputStream.writeObject(sims);
            objectOutputStream.close();
            JOptionPane.showMessageDialog(null, "SIMs Salvos com sucesso");
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar sim" + ex.getMessage());
            return false;
        }
    }  
    
    
    public boolean salvar(ArrayList<SIM> sims){
        try {
            
            FileOutputStream fileOutputStream = new FileOutputStream(ficheiro);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            
            objectOutputStream.writeObject(sims);
            objectOutputStream.close();
            JOptionPane.showMessageDialog(null, "SIMs Salvos com sucesso");
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar sim" + ex.getMessage());
            return false;
        }
    }  
    
    
    /**
    * Metodo que Permite pesquisar umsim em funcao do seu Codigo
    * @param id  codigo do SIM
    * @return O sim se for encontrado, caso contrario reorna null;
    */
    public SIM pesquisar(int id){
        ArrayList<SIM> sims = listarTodos();
        
        boolean existe = false;

        for(SIM sim : sims){
               if(id == sim.getId()){
                   existe = true;
                 return sim;
               }    
        }
        
        if (existe != true){
            JOptionPane.showMessageDialog(null, "SIM Introduzido nao existe!");  
            
        }
        
        return null;
    
    }
 

    /**
     * Actualiza um determinado sim 
     * @param simUpdate
     * @return 
     */
    public ArrayList<SIM> actualizar(SIM simUpdate){
        ArrayList<SIM> sims = listarTodos();
        SIM sim = pesquisar(simUpdate.getId());
        
        if(sim != null){
        
            int index = this.buscarIndex(sim.getId());
            if(index >= 0){
                
                sims.set(index, simUpdate);
                this.salvar(sims);
            }
            else
                JOptionPane.showMessageDialog(null, "SIM nao existe na lista");
        }else
            JOptionPane.showMessageDialog(null, "O livro nao existe");
        return sims;
        
    }
    
  
    
    public int buscarIndex(int id){
        
        ArrayList<SIM>  sims = this.listarTodos();
        for (int i=0; i<sims.size(); i++){
            if(id == sims.get(i).getId())
                return i;
        }
        
        return -1;
        
    }
    
    
    
    
    
    /**
     * Gera o proximo id para um determinado SIM
     * @return id do sim
     */
    public int gerarId(){
        return this.listarTodos().size() + 1;   
    }
    
    
    /**
     * verifica se um determinado ficheiro ja foi criado ou nao
     * @param ficheior
     * @return 
     */
    public boolean verificarFicheiro(String ficheior){
        return new File(this.ficheiro).exists();
    }
    
    
    
}
