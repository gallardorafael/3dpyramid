import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rafael Gallardo García
 */

public class Piramide3D
{
    ArrayList<Punto3D> puntos;
    int numeroLados = 0;
    Punto3D Centro;
    Punto3D Punta;
    Punto3D Centroide;
    public Piramide3D(int CentroX, int CentroY, int numeroLados) //Al crear la piramide definimos el centro y la punta.
    {
        puntos = new ArrayList<  >();
        this.numeroLados = numeroLados;
        //Definiendo el centro.
        Centro = new Punto3D(CentroX, CentroY, 10);
        //Definiendo la punta.
        Punta = new Punto3D(CentroX, CentroY, 50);
    }
    public void crearBase()
    {
        Punto3D primerPunto = new Punto3D(55, 55, 10); //Creamos el punto a partir del cual generaremos los otros rotando.
        int px, py;
        float ang = 360 / numeroLados;
        ang = (float) Math.toRadians(ang);
        for (int i = 0 ; i < numeroLados; i++) //Agregaremos los n puntos al arraylist de puntos.
        { 
            px = (int) Math.round((primerPunto.x * Math.cos(ang * i)) - (primerPunto.y * Math.sin(ang * i)));
            py = (int) Math.round((primerPunto.x * Math.sin(ang * i)) + (primerPunto.y * Math.cos(ang * i)));
            Punto3D temporal = new Punto3D(px, py, 10); //Creamos un punto temporal.
            puntos.add(temporal); //Agregamos cada punto al arraylist.
        }
    }
    public String mostrarCoordenadas()
    {
        String coordenadas = "";
        for( Punto3D punto : puntos)
            coordenadas += punto.getCoordenadas();
        coordenadas += "Centro: ("+Centro.x+","+Centro.y+","+Centro.z+")";
        coordenadas += "\nPunta: ("+Punta.x+","+Punta.y+","+Punta.z+")";
        coordenadas += "\nCentroide: ("+Centroide.x+","+Centroide.y+","+Centroide.z+")";
        return coordenadas;
    }
    public void calcularCentroide()
    {
        int xmin,xmax,ymin,ymax,zmin,zmax;
        int X[] = new int[numeroLados+1];
        int Y[] = new int[numeroLados+1];
        int Z[] = new int[numeroLados+1];
        for(int i = 0 ; i < numeroLados ; i++) //Añadiendo todos los puntos de la base.
        {
            X[i] = puntos.get(i).x;
            Y[i] = puntos.get(i).y;
            Z[i] = puntos.get(i).z;
        }
        X[numeroLados] = Punta.x; //Añadiendo la punta.
        Y[numeroLados] = Punta.y;
        Z[numeroLados] = Punta.z;
        Arrays.sort(X);
        Arrays.sort(Y);
        Arrays.sort(Z);
        xmin = X[0]; xmax = X[numeroLados];
        int Cx = Math.round((xmin + xmax) / 2);
        ymin = Y[0]; ymax = Y[numeroLados];
        int Yx = Math.round((ymin + ymax) / 2);
        zmin = Z[0]; zmax = Z[numeroLados];
        int Zx = Math.round((zmin + zmax) / 2);
        Centroide = new Punto3D(Cx, Yx, Zx);
    }
    
}
