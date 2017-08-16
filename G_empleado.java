/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import Negocio.Empleado;
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
public class G_empleado {
    
    private String ruta;
    public G_empleado() {
        ruta = "./Archivos/Empleado.txt";
        this.crear_arc();
    }
         
    public void C_empleado(Empleado pro)
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
                JOptionPane.showMessageDialog(null,"Ya existe un empleado con este ID","ADVERTENCIA", 1);
        }
    }
    public Empleado R_empleado(String id)
    {
        String[] datos;
        boolean encontrado = false; 
        String studiant;
        Empleado pro=null;
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
                    pro = new Empleado(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5]);
                    encontrado = true;
                    break;
               }
            }
            le.close();
            cont.close();
            if(!encontrado)
                JOptionPane.showMessageDialog(null,"El empleado no existe","ADVERTENCIA", 1);
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }
        return pro;
    }
    public ArrayList<Empleado> R_empleado()
    {
        FileReader le;
        BufferedReader cont;
        ArrayList<Empleado> em = new ArrayList<>();
        String studiant;
        String[] datos;
        Empleado em1 = null;
        try
        {            
            le = new FileReader(ruta);
            cont = new BufferedReader(le);
            while((studiant = cont.readLine()) != null)
            {
               datos = studiant.split(",");
               em1 = new Empleado(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5]);
               em.add(em1);
            }
            le.close();
            cont.close();                         
        }
        catch(Exception e)
        {                                  
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }         
        return em;
    }
    public void U_empleado(Empleado em)
    {           
        try
        {
            ArrayList<Empleado> lista = this.R_empleado();
            System.out.println(lista);
            boolean encontrado = false;                       
            for(Empleado xxx:lista)            
            {
                if(xxx.getID().equals(em.getID()))
                {                      
                    xxx.setNombre(em.getNombre());
                    xxx.setApellidos(em.getApellidos());
                    xxx.setFoto(em.getFoto());
                    xxx.setCargo(em.getCargo());
                    JOptionPane.showMessageDialog(null,"Operacion exitosa");
                    encontrado = true;
                    break;
                }            
            }
            this.LLenar_archivo(lista);
            if(!encontrado)            
                JOptionPane.showMessageDialog(null,"El empleado no existe","ADVERTENCIA", 1);
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
    private void LLenar_archivo(ArrayList<Empleado> listado)
    {
        try
        {
            File archivo = new File(ruta);
            FileWriter es = new FileWriter(archivo,false);
            for(Empleado xxx:listado)            
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
    public boolean login(String usu,String con)
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
               if(datos[0].equals(usu)&& datos[4].equals(con))
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
