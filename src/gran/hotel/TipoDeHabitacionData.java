/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gran.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class TipoDeHabitacionData {
    String sql = "";
    private Connection con = null;
    public Integer totalregistros;
    
    public TipoDeHabitacionData(Conexion conexion){
        try {
            con = conexion.getConexion();
        } catch (Exception e) {
            System.out.println("Error al obtener la conexion para Tipos de habitacion");
        }
    }
    
    
    //El siguiente método permite buscar retornar el listado de habitaciones disponibles a partir de su tipo, en una tabla (retorna una tabla):
    public DefaultTableModel buscardisponibles(int cantidad) {

        DefaultTableModel modelo;
        
        String[] titulos = {"ID","Tipo","Capacidad","Cantidad de camas","Tipo de cama", "Precio x noche"};    //Defino los nombres de las columnas de la tabla

        String[] registro = new String[6];  //Defino rgistro que ira guardando las filas con 3 parametros

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sql = "SELECT * FROM tipos_de_habitacion WHERE capacidad >= ? ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                registro[0] = rs.getString("id_tipoHabitacion");
                registro[1] = rs.getString("tipo");
                registro[2] = rs.getString("capacidad");
                registro[3] = rs.getString("cantidad_camas");
                registro[4] = rs.getString("tipo_cama");
                registro[5] = rs.getString("precio_noche");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            System.out.println("error tiposdehabitaciondata metodo buscar disponibles");
            return null;
        }
    }
    
    
    
}
