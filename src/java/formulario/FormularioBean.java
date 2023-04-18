/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario;

import conexionbd.Conexion;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author USUARIO
 */
@Named(value = "formularioBean")
@ApplicationScoped
public class FormularioBean {
    
    private String codigoProducto;
    private int cantidadCompra;
    private String instalacion;
    private double precioCompra;
    private double costoMedio;
    private Conexion conexion;
    ArrayList<Inventario> inventarios;

    /**
     * Creates a new instance of FormularioBean
     */
    public FormularioBean() {
        conexion= new Conexion();
        this.inventarios = new ArrayList<Inventario>();
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public String getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(String instalacion) {
        this.instalacion = instalacion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
        calcularCostoMedio();
    }

    public double getCostoMedio() {
        return costoMedio;
    }

    public void setCostoMedio(double costoMedio) {
        this.costoMedio = costoMedio;
    }
    
    public void calcularCostoMedio(){
        String sql = "select * from tabla1 where cod_pro = "+this.codigoProducto+" and instalacion = '"+this.instalacion+"'";
        this.inventarios = conexion.obtenerInventarioBodegas(sql);
        double inventarioValorado=0;
        double costoEntrada=this.cantidadCompra*this.precioCompra;
        double inventarioFinal = 0;
        double costoFinal = 0;
        for (int i = 0; i < inventarios.size(); i++) {
            Inventario objeto = inventarios.get(i);  
            inventarioValorado += objeto.getInventario()*objeto.getCostoUnitario();
            inventarioFinal += objeto.getInventario();
        }
        inventarioFinal+=this.cantidadCompra;
        costoFinal=inventarioValorado+costoEntrada;
        this.costoMedio = costoFinal/inventarioFinal;
    }
    
}
