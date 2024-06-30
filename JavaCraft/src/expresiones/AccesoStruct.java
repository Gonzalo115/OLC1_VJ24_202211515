/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import instrucciones.Atributos;
import java.util.HashMap;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class AccesoStruct extends Instruccion {

    public String id1;
    public String id2;
    public String id3;

    public AccesoStruct(String id1, String id2, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id1 = id1;
        this.id2 = id2;
    }

    public AccesoStruct(String id1, String id2, String id3, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        simbolo simbolo = tabla.getVariable(this.id1);//obtener el objeto simbolo
        if (simbolo == null) {
            return new Errores("SEMANTICA", "Variable no existente",
                    this.linea, this.col);
        }

        var consulta = simbolo.getValor();//obtener el objeto setapadao

        if (consulta instanceof Struct struct) {

            Struct consulta_a = (Struct) consulta;

            HashMap<String, Atributos> atributo = (HashMap<String, Atributos>) consulta_a.getAtributo();//obtner la lista de atributos
            var atributo1 = atributo.get(this.id2);

            if (id3 == null) {
                this.tipo.setTipo(atributo1.tipo.getTipo());
                return atributo1.valor;
            } else {
                HashMap<String, Atributos> consulta2 = (HashMap<String, Atributos>) atributo1.valor;
                //Struct consulta2 = (Struct) atributo1.getValor();
                var atributo2 = consulta2.get(this.id2);
                this.tipo.setTipo(atributo2.tipo.getTipo());
                return atributo2.valor;
            }

        } else {
            return new Errores("SEMANTICA", "Esta trantando de acceder a una variable distinta Struct como uno", this.linea, this.col);
        }

    }

}
