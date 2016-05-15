/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller_automatas;

import java.io.*;

/**
 *
 * @author Loca
 */
public class Taller_Automatas {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        //Leo por teclado el archivo a Convertir.
        BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese Nombre del Archivo NFA a Convertir: ");
        String nombre = lectura.readLine();
        //Creo el automata segun lo datos leidos en el archivo.
        Automata at = new Automata();
        at.LecturaNFA(nombre);
        //Convierto el automata creado de NFA a DFA
        Conversion conversionNFAtoDFA = new Conversion();
        //Obtengo el automata resultante
        Automata DFAResultante = conversionNFAtoDFA.ConversionNFAtoDFA(at);
        //Creo el archivo de salida para Graphviz con el automata resultante.
        SalidaNewDFA gg = new SalidaNewDFA();
        gg.crearArchivo(DFAResultante);

        
    }
}
