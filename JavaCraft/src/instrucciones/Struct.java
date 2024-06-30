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
public class Struct extends Instruccion {

    public String id;
    public HashMap<String, Atributos> atributo;

    public Struct(String id, HashMap<String, Atributos> atributo, int linea, int col) {
        super(new Tipo(tipoDato.STRUCT), linea, col);
        this.id = id;
        this.atributo = atributo;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        return null;
    }

    public boolean setStruct(Atributos Atributos) {
        Atributos busqueda = (Atributos) this.atributo.get(Atributos.id.toLowerCase());
        if (busqueda == null) {
            this.atributo.put(Atributos.id.toLowerCase(), Atributos);
            return true;
        }
        return false;
    }

    public Atributos getStruct(String id) {
        Atributos busqueda = (Atributos) this.atributo.get(id.toLowerCase());
        if (busqueda != null) {
            return busqueda;
        }
        return null;
    }

    public HashMap<String, Atributos> getAtributo() {
        return atributo;
    }

    public void setAtributo(HashMap<String, Atributos> atributo) {
        this.atributo = atributo;
    }
    
    
    
}
