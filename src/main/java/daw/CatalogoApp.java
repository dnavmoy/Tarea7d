/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dan_n
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogoApp {
    
      @XmlElementWrapper(name = "catalogo")
      
    // XmlElement establece el nombre de los elementos
    // Cada elemento de la lista llevará esta etiqueta en el fichero xml
    @XmlElement(name = "App")
    private ArrayList<App> listaApp;
    private String descripcion;
    
    public ArrayList<App> getListaApp() {
        return listaApp;
    }

    public void setLista(ArrayList<App> lista) {
        this.listaApp = lista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
