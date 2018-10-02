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
public class Habitacion {
   private int id_habitacion = -1;
   private int id_tipoHabitacion;
   private int numero;
   private int piso;
   private boolean disponible;

    public Habitacion(int id_habitacion,int id_tipoHabitacion, int numero, int piso, boolean disponible) {
        this.id_habitacion= id_habitacion;
        this.id_tipoHabitacion = id_tipoHabitacion;
        this.numero = numero;
        this.piso = piso;
        this.disponible = disponible;
    }

    public Habitacion(int id_tipoHabitacion, int numero, int piso, boolean disponible) {
        this.id_habitacion =-1;
        this.id_tipoHabitacion = id_tipoHabitacion;
        this.numero = numero;
        this.piso = piso;
        this.disponible=disponible;
    }

    public Habitacion(int id_tipoHabitacion, int numero, int piso) {
        this.id_habitacion= -1;
        this.id_tipoHabitacion = id_tipoHabitacion;
        this.numero = numero;
        this.piso = piso;
        this.disponible = true;
    }

    public Habitacion() {
        this.id_habitacion= -1;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getId_tipoHabitacion() {
        return id_tipoHabitacion;
    }

    public void setId_tipoHabitacion(int id_tipoHabitacion) {
        this.id_tipoHabitacion = id_tipoHabitacion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }}