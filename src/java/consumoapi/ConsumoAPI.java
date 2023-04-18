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
    
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        try {
            URL url = new URL ("https://dummyapi.io/data/v1/user?limit=2&page=2");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("app-id", "6346ecdc44ffd6a5fcfa071c");
            conn.setRequestMethod("GET");
            conn.connect();
            
            int responseCode = conn.getResponseCode();
            if(responseCode != 200){
                throw new RuntimeException("Ocurrio un error: " + responseCode);                
            }else{
                //String json = "{\"data\":[{\"id\":\"60d0fe4f5311236168a109d4\",\"title\":\"mr\",\"firstName\":\"Valentin\",\"lastName\":\"Ortega\",\"picture\":\"https://randomuser.me/api/portraits/med/men/3.jpg\"},{\"id\":\"60d0fe4f5311236168a109d5\",\"title\":\"mrs\",\"firstName\":\"Sibylle\",\"lastName\":\"Leibold\",\"picture\":\"https://randomuser.me/api/portraits/med/women/89.jpg\"},{\"id\":\"60d0fe4f5311236168a109d6\",\"title\":\"mrs\",\"firstName\":\"Elisa\",\"lastName\":\"Lorenzo\",\"picture\":\"https://randomuser.me/api/portraits/med/women/89.jpg\"},{\"id\":\"60d0fe4f5311236168a109d7\",\"title\":\"mr\",\"firstName\":\"Leevi\",\"lastName\":\"Savela\",\"picture\":\"https://randomuser.me/api/portraits/med/men/67.jpg\"},{\"id\":\"60d0fe4f5311236168a109d8\",\"title\":\"mrs\",\"firstName\":\"Karoline\",\"lastName\":\"Sviggum\",\"picture\":\"https://randomuser.me/api/portraits/med/women/61.jpg\"}],\"total\":99,\"page\":2,\"limit\":5}";
                //JSONObject jsonResponse = new JSONObject(json);
                StringBuilder informacionString = new StringBuilder();                
                JSONObject jsonResponse = new JSONObject(informacionString.toString());
                JSONArray jsonArray = jsonResponse.getJSONArray("data");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                
                Usuario u = new Usuario(
                        jsonObject.getString("id"),
                        jsonObject.getString("title"),
                        jsonObject.getString("firstName"),
                        jsonObject.getString("lastName"),
                        jsonObject.getString("picture"));
                usuarios.add(u);
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ConsumoAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return usuarios;    
    }
    
    public static ArrayList<Usuario> peticionHttpGet() throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        // Esto es lo que vamos a devolver
        StringBuilder resultado = new StringBuilder();
        // Crear un objeto de tipo URL
        URL url = new URL("https://dummyapi.io/data/v1/user?limit=2&page=2");

        // Abrir la conexión e indicar que será de tipo GET
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.addRequestProperty("app-id", "6346ecdc44ffd6a5fcfa071c");
        conexion.setRequestMethod("GET");
        // Búferes para leer
        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
        while ((linea = rd.readLine()) != null) {
          resultado.append(linea);
        }
        // Cerrar el BufferedReader
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
