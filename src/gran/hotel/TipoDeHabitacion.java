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
public class TipoDeHabitacion {
    private int id_tipoHabitacion;
    private String tipo;
    private int capacidad;
    private int cantidad_camas;
    private String tipo_cama;
    private double precio_noche;

    public TipoDeHabitacion(int id_tipoHabitacion, String tipo, int capacidad, int cantidad_camas, String tipo_cama, double precio_noche) {
        this.id_tipoHabitacion = id_tipoHabitacion;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.cantidad_camas = cantidad_camas;
        this.tipo_cama = tipo_cama;
        this.precio_noche = precio_noche;
    }

    public TipoDeHabitacion() {
    }
    

    public int getId_tipoHabitacion() {
        return id_tipoHabitacion;
    }

    public void setId_tipoHabitacion(int id_tipoHabitacion) {
        this.id_tipoHabitacion = id_tipoHabitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCantidad_camas() {
        return cantidad_camas;
    }

    public void setCantidad_camas(int cantidad_camas) {
        this.cantidad_camas = cantidad_camas;
    }

    public String getTipo_cama() {
        return tipo_cama;
    }

    public void setTipo_cama(String tipo_cama) {
        this.tipo_cama = tipo_cama;
    }

    public double getPrecio_noche() {
        return precio_noche;
    }

    public void setPrecio_noche(double precio_noche) {
        this.precio_noche = precio_noche;
    }

}
