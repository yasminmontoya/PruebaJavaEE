/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import formulario.FormularioBean;
import formulario.Inventario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class Conexion {
    Connection con;
    Statement ps;
    ResultSet rs;
    String sql;
    ArrayList<Inventario> inventarios; 

    public Conexion() {
        this.inventarios = new ArrayList<Inventario>();
    }
    
    public ArrayList<Inventario> obtenerInventarioBodegas (String sql){
        inventarios.clear();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://107.180.2.89:3306/pruebatec","pruebatec", "Tecnica2@");
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            while(rs.next()){
                String codigoProducto = rs.getString("cod_pro");
                String producto = rs.getString("decr_prod");
                String instalacion = rs.getString("instalacion");
                String bodega = rs.getString("bodega");
                int inventario = rs.getInt("inventario");
                double costoUnitario = rs.getDouble("cost_unit");
                Inventario inventarioBodega = new Inventario(codigoProducto,producto,instalacion,bodega,inventario,costoUnitario);
                inventarios.add(inventarioBodega);
            } 
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormularioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormularioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventarios;
    }
    
    public int actualizar (String sql){
        int cantidad =0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://107.180.2.89:3306/pruebatec","pruebatec", "Tecnica2@");
            ps = con.createStatement();
            cantidad = ps.executeUpdate(sql);
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormularioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormularioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }
}
