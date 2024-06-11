
package javacraft;

import Interfaz.Principal;
import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.tablaSimbolos;

/**
 *
 * @author Gonzalo 202211515
 */
public class JavaCraft {

    public static void main(String[] args) {
             
        Principal VentanaPrincipal = new Principal();
        VentanaPrincipal.setVisible(true);
    }
    
}
