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

        ArrayList<App> listaApp = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            listaApp.add(new App());
        }
        listaApp.forEach(k -> System.out.println(k));
        crearDirectorio("appscsv");
        crearDirectorio("appscsv2");

        escribirLista(listaApp, "./appscsv/aplicacionestxt.csv");

        for (int i = 0; i < listaApp.size(); i++) {
            String nombreFichero;
            nombreFichero = "./appscsv2/" + listaApp.get(i).getNombre() + ".csv";
            escribirApp(listaApp.get(i), nombreFichero);

        }
        crearDirectorio("appsxml");
        EscribirCatalogoApp.escribirCatalogo(listaApp);

        crearDirectorio("appsjson");
        EscribirJson.escribirJson(listaApp);

        crearDirectorio("copia");

        File carpeta = new File("./appscsv2");
        File[] arrayFile = carpeta.listFiles();
        for (File file : arrayFile) {
            copiarFicheros("./appscsv2/" + file.getName(), "./copia/" + file.getName());
        }
        File carpeta1 = new File("./appscsv");
        File[] arrayFile1 = carpeta.listFiles();
        for (File file : arrayFile) {
            copiarFicheros("./appscsv/" + file.getName(), "./copia/" + file.getName());
        }

        crearDirectorio("aplicaciones");

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

        String tmp = "";
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

        String tmp = "";
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
            try (BufferedWriter flujo = new BufferedWriter(new FileWriter(lista.get(i).getNombre()))) {

                tmp=lista.get(i).getDescripcion()+";"+lista.get(i).getNombre();
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
