package testes;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import persistence.VagasDao;

public class TesteJsonParser {
	
	public static void main(String[] args) throws Exception {
		
		//String json = "{\"data\": [\"vaga\" {\"id\" : 1,\"disponibilidade\" : 1},\"vaga\" {\"id\" : 2,\"disponibilidade\" : 1}]}";
		
		String json = "{\"data\": [{\"id\" : 1,\"disponibilidade\" : 0}, {\"id\" : 2,\"disponibilidade\" : 0}]}";
		/*InputStream instream = json;
        BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
        StringBuilder out = new StringBuilder();
        String line;
        
        line = reader.readLine();
        readerLine = new StringReader(line);
        
        reader.close();
        instream.close();*/
		System.out.println(json);
		
		JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
        JSONArray lang = (JSONArray) jsonObject.get("data");
        
        VagasDao vd = new VagasDao();
        for(int i =0; i < lang.size(); i++){
        	JSONObject jsonObjectDados = (JSONObject) lang.get(i);
        	Long id = (Long)jsonObjectDados.get("id");
        	Long disponibilidade = (Long)jsonObjectDados.get("disponibilidade");
        	vd.persisteVagas(id, disponibilidade);
        }

       
	}

}
