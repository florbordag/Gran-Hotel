/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gran.hotel;
import java.sql.PreparedStatement;
import java.text.ParseException;



/**
 *
 * @author Flor
 */
public class GranHotel {
  public static void main(String[] arg) throws ParseException {
       Conexion conexion; 
       
       /* String url= "jdbc:mysql://localhost/granhotel";
        String usuario= "root";
        String password= ""; 
        
       
        PreparedStatement ps; */
       //-----------------------------------------------------------------------------------------
        
     /*   try { //CREAR HUESPED Y DEVOLVER DNI
            conexion = new Conexion("jdbc:mysql://localhost/elgranhotel", "root", "");
            HuespedData huespedData = new HuespedData(conexion);
     
              Huesped huesped1 = new Huesped("Florencia", "35451936", "Barrio 142 viv.", "florbordag@gmail.com", "2664258284");
              huespedData.registrarHuesped(huesped1);
              System.out.println("DNI: " + huesped1.getDni());
                    } catch (Exception e) {
            System.out.println("Error al instanciar la clase conexion: " + e.getMessage());
        } */
     
     //------------------------------------------------------------------------------------
    
      /*   try { //CONSULTAR COLUMNAS
            conexion = new Conexion("jdbc:mysql://localhost/granhotel", "root", "");
            HuespedData huespedData = new HuespedData(conexion);
          
            
            huespedData.obtenerHuespedes().forEach(huesped -> {
                System.out.println("Nombre: " + huesped.getNombre());
            });
    
                    } catch (Exception e) {
            System.out.println("Error al instanciar la clase conexion: " + e.getMessage());
        } */
   //---------------------------------------------------------------------------------------------------
        
         //TEST
            /*Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);*/
            
            //CREATE
           /*
            ps= conexion.prepareStatement("INSERT INTO alumno;");
            
            ps.setString(1, "Rosales Esteban");
            LocalDate fechalocal=LocalDate.of(1995, 10, 15);
            Date fecha= Date.valueOf(fechalocal);
            
            ps.setDate(2, fecha);
            ps.setBoolean(3, true);
            ps.executeUpdate();
            ps.close(); 
                    */
 
            
            //UPDATE
            /*
            ps= conexion.prepareStatement("UPDATE habitacion SET ocupado =1;");
            ps.executeUpdate();
            ps.close();
            */
           
           //DELETE
          /* ps= conexion.prepareStatement("DELETE FROM alumno WHERE id=?;");
           ps.setInt (1, 7);
           ps.executeUpdate();
           ps.close(); */
          
            
      /*  } catch (ClassNotFoundException ex) {
            Logger.getLogger(GranHotel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GranHotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
} */

  }}