/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

import java.util.HashMap;

/**
 *
 * @author 5gonz
 */
public class tablaSimbolos {

    private tablaSimbolos tablaAnterior;
    public HashMap<String, Object> tablaActual;
    public String nombre;

    public tablaSimbolos() {
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public tablaSimbolos(tablaSimbolos tablaAnterior, String nombre) {
        this.tablaAnterior = tablaAnterior;
        this.tablaActual = new HashMap<>();
        this.nombre = nombre;
    }

    public tablaSimbolos getTablaAnterior() {
        return tablaAnterior;
    }

    public void setTablaAnterior(tablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
    }

    public HashMap<String, Object> getTablaActual() {
        return tablaActual;
    }

    public void setTablaActual(HashMap<String, Object> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean setVariable(simbolo simbolo) {
        simbolo busqueda = (simbolo) this.tablaActual.get(simbolo.getId().toLowerCase());
        if (busqueda == null) {
            this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo);
            return true;
        }
        return false;
    }

    public simbolo getVariable(String id) {
        for (tablaSimbolos i = this; i != null; i = i.getTablaAnterior()) {
            simbolo busqueda = (simbolo) i.tablaActual.get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }

    public String tablaHtml() {

        String html = "";

        if(this.tablaActual == null){
            return "";
        }
        
        for (String key : this.tablaActual.keySet()) {
            simbolo value = (simbolo) this.tablaActual.get(key);
            System.out.println("Key: " + key + ", Value: " + value);

            html += "<tr>\n";
            html += "<td>" + value.getId() + "</td>\n";
            if (value.getMutable()) {
                html += "<td>Variable</td>\n";
            }else{
                html += "<td>Constante</td>\n";
            }
            html += "<td>" + value.getTipo().getTipo() + "</td>\n";
            html += "<td>" + this.nombre + "</td>\n";
            html += "<td>" + value.getValor() + "</td>\n";
            html += "<td>" + value.getLinea() + "</td>\n";
            html += "<td>" + value.getColumna() + "</td>\n";
            html += "</tr>\n";
            System.out.println(html);
        }
        return html;
    }

}
