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
        
        BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese Nombre del Archivo NFA a Convertir: ");
        String nombre = lectura.readLine();
        Automata at = new Automata();
        at.LecturaNFA(nombre);
        Conversion conversionNFAtoDFA = new Conversion();
        Automata DFAResultante = conversionNFAtoDFA.ConversionNFAtoDFA(at);
        
    }
}
