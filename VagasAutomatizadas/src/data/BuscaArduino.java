package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.ejb.Schedule;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import persistence.VagasDao;

public class BuscaArduino {
	
	@Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
	public boolean buscaArduino() {
		try{
			URL url = new URL("http://192.168.43.168:666/");
			URLConnection conn = url.openConnection();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String linha = buffer.readLine();
			String json = "";
			System.out.println(linha);
			while ((linha = buffer.readLine()) != null) {
				if (linha.contains("data")) {
					System.out.println(linha);
					json = linha;
				}
			}
			
			
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
	        JSONArray lang = (JSONArray) jsonObject.get("data");
	        VagasDao vd = new VagasDao();
	        for(int i =0; i < lang.size(); i++){
	        	JSONObject jsonObjectDados = (JSONObject) lang.get(i);
	        	Long id = (Long)jsonObjectDados.get("id");
	        	Long disponibilidade = (Long)jsonObjectDados.get("disponibilidade");
	        	vd.persisteVagas(id, disponibilidade);
	        	vd.persisteVagasHistorico(id, disponibilidade);
	        }
	        System.out.println("Busquei arduino");
	        return true;
	       
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	
	
	

}
