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
    
    private int prueba2;
    
    public ConversionNFAtoDFA(Automata NFA){
    
        this.DFA = new Automata();  
        this.cantEstados = NFA.listaEstados.size();
        this.cantAlfabeto = NFA.alfabeto.size();
        Conversion(NFA);
//        System.out.println(this.cantEstados);
//        System.out.println(this.cantAlfabeto);
    }
    
    private void Conversion(Automata NFA){
        
        String[][] matrizNFA = new String[this.cantEstados][this.cantAlfabeto+1];
        
        for(int i=0;i <this.cantEstados; i++){
            String estado = NFA.listaEstados.get(i);
            for(int j=0;j <this.cantAlfabeto+1; j++){
                String caracter;
                if(j==this.cantAlfabeto){
                    caracter = "epsilon";
                }else{  
                    caracter = NFA.alfabeto.get(j);
                }
                String[] aux;
                String[] aux2;
                for(String trans:NFA.listaTransiciones){
                    aux = trans.split(":");
                    if(aux[0].equals(estado)){
                        aux2 = aux[1].split("–");
                        aux2[0] = aux2[0].replaceAll("\\s", "");
                        aux2[1] = aux2[1].replaceAll("\\s", "");
                        if(aux2[0].equals(caracter)){
                            matrizNFA[i][j] = aux2[1];
                            if(caracter.equals("epsilon")){
                                if(matrizNFA[i][j].equals(null)){
                                    matrizNFA[i][j] = estado+","+aux2[1];
                                }else{
                                    matrizNFA[i][j] = matrizNFA[i][j]+","+estado+","+aux2[1];
                                }
                            }
                        }
                    }
                }
                if(matrizNFA[i][j]==null) {
                    matrizNFA[i][j]="-";
                    if(caracter.equals("epsilon")){
                                matrizNFA[i][j] = estado;
                    }
                }
                System.out.print(matrizNFA[i][j]+" ");
            }
            System.out.println();
        }
    }
}
