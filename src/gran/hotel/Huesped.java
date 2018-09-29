/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gran.hotel;

import java.time.LocalDate;

/**
 *
 * @author Flor
 */
public class Huesped {
    //ATRIBUTOS
    private String  dni;
    private String nombre;
    private String domicilio;
    private String correo;
    private String celular;
    
  /*  //METODOS
        public void CrearReserva(LocalDate inicio, LocalDate salida, int personas){
                Conexion conexion;
 
        try {
            conexion = new Conexion("jdbc:mysql://localhost/pruebaflor", "root", "");
            HabitacionData habitacionData = new HabitacionData(conexion);
            
            
       Alumno alumno1 = new Alumno("Ramon", LocalDate.of(2003, 2, 15));
        alumnoData.guardarAlumno(alumno1);
       System.out.println("El id del alumno es: " + alumno1.getId());

            //TENDRIA Q USAR UN HASMAP PARA PODER USAR ALLMATCH
            habitacionData.obtenerHabitaciones().forEach(habitacion -> {
                System.out.println(" "+habitacion.getMaxCapacidad());
            });
            
            
        } catch (Exception e) {
            System.out.println("Error al instanciar la clase conexion: " + e.getMessage());
        }}

*/
//CONSTRUCTOR
    public Huesped(String dni, String nombre, String domicilio, String correo, String celular) {
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.correo = correo;
        this.celular = celular;
    }

    public Huesped() {
    }
    
    
    
//GETTERS Y SETTERS
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    
}
