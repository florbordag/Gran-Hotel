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
            System.out.println("Error al abrir al obtener la conexion");
        }}
     
    
        public void crearHabitacion(Habitacion habitacion){
        try {
            
            String sql = "INSERT INTO habitacion (codigo, tipo, ocupado, precioxnoche, maxCapacidad, cantidadCamas, tipoCamas) VALUES ( ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, habitacion.getCantidad() +1);
            statement.setString(2, habitacion.getTipo());
            statement.setBoolean(3, false);
            statement.setDouble(4, habitacion.getPrecioxnoche());
            statement.setInt(5, habitacion.getMaxCapacidad());
            statement.setInt(3, habitacion.getCantidadCamas());
            statement.setString(3, habitacion.getTipoCamas());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
if (rs.next()) {
                habitacion.setCodigo(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar una habitacion");
            }
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una habitacion: " + ex.getMessage());
        }
    }


    public List<Habitacion> obtenerHabitaciones(){
        List<Habitacion> habitaciones = new ArrayList<Habitacion>();
            

        try {
            String sql = "SELECT * FROM habitacion;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Habitacion habitacion;
            while(resultSet.next()){
                habitacion = new Habitacion();
                habitacion.setCodigo(resultSet.getInt("codigo"));
                habitacion.setTipo(resultSet.getString("tipo"));
                habitacion.setOcupado(resultSet.getBoolean("ocupado"));
                habitacion.setPrecioxnoche(resultSet.getDouble("precioxnoche"));
                habitacion.setMaxCapacidad(resultSet.getInt("maxCapacidad"));
                habitacion.setCantidadCamas(resultSet.getInt("cantidadCamas"));
                habitacion.setTipoCamas(resultSet.getString("tipoCamas"));

                habitaciones.add(habitacion);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las habitaciones: " + ex.getMessage());
        }
        
        
        return habitaciones;
    }
    
}
