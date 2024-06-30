/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import expresiones.AccesoVC;
import java.util.HashMap;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class InstanciarStruct extends Instruccion {

    private String id;
    private String id_Struct;
    private boolean mutable;
        public HashMap<String, Instruccion> atributos;

    public InstanciarStruct(String id, String id_Struct, boolean mutabilidad, HashMap<String, Instruccion> atributos, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.id_Struct = id_Struct;
        this.mutable = mutabilidad;
        this.atributos = atributos;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        simbolo s = null;

        //Obtener y extraer la construcion de un Struct
        Struct var_struct = arbol.getStruct(id_Struct);

        if (var_struct == null) {
            return new Errores("SEMANTICO", "El Struct " + id_Struct + " no Existe. ", this.linea, this.col);
        }

        HashMap<String, Atributos> atributosS = var_struct.atributo;

        if (atributosS.size() != this.atributos.size()) {
            return new Errores("SEMANTICO", "Estas instanciando con menos atributos el Estruct.", this.linea, this.col);

        }

        HashMap<String, Atributos> atributos_nuevos = new HashMap<>();

        for (String key : this.atributos.keySet()) {
            Instruccion value = (Instruccion) this.atributos.get(key);
            Atributos valueS = (Atributos) atributosS.get(key);
            if (valueS == null) {
                return new Errores("SEMANTICO", "El atributo " + key + " no existe en el struct que estas instaciando.", this.linea, this.col);
            }

            if (value instanceof AtributosAux AtributosAux) {
                HashMap<String, AtributosAux> r = (HashMap<String, AtributosAux>) value.interpretar(arbol, tabla); // tabla del parser
                String llave = valueS.id_tipo;
                
                Struct var_struct_struct = arbol.getStruct(llave);
                if (var_struct_struct == null) {
                    return new Errores("SEMANTICO", "El atributo " + llave + " no existe en el struct que estas instaciando.", this.linea, this.col);
                }
                HashMap<String, Atributos> atributosS2 = var_struct_struct.atributo;//tabla base

                if (r.size() != atributosS2.size()) {
                    return new Errores("SEMANTICO", "Estas instanciando con menos atributos el Estruct.", this.linea, this.col);

                }

                for (String key2 : r.keySet()) {
                    Instruccion value2 = (Instruccion) this.atributos.get(key);
                    Atributos valueS2 = (Atributos) atributosS.get(key);
                    if (value2.tipo.getTipo() != valueS2.tipo.getTipo()) {
                        return new Errores("SEMANTICO", "El tipo del atibuto del struct (" + valueS.tipo.getTipo() + ")y el tipo del valor (" + value.tipo.getTipo() + ") que le estas pasando no son iguales", this.linea, this.col);
                    }
                }
                atributos_nuevos.put(key.toLowerCase(), new Atributos(key, r, value.tipo, this.linea, this.col));

            } else if(value instanceof Atributos Atributos){
                var atri_Res = value.interpretar(arbol, tabla);

                if (atri_Res instanceof Errores) {
                    return atri_Res;
                }

                if (valueS.tipo.getTipo() == value.tipo.getTipo()) {
                    atributos_nuevos.put(key.toLowerCase(), new Atributos(((Atributos) value).id, atri_Res, value.tipo, this.linea, this.col));
                } else {
                    return new Errores("SEMANTICO", "El tipo del atibuto del struct (" + valueS.tipo.getTipo() + ")y el tipo del valor (" + value.tipo.getTipo() + ") que le estas pasando no son iguales", this.linea, this.col);
                }
            }

        }
        //Tipo tipo, String id, boolean mutable, Object valor, int linea, int columna
        s = new simbolo(new Tipo(tipoDato.STRUCT), id, mutable, new Struct(id_Struct, atributos_nuevos, this.linea, this.col), this.linea, this.col);

        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "Variable ya existente", this.linea, this.col);
        }

        return null;
    }

}
