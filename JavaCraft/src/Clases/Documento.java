package Clases;

public class Documento {

    String nombre;
    String ruta; 
    String contenido;

    public Documento(String nombre, String ruta, String contenido) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.contenido = contenido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public String getContenido() {
        return contenido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    
    
}
