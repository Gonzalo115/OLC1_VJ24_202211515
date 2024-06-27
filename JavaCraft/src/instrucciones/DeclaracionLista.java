/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class DeclaracionLista extends Instruccion {
    private String identificador;
    private Tipo tipoD;

    public DeclaracionLista(String identificador, Tipo tipoD, int linea, int col) {
        super(new Tipo(tipoDato.LISTA), linea, col);
        this.identificador = identificador;
        this.tipoD = tipoD;
    }
        
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        LinkedList<Object> list = new LinkedList<>();
    
        simbolo s = new simbolo(this.tipo, this.tipoD, this.identificador, true, list, this.linea, this.col);
        
        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "Variable ya existente", this.linea, this.col);
        }
        
        return null;
    }
    
}
