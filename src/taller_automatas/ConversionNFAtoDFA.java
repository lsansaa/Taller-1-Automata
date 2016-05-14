/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller_automatas;

import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class ConversionNFAtoDFA {
    
    private int cantEstados;
    private int cantAlfabeto;
    private Automata DFA;
    //private Automata NFA;
    
    public ConversionNFAtoDFA(Automata NFA){
    
        this.DFA = new Automata();  
        this.cantEstados = NFA.listaEstados.size();
        this.cantAlfabeto = NFA.listaAlfabeto.size();
        Conversion(NFA);
//        System.out.println(this.cantEstados);
//        System.out.println(this.cantAlfabeto);
    }
    
    private void Conversion(Automata NFA){
        
        String[][] matrizNFA = MatrizNFA(NFA);
        
        for(int i=0;i<this.cantEstados;i++){
            for(int j=0; j<this.cantAlfabeto+1; j++){
            System.out.print(matrizNFA[i][j]+" ");
            }
            System.out.println("");
        }
        Automata DFA = DFAResultante(matrizNFA);
        
    }
    
    private String [][] MatrizNFA(Automata NFA){
        
        String[][]matrizNFA = new String[this.cantEstados][this.cantAlfabeto+1];
        for(int i=0;i <this.cantEstados; i++){
            String estado = NFA.listaEstados.get(i);
            for(int j=0;j <this.cantAlfabeto+1; j++){
                String caracter;
                if(j==this.cantAlfabeto){
                    caracter = "epsilon";
                }else{  
                    caracter = NFA.listaAlfabeto.get(j);
                }
                String[] aux;
                String[] aux2;
                for(String trans:NFA.listaTransiciones){
                    aux = trans.split(":");
                    if(aux[0].equals(estado)&&!aux[1].equals(" ")){
                        aux2 = aux[1].split("–");
                        aux2[0] = aux2[0].replaceAll("\\s", "");
                        aux2[1] = aux2[1].replaceAll("\\s", "");
                        String caracterTransicion = aux2[0];
                        if(caracterTransicion.equals(caracter)){
                            if(caracter.equals("epsilon")){
                                if(matrizNFA[i][j]==null){
                                    matrizNFA[i][j] = estado;
                                }
                                matrizNFA[i][j] = matrizNFA[i][j]+","+aux2[1];
                            }else{
                                if(matrizNFA[i][j]==null){
                                    matrizNFA[i][j] = aux2[1];
                                }else{
                                    matrizNFA[i][j] = matrizNFA[i][j]+","+aux2[1];
                                }
                            }
                        }
                    }
                }
                if(matrizNFA[i][j]==null) {
                    matrizNFA[i][j]="q";
                    if(caracter.equals("epsilon")&&j==this.cantAlfabeto){
                                matrizNFA[i][j] = estado;
                    }
                }
            }
        }
        
        
        return  matrizNFA;
    }
    
    private Automata DFAResultante(String [][] matrizNFA){
        
        String[][] matrizDFA = matrizNFA;
        Automata DFA = new Automata();
        ArrayList<String> estadosDFA = new ArrayList();
        ArrayList<String> transicionesDFA = new ArrayList();
        ArrayList<String> alfabeto = new ArrayList();
        //variables para estados
        int posicionEps;
        //variables para transiciones
        String transiciónActual;
        
        //Obtener los estados
        posicionEps = this.cantAlfabeto;
        for(int i = 0;i<this.cantEstados;i++){
            boolean estadosDFAVacio = estadosDFA.isEmpty();
            if(estadosDFAVacio){
                estadosDFA.add(matrizNFA[i][posicionEps]);
            }else{
                boolean estadoRepetido= estadosDFA.contains(matrizNFA[i][posicionEps]);
                if(!estadoRepetido){
                    estadosDFA.add(matrizNFA[i][posicionEps]);
                }
            }
        }
        
        //Obtener estados de las transiciones
        
        
        
        
        //DFA = new Automata(estadosDFA);
        return DFA;
    }

}
