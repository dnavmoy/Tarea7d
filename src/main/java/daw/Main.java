/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package daw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 *
 * @author daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException, IOException {

        
        //Crea 50 aplicaciones usando el constructor por defecto, guárdalas en una lista y muéstralas por pantalla. 
        //bucle for para crear 50 aplicaciones por defecto segun el contrusctor
        ArrayList<App> listaApp = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            listaApp.add(new App());
        }
        //mostrar datos de aplicaciones 
        listaApp.forEach(k -> System.out.println(k));
        
                        
        //Guarda los datos de todas las App de la lista, en un fichero de texto 
        //llamado aplicacionestxt.csv, dentro del directorio “./appscsv”.
        crearDirectorio("appscsv");
        escribirLista(listaApp, "./appscsv/aplicacionestxt.csv");
        
        //Crea un directorio, "./appscsv2", donde se guarden en archivos individuales CSV,
        //los datos de cada una de las aplicaciones. En este directorio deben generarse 50 
        //ficheros con el nombre que tenga la aplicación en su atributo.
        
        crearDirectorio("appscsv2");
        
        //recorro la lista de apps mediante for y voy creando ficheros con los datos de cada una
        for (int i = 0; i < listaApp.size(); i++) {
            String nombreFichero;
            nombreFichero = "./appscsv2/" + listaApp.get(i).getNombre() + ".csv";
            escribirApp(listaApp.get(i), nombreFichero);

        }
        //Guarda los datos de todas las App de la lista, en un fichero XML llamado
        //aplicacionesxml.xml, dentro del directorio “./appsxml”. Ayúdate del ejemplo
        //del repositorio de clase. Incluye las dependencias necesarias en el pom.xml
        
        crearDirectorio("appsxml");
        EscribirCatalogoApp.escribirCatalogo(listaApp);

        //Guarda los datos de todas las App de la lista, en un fichero JSON llamado 
        //aplicacionesxml.json, dentro del directorio “./appsjson”. Ayúdate del ejemplo 
        //del repositorio de clase. Incluye las dependencias necesarias en el pom.xml.
        crearDirectorio("appsjson");
        EscribirJson.escribirJson(listaApp);

        
        //Crea una carpeta “./copias” y realiza una copia de los ficheros anteriores dentro de ella. 
        crearDirectorio("copia");

        File carpeta = new File("./appscsv2");
        File[] arrayFile = carpeta.listFiles();
        for (File file : arrayFile) {
            copiarFicheros("./appscsv2/" + file.getName(), "./copia/" + file.getName());
        }
        File carpeta1 = new File("./appscsv");
        File[] arrayFile1 = carpeta1.listFiles();
        for (File file : arrayFile1) {
            copiarFicheros("./appscsv/" + file.getName(), "./copia/" + file.getName());
        }

        
        //En una carpeta “./aplicaciones” crea un archivo de texto por cada aplicación
        //que haya en la lista. El archivo se llamará igual que la app y contendrá los 
        //datos de la aplicación, separando los campos por el carácter (;).
        crearDirectorio("aplicaciones");
        creartxt(listaApp);

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

    public static void escribirLista(ArrayList<App> lista, String fichero) {
        //metodo que crea el fichero segun nombre recibido, va añadiendo al flujo 
        //los datos de las apps de la lista y cuando termina lo añade al fichero
       
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(fichero))) {
            for (int i = 0; i < lista.size(); i++) {
                flujo.write(lista.get(i).toString());
            }

            flujo.newLine();
            flujo.flush();
            System.out.println("Fichero " + fichero + " creado correctamente.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void escribirApp(App app, String fichero) {
        //como metodo anterior, crea un flujo que rellena con los datos de la app y la escribe
       
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(fichero))) {
            flujo.write(app.toString());
            flujo.newLine();
            flujo.flush();
            System.out.println("Fichero " + fichero + " creado correctamente.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
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

    public static void copiarFicheros(String rutaOrigen, String rutaDestino) {
        Path origen = Paths.get(rutaOrigen);
        Path destino = Paths.get(rutaDestino);
        try {
            Files.copy(origen, destino);
        } catch (IOException e) {
            System.out.println("Problema copiando el archivo.");
            System.out.println(e.toString());
        }
    }

    public static void creartxt(ArrayList<App> lista) {

        String tmp = "";
        for (int i = 0; i < lista.size(); i++) {
            try (BufferedWriter flujo = new BufferedWriter(new FileWriter("./aplicaciones/"+lista.get(i).getNombre()+".txt"))) {

                tmp=lista.get(i).getDescripcion()+";"+lista.get(i).getNombre()+";"+ lista.get(i).getCodigo()+";"+ lista.get(i).getDescargas()+";"+ lista.get(i).getTam();
                flujo.write(tmp);

                flujo.newLine();
                flujo.flush();
                System.out.println("Fichero " + lista.get(i).getNombre() + " creado correctamente.");

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
