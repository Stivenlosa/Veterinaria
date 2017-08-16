/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import Negocio.Ingrediente;
import Negocio.Plato;
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
public class G_platos {
    private String ruta,ruta_ingredientes;
    public G_platos() {
        ruta = "./Archivos/Platos.txt";
        ruta_ingredientes = "./Archivos/Ingredientes.txt";
        this.crear_arc();        
    }
         
    public void C_Plato(Plato pla,ArrayList<Ingrediente> Ing)
    {
        if(!this.Existe(pla.getId()))
        {
                FileWriter es;
                try
                {
                    File archivo = new File(ruta);                    
                    es = new FileWriter(archivo,true);                    
                    es.write(pla.toString());            
                    JOptionPane.showMessageDialog(null,"Operación exitosa","ADVERTENCIA", 1);
                    es.close();
                    this.C_ingredientes(Ing);
                }
                catch(Exception e)                    
                {
                    System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
                }
        }
        else
        {
                JOptionPane.showMessageDialog(null,"Ya existe un plato con este ID","ADVERTENCIA", 1);
        }
    }
    public void C_ingredientes(ArrayList<Ingrediente> Ing)
    {
        try
        {
            FileWriter es;
            File archivo_in = new File(ruta_ingredientes);
            es = new FileWriter(archivo_in,true);
            for(Ingrediente xxx:Ing)
            {
                es.write(xxx.toString());
            }
            es.close();
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }
    }
    public Plato R_plato(String id)
    {
        String[] datos;
        boolean encontrado = false; 
        String studiant;
        Plato pla=null;
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
                    pla = new Plato(datos[0],datos[1],Integer.parseInt(datos[2]),Integer.parseInt(datos[3]),datos[4]);
                    encontrado = true;
                    break;
               }
            }
            le.close();
            cont.close();
            if(!encontrado)
                JOptionPane.showMessageDialog(null,"El plato consultado no existe","ADVERTENCIA", 1);
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }
        return pla;        
    }
    public ArrayList<Ingrediente> R_plato_in(String ID)
    {
        FileReader le;
        BufferedReader cont;
        ArrayList<Ingrediente> In =new ArrayList<>();
        String studiant;
        String[] datos = null;
        Ingrediente In1 = null;
        try
        {            
            le = new FileReader(this.ruta_ingredientes);
            cont = new BufferedReader(le);
            while((studiant = cont.readLine()) != null)
            {               
               datos = studiant.split(",");
               In1 = new Ingrediente(datos[0],datos[1],Integer.parseInt(datos[2]),datos[3]);
               System.out.print(In1);
               if(In1.getID_plato().equals(ID))
               In.add(In1);               
            }
            le.close();
            cont.close();
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }  
        return In;
    }
    public ArrayList<Ingrediente> R_plato_in()
    {
        FileReader le;
        BufferedReader cont;
        ArrayList<Ingrediente> In = null;
        String studiant;
        String[] datos = null;
        Ingrediente In1 = null;
        try
        {            
            le = new FileReader(this.ruta_ingredientes);
            cont = new BufferedReader(le);
            while((studiant = cont.readLine()) != null)
            {               
               In1 = new Ingrediente(datos[0],datos[1],Integer.parseInt(datos[2]),datos[3]);               
               In.add(In1);               
            }
            le.close();
            cont.close();
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }  
        return In;
    }
    public ArrayList<Plato> R_plato()
    {
        FileReader le;
        BufferedReader cont;
        ArrayList<Plato> pla= new ArrayList<>();
        String studiant;
        String[] datos = null;
        Plato pla1 = null;
        try
        {            
            le = new FileReader(ruta);
            cont = new BufferedReader(le);
            while((studiant = cont.readLine()) != null)
            {
               datos=studiant.split(",");
               pla1 = new Plato(datos[0],datos[1],Integer.parseInt(datos[2]),Integer.parseInt(datos[3]),datos[4]);
               pla.add(pla1);               
            }
            le.close();
            cont.close();
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      \n" + e);
        }  
        return pla;
    }
    public void U_plato(Plato pla)
    {           
        try
        {
            ArrayList<Plato> lista = this.R_plato();
            System.out.print(pla);
             System.out.print(lista);
            boolean encontrado = false;                       
            for(Plato xxx:lista)            
            {
                if(xxx.getId().equals(pla.getId()))
                {                    
                    xxx.setNombre(pla.getNombre());
                    xxx.setCosto_preparacion((int)pla.getCosto_preparacion());
                    xxx.setCosto_venta((int)pla.getCosto_venta());
                    xxx.setFoto(pla.getFoto());                    
                    JOptionPane.showMessageDialog(null,"Operacion exitosa");
                    encontrado = true;
                    break;
                }            
            }
            this.LLenar_archivo(lista);
            if(!encontrado)            
                JOptionPane.showMessageDialog(null,"El plato no existe","ADVERTENCIA", 1);
        }
        catch(Exception e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<" + e);
        }
    }
    public void D_plato_in(Ingrediente in)
    {
        try
        {
            ArrayList<Ingrediente> lista = this.R_plato_in();                      
            for(Ingrediente xxx:lista)            
            {
                if(in.equals(xxx))
                lista.remove(in);
            }
            this.LLenar_archivo1(lista);                            
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
    private void LLenar_archivo(ArrayList<Plato> listado)
    {
        try
        {
            File archivo = new File(ruta);
            FileWriter es = new FileWriter(archivo,false);
            for(Plato xxx:listado)            
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
    private void LLenar_archivo1(ArrayList<Ingrediente> listado)
    {
        try
        {
            File archivo = new File(this.ruta_ingredientes);
            FileWriter es = new FileWriter(archivo,false);
            for(Ingrediente xxx:listado)            
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
           File archivo1 = new File(this.ruta_ingredientes);
           if(!archivo.exists())
               archivo.createNewFile();
           if(!archivo1.exists())
               archivo1.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println("Error <<<<<<<<<<<<<<<<<<<<<<<" + e);
        }
                
    }
    
}
