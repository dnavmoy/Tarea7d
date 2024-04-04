/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package daw;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException {

        ArrayList<App> listaApp = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            listaApp.add(new App());
        }
        listaApp.forEach(k -> System.out.println(k));
        crearDirectorio("appscsv");
        crearDirectorio("appscsv2");
        

        escribirLista(listaApp,"./appscsv/aplicacionestxt.csv");
        for(int i =0;i<listaApp.size();i++){
            String nombreFichero;
            nombreFichero= "./appscsv2/"+listaApp.get(i).getNombre()+".csv";
            escribirApp(listaApp.get(i), nombreFichero);
            
            
        }
        crearDirectorio("appsxml");
        EscribirCatalogoApp.escribirCatalogo(listaApp);
        
        
    }

    public static void crearDirectorio(String ruta) {

        Path directorio = Paths.get(ruta);

        try {
            Files.createDirectories(directorio);
        } catch (FileAlreadyExistsException faee) {
            System.out.println("No se puede crear " + ruta + " porque ya existe");
        } catch (AccessDeniedException ade) {
            System.out.println("No tiene permisos para crear " + ruta);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio " + ruta);
            System.out.println("Seguramente la ruta está mal escrita o no existe");
        }
    }

    public static void escribirLista(ArrayList<App> lista,String fichero){
        
        
        for(int i=0; i<lista.size();i++){
      try {
      
          Files.write(Paths.get(fichero), 
                    lista.get(i).toString().getBytes(),
                   StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Error creando el fichero");
        }  
        }
    }
    
    public static void escribirApp(App app,String fichero){
        
        
        
      try {
      
          Files.write(Paths.get(fichero), 
                    app.toString().getBytes(),
                   StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Error creando el fichero");
        }  
        
    }
    
    
    public static void escribirlistaappcsv(ArrayList<App> lista) {

        try (ObjectOutputStream flujo = new ObjectOutputStream(new FileOutputStream("./appscsv/aplicacionestxt.csv"))) {

            for (int i = 0; i < lista.size(); i++) {
                flujo.writeObject(lista.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("error e");
            System.out.println(e.getMessage());
        }

    }

}


