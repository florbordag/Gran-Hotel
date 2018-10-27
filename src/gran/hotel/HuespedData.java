
package gran.hotel;

import gran.hotel.Huesped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import javax.swing.JOptionPane;

public class HuespedData {
    private int id_huesped;

    String sql = "";
    private Connection con = null;
    public Integer totalregistros;

    public HuespedData(Conexion conexion) {
        try {
            con = conexion.getConexion();
        } catch (SQLException e) {
        }
    }

    public int registrarHuesped(Huesped huesped) {
        
        sql = "INSERT INTO huesped (nombre, apellido, dni, domicilio, correo, telefono) VALUES ( ? , ? , ? , ? , ? , ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, huesped.getNombre());
            ps.setString(2, huesped.getApellido());
            ps.setString(3, huesped.getDni());
            ps.setString(4, huesped.getDomicilio());
            ps.setString(5, huesped.getCorreo());
            ps.setString(6, huesped.getTelefono());

            ps.executeUpdate();
            //A continuacion obtengo el ID asignado al huesped:
            ResultSet rs = ps.getGeneratedKeys();
            
            while(rs.next()){
                id_huesped = rs.getInt(1);
            }

            JOptionPane.showConfirmDialog(null, "Registro Exitoso!!");

        } catch (Exception e) {
            System.out.println(e);
            return id_huesped;
        }
        return id_huesped;
    }

    public void editarHuesped(Huesped huesped) {
        sql = "UPDATE huesped SET nombre = ? , apellido = ? , dni = ? , domicilio = ? , correo = ? , telefono = ? WHERE id_huesped = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, huesped.getNombre());
            ps.setString(2, huesped.getApellido());
            ps.setString(3, huesped.getDni());
            ps.setString(4, huesped.getDomicilio());
            ps.setString(5, huesped.getCorreo());
            ps.setString(6, huesped.getTelefono());

            ps.setInt(7, huesped.getId_huesped());

            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void eliminarHuesped(Huesped huesped) {
        sql = "DELETE FROM huesped WHERE id_huesped = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, huesped.getId_huesped());

            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public Huesped buscarHuesped(String loginapellido, String logindni) {
        Huesped hues = new Huesped(); //Instancio un objeto tipo Huesped para almacenar los datos leidos
        sql = "SELECT * FROM huesped WHERE apellido = '" + loginapellido + "' AND dni = '" + logindni + "' ";

        try {

            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            
            //Si existe este huesped, entonces se ingresara al siguiente WHILE:
            while (rs.next()) {
                hues.setId_huesped(rs.getInt("id_huesped"));
                hues.setNombre(rs.getString("nombre"));
                hues.setApellido(rs.getString("apellido"));
                hues.setDni(rs.getString("dni"));
                hues.setDomicilio(rs.getString("domicilio"));
                hues.setCorreo(rs.getString("correo"));
                hues.setTelefono(rs.getString("telefono"));

                //System.out.println(hues.getId_huesped() + hues.getApellido() + hues.getNombre() + hues.getDni());
            }
        } catch (Exception e) {
        }
        return hues;
    }
    
    
    public Huesped buscarHuesped(int id_huesped) {
        Huesped hues = new Huesped(); //Instancio un objeto tipo Huesped para almacenar los datos leidos
        sql = "SELECT * FROM huesped WHERE id_huesped =" + id_huesped;

        try {

            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            
            //Si existe este huesped, entonces se ingresara al siguiente WHILE:
            while (rs.next()) {
                hues.setId_huesped(rs.getInt("id_huesped"));
                hues.setNombre(rs.getString("nombre"));
                hues.setApellido(rs.getString("apellido"));
                hues.setDni(rs.getString("dni"));
                hues.setDomicilio(rs.getString("domicilio"));
                hues.setCorreo(rs.getString("correo"));
                hues.setTelefono(rs.getString("telefono"));

                //System.out.println(hues.getId_huesped() + hues.getApellido() + hues.getNombre() + hues.getDni());
            }
        } catch (Exception e) {
        }
        return hues;
    }
    
    
}
