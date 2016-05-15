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
public class Conversion{
    
    private int cantEstados;
    private int cantAlfabeto;
    //private Automata DFA;
    private Automata NFA;
    
    public Conversion(){}
    
    public Automata ConversionNFAtoDFA(Automata NFA){
    
        //this.DFA = new Automata();
        this.NFA = NFA;
        this.cantEstados = NFA.listaEstados.size();
        this.cantAlfabeto = NFA.listaAlfabeto.size();
        return Conversion();
//        System.out.println(this.cantEstados);
//        System.out.println(this.cantAlfabeto);
    }
    
    private Automata Conversion(){
        
        String[][] matrizNFA = MatrizNFA(this.NFA);
        
//        for(int i=0;i<this.cantEstados;i++){
//            for(int j=0; j<this.cantAlfabeto+1; j++){
//            System.out.print(matrizNFA[i][j]+" ");
//            }
//            System.out.println("");
//        }
        return DFAResultante(matrizNFA);
        
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
                        if(caracterTransicion.trim().equals(caracter.trim())){
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
        Automata DFA;
        ArrayList<String> estadosDFA = new ArrayList();
        ArrayList<String> transicionesDFA = new ArrayList();
        ArrayList<String> alfabetoDFA;
        ArrayList<String> estadosAceptacionDFA = new ArrayList();
        //variables para estados
        int posicionEps;
        //variables para transiciones
        String transiciónActual;
        
        //Reescribir matriz
        for(int i = 0;i<this.cantEstados;i++){
            for(int j=0;j<this.cantAlfabeto;j++){
                for(int k=0;k<this.cantEstados;k++){
                    String[] estadosContenidos = matrizNFA[k][cantAlfabeto].split(",");
                    if(estadosContenidos[0].equals(matrizNFA[i][j])){                        
                        matrizNFA[i][j] = matrizNFA[k][cantAlfabeto];
                        break;
                    }
                }
            }
        }
        
        for(int i=0;i<this.cantEstados;i++){
            for(int j=0; j<this.cantAlfabeto+1; j++){
            System.out.print(matrizNFA[i][j]+" ");
            }
            System.out.println("");
        }
        
        //Obtener los estados
        int contador = 0;
        posicionEps = this.cantAlfabeto;
        for(int i = 0;i<this.cantEstados;i++){
            for(int j=0;j<this.cantAlfabeto;j++){
                boolean estadosDFAVacio = estadosDFA.isEmpty();
                if(estadosDFAVacio){
                    estadosDFA.add(matrizNFA[i][j]);
                //    System.out.println(estadosDFA.get(contador));
                }else{
                    boolean estadoRepetido = false;
                    for(String estadoDelDFA:estadosDFA){
                        estadoRepetido= estadosDFA.contains(matrizNFA[i][j])||sonIguales(estadoDelDFA,matrizNFA[i][j]);
                    }
                    if(!estadoRepetido){
                        estadosDFA.add(matrizNFA[i][j]);
                        contador++;
                //        System.out.println(estadosDFA.get(contador));
                    }
                }
            }
        }
        
        //Obtener las transiciones
        
        for(String estado:estadosDFA){
            
            for(int i = 0;i<this.cantEstados;i++){
                boolean estadosIguales = estado.equals(matrizNFA[i][this.cantAlfabeto]);
                if(estadosIguales){
                    String transicion = "vacio";
                    for(int j = 0;j<this.cantAlfabeto;j++){
                        String caracter = this.NFA.listaAlfabeto.get(j);
                        if(j==0){
                            transicion = estado+": "+caracter+" – "+matrizNFA[i][j];
                            
                        }else{
                            transicion = transicion+", "+caracter+" – "+matrizNFA[i][j];
                        }
                    }
                    transicionesDFA.add(transicion);
//                    System.out.println(transicion);
                }
                
            }
        }
        
        //Obtener alfabeto
        
        alfabetoDFA = this.NFA.listaAlfabeto;
        
        //Obtener estados de Aceptación
        
        for(String estado: estadosDFA){
            for(String estado2: this.NFA.listaEstadosFinales){
                boolean estadoContieneEstado2 = estado.contains(estado2);
                if(estadoContieneEstado2){
                    estadosAceptacionDFA.add(estado);
                    System.out.println(estado);
                    break;
                }
            }
        }
        
        DFA = new Automata(estadosDFA,transicionesDFA, estadosAceptacionDFA, alfabetoDFA);
        return DFA;
    }
    
    public boolean sonIguales(String a, String b) {
        boolean contiene = false;
        if (a.length() != b.length()) {
            return contiene;
        }
        String[] aux = a.split(",");
        for (String estados: aux) {
            if (b.contains(estados)) {
                contiene = true;
            } else {
                contiene = false;
                return contiene;
            }
        }
        return contiene;
    }

}
