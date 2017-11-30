package testes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class TesteArduino {
	public static void main(String[] args) throws Exception {

		HttpClient client = new DefaultHttpClient();

		 String uri = "http://localhost:8081/VagasAutomatizadas/restServiceVagas/vagasStatus";

		// HttpHost proxy = new HttpHost("172.16.0.10", 3128, "http");
		// RequestConfig config =
		// RequestConfig.custom().setProxy(proxy).build();
		 HttpGet httpget = new HttpGet(uri);
		// httpget.setConfig(config);
		 HttpResponse response = client.execute(httpget);
		// System.out.println("----------------------------------------");
		// System.out.println(response.getStatusLine().getStatusCode());
		 Header[] h = (Header[]) response.getAllHeaders();

		for (Header head : h) {
			// System.out.println(head.getValue());
		}

		HttpEntity entity = response.getEntity();

		StringReader readerLine = null;
		if (entity != null) {
			InputStream instream = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					instream));
			StringBuilder out = new StringBuilder();
			String line;

			line = reader.readLine();
			readerLine = new StringReader(line);

			reader.close();
			instream.close();

			System.out.println(line);
		}


	}
}
