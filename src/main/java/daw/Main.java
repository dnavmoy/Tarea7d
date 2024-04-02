/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package daw;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<App> listaApp = new ArrayList<>();
        for(int i =0;i<50;i++){
            listaApp.add(new App());
        }
        listaApp.forEach(k-> System.out.println(k));
        crearDirectorio("appscsv");
        
        
        
        
        
    }
    
    public static void crearDirectorio(String ruta){
        
        Path directorio = Paths.get(ruta);
        
        try {
            Files.createDirectories(directorio);
        } catch(FileAlreadyExistsException faee) {
            System.out.println("No se puede crear " + ruta + " porque ya existe");
        } catch (AccessDeniedException ade) {
            System.out.println("No tiene permisos para crear " + ruta);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio " + ruta);
            System.out.println("Seguramente la ruta está mal escrita o no existe");
        }
    }
    
    public static void escribirTexto(ArrayList<App> lista){
           try {
            Files.write(Paths.get("escribirLineas.csv"), , StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.out.println("Error creando el fichero");
        }
            
        }
        
    }
    
}
