/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import java.util.HashMap;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class AtributosAux extends Instruccion {
    public String id; 
    public HashMap<String, Instruccion> atributos;

    public AtributosAux(String id, HashMap<String, Instruccion> atributos, int linea, int col) {
        super(new Tipo(tipoDato.STRUCT), linea, col);
        this.id = id;
        this.atributos = atributos;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        return this.atributos;
    }
    
}
