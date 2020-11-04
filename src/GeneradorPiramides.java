
import java.awt.Graphics;
import java.util.ArrayList;
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

public class GeneradorPiramides 
{
    Piramide3D piramide; //Piramide original en 3D
    ArrayList<Punto3D> proyecciones; //Piramide proyectada en 2D
    public GeneradorPiramides(int numeroLados) //El generador construirá la piramide.
    {
        //Definiremos los puntos estaticos de la piramide.
        proyecciones = new ArrayList<>();
        piramide = new Piramide3D(0,0,numeroLados); //Mandamos un centro pequeño para generar los puntos.
        piramide.crearBase(); //Obtenemos los puntos para la base.
        piramide.calcularCentroide();
        piramide.Centro.x = 343; //Definimos el nuevo centro de la piramide.
        piramide.Centro.y = 227;
        //En este punto ya tenemos los puntos de la base y la punta.
    }
    public void escalarPiramide(float tamX, float tamY, float tamZ, int CentroideX, int CentroideY, int CentroideZ)
    {
        for(Punto3D punto : piramide.puntos) //Escalamos cada punto de la piramide.
            punto.escalar3D(tamX, tamY, tamZ, CentroideX, CentroideY, CentroideZ);
        piramide.Punta.escalar3D(tamX, tamY, tamZ, CentroideX, CentroideY, CentroideZ); //Escalamos la punta de la piramide.
    }
    public void trasladarPiramide(int moveX, int moveY, int moveZ)
    {
        for(Punto3D punto : piramide.puntos)
            punto.trasladar3D(moveX, moveY, moveZ);
        piramide.Punta.trasladar3D(moveX, moveY, moveZ);
    }
    public void rotar3DXPiramide(float teta, int centroideX, int centroideY, int centroideZ)
    {
        for(Punto3D punto : piramide.puntos)
            punto.rotarX3D(teta, centroideX, centroideY, centroideZ);
        piramide.Punta.rotarX3D(teta, centroideX, centroideY, centroideZ);
    }
    public void rotar3DYPiramide(float teta, int centroideX, int centroideY, int centroideZ)
    {
        for(Punto3D punto : piramide.puntos)
            punto.rotarY3D(teta, centroideX, centroideY, centroideZ);
        piramide.Punta.rotarY3D(teta, centroideX, centroideY, centroideZ);   
    }
    public void rotar3DZPiramide(float teta, int centroideX, int centroideY, int centroideZ)
    {
        for(Punto3D punto : piramide.puntos)
            punto.rotarZ3D(teta, centroideX, centroideY, centroideZ);
        piramide.Punta.rotarZ3D(teta, centroideX, centroideY, centroideZ);   
    }
    public void PerspectivaPiramide(int Xo, int Yo, int Zo)
    {
        proyecciones = new ArrayList<>();
        for(Punto3D punto3D : piramide.puntos)
        {
            proyecciones.add(punto3D.vistaPerspectiva(Xo, Yo, Zo));
        }
        proyecciones.add(piramide.Punta.vistaPerspectiva(Xo, Yo, Zo));
    }
    public String getCoordenadasPiramide()
    {
        String cord = "Puntos 3D:\n";
        for(Punto3D punto : piramide.puntos)
        {
            cord += punto.getCoordenadas();
        }
        cord += "Punta: "+piramide.Punta.getCoordenadas();
        cord += "Centroide: "+piramide.Centroide.getCoordenadas();
        return cord;
    }
    public String getCoordenadasProyectadas()
    {
        String cord = "Puntos proyectados:\n";
        for(Punto3D punto : proyecciones)
            cord += punto.getCoordenadas();
        return cord;
    }
}
