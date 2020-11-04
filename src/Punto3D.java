/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rafael Gallardo García
 */

public class Punto3D 
{
    int x;
    int y;
    int z;
    
    public Punto3D(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public int rotarX2D(int teta, int xi, int yi) //Rotacion del punto x en 2D.
    {
        return (int) Math.round((xi * Math.cos(teta)) - (yi * Math.sin(teta)));
    }
    public int rotarY2D(int teta, int xi, int yi) //Rotación del punto y en 2D.
    {
        return (int) Math.round((xi * Math.sin(teta)) + (yi * Math.cos(teta)));
    }
    public int rotarZ2D(int teta) //Rotación del punto z en 2D.
    {
        return (int) Math.round((this.x * Math.sin(teta)) + (this.y * Math.cos(teta)));
    }
    public void rotarX3D(float teta, int centroideX, int centroideY, int centroideZ) //Rotando el punto sobre el eje x.
    {
       this.y -= centroideY;
       this.z -= centroideZ;
       int yaux = this.y;
       this.y = (int) Math.round((this.y * Math.cos(teta)) - (this.z * Math.sin(teta))); //Rotando
       this.z = (int) Math.round((yaux * Math.sin(teta)) + (this.z * Math.cos(teta)));
       this.y += centroideY;
       this.z += centroideZ;
    }
    public void rotarY3D(float teta, int centroideX, int centroideY, int centroideZ) //Rotando el punto sobre el eje y.
    {
        this.x -= centroideX; //Referenciando al centroide.
        this.z -= centroideZ; 
        int xaux = this.x;
        this.x = (int) Math.round((this.x * Math.cos(teta)) - (this.z * Math.sin(teta))); //Rotando 
        this.z = (int) Math.round((xaux * Math.sin(teta)) + (this.z * Math.cos(teta)));
        this.x += centroideX; //Referenciando al centroide.
        this.z += centroideZ;
    }
    public void rotarZ3D(float teta, int centroideX, int centroideY, int centroideZ) //Rotando el punto sobre el eje z.
    {
        this.x -= centroideX; //Referenciando al centroide.
        this.y -= centroideY;
        int xaux = this.x;
        this.x = (int) Math.round((this.x * Math.cos(teta)) - (this.y * Math.sin(teta)));
        this.y = (int) Math.round((xaux * Math.sin(teta)) + (this.y * Math.cos(teta)));
        this.x += centroideX; //Referenciando al centroide.
        this.y += centroideY;
    }
    public void trasladar3D(int moveX, int moveY, int moveZ)
    {
        this.x += moveX;
        this.y += moveY;
        this.z += moveZ;
    }
    public void escalar3D(float tamX, float tamY, float tamZ, int centroideX, int centroideY, int centroideZ)
    {
        this.x -= centroideX; //Referenciando al centroide.
        this.y -= centroideY;
        this.z -= centroideZ; 
        this.x = Math.round(this.x * tamX); //Escalando
        this.y = Math.round(this.y * tamY);
        this.z = Math.round(this.z * tamZ);
        this.x += centroideX; //Referenciando al centroide.
        this.y += centroideY;
        this.z += centroideZ; 
    }
    public Punto3D vistaPerspectiva(int Xo, int Yo, int Zo)
    {
        int xp,yp;
        //System.out.println("Xp=((("+Zo+"/("+this.z+"+"+Zo+"))*"+this.x+") + ((1-("+Zo+"/("+this.z+"+"+Zo+"))*"+Xo+")))");
        xp = (int) Math.round(((Zo/(double)(this.z+Zo))*this.x) + ((1-(Zo/(double)(this.z+Zo)))*Xo));
        //System.out.println("Yp=((("+Zo+"/("+this.z+"+"+Zo+"))*"+this.y+") + ((1-("+Zo+"/("+this.z+"+"+Zo+"))*"+Yo+")))");
        yp = (int) Math.round(((Zo/(double)(this.z+Zo))*this.y) + ((1-(Zo/(double)(this.z+Zo)))*Yo));
        //System.out.println("Vista perspectiva calculó: Xp:"+xp+" Yp:"+yp);
        return new Punto3D(xp, yp, 0);
    }
    public String getCoordenadas()
    {
        return "("+x+","+y+","+z+")\n";
    }
}
