/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller_automatas;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Levi
 */
public class Automata {
    
    /* Defino listas de estados, transiciones y estados finales */
    ArrayList<String> listaEstados;
    ArrayList<String> listaTransiciones;
    ArrayList<String> listaEstadosFinales;
    ArrayList<String> alfabeto;
    /* Leo el archivo desde el directorio por defecto */
    public Automata (){
        
    listaEstados = new ArrayList();
    listaTransiciones = new ArrayList();
    listaEstadosFinales = new ArrayList();
    alfabeto = new ArrayList();
    
    }
        
    public void LecturaNFA (String nombreArchivo){
        try {
		File fileDir = new File(System.getProperty("user.dir")+"\\"+nombreArchivo+".txt");
			
            try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"))) {
                String linea;
                String lenguaje;
                int cantEstados = 0;
                /* Leo la primera linea del archivo hasta encontrar la palabra "epsilon" */
                linea = in.readLine();
                int indice = linea.indexOf("epsilon");
                lenguaje = linea.substring(0, indice);
                //System.out.println(lenguaje);
                String[]caracter = lenguaje.split(" ");
                //contruccion alfabeto
                for(String cha:caracter){
                    //System.out.println(caracter[i]);
                    alfabeto.add(cha);
                }
                
                System.out.println("El Lenguaje aceptado para el automata es: "+lenguaje+(char)949);
                
                while ((linea = in.readLine()) != null) {
                    System.out.println(linea);
                    cantEstados++;
                    /* Por cada linea leida se cuenta un estado */
                    listaEstados.add("q"+cantEstados);
                    /* Guardo cada transicion separada por comas en el arreglo transiciones */
                    String[] transiciones = linea.split(", ");
                    for (String trans: transiciones) {
                        /* Si es que mi transicion leida tiene un *, quiere decir que es un estado final y no una transicion */
                        if (trans.contains("*")) {
                            listaEstadosFinales.add("q"+cantEstados);
                        } else {
                            /* Caso contrario, guardo mi transicion en la lista transiciones */
                            listaTransiciones.add(trans);
                        }
                    }
                }
                
                /**
                 * Imprimo cantidad de estados
                 * Imprimo cantidad de transiciones
                 * Imprimo los estados leidos en el archivo
                 * Imprimo los estados finales
                 * Imprimo las transiciones
                 */
                System.out.println("Cantidad de Estados: "+cantEstados);
                System.out.println("Cantidad de Transiciones: "+listaTransiciones.size());
                System.out.println("Estados:");
                listaEstados.stream().forEach((estados) -> {
                    System.out.println(estados);
                });
                System.out.println("Estados Finales:");
                listaEstadosFinales.stream().forEach((estados) -> {
                    System.out.println(estados);
                });
                System.out.println("Transiciones:");
                listaTransiciones.stream().forEach((transiciones) -> {
                    System.out.println(transiciones);
                });
                alfabeto.stream().forEach((car)->{
                    System.out.println(car);
                });
            }
                
	    } 
	    catch (UnsupportedEncodingException e) 
	    {
			System.out.println(e.getMessage());
	    } 
	    catch (IOException e) 
	    {
			System.out.println(e.getMessage());
	    }
	    catch (Exception e)
	    {
			System.out.println(e.getMessage());
	    }
	}
    
}
