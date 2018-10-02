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
 * @author Flor
 */
public class HabitacionData {
     private Connection connection = null;
         public HabitacionData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion HabitacionData");
        }}
     
    
        public void crearHabitacion(Habitacion habitacion){
        try {
            
            String sql = "INSERT INTO habitacion (id_tipoHabitacion, numero, piso, disponible) VALUES ( ?, ?, ?, ?);";

            PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statment.setInt(1, habitacion.getId_tipoHabitacion());
            statment.setInt(2, habitacion.getNumero());
            statment.setInt(3, habitacion.getPiso());
            statment.setBoolean(4, habitacion.isDisponible());
          
            
            statment.executeUpdate();
            
            ResultSet rs = statment.getGeneratedKeys();
            
            if (rs.next()) {
                habitacion.setId_habitacion(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar una habitacion");
            }
            
            statment.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una habitacion: " + ex.getMessage());
        }
    }


    public List<Habitacion> obtenerHabitaciones(){
        List<Habitacion> habitaciones = new ArrayList<>();
            

        try {
            String sql = "SELECT * FROM habitacion;";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet resultSet = statment.executeQuery();
            Habitacion habitacion;
            while(resultSet.next()){
                habitacion = new Habitacion();
                habitacion.setId_habitacion(resultSet.getInt("id_habitacion"));
                habitacion.setId_tipoHabitacion(resultSet.getInt("id_tipoHabitacion"));
                habitacion.setNumero(resultSet.getInt("numero"));
                habitacion.setPiso(resultSet.getInt("piso"));
                habitacion.setDisponible(resultSet.getBoolean("disponible"));

                habitaciones.add(habitacion);
            }      
            statment.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener las habitaciones: " + ex.getMessage());
        }
        
        
        return habitaciones;
    }
    //----------------------------------------------------------------
     public void borrarHabitacion(int id_habitacion){
    try {
            
            String sql = "DELETE FROM habitacion WHERE id_habitacion =?;";

            PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statment.setInt(1, id_habitacion);
           
            
            statment.executeUpdate();
            
            
            statment.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar una habitacion: " + ex.getMessage());
        }
        
    
    }
     public void actualizarHabitacion(Habitacion habitacion){
    
        try {
            
            String sql = "UPDATE habitacion SET id_tipoHabitacion = ?, numero = ? , piso =?, disponible = ? WHERE id_habitacion = ?;";

            PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statment.setInt(1, habitacion.getId_habitacion());
            statment.setInt(2, habitacion.getNumero());
            statment.setInt(3, habitacion.getPiso());
            statment.setBoolean(4, habitacion.isDisponible());

            statment.executeUpdate();
            
          
            statment.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar un huesped: " + ex.getMessage());
        }
    
    }
     public Habitacion buscarHabitacion(int id_habitacion){
    Habitacion habitacion=null;
    try {
            
            String sql = "SELECT * FROM habitacion WHERE id_habitacion =?;";

            PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statment.setInt(1, id_habitacion);
           
            
            ResultSet resultSet=statment.executeQuery();
            
            while(resultSet.next()){
                habitacion = new Habitacion();
                habitacion.setId_habitacion(resultSet.getInt("id_habitacion"));
                habitacion.setNumero(resultSet.getInt("numero"));
                habitacion.setPiso(resultSet.getInt("piso"));
                habitacion.setDisponible(resultSet.getBoolean("disponible"));
            }      
            statment.close();

        } catch (SQLException ex) {
            System.out.println("Error al buscar ua habitacion: " + ex.getMessage());
        }
        
        return habitacion;
    }
    
}
