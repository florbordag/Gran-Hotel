
package gran.hotel;
import gran.hotel.Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HabitacionData {

    String sql = "";
    private Connection con = null;
    public Integer totalregistros;

    public HabitacionData(Conexion conexion) {
        try {
            con = conexion.getConexion();
        } catch (SQLException e) {

        }
    }

    
    //El siguiente método permite buscar retornar el listado de habitaciones disponibles a partir de su tipo, en una tabla (retorna una tabla):
    public DefaultTableModel buscardisponibles(int id_tipo, int cant_noches, double precio_total) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Numero", "Piso", "Noches", "Costo toal"};    //Defino los nombres de las columnas de la tabla

        String[] registro = new String[5];  //Defino rgistro que ira guardando las filas con 3 parametros

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sql = "SELECT * FROM habitacion WHERE id_tipoHabitacion = ? AND disponible = 1 ORDER BY id_habitacion";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_tipo);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                registro[0] = rs.getString("id_habitacion");
                registro[1] = rs.getString("numero");
                registro[2] = rs.getString("piso");
                registro[3] = String.valueOf(cant_noches);
                registro[4] = String.valueOf(precio_total);

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    //El siguiente método permitirá insertar una nueva habitacion:
    public boolean insertar(Habitacion hab) {
        sql = "INSERT INTO habitacion (id_tipoHabitacion, numero, piso, disponible) VALUES ( ? , ? , ? , ? )";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hab.getId_tipoHabitacion());
            ps.setInt(2, hab.getNumero());
            ps.setInt(3, hab.getPiso());
            ps.setInt(4, hab.getDisponible());

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

    //El siguiente método permitirá editar los atributos de una habitacion:
    public boolean editar(Habitacion hab) {
        sql = "UPDATE habitacion SET id_tipoHabitacion = ?, numero = ?, piso = ?, disponible = ? WHERE id_habitacion = ? ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hab.getId_tipoHabitacion());
            ps.setInt(2, hab.getNumero());
            ps.setInt(3, hab.getPiso());
            ps.setInt(4, hab.getDisponible());

            ps.setInt(5, hab.getId_habitacion());

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

    //El siguiente método permitirá cambiar el estado de una habitacion a Ocupada (disponible = 0):
    public boolean ocupar(Habitacion hab) {
        sql = "UPDATE habitacion SET disponible = 0 WHERE id_habitacion = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hab.getId_habitacion());

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

    //El siguiente método permitirá cambiar el estado de una habitacion a Desocupada (disponible = 1):
    public boolean desocupar(Habitacion hab) {
        sql = "UPDATE habitacion SET disponible = 1 WHERE id_habitacion = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hab.getId_habitacion());

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

    //El siguiente método permitirá eliminar una habitacion:
    public boolean eliminar(Habitacion hab) {
        sql = "DELETE FROM habitacion WHERE id_habitacion = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hab.getId_habitacion());

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

}
