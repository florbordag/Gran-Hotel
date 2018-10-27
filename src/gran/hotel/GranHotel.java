/*
PROYECTO GRAN HOTEL, COMISION 1, GRUPO 4 
BORDAGORRY MARIA FLORENCIA,   BUNINO YAMILE NAIR,      BUSTOS DANIEL ALEJANDRO,  BERTERO RODOLFO.
                                  << CLASE CONEXION >>
                                << CLASE HUESPED DATA >>
                            <<< ABM DE LA CLASE HUESPED >>> 
                        (Las demás clases estan en construcción)
 */
package gran.hotel;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




/**
 *
 * @author Flor
 */
public class GranHotel {
    
  public static void main(String[] arg) throws ParseException {
   
       Conexion conexion; 
      /* Reserva reserva = new Reserva("10/10/2018","12/10/2018");
       double days=reserva.dias("10/10/2018","12/10/2018");
       int day=(int) days;
    
       
       System.out.println("Se queda "+day+" dias");
       System.out.println(reserva.getFecha_entrada());*/
       /* String url= "jdbc:mysql://localhost/granhotel";
        String usuario= "root";
        String password= ""; 
        */
                        //LOS SIQUIENTES METODOS SON A MODO DE EJEMPLO
//-----------------------------------------------------------------------------------------------
      /*  
        try {             //<<< CREAR HUESPED >>>
            conexion = new Conexion("jdbc:mysql://localhost/elgranhotel", "root", "");
            HuespedData huespedData = new HuespedData(conexion);
     
              Huesped huesped1 = new Huesped("Florinda Mesa", "30123456", "Barrio La Vecindad", "florindam@gmail.com", "0303456");
              huespedData.registrarHuesped(huesped1);
                    //<< DEVOLVER DATOS SI SE REQUIERE >>
              System.out.println("El huesped a sido creado con éxito =)");
                    } catch (Exception e) {
            System.out.println("Error al instanciar la clase conexion crear huesped: " + e.getMessage());
        } */
     
//--------------------------------------------------------------------------------------------
    
       /*  try { //             << CONSULTAR COLUMNAS >>
            conexion = new Conexion("jdbc:mysql://localhost/elgranhotel", "root", "");
            HuespedData huespedData = new HuespedData(conexion);
          
            
            huespedData.obtenerHuespedes().forEach(huesped -> {
                System.out.println("Huespes nro: "+ huesped.getId_huesped() +" Nombre: " + huesped.getNombre() + " Dni: " + huesped.getDni() + " Correo: "+ huesped.getCorreo() + " Telefono: "+ huesped.getTelefono());
            });
    
                    } catch (Exception e) {
            System.out.println("Error al instanciar la clase conexion obtener huesped: " + e.getMessage());
        } 
         */
//---------------------------------------------------------------------------------------------------
   
   /*   try {             //<<< BORRAR HUESPED por ID >>>
            conexion = new Conexion("jdbc:mysql://localhost/elgranhotel", "root", "");
            HuespedData huespedData = new HuespedData(conexion);

      huespedData.borrarHuesped(1);
                    } catch (Exception e) {
            System.out.println("Error al instanciar la clase conexion crear huesped: " + e.getMessage());
        } 
   */
//-----------------------------------------------------------------------------------------------------
    /*       
                 try {             //<<< MODIFICAR HUESPED por ID >>>
            conexion = new Conexion("jdbc:mysql://localhost/elgranhotel", "root", "");
            HuespedData huespedData = new HuespedData(conexion);
            //Modificamos el huesped por el id, cambiando el resto de los datos

            Huesped huesped = new Huesped(1,"Ramona Carranza", "dniAmodificar", "barrioModificado", "correonuevo", "Telefononuevo");
            huespedData.actualizarHuesped(huesped);
                    } catch (Exception e) {
            System.out.println("Error al instanciar la clase conexion crear huesped: " + e.getMessage());
        } 
*/
  }}