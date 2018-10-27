/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gran.hotel;
import gran.hotel.Reserva;
import gran.hotel.Huesped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReservaData {

    String sql = "";
    private Connection con = null;
    public Integer totalregistros;
    private int id_reserva = 0;

    public ReservaData(Conexion conexion) {
        try {
            con = conexion.getConexion();
        } catch (SQLException e) {

        }
    }

    //El siguiente método me permite insertar una nueva reserva y retorna el ID asignado a la misma
    public int insertar(Reserva nr) {
        sql = "INSERT INTO reserva (id_huesped, id_habitacion, fecha_entrada, fecha_salida, cantidad_personas, importe, estado) VALUES ( ? , ? , ? , ? , ? , ? , ? )";

        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, nr.getId_huesped());
            ps.setInt(2, nr.getId_habitacion());
            ps.setDate(3, nr.getFecha_entrada());
            ps.setDate(4, nr.getFecha_salida());
            ps.setInt(5, nr.getCantidad_personas());
            ps.setDouble(6, nr.getImporte());
            ps.setInt(7, nr.getEstado());

            ps.executeUpdate();
            
            //A continuacion obtengo el ID asignado a la reserva
            ResultSet rs = ps.getGeneratedKeys();
            
            while(rs.next()){
                id_reserva = rs.getInt(1);
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return id_reserva;
        }
        return id_reserva;
    }
    
    public void insertarIdHuesped(int id_r, int id_h) {
        sql = "UPDATE reserva SET id_huesped = ? WHERE id_reserva = ? ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_h);
            ps.setInt(2, id_r);

            ps.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    
    
    //El siguiente método permite mostrar todas las reservas:
    public DefaultTableModel mostrartodas() {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Id_Huesped", "Id_Habitacion" , "Fecha Entrada", "Fecha Salida", "Cantidad de Personas", "Importe", "Estado"};

        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        
        sql = "SELECT * FROM reserva";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                registro[0] = rs.getString("id_reserva");
                registro[1] = rs.getString("id_huesped");
                registro[2] = rs.getString("id_habitacion");
                registro[3] = rs.getString("fecha_entrada");
                registro[4] = rs.getString("fecha_salida");
                registro[5] = rs.getString("cantidad_personas");
                registro[6] = rs.getString("importe");
                registro[7] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        } 
    }
    
    
    //
    public DefaultTableModel buscar(int id_hpd) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Id_Huesped", "Id_Habitacion" , "Fecha Entrada", "Fecha Salida", "Cantidad de Personas", "Importe", "Estado"};

        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        
        sql = "SELECT * FROM reserva WHERE id_huesped = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_hpd);
            
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                registro[0] = rs.getString("id_reserva");
                registro[1] = rs.getString("id_huesped");
                registro[2] = rs.getString("id_habitacion");
                registro[3] = rs.getString("fecha_entrada");
                registro[4] = rs.getString("fecha_salida");
                registro[5] = rs.getString("cantidad_personas");
                registro[6] = rs.getString("importe");
                registro[7] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        } 
    }
    
    //El siguiente método permite buscar una reserva a partir del id de un huesped:
    public Reserva buscarReserva(int id_res) {
        Reserva res = new Reserva(); //Instancio un objeto tipo Huesped para almacenar los datos leidos
        sql = "SELECT * FROM reserva WHERE id_reserva =" + id_res ;

        try {

            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            
            //Si existe este huesped, entonces se ingresara al siguiente WHILE:
            while (rs.next()) {
                
                res.setId_huesped(rs.getInt("id_huesped"));
                res.setId_huesped(rs.getInt("id_habitacion"));
                res.setFecha_entrada(rs.getDate("fecha_entrada"));
                res.setFecha_salida(rs.getDate("fecha_salida"));
                res.setCantidad_personas(rs.getInt("cantidad_personas"));
                res.setImporte(rs.getDouble("importe"));
                res.setEstado(rs.getInt("estado"));

            }
        } catch (Exception e) {
        }
        return res;
    }
    
    
    

    public boolean finreserva(int id_hd) {
        sql = "UPDATE reserva SET estado = 0 WHERE id_huesped = ? ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_hd);
            int n = ps.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

        public void cancelarReserva(int id_reserva){
               sql = "DELETE FROM reserva WHERE id_reserva =?;";        
    try {
             PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id_reserva);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al cancelar una reserva: " + ex.getMessage());
        }
        
    
    }
}
