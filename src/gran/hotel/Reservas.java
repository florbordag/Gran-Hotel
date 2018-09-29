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
public class Reservas {
    
    //ATRIBUTOS
    Habitacion habitacion;
    private int dni_huesped;
    private LocalDate checkin;
    private LocalDate checkout;
    private int codigo_habitacion;
    private boolean activa=false;
    private double monto;
    int dias;
    //METODOS
    public double monto(){return dias* habitacion.getPrecioxnoche();}


    //GETTERS Y SETTERS
    public int getDni_huesped() {
        return dni_huesped;
    }

    public void setDni_huesped(int dni_huesped) {
        this.dni_huesped = dni_huesped;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public int getCodigo_habitacion() {
        return codigo_habitacion;
    }

    public void setCodigo_habitacion(int nro_habitacion) {
        this.codigo_habitacion = nro_habitacion;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean estado) {
        this.activa = estado;
    }


    public void setMonto(double monto) {
        this.monto = monto;
    }
    
}
