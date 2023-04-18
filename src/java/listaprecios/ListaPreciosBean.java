/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaprecios;

import conexionbd.Conexion;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author USUARIO
 */
@Named(value = "listaPreciosBean")
@ApplicationScoped
public class ListaPreciosBean {
    
    private String nombre;
    private double porcentaje;
    private Conexion conexion;
    private int cantidad;

    /**
     * Creates a new instance of ListaPreciosBean
     */
    public ListaPreciosBean() {
        conexion= new Conexion();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = (porcentaje/100)+1;;
        actualizar();       
    } 

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public void actualizar(){
        String sql = "update lista_precios set precio=precio*"+this.porcentaje+" where lista_precios = '"+this.nombre+"'";
        this.cantidad = conexion.actualizar(sql);

    }
}
