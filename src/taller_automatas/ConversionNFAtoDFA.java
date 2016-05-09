/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller_automatas;

/**
 *
 * @author Luis
 */
public class ConversionNFAtoDFA {
    
    private int cantEstados;
    private int cantAlfabeto;
    private Automata DFA;
    //private Automata NFA;
    
    private int prueba;
    
    public ConversionNFAtoDFA(Automata NFA){
    
        this.DFA = new Automata();  
        this.cantEstados = NFA.listaEstados.size();
        this.cantAlfabeto = NFA.alfabeto.size();
        //Conversion(NFA);
        System.out.println(this.cantEstados);
        System.out.println(this.cantAlfabeto);
    }
    
    private void Conversion(Automata NFA){
        
        String[][] matrizNFA = new String[this.cantAlfabeto+1][this.cantEstados];
        
        for(int i=0;i <this.cantAlfabeto; i++){
            String caracter = NFA.alfabeto.get(i);
            String[] aux;
            String[] aux2;
            for(int j=0;j <this.cantEstados; j++){
                String estado = NFA.listaEstados.get(j);
                for(String trans:NFA.listaTransiciones){
                    aux = trans.split(":");
                    if(aux[1].equals(estado)){
                        aux2 = aux[1].split("-");
                        if(aux2[0].equals(caracter)){
                            matrizNFA[i][j] = aux2[1];
                        }
                    }
                }
                if(matrizNFA[i][j].isEmpty()) {
                    matrizNFA[i][j]="-";
                }
            }
        }
    }
}
