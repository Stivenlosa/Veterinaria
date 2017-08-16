/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import Negocio.Producto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JORDAN
 */
public class G_producto {
    private Producto producto;
    private String ruta;
    public G_producto() {
        ruta = "./Archivos/Productos.txt";
        this.crear_arc();
    }

    public G_producto(Producto producto) {
        this.producto = producto;
    }        
    public void C_producto(Producto pro)
    {
        if(!this.Existe(pro.getID()))
        {
                FileWriter es;
                try
                {
                    File archivo = new File(ruta);
                    es = new FileWriter(archivo,true);
                    es.write(pro.toString());            
                    JOptionPane.showMessageDialog(null,"Operación exitosa","ADVERTENCIA", 1);
                    es.close();
                }
                catch(Exception e)
                {
                    System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
                }
        }
        else
        {
                JOptionPane.showMessageDialog(null,"Ya existe un producto con este ID","ADVERTENCIA", 1);
        }
    }
    public Producto R_producto(String id)
    {
        String[] datos;
        boolean encontrado = false; 
        String studiant;
        Producto pro=null;
        FileReader le;
        BufferedReader cont;
        try
        {            
            le = new FileReader(ruta);
            cont = new BufferedReader(le);
            while((studiant = cont.readLine()) != null)
            {
               datos = studiant.split(",");
               if(datos[0].equals(id))
               {
                    pro = new Producto(datos[0],datos[1],Integer.parseInt(datos[2]),Integer.parseInt(datos[3]));
                    encontrado = true;
                    break;
               }
            }
            le.close();
            cont.close();
            if(!encontrado)
                JOptionPane.showMessageDialog(null,"El producto no existe","ADVERTENCIA", 1);
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }
        return pro;
    }
    public ArrayList<Producto> R_producto()
    {
        FileReader le;
        BufferedReader cont;
        ArrayList<Producto> pro = new ArrayList<>();
        String studiant;
        String[] datos = null;
        Producto pro1 = null;
        try
        {            
            le = new FileReader(ruta);
            cont = new BufferedReader(le);
            while((studiant = cont.readLine()) != null)
            {
               datos = studiant.split(",");
               pro1 = new Producto(datos[0],datos[1],Integer.parseInt(datos[2]),Integer.parseInt(datos[3]));
               pro.add(pro1);
               System.out.println(studiant);
            }
            le.close();
            cont.close();
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }  
        return pro;
    }
    public void U_producto(Producto pro)
    {           
        try
        {
            ArrayList<Producto> lista = this.R_producto();
            boolean encontrado = false;                        
            for(Producto xxx:lista)            
            {                
                if(xxx.getID().equals(pro.getID()))
                {                    
                    xxx.setNombre(pro.getNombre());
                    xxx.setPCompra(pro.getPCompra());
                    xxx.setPVenta(pro.getPVenta());
                    JOptionPane.showMessageDialog(null,"Operacion exitosa");
                    encontrado = true;
                    break;
                }            
            }
            this.LLenar_archivo(lista);
            if(!encontrado)            
                JOptionPane.showMessageDialog(null,"El producto no existe","ADVERTENCIA", 1);
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<" + e);
        }
    }
    private boolean Existe(String id)
    {
        String[] datos;
        boolean encontrado = false; 
        String studiant;
        FileReader le;
        BufferedReader cont;
        try
        {            
            le = new FileReader(ruta);
            cont = new BufferedReader(le);
            while((studiant = cont.readLine()) != null)
            {
               datos = studiant.split(",");
               if(datos[0].equals(id))
               {                    
                    encontrado = true;
                    break;
               }
            }
            le.close();
            cont.close();            
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }
        return encontrado;
    }
    private void LLenar_archivo(ArrayList<Producto> listado)
    {
        try
        {
            File archivo = new File(ruta);
            FileWriter es = new FileWriter(archivo,false);
            for(Producto xxx:listado)            
            {
                es.write(xxx.toString());                                               
            }
            es.close();
        }
        catch(IOException e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<" + e);
        }
    }
    private void crear_arc()
    {
        try
        {
           File archivo = new File(this.ruta);
           if(!archivo.exists())
               archivo.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<" + e);
        }
                
    }
    
}
