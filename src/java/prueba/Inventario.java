/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

/**
 *
 * @author USUARIO
 */
public class Inventario {
    
    private String codigoProducto;
    private String producto;
    private String instalacion;
    private String bodega;
    private int inventario;
    private double costoUnitario;

    public Inventario(String codigoProducto, String producto, String instalacion, String bodega, int inventario, double costoUnitario) {
        this.codigoProducto = codigoProducto;
        this.producto = producto;
        this.instalacion = instalacion;
        this.bodega = bodega;
        this.inventario = inventario;
        this.costoUnitario = costoUnitario;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(String instalacion) {
        this.instalacion = instalacion;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
    
    
}
