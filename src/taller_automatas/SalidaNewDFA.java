/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller_automatas;
import java.io.*;
/**
 *
 * @author Luis
 */
public class SalidaNewDFA {
    
    private String estadosFinales ="";
    private String input = "";
    private String estSiguiente = "";
    public SalidaNewDFA(){}
    
    public void formatearDatos(Automata a) {
        

       
    }
    
    public boolean crearArchivo(Automata a){
        //Un texto cualquiera guardado en una variable

        try {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            File archivo = new File("DFA.gv");
            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            FileWriter escribir = new FileWriter(archivo, true);
            //Escribimos en el archivo con el metodo write 
            escribir.write("digraph finite_state_machine {\n");
            escribir.write("rankdir = LR\n");
            escribir.write("size = 9\n");
            escribir.write("node[shape = doublecircle];");
            //Obtengo los estados finales para ponerlos en el archivo
            for (String ef : a.listaEstadosFinales) {
                this.estadosFinales = this.estadosFinales +String.valueOf((char)34)+ef+String.valueOf((char)34);
            }
            escribir.write(this.estadosFinales+";\n");
            //Los estados tendran forma de circulo
            escribir.write("node[shape = circle];\n");
            for (String trans: a.listaTransiciones) {
                //Obtengo el estado
                int indiceEstado = trans.indexOf(": ");
                String est = trans.substring(0, indiceEstado);
                //Si es que la longitud del estado es mayor a 2, lo cierro con comillas ""
                if (est.length() > 2) {
                    est = String.valueOf((char)34)+est+String.valueOf((char)34);
                }
                //Obtengo las transiciones
                String tr = trans.substring(indiceEstado + 2, trans.length());
                String[] trF = tr.split(", ");
                //Recorro las subTransiciones
                for (String subT: trF) {
                    //Si es que existe un epsilon en la transicion
                    if (subT.contains("epsilon")) {
                        this.estSiguiente = subT.substring(10, subT.length());
                        //Char del valor epsilon
                        this.input = String.valueOf((char)949);
                    } else {
                        this.estSiguiente = subT.substring(4, subT.length());
                        //Si es que la longitud del estado siguiente es mayor a 2, lo cierro con comillas ""
                        if (this.estSiguiente.length() > 2) {
                            this.estSiguiente = String.valueOf((char)34)+subT.substring(4, subT.length())+String.valueOf((char)34);
                        }
                        this.input = subT.substring(0, 1);
                    }
                    //Escribo las transiciones
                    escribir.write(est+" -> "+this.estSiguiente+"[ label = "+String.valueOf((char)34)+this.input+String.valueOf((char)34)+" ]\n");
                }
            }
            escribir.write("}");
            
            //Cerramos la conexion
            escribir.close();
        } 
        //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }
        return false;
    }
    
}
