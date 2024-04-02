/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package daw;

import java.util.Random;

/**
 *
 * @author daniel
 */
public class App {


    private int codigo;
    private static int instancia;
    private String nombre;
    private String descripcion;
    private double tam;
    private int descargas;
    private static String[] arrayDescrip={"cosa","lista","esos","tururu","tiriri","66666","77777","8888","99999","10000"};

    public App() {
        Random r = new Random();
        this.codigo=instancia++;
        this.nombre="app"+this.codigo+ (char)r.nextInt(96,123);
        this.descripcion=arrayDescrip[r.nextInt(0,10)];
        this.tam=r.doubles(1, 100, 1025).findFirst().getAsDouble();        
        this.descargas=r.ints(1, 0, 50001).findFirst().getAsInt();
        
    }

    public App(String nombre, String descripcion, double tam, int descargas) {
        this.codigo = instancia++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tam = tam;
        this.descargas = descargas;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTam() {
        return tam;
    }

    public void setTam(double tam) {
        this.tam = tam;
    }
    
    public static int getInstancia() {
        return instancia;
    }

    public static void setInstancia(int aInstancia) {
        instancia = aInstancia;
    }

    public static String[] getArrayDescrip() {
        return arrayDescrip;
    }

    public static void setArrayDescrip(String[] aArrayDescrip) {
        arrayDescrip = aArrayDescrip;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("App{");
        sb.append("codigo=").append(codigo);
        sb.append("; nombre=").append(nombre);
        sb.append("; descripcion=").append(descripcion);
        sb.append("; tam=").append(tam);
        sb.append("; descargas=").append(descargas);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
    
    
    
    
}
