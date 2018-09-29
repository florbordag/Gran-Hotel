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
    private int codigo;
    private String tipo;
    private boolean ocupado=false;
    private double precioxnoche;
    private int maxCapacidad;
    private int cantidadCamas;
    private String tipoCamas;
    private int cantidad=200;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public double getPrecioxnoche() {
        return precioxnoche;
    }

    public void setPrecioxnoche(double precioxnoche) {
        this.precioxnoche = precioxnoche;
    }

    public int getMaxCapacidad() {
        return maxCapacidad;
    }

    public void setMaxCapacidad(int maxCapacidad) {
        this.maxCapacidad = maxCapacidad;
    }

    public int getCantidadCamas() {
        return cantidadCamas;
    }

    public void setCantidadCamas(int cantidadCamas) {
        this.cantidadCamas = cantidadCamas;
    }

    public String getTipoCamas() {
        return tipoCamas;
    }

    public void setTipoCamas(String tipoCamas) {
        this.tipoCamas = tipoCamas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Habitacion(String tipo, double precioxnoche, int maxCapacidad, int cantidadCamas, String tipoCamas) {
        this.codigo = cantidad +1;
        this.tipo = tipo;
        this.ocupado = false;
        this.precioxnoche = precioxnoche;
        this.maxCapacidad = maxCapacidad;
        this.cantidadCamas = cantidadCamas;
        this.tipoCamas = tipoCamas;
        this.cantidad +=1;
        
    }
     public Habitacion(int codigo, String tipo, double precioxnoche, int maxCapacidad, int cantidadCamas, String tipoCamas) {
        this.codigo = cantidad +1;
        this.tipo = tipo;
        this.ocupado = false;
        this.precioxnoche = precioxnoche;
        this.maxCapacidad = maxCapacidad;
        this.cantidadCamas = cantidadCamas;
        this.tipoCamas = tipoCamas;
        this.cantidad +=1;
        
    }
    

    public Habitacion() {
        this.codigo = cantidad +1;
        this.cantidad +=1;
    }
   }