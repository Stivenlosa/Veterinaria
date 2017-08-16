/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import Negocio.Proveedor;
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
public class G_proveedor {
    private Proveedor proveedor;
    private String ruta;
    public G_proveedor() {
        ruta = "./Archivos/Proveedores.txt";
        this.crear_arc();
    }

    public G_proveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }        
    public void C_proveedor(Proveedor pro)
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
                JOptionPane.showMessageDialog(null,"Ya existe un proveedor con este ID","ADVERTENCIA", 1);
        }
    }
    public Proveedor R_proveedor(String id)
    {
        String[] datos;
        boolean encontrado = false; 
        String studiant;
        Proveedor pro=null;
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
                    pro = new Proveedor(datos[0],datos[1],datos[2],datos[3]);
                    encontrado = true;
                    break;
               }
            }
            le.close();
            cont.close();
            if(!encontrado)
                JOptionPane.showMessageDialog(null,"El proveedor no existe","ADVERTENCIA", 1);
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }
        return pro;
    }
    public ArrayList<Proveedor> R_proveedor()
    {
        FileReader le;
        BufferedReader cont;
        ArrayList<Proveedor> pro = null;
        String studiant;
        String[] datos = null;
        Proveedor pro1 = null;
        try
        {            
            le = new FileReader(ruta);
            cont = new BufferedReader(le);
            while((studiant = cont.readLine()) != null)
            {
               pro1 = new Proveedor(datos[0],datos[1],datos[2],datos[3]);
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
    public void U_proveedor(Proveedor pro)
    {           
        try
        {
            ArrayList<Proveedor> lista = this.R_proveedor();
            boolean encontrado = false;                       
            for(Proveedor xxx:lista)            
            {
                if(xxx.getID().equals(pro.getID()))
                {                    
                    xxx.setNombre(pro.getNombre());
                    xxx.setDireccion(pro.getDireccion());
                    xxx.setTelefono(pro.getTelefono());
                    JOptionPane.showMessageDialog(null,"Operacion exitosa");
                    encontrado = true;
                    break;
                }            
            }
            this.LLenar_archivo(lista);
            if(!encontrado)            
                JOptionPane.showMessageDialog(null,"El proveedor no existe","ADVERTENCIA", 1);
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
    private void LLenar_archivo(ArrayList<Proveedor> listado)
    {
        try
        {
            File archivo = new File(ruta);
            FileWriter es = new FileWriter(archivo,false);
            for(Proveedor xxx:listado)            
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
