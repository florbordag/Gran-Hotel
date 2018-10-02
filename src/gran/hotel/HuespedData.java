/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gran.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alejo
 */
public class HuespedData {
    private Connection connection=null;
    

    public HuespedData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion HuespedData");
        }
    }
    
    
    public void registrarHuesped(Huesped huesped){
        try {
            
            String sql = "INSERT INTO huesped (nombre, dni, domicilio, correo, telefono) VALUES ( ? , ? , ? , ? , ? );";

            try (PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statment.setString(1, huesped.getNombre());
                statment.setString(2, huesped.getDni());
                statment.setString(3, huesped.getDomicilio());
                statment.setString(4, huesped.getCorreo());
                statment.setString(5, huesped.getTelefono());
                
                statment.executeUpdate();
                
              ResultSet rs = statment.getGeneratedKeys();
                
                if (rs.next()) {
                    huesped.setId_huesped(rs.getInt(1));
                } else {
                    System.out.println("No se pudo obtener el id luego de insertar un huesped");
                } 
                statment.close();
            }
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un huesped: " + ex.getMessage());
        }
    }
    
    public List<Huesped> obtenerHuespedes(){
        List<Huesped> huespedes = new ArrayList<>();
            

        try {
            String sql = "SELECT * FROM huesped;";
            try (PreparedStatement statment = connection.prepareStatement(sql)) {
                ResultSet resultSet = statment.executeQuery();
                Huesped huesped;
                while(resultSet.next()){
                    huesped = new Huesped();
                    huesped.setId_huesped(resultSet.getInt("id_huesped"));
                    huesped.setNombre(resultSet.getString("nombre"));
                    huesped.setDni(resultSet.getString("dni"));
                    huesped.setDomicilio(resultSet.getString("domicilio"));
                    huesped.setCorreo(resultSet.getString("correo"));
                    huesped.setTelefono(resultSet.getString("telefono"));
                    huespedes.add(huesped);
                }
                statment.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener los huespedes: " + ex.getMessage());
        }
        
        
        return huespedes;
    }
     public void borrarHuesped(int id_huesped){
    try {
            
            String sql = "DELETE FROM huesped WHERE id_huesped =?;";

            PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statment.setInt(1, id_huesped);
           
            
            statment.executeUpdate();
            
            
            statment.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar un huesped: " + ex.getMessage());
        }
        
    
    }
     public void actualizarHuesped(Huesped huesped){
    
        try {
            
            String sql = "UPDATE huesped SET nombre = ?, dni = ? , domicilio =?, correo = ?, telefono = ?  WHERE id_huesped = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, huesped.getNombre());
            statement.setString(2, huesped.getDni());
            statement.setString(3, huesped.getDomicilio());
            statement.setString(4, huesped.getCorreo());
            statement.setString(5, huesped.getTelefono());
            statement.setInt(6, huesped.getId_huesped());
            statement.executeUpdate();
            
          
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar un huesped: " + ex.getMessage());
        }
    
    }
     public Huesped buscarHuesped(int id_huesped){
    Huesped huesped=null;
    try {
            
            String sql = "SELECT * FROM huesped WHERE id_huesped =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id_huesped);
           
            
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                huesped = new Huesped();
                huesped.setId_huesped(resultSet.getInt("id_huesped"));
                huesped.setNombre(resultSet.getString("nombre"));
                huesped.setDni(resultSet.getString("dni"));
                huesped.setDomicilio(resultSet.getString("domicilio"));
                huesped.setCorreo(resultSet.getString("correo"));
                huesped.setTelefono(resultSet.getString("telefono"));

                
            }      
            statement.close();
            
            
            
            
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un huesped: " + ex.getMessage());
        }
        
        return huesped;
    }
}

