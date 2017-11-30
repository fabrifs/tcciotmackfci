package testes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TesteArduinoSimple {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://192.168.43.168:666/");
		// URL url = new
		// URL("http://192.168.0.143:8081/VagasAutomatizadas/restServiceVagas/vagasStatus");
		URLConnection conn = url.openConnection();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String linha = buffer.readLine();
		System.out.println(linha);
		while ((linha = buffer.readLine()) != null) {
			//if (linha.contains("data")) {
				System.out.println(linha);
			//}
		}

	}

}
