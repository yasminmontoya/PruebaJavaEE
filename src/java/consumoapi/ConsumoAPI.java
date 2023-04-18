/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumoapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Lorena
 */
public class ConsumoAPI {    
    
    public static ArrayList<Usuario> getUsuarios() throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        StringBuilder resultado = new StringBuilder();
        URL url = new URL("https://dummyapi.io/data/v1/user?limit=2&page=2");
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.addRequestProperty("app-id", "6346ecdc44ffd6a5fcfa071c");
        conexion.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
 
        while ((linea = rd.readLine()) != null) {
          resultado.append(linea);
        }
        
        rd.close();
                     
        JSONObject jsonResponse = new JSONObject(resultado.toString());
        JSONArray jsonArray = jsonResponse.getJSONArray("data");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);;
            Usuario u = new Usuario(
            jsonObject.getString("id"),
            jsonObject.getString("title"),
            jsonObject.getString("firstName"),
            jsonObject.getString("lastName"),
            jsonObject.getString("picture"));
            usuarios.add(u);
        }
        
        return usuarios;
    }
    
}
