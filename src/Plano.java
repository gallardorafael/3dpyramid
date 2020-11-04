
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rafael Gallardo García
 */

public class Plano extends JPanel
{
    GeneradorPiramides generador;
    boolean bandera = false;
    int centrox,centroy;
    public Plano()
    {
        this.setSize(686,455);
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0, 0, 686, 455);
        if(bandera == true)
        {
            pintarPiramide(g); 
        }
    }
    public void construirPiramide(int numeroLados)
    {
        generador = new GeneradorPiramides(numeroLados);
        centrox = generador.piramide.Centro.x;
        centroy = generador.piramide.Centro.y;
    }
    public void rotarPiramide()
    {
        String opc = JOptionPane.showInputDialog(this, "1)Rotar en eje X.\n2)Rotar en eje Y.\n3)Rotar en eje Z.");
        float teta = Float.parseFloat(JOptionPane.showInputDialog("Angulo a rotar:"));
        teta = (float) Math.toRadians(teta);
        switch(opc)
        {
            case "1" : 
                for(Punto3D punto : generador.piramide.puntos)
                {
                    punto.rotarX3D(teta, generador.piramide.Centroide.x, generador.piramide.Centroide.y, generador.piramide.Centroide.z);
                }
                break;
            case "2" : 
                for(Punto3D punto : generador.piramide.puntos)
                {
                    punto.rotarY3D(teta, generador.piramide.Centroide.x, generador.piramide.Centroide.y, generador.piramide.Centroide.z);
                }
                break;
            case "3" : 
                for(Punto3D punto : generador.piramide.puntos)
                {
                    punto.rotarZ3D(teta, generador.piramide.Centroide.x, generador.piramide.Centroide.y, generador.piramide.Centroide.z);
                }
                break;
            default: JOptionPane.showMessageDialog(this, "Intenta de nuevo.","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void trasladarPiramide()
    {
        int moveX = Integer.parseInt(JOptionPane.showInputDialog("Traslado en X:"));
        int moveY = Integer.parseInt(JOptionPane.showInputDialog("Traslado en Y:"));
        int moveZ = Integer.parseInt(JOptionPane.showInputDialog("Traslado en Z:"));
        for(Punto3D punto : generador.piramide.puntos)
        {
            punto.trasladar3D(moveX, moveY, moveZ);
        }
        generador.piramide.Punta.trasladar3D(moveX, moveY, moveZ);
    }
    public void escalarPiramide()
    {
        float escalaX = Float.parseFloat(JOptionPane.showInputDialog("Escala en X:"));
        float escalaY = Float.parseFloat(JOptionPane.showInputDialog("Escala en Y:"));
        float escalaZ = Float.parseFloat(JOptionPane.showInputDialog("Escala en Z:"));
        for(Punto3D punto : generador.piramide.puntos)
        {
            punto.escalar3D(escalaX, escalaY, escalaZ, generador.piramide.Centroide.x, generador.piramide.Centroide.y, generador.piramide.Centroide.z);
        }
        generador.piramide.Punta.escalar3D(escalaX, escalaY, escalaZ, generador.piramide.Centroide.x, generador.piramide.Centroide.y, generador.piramide.Centroide.z);
    }
    public void VistaPerspectivaPiramide(int Xo, int Yo, int Zo)
    {
        generador.PerspectivaPiramide(centrox-Xo, centroy-Yo, Zo);
    }
    public void pintarPiramide(Graphics g)
    {
        int x1,x2,y1,y2;
        int i = 0;
        //Uniendo los puntos de la base.
        g.setColor(Color.white);
        //System.out.println(generador.getCoordenadasPiramide());
        //System.out.println(generador.getCoordenadasProyectadas());
        for (i = 0 ; i < generador.piramide.numeroLados ; i++) 
        {
            x1 = generador.proyecciones.get(i).x; //Primer punto.
            y1 = generador.proyecciones.get(i).y;
            x2 = generador.proyecciones.get(i+1).x; //Punto siguiente.
            y2 = generador.proyecciones.get(i+1).y;
            g.drawLine(centrox+x1, 455-(centroy+y1), centrox+x2 , 455-(centroy+y2)); //Trazamos la linea.
        }
        x1 = generador.proyecciones.get(generador.proyecciones.size()-2).x;
        y1 = generador.proyecciones.get(generador.proyecciones.size()-2).y;
        x2 = generador.proyecciones.get(0).x;
        y2 = generador.proyecciones.get(0).y;
        g.drawLine(centrox+x1, 455-(centroy+y1), centrox+x2 , 455-(centroy+y2)); //Cerramos el ultimo lado.
        //Uniendo los puntos de la base a la punta.
        //g.setColor(Color.cyan);
        for (i = 0 ; i < generador.piramide.numeroLados ; i++) 
        {
            x1 = generador.proyecciones.get(i).x;
            y1 = generador.proyecciones.get(i).y;
            g.drawString(""+(i+1),centrox+x1, 455-(centroy+y1));
            g.drawLine(centrox+x1, 455-(centroy+y1), centrox+generador.proyecciones.get(generador.proyecciones.size()-1).x, 455-(centroy+generador.proyecciones.get(generador.proyecciones.size()-1).y));
        }
        g.drawString("Punta.", centrox+generador.proyecciones.get(generador.proyecciones.size()-1).x, 455-(centroy+generador.proyecciones.get(generador.proyecciones.size()-1).y));
    }
}
