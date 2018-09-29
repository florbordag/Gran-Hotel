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
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    
    public void registrarHuesped(Huesped huesped){
        try {
            
            String sql = "INSERT INTO huesped (dni, nombre, domicilio, correo, celular) VALUES ( ? , ? , ? , ? , ? );";

            try (PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statment.setString(1, huesped.getDni());
                statment.setString(2, huesped.getNombre());
                statment.setString(3, huesped.getDomicilio());
                statment.setString(4, huesped.getCorreo());
                statment.setString(5, huesped.getCelular());
                
                statment.executeUpdate();
                
            /*    ResultSet rs = statment.getGeneratedKeys();
                
                if (rs.next()) {
                    huesped.setDni(rs.getString(1));
                } else {
                    System.out.println("No se pudo obtener el dni luego de insertar un huesped");
                } */
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
                    huesped.setDni(resultSet.getString("dni"));
                    huesped.setNombre(resultSet.getString("nombre"));
                    huesped.setDomicilio(resultSet.getString("domicilio"));
                    huesped.setCorreo(resultSet.getString("correo"));
                    huesped.setCelular(resultSet.getString("celular"));
                    huespedes.add(huesped);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener los alumnos: " + ex.getMessage());
        }
        
        
        return huespedes;
    }
    
}

