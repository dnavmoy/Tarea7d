/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Dan_n
 */
public class EscribirCatalogoApp {
    
    
    public static void escribirCatalogo (ArrayList<App> lista) throws JAXBException{
        
        CatalogoApp catalogo = new CatalogoApp();
        
        catalogo.setLista(lista);
        catalogo.setDescripcion("listado App para xml");
        
         JAXBContext contexto = JAXBContext.newInstance(CatalogoApp.class);
         
         Marshaller serializador = contexto.createMarshaller();
        
          serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
          
          // serializador.marshal(catalogo, System.out);
         
            serializador.marshal(catalogo, new File("./appsxml/aplicacionesxml.xml"));
           
            
            
    }
    

            
            
            
}


