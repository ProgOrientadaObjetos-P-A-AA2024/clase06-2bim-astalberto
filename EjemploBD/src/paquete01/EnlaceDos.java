/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete01;

import java.sql.Statement;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import paquete02.Ciudad;


public class EnlaceDos {
    /** 
     * Connect to a sample database 
     * @return 
    */
    private Connection conn;
    private ArrayList<Ciudad> lista;
       
    public void establecerConexion() {  

        try {  
            // db parameters  
            String url = "jdbc:sqlite:bd/base01.bd";  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
            // System.out.println(conn.isClosed());
            // System.out.println("Connection to SQLite has been established.");  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }   
        
    } 
    
    public Connection obtenerConexion(){
        return conn;
    }
    
    public void insertarCiudad(Ciudad ciudad) {  
  
        try{  
            establecerConexion();
            Statement statement = obtenerConexion().createStatement();
            String data = String.format("INSERT INTO Ciudad (nombre, poblacion) "
                    + "values ('%s', %d)", ciudad.obtenerNombre(), 
                    ciudad.obtenerPoblacion());
            statement.executeUpdate(data);
            obtenerConexion().close();
        } catch (SQLException e) {  
             System.out.println("Exception: insertarCiudad");
             System.out.println(e.getMessage());  
             
        }  
    }
    
    
    public void establecerListaCiudad() {  
        lista = new ArrayList<>();
        try{  
            establecerConexion();
            Statement statement = obtenerConexion().createStatement();
            String data = "Select * from Ciudad;";
            
            ResultSet rs = statement.executeQuery(data);
            while(rs.next()){
                // En esta parte del codigo se colecta todos los datos de la tabla
                // y con rs.next() recorre siempre y cuando exista un valor
                // con rs.getString lo que hacemos es que busque el valor
                // que tiene la seccion de nombre y la retorna y realiza el mismo
                // proceso con el getInt poblacion
                Ciudad miCiudad = new Ciudad(rs.getString("nombre"),
                rs.getInt("poblacion"));
                lista.add(miCiudad);
            }
            
            obtenerConexion().close();
        } catch (SQLException e) {  
             System.out.println("Exception: insertarCiudad");
             System.out.println(e.getMessage());  
             
        }  
        
    }
    
    public ArrayList<Ciudad> obtenerListaCiudad() {  
        return lista;
    }
    
    
     
}  
