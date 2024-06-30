/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class Atributos extends Instruccion {
    public String id;
    public String id_tipo;
    public Object valor;

    public Atributos(String id, Object valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.id = id;
        this.valor = valor;
    }

    public Atributos(String id, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.id = id;
    }

    public Atributos(String id, String id_tipo, int linea, int col) {
        super(new Tipo(tipoDato.STRUCT), linea, col);
        this.id = id;
        this.id_tipo = id_tipo;
    }
    
    
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        return null;
    }    
}
