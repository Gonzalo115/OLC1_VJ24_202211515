/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author 5gonz
 */
public class Start extends Instruccion {

    private String id;
    private LinkedList<Instruccion> parametros;

    public Start(String id, LinkedList<Instruccion> parametros, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var busqueda = arbol.getFuncion(id);
        if (busqueda == null) {
            return new Errores("SEMANTICO", "Funcion "+ id +" no existente", this.linea, this.col);
        }

        if (busqueda instanceof Metodo metodo) {
            var newTabla = new tablaSimbolos(arbol.getTablaGlobal(), "Global_metodo_"+id);
            newTabla.setNombre("EXECUTE");

            if (metodo.parametros.size() != this.parametros.size()) {
                return new Errores("SEMANTICO", "El numero de Parametros de la llamada y el metodo no coincide",
                        this.linea, this.col);
            }

            for (int i = 0; i < this.parametros.size(); i++) {
                var identificador = (String) metodo.parametros.get(i).get("id");
                var valor = this.parametros.get(i);
                var tipo2 = (Tipo) metodo.parametros.get(i).get("tipo");

                var declaracionParametro = new Declaracion(true, identificador, tipo2, valor, this.linea, this.col);

                var resultadoDeclaracion = declaracionParametro.interpretar(arbol, newTabla);

                if (resultadoDeclaracion instanceof Errores) {
                    return resultadoDeclaracion;
                }
            }

            var resultadoFuncion = metodo.interpretar(arbol, newTabla);
            if (resultadoFuncion instanceof Errores) {
                return resultadoFuncion;
            }
        }
        return null;
    }
}
