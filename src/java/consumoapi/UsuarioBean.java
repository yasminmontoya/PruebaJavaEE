/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumoapi;

import consumoapi.Usuario;
import consumoapi.ConsumoAPI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Lorena
 */
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean {
    
    private ArrayList<Usuario> usuarios;
    ConsumoAPI ca;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        this.usuarios= new ArrayList<Usuario>();
        this.ca = new ConsumoAPI();
        try {
            this.usuarios = ca.getUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
     
}
