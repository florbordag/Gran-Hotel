/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gran.hotel;


/**
 *
 * @author Flor
 */
public class Huesped {
    //ATRIBUTOS
    private int id_huesped= -1;
    private String  dni;
    private String nombre;
    private String domicilio;
    private String correo;
    private String telefono;
    
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
    public Huesped(int id,String nombre, String dni, String domicilio, String correo, String telefono) {
        this.id_huesped = id;
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.correo = correo;
        this.telefono = telefono;
    }
    
     public Huesped() {
        this.id_huesped= -1;
    }

    public Huesped(String nombre, String dni, String domicilio, String correo, String telefono) {
        this.id_huesped=-1;
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.correo = correo;
        this.telefono = telefono;
    }
     
    

    public int getId_huesped() {
        return id_huesped;
    }

    public void setId_huesped(int id_huesped) {
        this.id_huesped = id_huesped;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
}
